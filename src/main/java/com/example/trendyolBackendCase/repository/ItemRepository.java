package com.example.trendyolBackendCase.repository;

import com.example.trendyolBackendCase.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {



}
