package de.zalando.refugees.service;

import de.zalando.refugees.domain.Category;
import de.zalando.refugees.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Category.
 */
@Service
@Transactional
public class CategoryService {

    private final Logger log = LoggerFactory.getLogger(CategoryService.class);
    
    @Inject
    private CategoryRepository categoryRepository;
    
    /**
     * Save a category.
     * @return the persisted entity
     */
    public Category save(Category category) {
        log.debug("Request to save Category : {}", category);
        Category result = categoryRepository.save(category);
        return result;
    }

    /**
     *  get all the categorys.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Category> findAll() {
        log.debug("Request to get all Categorys");
        List<Category> result = categoryRepository.findAll();
        return result;
    }

    /**
     *  get one category by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Category findOne(Long id) {
        log.debug("Request to get Category : {}", id);
        Category category = categoryRepository.findOne(id);
        return category;
    }

    /**
     *  delete the  category by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete Category : {}", id);
        categoryRepository.delete(id);
    }
}
