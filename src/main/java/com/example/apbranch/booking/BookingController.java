package com.example.apbranch.booking;

import com.example.apbranch.response.Pair;
import com.example.apbranch.response.Response;
import com.example.apbranch.response.ResponseCode;
import com.example.apbranch.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private final BookingService bookingService;

    @Autowired
    private final UserService userService;

    public BookingController(BookingService bookingService, UserService userService) {
        this.bookingService = bookingService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBooking(@PathVariable UUID id, @PathVariable UUID userId) {
        Booking booking = bookingService.getBooking(id).u;
        if (booking.getUser().getId().equals(userId)) {
            return ResponseEntity.status(ResponseCode.FORBIDDEN).body("Forbidden");
        }
        Pair<Response, Booking> mainResponse = bookingService.getBooking(id);
        Response response = mainResponse.r;
        if (response.getStatus().equals(ResponseCode.OK))
            return ResponseEntity.ok(response.getMessage());
        return ResponseEntity.status(ResponseCode.NOT_FOUND).body(response.getMessage());
    }

    @PostMapping
    public ResponseEntity<?> addBooking(@RequestBody BookingDTO BookingDto) {
        Response response = bookingService.addBooking(BookingDto);
        if (response.getStatus().equals(ResponseCode.CREATED))
            return ResponseEntity.ok(response.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response.getMessage());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editMap(@PathVariable UUID id, @RequestBody BookingDTO BookingDto) {
        Response response = bookingService.editBooking(id, BookingDto);
        if (response.getStatus().equals(ResponseCode.CREATED))
            return ResponseEntity.ok(response.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response.getMessage());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMap(@PathVariable UUID id) {
        Response response = bookingService.deleteBooking(id);
        if (response.getStatus().equals(ResponseCode.OK))
            return ResponseEntity.ok(response.getMessage());
        return ResponseEntity.status(ResponseCode.NOT_FOUND).body(response.getMessage());
    }
}
