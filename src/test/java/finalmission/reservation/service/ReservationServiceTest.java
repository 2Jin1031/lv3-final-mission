package finalmission.reservation.service;

import finalmission.reservation.Reservation;
import finalmission.reservation.domain.dto.ReservationRequestDto;
import finalmission.reservation.domain.dto.ReservationResponseDto;
import finalmission.reservation.fixture.ReservationFixture;
import finalmission.reservation.repository.ReservationRepository;
import finalmission.room.domain.Room;
import finalmission.room.fixture.RoomFixture;
import finalmission.room.repository.RoomRepository;
import finalmission.user.User;
import finalmission.user.fixture.UserFixture;
import finalmission.user.repository.UserRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;

    private Room savedRoom;
    private User savedMember;


    @BeforeEach
    void beforeEach() {
        Room room = RoomFixture.createDefault();
        savedRoom = roomRepository.save(room);

        User member = UserFixture.createDefault();
        savedMember = userRepository.save(member);
    }

    @Nested
    @DisplayName("예약 생성")
    class create {

        @DisplayName("예약이 생성된다")
        @Test
        void create_success() {
            // given
            ReservationRequestDto requestDto = ReservationFixture.createReservationRequestDtoDefaultByRoomId(savedRoom.getId());

            // when
            ReservationResponseDto responseDto = reservationService.create(requestDto, savedMember);

            // then
            Assertions.assertThat(responseDto.roomResponseDto().id()).isEqualTo(savedRoom.getId());
        }
    }

    @Nested
    @DisplayName("회의실 ID로 예약 전체 목록 조회")
    class findAllByRoomId {

        @DisplayName("요청 회의실 ID로 예약 목록이 없더라도 예외 없이 List.of()를 반환한다.")
        @Test
        void findAllByRoomId_success_byNoData() {
            // when
            List<ReservationResponseDto> responseDto = reservationService.findAllByRoomId(savedRoom.getId());

            // then
            Assertions.assertThat(responseDto).hasSize(0);
        }

        @DisplayName("요청 회의실 ID로 예약 목록을 전체 조회할 수 있다.")
        @Test
        void findAllByRoomId_success() {
            // given
            Reservation reservation1 = ReservationFixture.createReservationDefaultByRoomIdAndUserId(savedRoom, savedMember);
            reservationRepository.save(reservation1);
            Reservation reservation2 = ReservationFixture.createReservationDefaultByRoomIdAndUserId(savedRoom, savedMember);
            reservationRepository.save(reservation2);

            // when
            List<ReservationResponseDto> responseDto = reservationService.findAllByRoomId(savedRoom.getId());

            // then
            Assertions.assertThat(responseDto).hasSize(2);
        }
    }
}
