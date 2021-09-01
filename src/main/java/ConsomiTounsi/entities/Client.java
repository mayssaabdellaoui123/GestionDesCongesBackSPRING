package ConsomiTounsi.entities;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import java.util.Set;

@Entity
@AllArgsConstructor

public class Client extends User implements Serializable {

	public Client() {
		super();
	}

	private double Latitude;
	private double Longitude;

	private String matricule;

	private LocalDateTime subscriptionDate;
	private String subMonth;

	@Enumerated(EnumType.STRING)
	private Gender genderClient;

	@Enumerated(EnumType.STRING)
	private WorkField workfieldClient;

	public WorkField getWorkfieldClient() {
		return workfieldClient;
	}

	public void setWorkfieldClient(WorkField workfieldClient) {
		this.workfieldClient = workfieldClient;
	}

	public Gender getGenderClient() {
		return genderClient;
	}

	public void setGenderClient(Gender genderClient) {
		this.genderClient = genderClient;
	}

	public String getSubMonth() {
		return subMonth;
	}

	public void setSubMonth(String subMonth) {
		this.subMonth = subMonth;
	}

	public void setSubscriptionDate(LocalDateTime subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

	public LocalDateTime getSubscriptionDate() {
		return subscriptionDate;
	}

    
   /* @OneToMany(cascade = CascadeType.ALL, mappedBy="client")
	private Set<Advertisement> advertisement;*/

	/*@ManyToMany(cascade = CascadeType.ALL)
	private List<Event> event;*/

/*	@JsonIgnore
	@ManyToMany(mappedBy="clients",cascade= CascadeType.PERSIST,fetch = FetchType.EAGER)
	private List<Event> events;*/

	/*public boolean addEvent(Event ev) {
		if(events == null)
			events = new ArrayList<>();

		return events.add(ev);
	}

	public boolean removeEvent(Event ev) {
		if(events == null)
			events = new ArrayList<>();

		return events.remove(ev);
	}*/



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



}
