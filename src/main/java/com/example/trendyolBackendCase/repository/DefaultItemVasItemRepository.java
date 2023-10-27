package com.example.trendyolBackendCase.repository;

import com.example.trendyolBackendCase.entity.DefaultItemVasItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DefaultItemVasItemRepository extends JpaRepository<DefaultItemVasItem, Integer> {

    Optional<List<DefaultItemVasItem>> findAllByDefaultItemId(Integer defaultItemId);

}
