package ConsomiTounsi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Store implements Serializable {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long id_store;
	private String name_store;
	private String address_store;
	private boolean open_store;
	private double gain_store;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="store")
	private Set<Shelf> shelf;

	/*@OneToMany(cascade = CascadeType.ALL, mappedBy="store")
	private Set<User> user;*/


}
