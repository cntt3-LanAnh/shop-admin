package com.justcoffeeshop.services;

import com.justcoffeeshop.models.CategoryModel;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryModel> findAll();

    List<CategoryModel> search(String term);

    Optional<CategoryModel> findById(Long id);

    boolean update(CategoryModel category);

    CategoryModel save(CategoryModel category);

    boolean delete(Long id);
}
