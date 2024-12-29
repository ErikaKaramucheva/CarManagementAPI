package uni.pu.fmi.CarManagementAPI.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
public class UpdateCarDTO {
    @NotEmpty(message = "Make is required!")
    private String make;
    @NotEmpty(message = "Model is required!")
    private String model;
    @Positive(message = "Production year must be positive!")
    private int productionYear;
    @NotEmpty(message = "License plate is required!")
    private String licensePlate;
    private List<Long> garageIds;
}
