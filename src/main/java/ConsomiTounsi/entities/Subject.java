package ConsomiTounsi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Subject implements Serializable {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long idSubject;
	
	private boolean featuredSubject;
	private String descriptionSubject;
	private int likesSubject;

	@ManyToOne
	User user;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="subject")
	private Set<Comment> comment;
}
