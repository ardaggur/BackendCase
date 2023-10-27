package com.example.trendyolBackendCase.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "promotion")
public abstract class Promotion {

    @Id
    private int id;

    public Promotion(int id)
    {
        this.id = id;
    }

}
