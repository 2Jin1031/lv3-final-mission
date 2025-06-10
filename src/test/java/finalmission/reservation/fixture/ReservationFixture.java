package finalmission.reservation.fixture;

import finalmission.reservation.Reservation;
import finalmission.reservation.domain.dto.ReservationRequestDto;
import finalmission.room.domain.Room;
import finalmission.user.User;

public class ReservationFixture {

    public static ReservationRequestDto createReservationRequestDtoDefaultByRoomId(Long roomId) {
        return new ReservationRequestDto("content1", roomId);
    }

    public static Reservation createReservationDefaultByRoomIdAndUserId(Room savedRoom, User savedMember) {
        return new Reservation("contnent1", savedRoom, savedMember);
    }
}
