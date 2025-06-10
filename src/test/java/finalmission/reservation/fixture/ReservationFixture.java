package finalmission.reservation.fixture;

import finalmission.reservation.domain.dto.ReservationRequestDto;

public class ReservationFixture {

    public static ReservationRequestDto createReservationRequestDtoDefaultByRoomId(Long roomId) {
        return new ReservationRequestDto("content1", roomId);
    }
}
