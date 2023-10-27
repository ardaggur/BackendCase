package com.example.trendyolBackendCase.controller;

import com.example.trendyolBackendCase.dto.DefaultItemVasItemDTO;
import com.example.trendyolBackendCase.entity.Item;
import com.example.trendyolBackendCase.entity.VasItem;
import com.example.trendyolBackendCase.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping()
    public ResponseEntity<Boolean> addVasItemToItem(@RequestBody DefaultItemVasItemDTO defaultItemVasItemDTO)
    {
        return new ResponseEntity<>(itemService.addVasItemToItem(defaultItemVasItemDTO), HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Boolean> addItem(@RequestBody Item item)
    {
        return new ResponseEntity<>(itemService.addItemToCart(item), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> removeItem(@PathVariable("id") int itemId) {
        return new ResponseEntity<>(itemService.removeItemFromCart(itemId), HttpStatus.NO_CONTENT);
    }


}