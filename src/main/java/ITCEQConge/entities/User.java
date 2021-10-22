package ITCEQConge.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable
{

	public User(String firstNameUser, String lastNameUser, String usernameUser, String passwordUser, String phoneNumberUser, String emailAddressUser, Date dateBirthUser, String addressUser, UserRole roleUser, String matricule ) {
		this.firstNameUser = firstNameUser;
		this.lastNameUser = lastNameUser;
		this.usernameUser = usernameUser;
		this.passwordUser = passwordUser;
		this.phoneNumberUser = phoneNumberUser;
		this.emailAddressUser = emailAddressUser;
		this.dateBirthUser = dateBirthUser;
		this.addressUser = addressUser;
		this.roleUser = roleUser;
		this.matricule = matricule;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private long idUser;
	
	@Column(  length=20)
	private String firstNameUser;
	
	@Column(length=20)
	private String lastNameUser;

	@Column(unique=true , length=30)
	private String usernameUser;
	
	@Column(nullable=false, length=64)
	private String passwordUser;
	
	private String phoneNumberUser;
	
	@Column(unique=true , nullable=false , length=45)
	private String emailAddressUser;
	
	@Temporal (TemporalType.DATE)
	private Date dateBirthUser;
	
	private String addressUser;

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	public Departement getDepartement() {
		return departement;
	}

	public Carriere getCarriere() {
		return carriere;
	}

	@Enumerated(EnumType.STRING)
	private UserRole roleUser;

	private  String matricule;

	public void setConge(List<Conge> conge) {
		this.conge = conge;
	}

	public void setJoursferies(List<JoursFeries> joursferies) {
		this.joursferies = joursferies;
	}

	public void setCarriere(Carriere carriere) {
		this.carriere = carriere;
	}



	public List<Conge> getConge() {
		return conge;
	}

	public List<JoursFeries> getJoursferies() {
		return joursferies;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getMatricule() {
		return matricule;
	}

	private boolean updatedPassword ;
	private int nbaccessUser ;


	private double salary;

	private Long soldeDeConge;

	public void setSoldeDeConge(Long soldeDeConge) {
		this.soldeDeConge = soldeDeConge;
	}

	public Long getSoldeDeConge() {
		return soldeDeConge;
	}

	private boolean enabled = true;
	private boolean locked = false;

/*	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private Set<Subject> subject;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private Set<Claim> claim;*/

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public String getFirstNameUser() {
		return firstNameUser;
	}

	public void setFirstNameUser(String firstNameUser) {
		this.firstNameUser = firstNameUser;
	}

	public String getLastNameUser() {
		return lastNameUser;
	}

	public void setLastNameUser(String lastNameUser) {
		this.lastNameUser = lastNameUser;
	}

	public String getUsernameUser() {
		return usernameUser;
	}

	public void setUsernameUser(String usernameUser) {
		this.usernameUser = usernameUser;
	}

	public String getPasswordUser() {
		return passwordUser;
	}

	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}

	public String getPhoneNumberUser() {
		return phoneNumberUser;
	}

	public void setPhoneNumberUser(String phoneNumberUser) {
		this.phoneNumberUser = phoneNumberUser;
	}

	public String getEmailAddressUser() {
		return emailAddressUser;
	}

	public void setEmailAddressUser(String emailAddressUser) {
		this.emailAddressUser = emailAddressUser;
	}

	public Date getDateBirthUser() {
		return dateBirthUser;
	}

	public void setDateBirthUser(Date dateBirthUser) {
		this.dateBirthUser = dateBirthUser;
	}

	public String getAddressUser() {
		return addressUser;
	}

	public void setAddressUser(String addressUser) {
		this.addressUser = addressUser;
	}

	public  UserRole getRoleUser() {
		return roleUser;
	}

	public void setRoleUser(UserRole roleUser) {
		this.roleUser = roleUser;
	}

	public boolean isUpdatedPassword() {
		return updatedPassword;
	}

	public void setUpdatedPassword(boolean updatedPassword) {
		this.updatedPassword = updatedPassword;
	}

	public int getNbaccessUser() {
		return nbaccessUser;
	}

	public void setNbaccessUser(int nbaccessUser) {
		this.nbaccessUser = nbaccessUser;
	}

	public double getSalary() {
		return salary;
	}


	public void setSalary(double salary) {
		this.salary = salary;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}


	@JsonIgnore
	@OneToMany(mappedBy="users",
			cascade = CascadeType.ALL,
			fetch= FetchType.EAGER)
	private List<Conge> conge = new ArrayList<>();


	@ManyToMany(cascade = CascadeType.ALL)
	private List<JoursFeries> joursferies;

	@JsonIgnore
	@ManyToOne
	private Departement departement;

	@OneToOne
	private Carriere carriere;


}
