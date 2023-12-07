package com.luchonicolosi.willitaapi.repository;

import com.luchonicolosi.willitaapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String categoryName);

}
