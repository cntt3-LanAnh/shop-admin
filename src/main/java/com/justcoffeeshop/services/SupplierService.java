package com.justcoffeeshop.services;

import com.justcoffeeshop.models.SupplierModel;

import java.util.List;
import java.util.Optional;

public interface SupplierService {

    List<SupplierModel> findAll();

    List<SupplierModel> search(String term);

    Optional<SupplierModel> findById(Long id);

    boolean update(SupplierModel supplier);

    SupplierModel save(SupplierModel supplier);

    boolean delete(Long id);
}
