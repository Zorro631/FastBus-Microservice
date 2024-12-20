package hwg.bachelor.routesservice.repository;

import hwg.bachelor.routesservice.model.BusInfo;
import hwg.bachelor.routesservice.model.Routes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface BusInfoRepository extends JpaRepository<BusInfo, Long> {

    Optional<BusInfo> findByStartDateAndStartTimeAndRoute(LocalDate startDate, LocalTime startTime, Routes route);
}
