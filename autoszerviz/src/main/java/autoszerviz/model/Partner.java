package autoszerviz.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

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
}
