package finalmission.reservation.controller;

import finalmission.global.email.MailDataDto;
import finalmission.global.email.MailService;
import finalmission.reservation.domain.dto.ReservationRequestDto;
import finalmission.reservation.domain.dto.ReservationResponseDto;
import finalmission.reservation.service.ReservationService;
import finalmission.user.User;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final MailService mailService;

    public ReservationController(ReservationService reservationService, MailService mailService) {
        this.reservationService = reservationService;
        this.mailService = mailService;
    }

    @PostMapping("/member")
    public ResponseEntity<ReservationResponseDto> create(@RequestBody ReservationRequestDto requestDto, User member) {
        ReservationResponseDto responseDto = reservationService.create(requestDto, member);
        mailService.send(new MailDataDto("zkffl0@naver.com", "회의실 예약 승인 이메일 발송", responseDto.userResponseDto().email(), "예약이 성공적으로 처리되었습니다"));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<ReservationResponseDto>> findAllByRoomId(@PathVariable(value = "roomId") Long id) {
        List<ReservationResponseDto> reservationResponseDtos = reservationService.findAllByRoomId(id);
        return ResponseEntity.status(HttpStatus.OK).body(reservationResponseDtos);
    }

    @GetMapping("/member")
    public ResponseEntity<List<ReservationResponseDto>> findAllByRoomId(User member) {
        List<ReservationResponseDto> reservationResponseDtos = reservationService.findAllByUser(member);
        return ResponseEntity.status(HttpStatus.OK).body(reservationResponseDtos);
    }

    @PostMapping("/{reservationId}")
    public ResponseEntity<ReservationResponseDto> update(@PathVariable(value = "reservationId") Long reservationId, @RequestBody ReservationRequestDto requestDto, User user) {
        ReservationResponseDto responseDto = reservationService.update(reservationId, requestDto, user);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "reservationId") Long reservationId, User user) {
        reservationService.deleteById(reservationId, user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

