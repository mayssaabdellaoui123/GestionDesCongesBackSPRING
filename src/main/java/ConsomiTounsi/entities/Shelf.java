package ConsomiTounsi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shelf implements Serializable {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long idShelf;
	
	//@Enumerated(EnumType.STRING)
    private String typeShelf;
    private String description;
	private String image_URL;


	@ManyToOne
	Store store;

	@JsonIgnore
	@OneToMany(mappedBy="shelf",
			cascade = CascadeType.ALL,
			fetch= FetchType.EAGER)
	private List<Product> product = new ArrayList<>();


}
