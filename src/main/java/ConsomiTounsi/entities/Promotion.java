package ConsomiTounsi.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
  
/*{
	"id": 3,
	"date_promotion_debut":"2021-04-12",
	"date_promotion_fin":"2021-04-13",
	"type_promotion": "Romdhane",
	"valeur_promotion": 25,
	"new_product_price": 0
}*/
/*@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor*/
@Entity
public class Promotion implements Serializable {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id_promotion;
	
	//@Temporal (TemporalType.DATE)
	private LocalDateTime date_promotion_debut;

	@Temporal (TemporalType.DATE)
	private Date date_promotion_fin;

	private String type_promotion;
	private double valeur_promotion;
	private double new_product_price; 
	
	public double getNew_product_price() {
		return new_product_price;
	}
	public void setNew_product_price(double new_product_price) {
		this.new_product_price = new_product_price;
	}
	public Long getId_promotion() {
		return id_promotion;
	}
	public void setId_promotion(Long id_promotion) {
		this.id_promotion = id_promotion;
	}
	public LocalDateTime getDate_promotion_debut() {
		return date_promotion_debut;
	}
	public void setDate_promotion_debut(LocalDateTime date_promotion_debut) {
		this.date_promotion_debut = date_promotion_debut;
	}
	public Date getDate_promotion_fin() {
		return date_promotion_fin;
	}
	public void setDate_promotion_fin(Date date_promotion_fin) {
		this.date_promotion_fin = date_promotion_fin;
	}
	public String getType_promotion() {
		return type_promotion;
	}
	public void setType_promotion(String type_promotion) {
		this.type_promotion = type_promotion;
	}
	public double getValeur_promotion() {
		return valeur_promotion;
	}
	public void setValeur_promotion(double valeur_promotion) {
		this.valeur_promotion = valeur_promotion;
	}
	
	@OneToMany(mappedBy="promotion",
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
			fetch= FetchType.EAGER)
	private List<Product> product = new ArrayList<>();

}
