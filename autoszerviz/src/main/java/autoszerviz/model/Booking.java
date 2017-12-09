package autoszerviz.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Booking {

    @Id
	@GeneratedValue
	public int id;
	
	@JoinColumn
	@ManyToOne(targetEntity = Partner.class)
    public Partner partner;

    public String date; //valami date formátum kell majd
	
	@JoinColumn
	@ManyToOne(targetEntity = Mechanic.class)
    public Mechanic mechanic;
	
	@Column
    @Enumerated(EnumType.STRING)
    private Type type;
    
    public enum Type {
        COMPULSORY_SERVICE, TECHNICAL_EXAMINATION, MALFUNCTION;
	}
	
	public String comment;
}
