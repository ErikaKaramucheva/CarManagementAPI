package uni.pu.fmi.CarManagementAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;
import java.util.Set;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
    @Column(nullable = false)
    private String make;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private Integer productionYear;
    @Column(nullable = false)
    private String licensePlate;
   @ManyToMany
   @JoinTable( name="maintenance",
           joinColumns = @JoinColumn(name="garageId")
   )
    private Set<Garage> garages;
    @OneToMany(mappedBy = "car")
    private Set<Maintenance> maintenances;


}
