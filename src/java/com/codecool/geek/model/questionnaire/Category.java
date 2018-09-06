package com.codecool.geek.model.questionnaire;

import com.codecool.geek.model.customer.UserDetail;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    private String shortDescription;

    @Column(columnDefinition = "TEXT")
    private String image;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "categories")
    private Set<UserDetail> users = new HashSet<>();

    private Category() {
    }

    public Category(String category) {
        this.category = category;
    }

    public Category(String category, String shortDescription, String image) {
        this.category = category;
        this.shortDescription = shortDescription;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
