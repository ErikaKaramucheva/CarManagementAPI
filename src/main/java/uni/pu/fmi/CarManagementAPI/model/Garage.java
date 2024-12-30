package uni.pu.fmi.CarManagementAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Garage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long garageId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private int capacity;
    @ManyToMany(mappedBy = "garages")
    private Set<Car> cars;
    @OneToMany(mappedBy = "garage")
    private Set<Maintenance> maintenances;


}
