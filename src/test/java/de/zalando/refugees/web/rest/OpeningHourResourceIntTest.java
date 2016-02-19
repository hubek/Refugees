package de.zalando.refugees.web.rest;

import de.zalando.refugees.Application;
import de.zalando.refugees.domain.OpeningHour;
import de.zalando.refugees.repository.OpeningHourRepository;

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
 * Test class for the OpeningHourResource REST controller.
 *
 * @see OpeningHourResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class OpeningHourResourceIntTest {

    private static final String DEFAULT_DAY = "AAAAA";
    private static final String UPDATED_DAY = "BBBBB";

    private static final Integer DEFAULT_OPEN = 1;
    private static final Integer UPDATED_OPEN = 2;

    private static final Integer DEFAULT_CLOSE = 1;
    private static final Integer UPDATED_CLOSE = 2;

    @Inject
    private OpeningHourRepository openingHourRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restOpeningHourMockMvc;

    private OpeningHour openingHour;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OpeningHourResource openingHourResource = new OpeningHourResource();
        ReflectionTestUtils.setField(openingHourResource, "openingHourRepository", openingHourRepository);
        this.restOpeningHourMockMvc = MockMvcBuilders.standaloneSetup(openingHourResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        openingHour = new OpeningHour();
        openingHour.setDay(DEFAULT_DAY);
        openingHour.setOpen(DEFAULT_OPEN);
        openingHour.setClose(DEFAULT_CLOSE);
    }

    @Test
    @Transactional
    public void createOpeningHour() throws Exception {
        int databaseSizeBeforeCreate = openingHourRepository.findAll().size();

        // Create the OpeningHour

        restOpeningHourMockMvc.perform(post("/api/openingHours")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(openingHour)))
                .andExpect(status().isCreated());

        // Validate the OpeningHour in the database
        List<OpeningHour> openingHours = openingHourRepository.findAll();
        assertThat(openingHours).hasSize(databaseSizeBeforeCreate + 1);
        OpeningHour testOpeningHour = openingHours.get(openingHours.size() - 1);
        assertThat(testOpeningHour.getDay()).isEqualTo(DEFAULT_DAY);
        assertThat(testOpeningHour.getOpen()).isEqualTo(DEFAULT_OPEN);
        assertThat(testOpeningHour.getClose()).isEqualTo(DEFAULT_CLOSE);
    }

    @Test
    @Transactional
    public void getAllOpeningHours() throws Exception {
        // Initialize the database
        openingHourRepository.saveAndFlush(openingHour);

        // Get all the openingHours
        restOpeningHourMockMvc.perform(get("/api/openingHours?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(openingHour.getId().intValue())))
                .andExpect(jsonPath("$.[*].day").value(hasItem(DEFAULT_DAY.toString())))
                .andExpect(jsonPath("$.[*].open").value(hasItem(DEFAULT_OPEN)))
                .andExpect(jsonPath("$.[*].close").value(hasItem(DEFAULT_CLOSE)));
    }

    @Test
    @Transactional
    public void getOpeningHour() throws Exception {
        // Initialize the database
        openingHourRepository.saveAndFlush(openingHour);

        // Get the openingHour
        restOpeningHourMockMvc.perform(get("/api/openingHours/{id}", openingHour.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(openingHour.getId().intValue()))
            .andExpect(jsonPath("$.day").value(DEFAULT_DAY.toString()))
            .andExpect(jsonPath("$.open").value(DEFAULT_OPEN))
            .andExpect(jsonPath("$.close").value(DEFAULT_CLOSE));
    }

    @Test
    @Transactional
    public void getNonExistingOpeningHour() throws Exception {
        // Get the openingHour
        restOpeningHourMockMvc.perform(get("/api/openingHours/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOpeningHour() throws Exception {
        // Initialize the database
        openingHourRepository.saveAndFlush(openingHour);

		int databaseSizeBeforeUpdate = openingHourRepository.findAll().size();

        // Update the openingHour
        openingHour.setDay(UPDATED_DAY);
        openingHour.setOpen(UPDATED_OPEN);
        openingHour.setClose(UPDATED_CLOSE);

        restOpeningHourMockMvc.perform(put("/api/openingHours")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(openingHour)))
                .andExpect(status().isOk());

        // Validate the OpeningHour in the database
        List<OpeningHour> openingHours = openingHourRepository.findAll();
        assertThat(openingHours).hasSize(databaseSizeBeforeUpdate);
        OpeningHour testOpeningHour = openingHours.get(openingHours.size() - 1);
        assertThat(testOpeningHour.getDay()).isEqualTo(UPDATED_DAY);
        assertThat(testOpeningHour.getOpen()).isEqualTo(UPDATED_OPEN);
        assertThat(testOpeningHour.getClose()).isEqualTo(UPDATED_CLOSE);
    }

    @Test
    @Transactional
    public void deleteOpeningHour() throws Exception {
        // Initialize the database
        openingHourRepository.saveAndFlush(openingHour);

		int databaseSizeBeforeDelete = openingHourRepository.findAll().size();

        // Get the openingHour
        restOpeningHourMockMvc.perform(delete("/api/openingHours/{id}", openingHour.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<OpeningHour> openingHours = openingHourRepository.findAll();
        assertThat(openingHours).hasSize(databaseSizeBeforeDelete - 1);
    }
}
