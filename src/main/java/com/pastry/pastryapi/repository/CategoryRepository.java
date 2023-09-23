package com.pastry.pastryapi.repository;

import com.pastry.pastryapi.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CategoryRepository extends MongoRepository<Category, String> {
  Category findByCategoryName(String categoryName);

}
