package autoszerviz.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Worksheet {

    @Id
	@GeneratedValue
	public int id;
	
    @JoinColumn
	@ManyToOne(targetEntity = Partner.class)
    public Partner partner;

    @JoinColumn
	@ManyToOne(targetEntity = Mechanic.class)
    public Mechanic mechanic;
	
	@JoinColumn
	@ManyToOne(targetEntity = Material.class)
    public Material material;
	
	@JoinColumn
	@ManyToOne(targetEntity = Part.class)
    public Part part;
}
