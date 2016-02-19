package de.zalando.refugees.web.rest;

import de.zalando.refugees.Application;
import de.zalando.refugees.domain.Demand;
import de.zalando.refugees.repository.DemandRepository;
import de.zalando.refugees.service.DemandService;
import de.zalando.refugees.web.rest.dto.DemandDTO;
import de.zalando.refugees.web.rest.mapper.DemandMapper;

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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the DemandResource REST controller.
 *
 * @see DemandResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class DemandResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));


    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final ZonedDateTime DEFAULT_CREATED = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CREATED = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATED_STR = dateTimeFormatter.format(DEFAULT_CREATED);

    @Inject
    private DemandRepository demandRepository;

    @Inject
    private DemandMapper demandMapper;

    @Inject
    private DemandService demandService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restDemandMockMvc;

    private Demand demand;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DemandResource demandResource = new DemandResource();
        ReflectionTestUtils.setField(demandResource, "demandService", demandService);
        ReflectionTestUtils.setField(demandResource, "demandMapper", demandMapper);
        this.restDemandMockMvc = MockMvcBuilders.standaloneSetup(demandResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        demand = new Demand();
        demand.setQuantity(DEFAULT_QUANTITY);
        demand.setCreated(DEFAULT_CREATED);
    }

    @Test
    @Transactional
    public void createDemand() throws Exception {
        int databaseSizeBeforeCreate = demandRepository.findAll().size();

        // Create the Demand
        DemandDTO demandDTO = demandMapper.demandToDemandDTO(demand);

        restDemandMockMvc.perform(post("/api/demands")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(demandDTO)))
                .andExpect(status().isCreated());

        // Validate the Demand in the database
        List<Demand> demands = demandRepository.findAll();
        assertThat(demands).hasSize(databaseSizeBeforeCreate + 1);
        Demand testDemand = demands.get(demands.size() - 1);
        assertThat(testDemand.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testDemand.getCreated()).isEqualTo(DEFAULT_CREATED);
    }

    @Test
    @Transactional
    public void getAllDemands() throws Exception {
        // Initialize the database
        demandRepository.saveAndFlush(demand);

        // Get all the demands
        restDemandMockMvc.perform(get("/api/demands?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(demand.getId().intValue())))
                .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
                .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED_STR)));
    }

    @Test
    @Transactional
    public void getDemand() throws Exception {
        // Initialize the database
        demandRepository.saveAndFlush(demand);

        // Get the demand
        restDemandMockMvc.perform(get("/api/demands/{id}", demand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(demand.getId().intValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED_STR));
    }

    @Test
    @Transactional
    public void getNonExistingDemand() throws Exception {
        // Get the demand
        restDemandMockMvc.perform(get("/api/demands/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDemand() throws Exception {
        // Initialize the database
        demandRepository.saveAndFlush(demand);

		int databaseSizeBeforeUpdate = demandRepository.findAll().size();

        // Update the demand
        demand.setQuantity(UPDATED_QUANTITY);
        demand.setCreated(UPDATED_CREATED);
        DemandDTO demandDTO = demandMapper.demandToDemandDTO(demand);

        restDemandMockMvc.perform(put("/api/demands")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(demandDTO)))
                .andExpect(status().isOk());

        // Validate the Demand in the database
        List<Demand> demands = demandRepository.findAll();
        assertThat(demands).hasSize(databaseSizeBeforeUpdate);
        Demand testDemand = demands.get(demands.size() - 1);
        assertThat(testDemand.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testDemand.getCreated()).isEqualTo(UPDATED_CREATED);
    }

    @Test
    @Transactional
    public void deleteDemand() throws Exception {
        // Initialize the database
        demandRepository.saveAndFlush(demand);

		int databaseSizeBeforeDelete = demandRepository.findAll().size();

        // Get the demand
        restDemandMockMvc.perform(delete("/api/demands/{id}", demand.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Demand> demands = demandRepository.findAll();
        assertThat(demands).hasSize(databaseSizeBeforeDelete - 1);
    }
}
