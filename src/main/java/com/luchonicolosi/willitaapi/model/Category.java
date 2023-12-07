package com.luchonicolosi.willitaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private Set<Product> products;

    public Category() {
    }

    public Category(String categoryName) {
        this.name = categoryName;
    }
}
