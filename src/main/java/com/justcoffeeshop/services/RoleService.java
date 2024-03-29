package com.justcoffeeshop.services;

import com.justcoffeeshop.models.RoleModel;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<RoleModel> findAll();

    List<RoleModel> search(String term);

    Optional<RoleModel> findById(Long id);

    boolean update(RoleModel roleModel);

    RoleModel save(RoleModel roleModel);

    boolean delete(Long id);
}
