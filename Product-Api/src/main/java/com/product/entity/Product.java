package com.product.entity;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Entity
@Table(name = "products")
@Builder
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "{product.name.notBlank}")
    @Size(min = 2, max = 100, message = "{product.name.size}")
    @Column(nullable = false, length = 100)
	private String name;
	
	@Size(max = 500, message = "{product.description.size}")
    @Column(length = 500)
	private String description;
	
	@NotNull(message = "{product.price.notNull}")
    @Column(nullable = false)
	private Double price;
	
	public Product() {}
	
	public Product(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
	
	 public Product(Long id, String name, String description, double price) {
	        this.id = id;
	        this.name = name;
	        this.description = description;
	        this.price = price;
	    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
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
    
    

}
