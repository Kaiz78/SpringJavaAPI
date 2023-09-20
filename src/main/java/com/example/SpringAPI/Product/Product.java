package com.example.SpringAPI.Product;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Entity
@Table
public class Product {
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Column(name = "price")
    private Double price;
    @Column(name = "sku", unique = true , nullable = false , length = 15)
    private String sku;
    @Column(name = "image")
    private String image;
    @Column(name = "category")
    private String category;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }


    @Column(name = "status")
    private String status;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false) // Spécifier le nom de la colonne et son type
    private Date created_at;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at") // Spécifier le nom de la colonne
    private Date updated_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", sku='" + sku + '\'' +
                ", image='" + image + '\'' +
                ", category='" + category + '\'' +
                ", status='" + status + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }

    public Product(String name, String description, Double price,String sku, String image, String category, String status, Date created_at, Date updated_at) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.sku = sku;
        this.image = image;
        this.category = category;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    public Product() {
        // Constructeur par défaut vide
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) { this.status = status;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Product(Long id,
                   String name,
                   String description,
                   Double price,
                   String sku,
                   String image,
                   String category,
                   String status,
                   Date created_at,
                   Date updated_at) {
          this.name = name;
          this.description = description;
          this.price = price;
          this.sku = sku;
          this.image = image;
          this.category = category;
          this.status = status;
          this.created_at = created_at;
          this.updated_at = updated_at;
     }


}
