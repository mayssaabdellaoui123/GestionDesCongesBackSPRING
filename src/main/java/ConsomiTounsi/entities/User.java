package ConsomiTounsi.entities;

import javax.persistence.*;
import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable
{

	public User(String firstNameUser, String lastNameUser, String usernameUser, String passwordUser, String phoneNumberUser, String emailAddressUser, Date dateBirthUser, String addressUser, UserRole roleUser) {
		this.firstNameUser = firstNameUser;
		this.lastNameUser = lastNameUser;
		this.usernameUser = usernameUser;
		this.passwordUser = passwordUser;
		this.phoneNumberUser = phoneNumberUser;
		this.emailAddressUser = emailAddressUser;
		this.dateBirthUser = dateBirthUser;
		this.addressUser = addressUser;
		this.roleUser = roleUser;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private long idUser;
	
	@Column(nullable=false , length=20)
	private String firstNameUser; 
	
	@Column(nullable=false , length=20)
	private String lastNameUser;

	@Column(unique=true , nullable=false , length=30)
	private String usernameUser;
	
	@Column(nullable=false, length=64)
	private String passwordUser;
	
	private String phoneNumberUser;
	
	@Column(unique=true , nullable=false , length=45)
	private String emailAddressUser;
	
	@Temporal (TemporalType.DATE)
	private Date dateBirthUser;
	
	private String addressUser;

	@Enumerated(EnumType.STRING)
	private UserRole roleUser;

	private boolean updatedPassword ;
	private int nbaccessUser ;


	private double salary;

	private boolean enabled = true;
	private boolean locked = false;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private Set<Subject> subject;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private Set<Claim> claim;

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



}
