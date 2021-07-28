package ConsomiTounsi.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Delivery implements Serializable {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id_delivery;
	
	@Temporal (TemporalType.DATE)
	private Date date_delivery;
	
	private String destination_delivery;
	
	@Enumerated(EnumType.STRING)
	private MeansOfTransport meanOfTransport_delivery;
	private float cost_delivery;
	
	@Enumerated(EnumType.STRING)
	private QualityOfDelivering quality_delivery;	
	
	@Enumerated(EnumType.STRING)
	private DurationOfDelivering duration_delivery;

	private int scoreDelivery;
	
	private double distance;
	
	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	@Enumerated(EnumType.STRING)
	private ServiceDelivering Stars_service_delivery;

	public Long getId_delivery() {
		return id_delivery;
	}

	public void setId_delivery(Long id_delivery) {
		this.id_delivery = id_delivery;
	}
	

	public Date getDate_delivery() {
		return date_delivery;
	}

	public void setDate_delivery(Date date_delivery) {
		this.date_delivery = date_delivery;
	}

	public String getDestination_delivery() {
		return destination_delivery;
	}

	public void setDestination_delivery(String destination_delivery) {
		this.destination_delivery = destination_delivery;
	}

	public MeansOfTransport getMeanOfTransport_delivery() {
		return meanOfTransport_delivery;
	}

	public void setMeanOfTransport_delivery(MeansOfTransport meanOfTransport_delivery) {
		this.meanOfTransport_delivery = meanOfTransport_delivery;
	}

	public float getCost_delivery() {
		return cost_delivery;
	}

	public void setCost_delivery(float cost_delivery) {
		this.cost_delivery = cost_delivery;
	}
	
	public DurationOfDelivering getDuration_delivery() {
		return duration_delivery;
	}

	public void setDuration_delivery(DurationOfDelivering duration_delivery) {
		this.duration_delivery = duration_delivery;
	}

	public QualityOfDelivering getQuality_delivery() {
		return quality_delivery;
	}

	public int getScoreDelivery() {
		return scoreDelivery;
	}

	public void setScoreDelivery(int scoreDelivery) {
		this.scoreDelivery = scoreDelivery;
	}

	public void setQuality_delivery(QualityOfDelivering quality_delivery) {
		this.quality_delivery = quality_delivery;
	}
	
	public ServiceDelivering getService_delivery() {
		return Stars_service_delivery;
	}
	public void setService_delivery(ServiceDelivering service_delivery) {
		this.Stars_service_delivery = service_delivery; 
	}
	public Deliverer getDeliverer() {
		return deliverer;
	}

	public void setDeliverer(Deliverer deliverer) {
		this.deliverer = deliverer;
	}
	/*public int getScoreDelivery() {
		return scoreDelivery;
	}

	public void setScoreDelivery(int scoreDelivery) {
		this.scoreDelivery = scoreDelivery;
	}*/
	@ManyToOne
	Deliverer deliverer;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="delivery")
	private Set<Order> order;
}
