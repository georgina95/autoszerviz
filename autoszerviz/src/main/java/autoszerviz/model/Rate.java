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
public class Rate {

    @Id
	@GeneratedValue
	public int id;
	
    public String name;

    public int price;    
}
