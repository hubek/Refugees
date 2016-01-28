package de.zalando.refugees.web.rest;

import de.zalando.refugees.Application;
import de.zalando.refugees.domain.OrganizationType;
import de.zalando.refugees.repository.OrganizationTypeRepository;

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
 * Test class for the OrganizationTypeResource REST controller.
 *
 * @see OrganizationTypeResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class OrganizationTypeResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";

    @Inject
    private OrganizationTypeRepository organizationTypeRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restOrganizationTypeMockMvc;

    private OrganizationType organizationType;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OrganizationTypeResource organizationTypeResource = new OrganizationTypeResource();
        ReflectionTestUtils.setField(organizationTypeResource, "organizationTypeRepository", organizationTypeRepository);
        this.restOrganizationTypeMockMvc = MockMvcBuilders.standaloneSetup(organizationTypeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        organizationType = new OrganizationType();
        organizationType.setName(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createOrganizationType() throws Exception {
        int databaseSizeBeforeCreate = organizationTypeRepository.findAll().size();

        // Create the OrganizationType

        restOrganizationTypeMockMvc.perform(post("/api/organizationTypes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(organizationType)))
                .andExpect(status().isCreated());

        // Validate the OrganizationType in the database
        List<OrganizationType> organizationTypes = organizationTypeRepository.findAll();
        assertThat(organizationTypes).hasSize(databaseSizeBeforeCreate + 1);
        OrganizationType testOrganizationType = organizationTypes.get(organizationTypes.size() - 1);
        assertThat(testOrganizationType.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void getAllOrganizationTypes() throws Exception {
        // Initialize the database
        organizationTypeRepository.saveAndFlush(organizationType);

        // Get all the organizationTypes
        restOrganizationTypeMockMvc.perform(get("/api/organizationTypes?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(organizationType.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    @Transactional
    public void getOrganizationType() throws Exception {
        // Initialize the database
        organizationTypeRepository.saveAndFlush(organizationType);

        // Get the organizationType
        restOrganizationTypeMockMvc.perform(get("/api/organizationTypes/{id}", organizationType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(organizationType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOrganizationType() throws Exception {
        // Get the organizationType
        restOrganizationTypeMockMvc.perform(get("/api/organizationTypes/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrganizationType() throws Exception {
        // Initialize the database
        organizationTypeRepository.saveAndFlush(organizationType);

		int databaseSizeBeforeUpdate = organizationTypeRepository.findAll().size();

        // Update the organizationType
        organizationType.setName(UPDATED_NAME);

        restOrganizationTypeMockMvc.perform(put("/api/organizationTypes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(organizationType)))
                .andExpect(status().isOk());

        // Validate the OrganizationType in the database
        List<OrganizationType> organizationTypes = organizationTypeRepository.findAll();
        assertThat(organizationTypes).hasSize(databaseSizeBeforeUpdate);
        OrganizationType testOrganizationType = organizationTypes.get(organizationTypes.size() - 1);
        assertThat(testOrganizationType.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void deleteOrganizationType() throws Exception {
        // Initialize the database
        organizationTypeRepository.saveAndFlush(organizationType);

		int databaseSizeBeforeDelete = organizationTypeRepository.findAll().size();

        // Get the organizationType
        restOrganizationTypeMockMvc.perform(delete("/api/organizationTypes/{id}", organizationType.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<OrganizationType> organizationTypes = organizationTypeRepository.findAll();
        assertThat(organizationTypes).hasSize(databaseSizeBeforeDelete - 1);
    }
}
