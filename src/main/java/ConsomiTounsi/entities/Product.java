package ConsomiTounsi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Product product = (Product) o;
		return quantityProduct == product.quantityProduct && productofthemonth == product.productofthemonth && Double.compare(product.price, price) == 0 && Float.compare(product.rating, rating) == 0 && available == product.available && Double.compare(product.weight, weight) == 0 && Objects.equals(id, product.id) && Objects.equals(code, product.code) && Objects.equals(name, product.name) && Objects.equals(category, product.category) && Objects.equals(description, product.description) && Objects.equals(imagePath, product.imagePath) && Objects.equals(image_URL, product.image_URL) && categoryProduct == product.categoryProduct && Objects.equals(supplier, product.supplier) && Objects.equals(orders, product.orders) && Objects.equals(shelf, product.shelf) && Objects.equals(feedback, product.feedback) && Objects.equals(promotion, product.promotion);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, quantityProduct, productofthemonth, code, name, category, price, rating, available, description, weight, imagePath, image_URL, categoryProduct, supplier, orders, shelf, feedback, promotion);
	}

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)

	private Long id;
	private long quantityProduct;
	private boolean productofthemonth;
	private String code;
	private String name;
	private String category;
	private double price;
	private float rating;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private boolean available;
	private String description;
	private double weight;
	private String imagePath ;
	private String image_URL;

	@Enumerated(EnumType.STRING)
	private TypeCategory categoryProduct;


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	@ManyToOne
	Supplier supplier;

	@JsonIgnore
	@ManyToMany(mappedBy="products",cascade= CascadeType.PERSIST,fetch = FetchType.EAGER)
	private List<Order> orders;

	@JsonIgnore
	@ManyToOne
	private Shelf shelf;

	@OneToOne
	private Feedback feedback;

	@ManyToOne
	Promotion promotion;

}

