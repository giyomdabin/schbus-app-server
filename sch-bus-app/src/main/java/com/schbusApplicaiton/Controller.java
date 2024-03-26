package com.schbus;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import com.schbus.pay.KakaoPay;
import jakarta.servlet.http.HttpSession;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Log
@org.springframework.stereotype.Controller
@Component
public class Controller {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ReserveRepository reserveRepository;

    private ReserveDto tmp= new ReserveDto();
    @Setter(onMethod_ = @Autowired)
    private KakaoPay kakaopay;

    @PostMapping("/select_bus")
    public ResponseEntity<String> selectBus(@RequestBody ReserveDto reserveDto) {

        return ResponseEntity.ok("버스가 선택되었습니다.");
    }

    @PostMapping("/select_date")
    public ResponseEntity<?> selectDate(@RequestBody ReserveDto reserveDto) {
        String selectedBus = reserveDto.getBus();
        String selectedDate = reserveDto.getDate();

        List<Reserve> reserves = reserveRepository.findByBusAndDate(selectedBus, selectedDate);
        List<String> reservedSeats = reserves.stream()
               .map(reserve -> reserve.getSeat().toString())
               .collect(Collectors.toList());

        return ResponseEntity.ok(reservedSeats);
    }

    @PostMapping("/select_seat") //좌석 선택 -> 카카오페이 결제
    public ResponseEntity<?> selectSeat(@RequestBody ReserveDto reserveDto, HttpSession session) {
        tmp.setBus(reserveDto.getBus());
        tmp.setDate(reserveDto.getDate());
        tmp.setSeat(reserveDto.getSeat());
        tmp.setUser(reserveDto.getUser());

        List<Reserve> existingReservations = reserveRepository.findByBusAndDateAndSeat(reserveDto.getBus(), reserveDto.getDate(), reserveDto.getSeat());
        
        if(!existingReservations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("이미 예약된 좌석입니다.");
        }

        return ResponseEntity.ok(kakaopay.kakaoPayReady().getNext_redirect_mobile_url()); //카카오페이 결제창으로 이동하기 위한 url
    }


    @GetMapping ("/kakaoPaySuccess") //결제 성공 후 db에 저장
    public ResponseEntity<String> kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
        String bus = tmp.getBus();
        String date = tmp.getDate();
        String seat = tmp.getSeat();
        String user = tmp.getUser();

        Reserve list = new Reserve();

        list.setBus(bus);
        list.setDate(date);
        list.setSeat(seat);
        list.setUser(user);

        reserveRepository.save(list);

        return ResponseEntity.ok("좌석이 예약되었습니다.");
    }

    @PostMapping("/check_my_ticket") //예약 확인
    public ResponseEntity<?> checkMyTicket(@RequestBody ReserveDto recvPurchase) {
        List<Reserve> reserves = reserveRepository.findByUser(recvPurchase.getUser());
        List<ReserveDto> reservationInfo = new ArrayList<>();

        for(Reserve reserve : reserves) {
            ReserveDto reserveDto = new ReserveDto();
            reserveDto.setUser(reserve.getUser().toString());
            reserveDto.setBus(reserve.getBus().toString());
            reserveDto.setDate(reserve.getDate().toString());
            reserveDto.setSeat(reserve.getSeat().toString());

            reservationInfo.add(reserveDto);
        }

        while (!reservationInfo.isEmpty()) {
            return ResponseEntity.ok(reservationInfo);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("내역이 없습니다.");
    }

    @PostMapping("/login_app")
    public ResponseEntity<String> login_app(@RequestBody PersonDto recvPerson, HttpSession session) {
    	String userId = recvPerson.getUser_id();
        String password = recvPerson.getPassword();

        Person person = new Person();
        person.setUserId(userId);
        person.setUserPwd(password);

        Optional<Person> foundPerson = personRepository.findByUserId(userId);

        if (foundPerson.isPresent() && foundPerson.get().getUserPwd().equals(password)) {
            session.setAttribute("userId", userId);
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("아이디와 비밀번호를 확인하세요.");
    }
    
    @PostMapping("/signup_app")
    public ResponseEntity<String> signup_app(@RequestBody PersonDto recvPerson) {
        String userId = recvPerson.getUser_id();
        String password = recvPerson.getPassword();
        String name = recvPerson.getName();

        Person person = new Person();
        person.setUserId(userId);
        person.setUserPwd(password);
        person.setUserName(name);

        if (personRepository.findByUserId(recvPerson.getUser_id()).isPresent()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("사용중인 아이디 입니다.");
        }

        personRepository.save(person);
        return ResponseEntity.ok("회원 가입에 성공하였습니다.");
    }


}
