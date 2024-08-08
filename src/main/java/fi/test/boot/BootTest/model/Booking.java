package fi.test.boot.BootTest.model;

import jakarta.persistence.*;


import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "booking")
public class Booking implements Serializable {

    @Id
    @Column(name = "booking_id", nullable=false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingId;

    @Column(name = "booking_date", nullable=false)
    private Date bookingDate;
    @Column(name = "booking_slot", nullable=false)
    private String bookingSlot;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "resource_id")
    private Resource resource;

    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    public String getBookingSlot() {
        return bookingSlot;
    }
    public void setBookingSlot(String bookingSlot) {
        this.bookingSlot = bookingSlot;
    }
    public Resource getResource() {
        return resource;
    }
    public void setResource(Resource resource) {
        this.resource = resource;
    }
    public Date getBookingDate() {
        return (Date) bookingDate.clone();
    }
    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate!=null?(Date) bookingDate.clone():null;
    }
    @Override
    public String toString() {
        return "Booking [bookingId=" + bookingId + ", bookingSlot=" + bookingSlot + ", resource=" + resource + "]";
    }




}