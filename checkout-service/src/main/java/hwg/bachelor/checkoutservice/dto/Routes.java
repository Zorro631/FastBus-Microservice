package hwg.bachelor.checkoutservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Routes {
    private String routeId;

    private String departure;

    private String destination;

    private BigDecimal duration;

    private BigDecimal price;
}
