package hwg.bachelor.checkoutservice;


import hwg.bachelor.checkoutservice.dto.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class CheckoutService {
//    @Autowired
//    private RoutesRepository routesRepository;
    @Autowired
    private RestClient restClient;

    public ConnectionDetailsDto getConnectionDetails(String routeId, LocalTime departureTime, LocalDate departureDate, int quantity) {

        Routes route = restClient.get()
                .uri("http://localhost:8080/routes/"+routeId)
                .retrieve()
                .body(Routes.class);
        System.out.println( route);
//        System.out.println(result);
//        Routes route = routesRepository.findById(routeId).orElseThrow();

        String departureCity = route.getDeparture();
        String destinationCity = route.getDestination();

        BigDecimal duration = route.getDuration();

        LocalTime destinationTime = calculateDestinaitonTime(duration, departureTime);

        LocalDate destinationDate = departureDate;
        if (destinationTime.isBefore(departureTime)) {
            destinationDate = destinationDate.plusDays(1);
        }
        BigDecimal totalPrice = route.getPrice().multiply(BigDecimal.valueOf(quantity));

        return new ConnectionDetailsDto(
                route.getRouteId(), departureCity, destinationCity,
                departureTime, destinationTime, departureDate,
                destinationDate, quantity, totalPrice
        );
    }

    private LocalTime calculateDestinaitonTime(BigDecimal duration, LocalTime departureTime) {
        long hours = duration.longValue(); // Ganze Stunden (3)
        long minutes = duration.subtract(BigDecimal.valueOf(hours)).multiply(BigDecimal.valueOf(100)).longValue(); // Minuten (45)
        return departureTime.plusHours(hours).plusMinutes(minutes);
    }


}
