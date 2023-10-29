package com.example.trendyolBackendCase.controller;

import com.example.trendyolBackendCase.dto.DefaultItemVasItemDTO;
import com.example.trendyolBackendCase.dto.GenericResponse;
import com.example.trendyolBackendCase.entity.Item;
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

    @PostMapping("/addVasItemToItem")
    public ResponseEntity<GenericResponse> addVasItemToItem(@RequestBody DefaultItemVasItemDTO defaultItemVasItemDTO) {
        GenericResponse response = new GenericResponse();
        try {
            Boolean result = itemService.addVasItemToItem(defaultItemVasItemDTO);
            response.setResult(result);
            response.setMessage(result ? "Vas Item added successfully" : "Failed to add Vas Item");
            return new ResponseEntity<>(response, result ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setResult(false);
            response.setMessage("Internal Server Error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/addItem")
    public ResponseEntity<GenericResponse> addItem(@RequestBody Item item) {
        GenericResponse response = new GenericResponse();
        try {
            Boolean result = itemService.addItemToCart(item);
            response.setResult(result);
            response.setMessage(result ? "Item added successfully" : "Failed to add item");
            return new ResponseEntity<>(response, result ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setResult(false);
            response.setMessage("Internal Server Error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/removeItem/{itemId}")
    public ResponseEntity<GenericResponse> removeItem(@PathVariable("itemId") int itemId) {
        GenericResponse response = new GenericResponse();
        try {
            Boolean result = itemService.removeItemFromCart(itemId);
            response.setResult(result);
            response.setMessage(result ? "Item removed successfully" : "Item not found or could not be removed");
            return new ResponseEntity<>(response, result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setResult(false);
            response.setMessage("Internal Server Error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
