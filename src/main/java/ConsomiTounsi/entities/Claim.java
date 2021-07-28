package ConsomiTounsi.entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity

public class Claim implements Serializable {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long idClaim;

	private String decision;

	private String date;

	private String status;

	private String description;
	private int Level;

	private Long ProductWithProb ;
	
	@Enumerated(EnumType.STRING)
	private SystemProb systemProblem ;
	
	@Enumerated(EnumType.STRING)
	private DeliveryProb deliveryProblem ;
	
	@Enumerated(EnumType.STRING)
	private ProductProb productProblem ;

	@Enumerated(EnumType.STRING)
	private ClaimType subject;

	@ManyToOne
	User user;

	public Long getIdClaim() {
		return idClaim;
	}

	public void setIdClaim(Long idClaim) {
		this.idClaim = idClaim;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLevel() {
		return Level;
	}

	public void setLevel(int level) {
		Level = level;
	}

	public Long getProductWithProb() {
		return ProductWithProb;
	}

	public void setProductWithProb(Long productWithProb) {
		ProductWithProb = productWithProb;
	}

	public SystemProb getSystemProblem() {
		return systemProblem;
	}

	public void setSystemProblem(SystemProb systemProblem) {
		this.systemProblem = systemProblem;
	}

	public DeliveryProb getDeliveryProblem() {
		return deliveryProblem;
	}

	public void setDeliveryProblem(DeliveryProb deliveryProblem) {
		this.deliveryProblem = deliveryProblem;
	}

	public ProductProb getProductProblem() {
		return productProblem;
	}

	public void setProductProblem(ProductProb productProblem) {
		this.productProblem = productProblem;
	}

	public ClaimType getSubject() {
		return subject;
	}

	public void setSubject(ClaimType subject) {
		this.subject = subject;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}

