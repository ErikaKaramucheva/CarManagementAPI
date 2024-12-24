package uni.pu.fmi.CarManagementAPI.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateGarageDTO {
    private String name;
    private String location;
    private String city;
    private int capacity;

}
