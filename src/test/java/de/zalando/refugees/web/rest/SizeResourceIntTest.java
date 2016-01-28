package de.zalando.refugees.web.rest;

import de.zalando.refugees.Application;
import de.zalando.refugees.domain.Size;
import de.zalando.refugees.repository.SizeRepository;

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
 * Test class for the SizeResource REST controller.
 *
 * @see SizeResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class SizeResourceIntTest {

    private static final String DEFAULT_VALUE = "AAAAA";
    private static final String UPDATED_VALUE = "BBBBB";

    @Inject
    private SizeRepository sizeRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restSizeMockMvc;

    private Size size;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SizeResource sizeResource = new SizeResource();
        ReflectionTestUtils.setField(sizeResource, "sizeRepository", sizeRepository);
        this.restSizeMockMvc = MockMvcBuilders.standaloneSetup(sizeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        size = new Size();
        size.setValue(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createSize() throws Exception {
        int databaseSizeBeforeCreate = sizeRepository.findAll().size();

        // Create the Size

        restSizeMockMvc.perform(post("/api/sizes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(size)))
                .andExpect(status().isCreated());

        // Validate the Size in the database
        List<Size> sizes = sizeRepository.findAll();
        assertThat(sizes).hasSize(databaseSizeBeforeCreate + 1);
        Size testSize = sizes.get(sizes.size() - 1);
        assertThat(testSize.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void getAllSizes() throws Exception {
        // Initialize the database
        sizeRepository.saveAndFlush(size);

        // Get all the sizes
        restSizeMockMvc.perform(get("/api/sizes?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(size.getId().intValue())))
                .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.toString())));
    }

    @Test
    @Transactional
    public void getSize() throws Exception {
        // Initialize the database
        sizeRepository.saveAndFlush(size);

        // Get the size
        restSizeMockMvc.perform(get("/api/sizes/{id}", size.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(size.getId().intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSize() throws Exception {
        // Get the size
        restSizeMockMvc.perform(get("/api/sizes/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSize() throws Exception {
        // Initialize the database
        sizeRepository.saveAndFlush(size);

		int databaseSizeBeforeUpdate = sizeRepository.findAll().size();

        // Update the size
        size.setValue(UPDATED_VALUE);

        restSizeMockMvc.perform(put("/api/sizes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(size)))
                .andExpect(status().isOk());

        // Validate the Size in the database
        List<Size> sizes = sizeRepository.findAll();
        assertThat(sizes).hasSize(databaseSizeBeforeUpdate);
        Size testSize = sizes.get(sizes.size() - 1);
        assertThat(testSize.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void deleteSize() throws Exception {
        // Initialize the database
        sizeRepository.saveAndFlush(size);

		int databaseSizeBeforeDelete = sizeRepository.findAll().size();

        // Get the size
        restSizeMockMvc.perform(delete("/api/sizes/{id}", size.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Size> sizes = sizeRepository.findAll();
        assertThat(sizes).hasSize(databaseSizeBeforeDelete - 1);
    }
}
