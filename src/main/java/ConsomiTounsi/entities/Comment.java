package ConsomiTounsi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Comment implements Serializable {


	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long idComment;
	private int likesComment;
	private boolean mostPertinentComment;
	private String textComment;

	@ManyToOne
	Subject subject;

}
