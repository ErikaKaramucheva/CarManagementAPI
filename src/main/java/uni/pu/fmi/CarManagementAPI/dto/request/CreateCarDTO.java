package uni.pu.fmi.CarManagementAPI.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
public class CreateCarDTO {
    @NotBlank(message = "Make is required!")
    @Size(min=1, message = "Make must be at least 1 character.")
    private String make;
    @NotBlank(message = "Model is required!")
    @Size(min=1, message = "Model must be at least 1 character.")
    private String model;
    @Positive(message = "Production year must be positive!")
    private Integer productionYear;
    @NotBlank(message = "License plate is required!")
    @Size(min=1, message = "License plate must be at least 1 character.")
    private String licensePlate;
    private List<Long> garageIds;
}
