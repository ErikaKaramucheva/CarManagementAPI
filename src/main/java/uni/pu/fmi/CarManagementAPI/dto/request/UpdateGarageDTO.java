package uni.pu.fmi.CarManagementAPI.dto.request;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGarageDTO {
    private Long id;
    private String name;
    private String location;
    private String city;
    private int capacity;
}
