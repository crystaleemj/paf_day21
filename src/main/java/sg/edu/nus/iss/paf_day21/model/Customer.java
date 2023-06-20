package sg.edu.nus.iss.paf_day21.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    
    private Integer id;
    private String firstName;
    private String lastName;

}
