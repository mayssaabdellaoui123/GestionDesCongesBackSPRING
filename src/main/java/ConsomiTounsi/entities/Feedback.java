package ConsomiTounsi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Feedback implements Serializable {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int id_feedback;

	private float note ;

	private String description_feedback;

	@OneToOne
	Product product;

}
