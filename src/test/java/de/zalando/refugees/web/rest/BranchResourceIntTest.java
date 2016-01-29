package de.zalando.refugees.web.rest;

import de.zalando.refugees.Application;
import de.zalando.refugees.domain.Branch;
import de.zalando.refugees.repository.BranchRepository;
import de.zalando.refugees.service.BranchService;
import de.zalando.refugees.web.rest.dto.BranchDTO;
import de.zalando.refugees.web.rest.mapper.BranchMapper;

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
 * Test class for the BranchResource REST controller.
 *
 * @see BranchResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class BranchResourceIntTest {

    private static final String DEFAULT_ADDRESS = "AAAAA";
    private static final String UPDATED_ADDRESS = "BBBBB";
    private static final String DEFAULT_PHONE = "AAAAA";
    private static final String UPDATED_PHONE = "BBBBB";
    private static final String DEFAULT_EMAIL = "AAAAA";
    private static final String UPDATED_EMAIL = "BBBBB";

    @Inject
    private BranchRepository branchRepository;

    @Inject
    private BranchMapper branchMapper;

    @Inject
    private BranchService branchService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restBranchMockMvc;

    private Branch branch;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BranchResource branchResource = new BranchResource();
        ReflectionTestUtils.setField(branchResource, "branchService", branchService);
        ReflectionTestUtils.setField(branchResource, "branchMapper", branchMapper);
        this.restBranchMockMvc = MockMvcBuilders.standaloneSetup(branchResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        branch = new Branch();
        branch.setAddress(DEFAULT_ADDRESS);
        branch.setPhone(DEFAULT_PHONE);
        branch.setEmail(DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    public void createBranch() throws Exception {
        int databaseSizeBeforeCreate = branchRepository.findAll().size();

        // Create the Branch
        BranchDTO branchDTO = branchMapper.branchToBranchDTO(branch);

        restBranchMockMvc.perform(post("/api/branchs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(branchDTO)))
                .andExpect(status().isCreated());

        // Validate the Branch in the database
        List<Branch> branchs = branchRepository.findAll();
        assertThat(branchs).hasSize(databaseSizeBeforeCreate + 1);
        Branch testBranch = branchs.get(branchs.size() - 1);
        assertThat(testBranch.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testBranch.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testBranch.getEmail()).isEqualTo(DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    public void getAllBranchs() throws Exception {
        // Initialize the database
        branchRepository.saveAndFlush(branch);

        // Get all the branchs
        restBranchMockMvc.perform(get("/api/branchs?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(branch.getId().intValue())))
                .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
                .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
                .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())));
    }

    @Test
    @Transactional
    public void getBranch() throws Exception {
        // Initialize the database
        branchRepository.saveAndFlush(branch);

        // Get the branch
        restBranchMockMvc.perform(get("/api/branchs/{id}", branch.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(branch.getId().intValue()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBranch() throws Exception {
        // Get the branch
        restBranchMockMvc.perform(get("/api/branchs/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBranch() throws Exception {
        // Initialize the database
        branchRepository.saveAndFlush(branch);

		int databaseSizeBeforeUpdate = branchRepository.findAll().size();

        // Update the branch
        branch.setAddress(UPDATED_ADDRESS);
        branch.setPhone(UPDATED_PHONE);
        branch.setEmail(UPDATED_EMAIL);
        BranchDTO branchDTO = branchMapper.branchToBranchDTO(branch);

        restBranchMockMvc.perform(put("/api/branchs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(branchDTO)))
                .andExpect(status().isOk());

        // Validate the Branch in the database
        List<Branch> branchs = branchRepository.findAll();
        assertThat(branchs).hasSize(databaseSizeBeforeUpdate);
        Branch testBranch = branchs.get(branchs.size() - 1);
        assertThat(testBranch.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testBranch.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testBranch.getEmail()).isEqualTo(UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void deleteBranch() throws Exception {
        // Initialize the database
        branchRepository.saveAndFlush(branch);

		int databaseSizeBeforeDelete = branchRepository.findAll().size();

        // Get the branch
        restBranchMockMvc.perform(delete("/api/branchs/{id}", branch.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Branch> branchs = branchRepository.findAll();
        assertThat(branchs).hasSize(databaseSizeBeforeDelete - 1);
    }
}
