package ITCEQConge.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Admin extends User implements Serializable {

	public Admin(Role roleAdmin) {
		super();
		this.roleAdmin = roleAdmin;
		this.setRoleUser(UserRole.ADMIN);
	}

	public Admin() {
		super();
		this.setRoleUser(UserRole.ADMIN);	}

	@Enumerated(EnumType.STRING)
	private Role roleAdmin;

	private int nbabsenceAdmin;

	private String matriculeBoss ;

	public void setMatriculeBoss(String matriculeBoss) {
		this.matriculeBoss = matriculeBoss;
	}

	public String getMatriculeBoss() {
		return matriculeBoss;
	}

	public int getNbabsenceAdmin() {
		return nbabsenceAdmin;
	}

	public void setNbabsenceAdmin(int nbabsenceAdmin) {
		this.nbabsenceAdmin = nbabsenceAdmin;
	}
	
	public Role getRoleAdmin() {
		return roleAdmin;
	}

	public void setRoleAdmin(Role roleAdmin) {
		this.roleAdmin = roleAdmin;
	}

	/*@OneToOne
	private Pool pool;*/

}
