package com.luchonicolosi.willitaapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private List<String> images;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    public Product() {
    }

    public Product(String name, Double price, List<String> images) {
        this.name = name;
        this.price = price;
        this.images = images;
    }
}
