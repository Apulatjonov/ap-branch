package com.example.apbranch.booking;

import com.example.apbranch.entity.Workplace;
import com.example.apbranch.response.Pair;
import com.example.apbranch.response.Response;
import com.example.apbranch.response.ResponseCode;
import com.example.apbranch.user.User;
import com.example.apbranch.user.UserRepository;
import com.example.apbranch.workplace.WorkplaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WorkplaceRepository workplaceRepository;

    public Pair<Response, List<Booking>> getBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        Response response = new Response();
        if (!bookings.isEmpty()) {
            response.setMessage("OK");
            response.setStatus(ResponseCode.OK);
            return new Pair<>(response, bookings);
        }
        response.setMessage("Not Found");
        response.setStatus(ResponseCode.NOT_FOUND);
        return new Pair<>(response, null);
    }

    public Pair<Response, Booking> getBooking(UUID id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        Response response = new Response();
        if (optionalBooking.isPresent()) {
            response.setStatus(ResponseCode.OK);
            response.setMessage("OK");
            Booking booking = optionalBooking.get();
            return new Pair<>(response, booking);
        }
        response.setStatus(ResponseCode.NOT_FOUND);
        response.setMessage("Not Found");
        return new Pair<>(response, null);
    }

    public Response addBooking(BookingDTO bookingDto) {
        Booking booking = new Booking();
        Workplace workplace = workplaceRepository.getById(bookingDto.getWorkplaceId());
        if (workplace == null){
            return new Response("Workplace not found!", ResponseCode.BAD_REQUEST);
        }
        booking.setWorkplace(workplace);
        booking.setFrequency(bookingDto.getFrequency());
        booking.setEndDate(bookingDto.getEndDate());
        booking.setStartDate(bookingDto.getStartDate());
        booking.setIsRecurring(bookingDto.getIsRecurring());
        User user = userRepository.getById(bookingDto.getUserId());
        if (user == null){
            return new Response("User not found!", ResponseCode.BAD_REQUEST);
        }
        booking.setUser(user);
        Optional<Workplace> optionalWorkplace = workplaceRepository.findById(bookingDto.getWorkplaceId());
        if (optionalWorkplace.isPresent()) {
            booking.setWorkplace(optionalWorkplace.get());
            bookingRepository.save(booking);
            return new Response("Successfully added", ResponseCode.CREATED);
        }
        return new Response("Bad Request", ResponseCode.BAD_REQUEST);
    }

    public Response editBooking(UUID id, BookingDTO bookingDto) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            Booking oldBooking = optionalBooking.get();
            Booking booking = new Booking();
            Workplace workplace = workplaceRepository.getById(bookingDto.getWorkplaceId());
            if (workplace == null){
                return new Response("Workplace not found!", ResponseCode.BAD_REQUEST);
            }
            booking.setWorkplace(workplace);
            booking.setFrequency(bookingDto.getFrequency());
            booking.setEndDate(bookingDto.getEndDate());
            booking.setStartDate(bookingDto.getStartDate());
            booking.setIsRecurring(bookingDto.getIsRecurring());
            User user = userRepository.getById(bookingDto.getUserId());
            if (user == null){
                return new Response("User not found!", ResponseCode.BAD_REQUEST);
            }
            booking.setUser(user);
            Optional<Workplace> optionalWorkplace = workplaceRepository.findById(bookingDto.getWorkplaceId());
            if (optionalWorkplace.isPresent()) {
                booking.setWorkplace(optionalWorkplace.get());
                bookingRepository.delete(oldBooking);
                bookingRepository.save(booking);
                return new Response("Successfully edited!", ResponseCode.CREATED);
            }
            return new Response("There is no such an workPlace", ResponseCode.BAD_REQUEST);
        }
        return new Response("There is no such an user", ResponseCode.NOT_FOUND);
    }

    public Response deleteBooking(UUID id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            bookingRepository.deleteById(id);
            return new Response("Successfully deleted", ResponseCode.OK);
        }
        return new Response("Not Found!", ResponseCode.NOT_FOUND);
    }
}
