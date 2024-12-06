package uni.pu.fmi.CarManagementAPI.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Entity
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String make;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private Year productionYear;
    @Column(nullable = false)
    private String licensePlate;
    @OneToMany
    @JoinColumn(name = "id")
    private Garage garage;

}
