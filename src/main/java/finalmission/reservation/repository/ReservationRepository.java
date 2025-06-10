package finalmission.reservation.repository;

import finalmission.reservation.Reservation;
import finalmission.room.domain.Room;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByRoom(Room room);
}
