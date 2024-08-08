package fi.test.boot.BootTest.controller;

import fi.test.boot.BootTest.dto.BookingDTO;
import fi.test.boot.BootTest.dto.ReservationsDTO;
import fi.test.boot.BootTest.model.Booking;
import fi.test.boot.BootTest.model.Resource;
import fi.test.boot.BootTest.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fi/test/v1")
public class Controller {
    @Autowired
    private Service service;

    @GetMapping("/resources")
    public @ResponseBody
    List<Resource> getAvailableResource(){
        return service.getAllResources();
    }
    @GetMapping("/resources/{id}")
    public @ResponseBody
    Optional<Resource> fetchSpecificResource(@PathVariable Integer id) {
        return service.fetchSpecificResource(id);
    }
    @PostMapping("/resources")
    public @ResponseBody
    ResponseEntity<Resource> addResource(@RequestBody Resource resource){
        return service.addResource(resource);
    }

    @PatchMapping(value = "/resources", consumes = {"application/json"})
    ResponseEntity modifyResource(@RequestBody Resource resource){
        return service.modifyResource(resource);
    }

    @DeleteMapping("/resources")
    public @ResponseBody
    ResponseEntity<Resource> deleteResource(@RequestBody Resource resource){
        return service.deleteResource(resource);
    }

    @GetMapping("/reservations")
    public @ResponseBody
    List<ReservationsDTO> fetchAllReservations(){
        List<ReservationsDTO> reservationsDTOS = new ArrayList<>();
        List<Booking> bookingList = service.fetchReservations();
        bookingList.forEach(booking -> {
            ReservationsDTO reservationsDTO = new ReservationsDTO();
            reservationsDTO.setBookingDate(String.valueOf(booking.getBookingDate()));
            reservationsDTO.setBookingSlot(booking.getBookingSlot());
            reservationsDTO.setResourceName(booking.getResource().getResourceName());
            reservationsDTOS.add(reservationsDTO);
        });
        return reservationsDTOS;

    }

    @PostMapping("/reservations")
    public @ResponseBody
    ResponseEntity<?> addNewReservation(@RequestBody BookingDTO bookingDTO) throws ParseException {
        return service.addReservation(bookingDTO);
    }


}