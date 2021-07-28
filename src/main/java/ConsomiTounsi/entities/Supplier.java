package ConsomiTounsi.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Supplier implements Serializable {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int id_supplier;
	
	private String name_supplier;
	private String phoneNumber_supplier;

	public int getId_supplier() {
		return id_supplier;
	}

	public void setId_supplier(int id_supplier) {
		this.id_supplier = id_supplier;
	}

	public String getName_supplier() {
		return name_supplier;
	}

	public void setName_supplier(String name_supplier) {
		this.name_supplier = name_supplier;
	}

	public String getPhoneNumber_supplier() {
		return phoneNumber_supplier;
	}

	public void setPhoneNumber_supplier(String phoneNumber_supplier) {
		this.phoneNumber_supplier = phoneNumber_supplier;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy="supplier")
	private Set<Product> product;
}
