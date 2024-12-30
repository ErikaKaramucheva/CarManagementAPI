package uni.pu.fmi.CarManagementAPI.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateGarageDTO {
    @NotBlank(message = "Name is required!")
    @Size(min=1, message = "Name must be at least 1 character.")
    private String name;
    @NotBlank(message = "Location is required!")
    @Size(min=1, message = "Location must be at least 1 character.")
    private String location;
    @NotBlank(message = "City is required!")
    @Size(min=1, message = "City must be at least 1 character.")
    private String city;
    @Positive(message = "Capacity must be positive!")
    private int capacity;

}
