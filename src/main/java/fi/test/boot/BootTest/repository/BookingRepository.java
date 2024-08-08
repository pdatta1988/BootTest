package fi.test.boot.BootTest.repository;

import fi.test.boot.BootTest.model.Booking;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BookingRepository extends CrudRepository<Booking, Integer>{
    @ReadOnlyProperty
    @Query("SELECT COALESCE(COUNT(bookingId), 0) FROM Booking b WHERE b.resource.resourceId=:resourceId AND b.bookingDate=:date AND b.bookingSlot=:slot")
    public int findAvailability(@Param("resourceId")int resourceId, @Param("date")java.util.Date date, @Param("slot")String slot);

}
