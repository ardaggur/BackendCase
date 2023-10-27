package com.example.trendyolBackendCase.entity;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "item")
public abstract class Item {

    public static final int MAX_ITEM_COUNT = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private double price;
    @Column
    private int sellerId;
    @Column
    private int categoryId;
    @Column
    private int quantity;

    public Item(int id, double price, int sellerId, int categoryId, int quantity){
        this.id = id;
        this.price = price;
        this.sellerId = sellerId;
        this.categoryId = categoryId;
        this.quantity = quantity;
    }

}
