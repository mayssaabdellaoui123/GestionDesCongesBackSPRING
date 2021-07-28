package ConsomiTounsi.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Advertisement implements Serializable {
	
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long idAdvertisment;
	
	
	@Temporal (TemporalType.DATE)
	private Date dateAdvertisment;
	
	private LocalDateTime  enddate;
	private int finalviews;
	private int targetviews;
	@Enumerated(EnumType.STRING)
	private TypeAdvertisement typeAdvertisement;
	private double cost;
	private String channel ;
	//@Enumerated(EnumType.STRING)
	//private TypeCriteria criteria ;
	//@Enumerated(EnumType.STRING)
	//private TypeCriteriaValue1 criteriaValue1 ;
	//@Temporal (TemporalType.DATE)
	//private Date criteriaValue2;
	//private String criteriaValue3;
	
	
	

	
	


	public Long getIdAdvertisment() {
		return idAdvertisment;
	}

	public void setIdAdvertisment(Long idAdvertisment) {
		this.idAdvertisment = idAdvertisment;
	}

	public Date getDateAdvertisment() {
		return dateAdvertisment;
	}

	public void setDateAdvertisment(Date dateAdvertisment) {
		this.dateAdvertisment = dateAdvertisment;
	}

	public LocalDateTime getEnddate() {
		return enddate;
	}

	public void setEnddate(LocalDateTime enddate) {
		this.enddate = enddate;
	}

	public int getFinalviews() {
		return finalviews;
	}

	public void setFinalviews(int finalviews) {
		this.finalviews = finalviews;
	}

	public int getTargetviews() {
		return targetviews;
	}

	public void setTargetviews(int targetviews) {
		this.targetviews = targetviews;
	}

	public TypeAdvertisement getTypeAdvertisement() {
		return typeAdvertisement;
	}

	public void setTypeAdvertisement(TypeAdvertisement typeAdvertisement) {
		this.typeAdvertisement = typeAdvertisement;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	/*public TypeCriteriaValue1 getCriteriaValue1() {
		return criteriaValue1;
	}

	public void setCriteriaValue1(TypeCriteriaValue1 criteriaValue1) {
		this.criteriaValue1 = criteriaValue1;
	}

	public Date getCriteriaValue2() {
		return criteriaValue2;
	}

	public void setCriteriaValue2(Date criteriaValue2) {
		this.criteriaValue2 = criteriaValue2;
	}

	public String getCriteriaValue3() {
		return criteriaValue3;
	}

	public void setCriteriaValue3(String criteriaValue3) {
		this.criteriaValue3 = criteriaValue3;
	}*/

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	/*@ManyToOne
	Admin admin;*/
	
	@ManyToOne
	Client client; 



}
