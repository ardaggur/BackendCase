package com.example.trendyolBackendCase.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "defaultItemVasItem")
@NoArgsConstructor
@Getter
@Setter
public class DefaultItemVasItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column
    private int vasItemId;
    @Column
    private int defaultItemId;

}
