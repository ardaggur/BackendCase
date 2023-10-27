package com.example.trendyolBackendCase.exception;

public class ItemNotFoundException extends RuntimeException{

    public ItemNotFoundException(){
        super("Item not found");
    }
}
