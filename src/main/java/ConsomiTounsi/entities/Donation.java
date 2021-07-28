package ConsomiTounsi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Donation implements Serializable {


	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int id_donation;
	private double amount_donation;
	long product_donation;
	private String username;
	private String emailAdress;
	//@Temporal (TemporalType.DATE)
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime date;



	@ManyToOne
	private Event event;
}
