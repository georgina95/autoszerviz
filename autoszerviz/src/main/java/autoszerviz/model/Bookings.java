package autoszerviz.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Bookings {

    @Id
	public int id;
	
    public int partnerid;

    public String date; //valami date formátum kell majd

    public int mechanicid;    
	
	public String type;
	
	public String comment;
}
