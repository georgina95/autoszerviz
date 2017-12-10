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
public class Material {

    @Id
	@GeneratedValue
	public int id;
	
    public String name;

    public int price;
	
	@JsonIgnore
    @OneToMany(targetEntity = Worksheet.class, 
               cascade = CascadeType.ALL, 
               mappedBy = "material")
	public List<Worksheet> worksheets;
}
