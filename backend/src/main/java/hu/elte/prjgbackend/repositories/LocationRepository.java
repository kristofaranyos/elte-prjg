package hu.elte.prjgbackend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import hu.elte.prjgbackend.models.Location;

public interface LocationRepository extends JpaRepository<Location, Long>{

}