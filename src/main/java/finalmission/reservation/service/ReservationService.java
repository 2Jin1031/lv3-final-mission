package finalmission.reservation.service;

import finalmission.reservation.Reservation;
import finalmission.reservation.domain.dto.ReservationRequestDto;
import finalmission.reservation.domain.dto.ReservationResponseDto;
import finalmission.reservation.repository.ReservationRepository;
import finalmission.room.domain.Room;
import finalmission.room.exception.RoomBadRequestException;
import finalmission.room.repository.RoomRepository;
import finalmission.user.User;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    public ReservationService(ReservationRepository reservationRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
    }

    public ReservationResponseDto create(ReservationRequestDto requestDto, User member) {
        Reservation reservation = convertToReservation(requestDto, member);
        Reservation savedReservation = save(reservation);
        return convertToReservationResponseDto(savedReservation);
    }

    private Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    private Reservation convertToReservation(ReservationRequestDto requestDto, User member) {
        Room room = roomRepository.findById(requestDto.roomId())
                .orElseThrow(RoomBadRequestException::new);
        return requestDto.toEntity(room, member);
    }

    private ReservationResponseDto convertToReservationResponseDto(Reservation savedReservation) {
        return ReservationResponseDto.of(savedReservation, savedReservation.getRoom(), savedReservation.getUser());
    }
}
