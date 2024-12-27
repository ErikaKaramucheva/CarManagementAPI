package uni.pu.fmi.CarManagementAPI.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGarageDTO {
    @NotEmpty(message = "Name is required!")
    private String name;
    @NotEmpty(message = "Location is required!")
    private String location;
    @NotEmpty(message = "City is required!")
    private String city;
    @Positive(message = "Capacity must be positive!")
    private int capacity;
}
