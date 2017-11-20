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
public class Worksheets {

    @Id
	public int id;
	
    public int partnerid;

    public int mechanicid;    
	
	public int materialid;
	
	public int partid;
}
