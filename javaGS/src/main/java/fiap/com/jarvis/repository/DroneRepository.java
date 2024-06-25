package fiap.com.jarvis.repository;

import fiap.com.jarvis.model.Drone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DroneRepository extends JpaRepository<Drone, Long> {

    @Query("from Drone d where lower(d.fabricante) like lower(concat('%', :fabricante, '%'))")
    Page<Drone> buscarPorFabricante(String fabricante, Pageable pageable);
}
