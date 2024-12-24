package uni.pu.fmi.CarManagementAPI.dto.request;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Data
@Getter
@Setter
public class CreateCarDTO {
    private String make;
    private String model;
    private Integer productionYear;
    private String licensePlate;
}
