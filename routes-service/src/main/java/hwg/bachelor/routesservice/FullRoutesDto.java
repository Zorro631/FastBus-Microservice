package hwg.bachelor.routesservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FullRoutesDto {
    private List<RoutesDto> routesDto;

    private String dayBeforeSelectedDate;
    private String selectedDate;
    private String dayAfterSelectedDate;
}
