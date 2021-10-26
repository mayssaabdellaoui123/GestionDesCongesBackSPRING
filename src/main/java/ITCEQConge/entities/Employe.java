package ITCEQConge.entities;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor

public class Employe extends User implements Serializable {

	public Employe() {
		super();
	}

	private double Latitude;
	private double Longitude;

	//private String matricule;
	private String Image_URL;

	public void setImage_URL(String image_URL) {
		Image_URL = image_URL;
	}

	public String getImage_URL() {
		return Image_URL;
	}

	/*public void setMatricule(String matricule) {
		this.matricule = matricule;
	}*/

/*	public String getMatricule() {
		return matricule;
	}*/

	private LocalDateTime subscriptionDate;
	private String subMonth;

	@Enumerated(EnumType.STRING)
	private Gender genderClient;

	@Enumerated(EnumType.STRING)
	private WorkField workfieldClient;

	private Boolean Remplaceur = Boolean.FALSE;

	public void setRemplaceur(Boolean remplaceur) {
		Remplaceur = remplaceur;
	}

	public Boolean getRemplaceur() {
		return Remplaceur;
	}

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