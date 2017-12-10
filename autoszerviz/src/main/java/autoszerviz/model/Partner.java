package autoszerviz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Partner {
	
	public String name;

    public String address;

    public String phonenumber;

    @Id
	@GeneratedValue
	public int id;
	
	public String password;
	
	@JsonIgnore
    @OneToMany(targetEntity = Booking.class, 
               cascade = CascadeType.ALL, 
               mappedBy = "partner")
	public List<Booking> bookings;
	
	@JsonIgnore
    @OneToMany(targetEntity = Worksheet.class, 
               cascade = CascadeType.ALL, 
               mappedBy = "partner")
	public List<Worksheet> worksheets;
}
