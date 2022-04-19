package com.socs.socs.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Data

@Table(name = "Socks")
@Entity
public class Socks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "color")
    private String color;
    @Min(0)
    @Max(100)
    @Column(name = "cottonPart")
    private int cottonPart;
    @Min(0)
    @Column(name = "quantity")
    private int quantity;

}
