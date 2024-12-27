package uni.pu.fmi.CarManagementAPI.dto.response;

import lombok.*;

import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCarDTO {
    private Long id;
    private String make;
    private String model;
    private Integer productionYear;
    private String licensePlate;
    private Set<ResponseGarageDTO> garages;
}
