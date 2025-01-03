package uni.pu.fmi.CarManagementAPI.dto.response;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseGarageDTO {
    private Long id;
    private String name;
    private String location;
    private String city;
    private int capacity;
}
