package ConsomiTounsi.entities;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;

public class DetailsUserConge {

   public  String firstname;
   public String lastname;
   public String matricule;
   public WorkField tache;
   public String department;
   public  String phone;
   public String matriculeBossdep;
   public String matriculeRemplaceur;

   public String soldeThisYear ;
    public String soldeYear_1 ;
    public String soldeYear_2 ;

    public String total ;
    public String nmbrDeJourConge ;
    public String RestDeJourConge ;

    @Temporal(TemporalType.DATE)
    public Date datevalidationFinale ;



    public String getSoldeThisYear() {
        return soldeThisYear;
    }

    public void setSoldeThisYear(String soldeThisYear) {
        this.soldeThisYear = soldeThisYear;
    }

    public String getSoldeYear_1() {
        return soldeYear_1;
    }

    public void setSoldeYear_1(String soldeYear_1) {
        this.soldeYear_1 = soldeYear_1;
    }

    public String getSoldeYear_2() {
        return soldeYear_2;
    }

    public void setSoldeYear_2(String soldeYear_2) {
        this.soldeYear_2 = soldeYear_2;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getNmbrDeJourConge() {
        return nmbrDeJourConge;
    }

    public void setNmbrDeJourConge(String nmbrDeJourConge) {
        this.nmbrDeJourConge = nmbrDeJourConge;
    }

    public String getRestDeJourConge() {
        return RestDeJourConge;
    }

    public void setRestDeJourConge(String restDeJourConge) {
        RestDeJourConge = restDeJourConge;
    }

    public Date getDatevalidationFinale() {
        return datevalidationFinale;
    }

    public void setDatevalidationFinale(Date datevalidationFinale) {
        this.datevalidationFinale = datevalidationFinale;
    }




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
