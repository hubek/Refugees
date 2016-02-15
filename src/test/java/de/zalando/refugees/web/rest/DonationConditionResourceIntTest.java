package de.zalando.refugees.web.rest;

import de.zalando.refugees.Application;
import de.zalando.refugees.domain.DonationCondition;
import de.zalando.refugees.repository.DonationConditionRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the DonationConditionResource REST controller.
 *
 * @see DonationConditionResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class DonationConditionResourceIntTest {

    private static final String DEFAULT_VALUE = "AAAAA";
    private static final String UPDATED_VALUE = "BBBBB";

    @Inject
    private DonationConditionRepository donationConditionRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restDonationConditionMockMvc;

    private DonationCondition donationCondition;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DonationConditionResource donationConditionResource = new DonationConditionResource();
        ReflectionTestUtils.setField(donationConditionResource, "donationConditionRepository", donationConditionRepository);
        this.restDonationConditionMockMvc = MockMvcBuilders.standaloneSetup(donationConditionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        donationCondition = new DonationCondition();
        donationCondition.setValue(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createDonationCondition() throws Exception {
        int databaseSizeBeforeCreate = donationConditionRepository.findAll().size();

        // Create the DonationCondition

        restDonationConditionMockMvc.perform(post("/api/donationConditions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(donationCondition)))
                .andExpect(status().isCreated());

        // Validate the DonationCondition in the database
        List<DonationCondition> donationConditions = donationConditionRepository.findAll();
        assertThat(donationConditions).hasSize(databaseSizeBeforeCreate + 1);
        DonationCondition testDonationCondition = donationConditions.get(donationConditions.size() - 1);
        assertThat(testDonationCondition.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void getAllDonationConditions() throws Exception {
        // Initialize the database
        donationConditionRepository.saveAndFlush(donationCondition);

        // Get all the donationConditions
        restDonationConditionMockMvc.perform(get("/api/donationConditions?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(donationCondition.getId().intValue())))
                .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.toString())));
    }

    @Test
    @Transactional
    public void getDonationCondition() throws Exception {
        // Initialize the database
        donationConditionRepository.saveAndFlush(donationCondition);

        // Get the donationCondition
        restDonationConditionMockMvc.perform(get("/api/donationConditions/{id}", donationCondition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(donationCondition.getId().intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDonationCondition() throws Exception {
        // Get the donationCondition
        restDonationConditionMockMvc.perform(get("/api/donationConditions/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDonationCondition() throws Exception {
        // Initialize the database
        donationConditionRepository.saveAndFlush(donationCondition);

		int databaseSizeBeforeUpdate = donationConditionRepository.findAll().size();

        // Update the donationCondition
        donationCondition.setValue(UPDATED_VALUE);

        restDonationConditionMockMvc.perform(put("/api/donationConditions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(donationCondition)))
                .andExpect(status().isOk());

        // Validate the DonationCondition in the database
        List<DonationCondition> donationConditions = donationConditionRepository.findAll();
        assertThat(donationConditions).hasSize(databaseSizeBeforeUpdate);
        DonationCondition testDonationCondition = donationConditions.get(donationConditions.size() - 1);
        assertThat(testDonationCondition.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void deleteDonationCondition() throws Exception {
        // Initialize the database
        donationConditionRepository.saveAndFlush(donationCondition);

		int databaseSizeBeforeDelete = donationConditionRepository.findAll().size();

        // Get the donationCondition
        restDonationConditionMockMvc.perform(delete("/api/donationConditions/{id}", donationCondition.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<DonationCondition> donationConditions = donationConditionRepository.findAll();
        assertThat(donationConditions).hasSize(databaseSizeBeforeDelete - 1);
    }
}
