package ConsomiTounsi.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Deliverer extends User implements Serializable {
	
	public Deliverer(boolean delivererOfTheMonthDeliverer, boolean availableDeliverer, double distanceDeliverer,
			double bonusDeliverer) {
		super();
		this.delivererOfTheMonthDeliverer = delivererOfTheMonthDeliverer;
		this.availableDeliverer = availableDeliverer;
		this.distanceDeliverer = distanceDeliverer;
		this.bonusDeliverer = bonusDeliverer;
	}

	public Deliverer() {
		super();
		// TODO Auto-generated constructor stub
	}

	private boolean delivererOfTheMonthDeliverer;
	
	@Column(columnDefinition = "boolean default true")
	private boolean availableDeliverer;
	
	private double distanceDeliverer;
	
	private double bonusDeliverer;
	private int score_deliverer;
	private double Latitude;

	public double getLatitude() {
		return Latitude;
	}

	public void setLatitude(double latitude) {
		Latitude = latitude;
	}

	public double getLongitude() {
		return Longitude;
	}

	public void setLongitude(double longitude) {
		Longitude = longitude;
	}

	private double Longitude;

	public int getScore_deliverer() {
		return score_deliverer;
	}

	public void setScore_deliverer(int score_deliverer) {
		this.score_deliverer = score_deliverer;
	}

	public boolean isDelivererOfTheMonthDeliverer() {
		return delivererOfTheMonthDeliverer;
	}

	public void setDelivererOfTheMonthDeliverer(boolean delivererOfTheMonthDeliverer) {
		this.delivererOfTheMonthDeliverer = delivererOfTheMonthDeliverer;
	}

	public boolean isAvailableDeliverer() {
		return availableDeliverer;
	}

	public void setAvailableDeliverer(boolean availableDeliverer) {
		this.availableDeliverer = availableDeliverer;
	}

	public double getDistanceDeliverer() {
		return distanceDeliverer;
	}

	public void setDistanceDeliverer(double distanceDeliverer) {
		this.distanceDeliverer = distanceDeliverer;
	}

	public double getBonusDeliverer() {
		return bonusDeliverer;
	}

	public void setBonusDeliverer(double bonusDeliverer) {
		this.bonusDeliverer = bonusDeliverer;
	}
	
	
	/*@OneToMany(mappedBy="deliverer",
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
			fetch= FetchType.LAZY)
	private List<Delivery> delivery = new ArrayList<>();*/


	/*@OneToMany(cascade = CascadeType.ALL, mappedBy="deliverer")
	private Set<Delivery> delivery;*/
}
