package hwg.bachelor.routesservice;

import hwg.bachelor.routesservice.model.Routes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin("*")
@RestController
public class RoutesController {

    @Autowired
    private RoutesService routesService;
//    @RequestMapping("routes")
    @Operation
    @GetMapping("/routes")
    public ResponseEntity<?> getRoutes(
            @RequestParam("departure") @Parameter(example = "Mannheim") String departure,
            @RequestParam("destination") @Parameter(example = "Hamburg") String destination,
            @RequestParam("date") @Parameter(example = "2024-12-14") LocalDate date,
            @RequestParam("quantity") @Parameter(example = "1") int quantity) {

        if (departure.equals(destination)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Departure and destination must be different.");
        }
        if (date.isBefore(LocalDate.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Date must be in the future.");
        }
        if (quantity <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quantity must be greater than zero.");
        }

        List<RoutesDto> availableRoutes = routesService.findRoutesByLocations(departure, destination, date);
        FullRoutesDto fullRoutesDto = routesService.fullRoutesDtoResponse(availableRoutes,date);
        return ResponseEntity.ok(fullRoutesDto);
    }
    @Operation
    @GetMapping("/routes/{routeId}")
    public ResponseEntity<?> findById(@PathVariable String routeId){
        Routes routes = routesService.findeById(routeId);
        return ResponseEntity.ok(routes);
    }

}
