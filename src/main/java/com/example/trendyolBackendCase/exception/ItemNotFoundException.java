package com.example.trendyolBackendCase.exception;

public class ItemNotFoundException extends RuntimeException{

    public ItemNotFoundException(String item_not_found){
        super("Item not found");
    }
}
