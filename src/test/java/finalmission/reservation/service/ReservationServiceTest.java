package finalmission.reservation.service;

import finalmission.reservation.domain.dto.ReservationRequestDto;
import finalmission.reservation.domain.dto.ReservationResponseDto;
import finalmission.reservation.fixture.ReservationFixture;
import finalmission.room.domain.Room;
import finalmission.room.fixture.RoomFixture;
import finalmission.room.repository.RoomRepository;
import finalmission.user.User;
import finalmission.user.fixture.UserFixture;
import finalmission.user.repository.UserRepository;
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
}
