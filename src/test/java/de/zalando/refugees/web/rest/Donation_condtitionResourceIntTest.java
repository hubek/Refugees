package de.zalando.refugees.web.rest;

import de.zalando.refugees.Application;
import de.zalando.refugees.domain.Donation_condtition;
import de.zalando.refugees.repository.Donation_condtitionRepository;

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
 * Test class for the Donation_condtitionResource REST controller.
 *
 * @see Donation_condtitionResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class Donation_condtitionResourceIntTest {

    private static final String DEFAULT_VALUE = "AAAAA";
    private static final String UPDATED_VALUE = "BBBBB";

    @Inject
    private Donation_condtitionRepository donation_condtitionRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restDonation_condtitionMockMvc;

    private Donation_condtition donation_condtition;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Donation_condtitionResource donation_condtitionResource = new Donation_condtitionResource();
        ReflectionTestUtils.setField(donation_condtitionResource, "donation_condtitionRepository", donation_condtitionRepository);
        this.restDonation_condtitionMockMvc = MockMvcBuilders.standaloneSetup(donation_condtitionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        donation_condtition = new Donation_condtition();
        donation_condtition.setValue(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createDonation_condtition() throws Exception {
        int databaseSizeBeforeCreate = donation_condtitionRepository.findAll().size();

        // Create the Donation_condtition

        restDonation_condtitionMockMvc.perform(post("/api/donation_condtitions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(donation_condtition)))
                .andExpect(status().isCreated());

        // Validate the Donation_condtition in the database
        List<Donation_condtition> donation_condtitions = donation_condtitionRepository.findAll();
        assertThat(donation_condtitions).hasSize(databaseSizeBeforeCreate + 1);
        Donation_condtition testDonation_condtition = donation_condtitions.get(donation_condtitions.size() - 1);
        assertThat(testDonation_condtition.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void getAllDonation_condtitions() throws Exception {
        // Initialize the database
        donation_condtitionRepository.saveAndFlush(donation_condtition);

        // Get all the donation_condtitions
        restDonation_condtitionMockMvc.perform(get("/api/donation_condtitions?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(donation_condtition.getId().intValue())))
                .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.toString())));
    }

    @Test
    @Transactional
    public void getDonation_condtition() throws Exception {
        // Initialize the database
        donation_condtitionRepository.saveAndFlush(donation_condtition);

        // Get the donation_condtition
        restDonation_condtitionMockMvc.perform(get("/api/donation_condtitions/{id}", donation_condtition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(donation_condtition.getId().intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDonation_condtition() throws Exception {
        // Get the donation_condtition
        restDonation_condtitionMockMvc.perform(get("/api/donation_condtitions/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDonation_condtition() throws Exception {
        // Initialize the database
        donation_condtitionRepository.saveAndFlush(donation_condtition);

		int databaseSizeBeforeUpdate = donation_condtitionRepository.findAll().size();

        // Update the donation_condtition
        donation_condtition.setValue(UPDATED_VALUE);

        restDonation_condtitionMockMvc.perform(put("/api/donation_condtitions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(donation_condtition)))
                .andExpect(status().isOk());

        // Validate the Donation_condtition in the database
        List<Donation_condtition> donation_condtitions = donation_condtitionRepository.findAll();
        assertThat(donation_condtitions).hasSize(databaseSizeBeforeUpdate);
        Donation_condtition testDonation_condtition = donation_condtitions.get(donation_condtitions.size() - 1);
        assertThat(testDonation_condtition.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void deleteDonation_condtition() throws Exception {
        // Initialize the database
        donation_condtitionRepository.saveAndFlush(donation_condtition);

		int databaseSizeBeforeDelete = donation_condtitionRepository.findAll().size();

        // Get the donation_condtition
        restDonation_condtitionMockMvc.perform(delete("/api/donation_condtitions/{id}", donation_condtition.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Donation_condtition> donation_condtitions = donation_condtitionRepository.findAll();
        assertThat(donation_condtitions).hasSize(databaseSizeBeforeDelete - 1);
    }
}
