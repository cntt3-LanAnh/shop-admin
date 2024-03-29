package com.justcoffeeshop.services;

import com.justcoffeeshop.models.ShipperModel;

import java.util.List;
import java.util.Optional;

public interface ShipperService {

    List<ShipperModel> findAll();

    List<ShipperModel> search(String term);

    Optional<ShipperModel> findById(Long id);

    boolean update(ShipperModel shipper);

    ShipperModel save(ShipperModel shipper);

    boolean delete(Long id);
}
