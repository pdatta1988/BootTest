package fi.test.boot.BootTest.service;

import fi.test.boot.BootTest.dto.BookingDTO;
import fi.test.boot.BootTest.model.Booking;
import fi.test.boot.BootTest.model.Resource;
import fi.test.boot.BootTest.repository.BookingRepository;
import fi.test.boot.BootTest.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    BookingRepository bookingRepository;

    public List<Resource> getAllResources(){

        return (List<Resource>)resourceRepository.findAll();
    }

    public Optional<Resource> fetchSpecificResource(Integer id){

        return resourceRepository.findById(id);
    }

    public ResponseEntity<Resource> addResource(Resource resource) {
      /*  if(resourceRepository.exists(resource.getResourceId())) {
            Resource fetchedResorce = resourceRepository.findOne(resource.getResourceId());
            fetchedResorce.setResourceName(resource.getResourceName());
            resourceRepository.save(fetchedResorce);

        }*/
       Resource addedResource = resourceRepository.save(resource);
       return  new ResponseEntity<Resource>(addedResource, HttpStatus.CREATED);
    }

    public ResponseEntity modifyResource(Resource resource) {
        if(resourceRepository.existsById(resource.getResourceId())) {
            Optional<Resource>  resourceOptional = resourceRepository.findById(resource.getResourceId());
            if(resourceOptional.isPresent()) {
                resourceRepository.save(resource);
                return ResponseEntity.status(HttpStatus.OK)
                        .body("Resource updated ");
            }
        }
         return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Resource not found ");



    }

    public ResponseEntity<Resource> deleteResource(Resource resource) {
        if(resourceRepository.existsById(resource.getResourceId()))
            resourceRepository.deleteById(resource.getResourceId());
        return new ResponseEntity<Resource>(resource, HttpStatus.OK);
    }

    public List<Booking> fetchReservations(){
       return (List<Booking>) bookingRepository.findAll();
    }

    public ResponseEntity<?>  addReservation(BookingDTO bookingDTO) throws ParseException {
        int available = bookingRepository.findAvailability(bookingDTO.getResourceId(),
                 new SimpleDateFormat("yyyy-MM-dd").parse(bookingDTO.getBookingDate()), bookingDTO.getBookingSlot());
        if(available==0) {
            Booking booking = new Booking();
            Optional<Resource> resource = fetchSpecificResource
                    (bookingDTO.getResourceId());
            booking.setBookingDate( new SimpleDateFormat("yyyy-MM-dd").parse(bookingDTO.getBookingDate()));
            booking.setBookingSlot(bookingDTO.getBookingSlot());
            booking.setResource(resource.orElse(null));
            Booking savedBooking = bookingRepository.save(booking);
            return new ResponseEntity<Booking>(savedBooking, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Resource not available",HttpStatus.OK);
        }
    }

}
