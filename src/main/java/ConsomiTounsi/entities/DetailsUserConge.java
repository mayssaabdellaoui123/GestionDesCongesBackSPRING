package ConsomiTounsi.entities;

public class DetailsUserConge {

   public  String firstname;
   public String lastname;
   public String matricule;
   public WorkField tache;
   public String department;
   public  String phone;
   public String matriculeBossdep;
   public String matriculeRemplaceur;

    public void setMatriculeBossdep(String matriculeBossdep) {
        this.matriculeBossdep = matriculeBossdep;
    }

    public void setMatriculeRemplaceur(String matriculeRemplaceur) {
        this.matriculeRemplaceur = matriculeRemplaceur;
    }

    public String getMatriculeBossdep() {
        return matriculeBossdep;
    }

    public String getMatriculeRemplaceur() {
        return matriculeRemplaceur;
    }

    public DetailsUserConge() {
    }


    public void setTache(WorkField tache) {
        this.tache = tache;
    }



    public void setPhone(String phone) {
        this.phone = phone;
    }


    public WorkField getTache() {
        return tache;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getDepartment() {
        return department;
    }

    public String getPhone() {
        return phone;
    }


}
