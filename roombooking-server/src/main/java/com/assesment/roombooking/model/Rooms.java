package com.assesment.roombooking.model;

import java.util.List;
import java.util.Map;

public class Rooms {


        private Long id;
        private String roomName;
        private Map<String, List<String>> bookedDate;
        private List<String> amenities;

        private int seatCapacity;
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public Map<String, List<String>> getBookedDate() {
            return bookedDate;
        }

        public void setBookedDate(Map<String, List<String>> bookedDate) {
            this.bookedDate = bookedDate;
        }

        public List<String> getAmenities() {
            return amenities;
        }

        public void setAmenities(List<String> amenities) {
            this.amenities = amenities;
        }

        public int getSeatCapacity() {
            return seatCapacity;
        }

        public void setSeatCapacity(int seatCapacity) {
            this.seatCapacity = seatCapacity;
        }
    }


