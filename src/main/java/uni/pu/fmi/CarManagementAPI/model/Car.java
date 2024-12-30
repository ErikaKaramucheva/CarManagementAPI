package uni.pu.fmi.CarManagementAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "car_garage",
    joinColumns = @JoinColumn(name = "car_id"),
    inverseJoinColumns = @JoinColumn(name = "garage_id"))

    private List<Garage> garages;
    /*@OneToMany(mappedBy = "car")
    private Set<Maintenance> maintenances;*/


}
