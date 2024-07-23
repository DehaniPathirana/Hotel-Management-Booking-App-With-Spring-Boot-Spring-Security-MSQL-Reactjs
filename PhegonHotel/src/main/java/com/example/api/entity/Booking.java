package com.example.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name ="Bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "check in date  is required")
    private LocalDate checkInDate;
//    @NotNull(message = "check out date  is required")
    @Future(message = "check out date must be in the future")
    private LocalDate checkOutDate;

    @Min(value = 1, message = "No of adults must not be less that 1")
    private int numOfAdults;

    @Min(value = 0, message = "No of children must not be less that 0" )
    private int numOfChildren;
    private int totalNumOfGuest;
    private String bookingConfirmationCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    public void calculateTotalNumOfGuest() {
        this.totalNumOfGuest = this.numOfAdults + this.numOfChildren;
    }


//    public void setId(Long id) {
//        this.id = id;
//    }

//    public void setNumOfAdults(@Min(value = 1, message = "No of adults must not be less that 1") int numOfAdults) {
//        this.numOfAdults = numOfAdults;
//    }
//
//    public void setNumOfChildren(@Min(value = 0, message = "No of children must not be less that 0") int numOfChildren) {
//        this.numOfChildren = numOfChildren;
//    }


    public void setNumOfAdults(int numOfAdults) {
        this.numOfAdults = numOfAdults;
        calculateTotalNumOfGuest();
    }

    public void setNumOfChildren(int numOfChildren) {
        this.numOfChildren = numOfChildren;
        calculateTotalNumOfGuest();
    }
}
