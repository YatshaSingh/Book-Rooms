package com.assesment.roombooking.services;

import com.assesment.roombooking.model.Rooms;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class BookRoom {

    private List<Rooms> rooms;
    public List<String> slots = new ArrayList<>(Arrays.asList(
            "10:00-10:30", "10:30-11:00", "11:00-11:30", "11:30-12:00",
            "12:00-12:30", "12:30-01:00", "01:00-01:30",
            "01:30-02:00", "02:00-2:30", "02:30-03:00", "03:00-03:30", "03:30-04:00",
            "04:00-04:30", "04:30-05:00", "05:00-05:30", "05:30-06:00",
            "06:00-06:30", "06:30-07:00"
    ));


    public List<Rooms> getRooms() {
        return rooms;
    }

    @PostConstruct
    public void init() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            rooms = objectMapper.readValue(new File("C:\\Users\\singh\\Downloads\\roombooking\\roombooking\\src\\main\\resources\\roomdetails.json"), new TypeReference<List<Rooms>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String bookRoom(String RoomName,String Date , List<String> TimeSlot){
            for (Rooms room : rooms) {
                if (room.getRoomName().equals(RoomName)) {
                    Map<String, List<String>> bookedDate = room.getBookedDate();
                    List<String> timeSlots = bookedDate.getOrDefault(Date, new ArrayList<>());
                    TimeSlot.stream().forEach(slots->{
                        timeSlots.add(slots);
                    });

                    bookedDate.put(Date, timeSlots);
                    room.setBookedDate(bookedDate);
                }
            }

            writeRoomsToFile();
            return "booked!!";
        }


        private void writeRoomsToFile() {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                objectMapper.writeValue(new File("C:\\Users\\singh\\Downloads\\roombooking\\roombooking\\src\\main\\resources\\roomdetails.json"), rooms);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public Map<String, Map<String, Object>> SearchByDate(String Date){
        Map<String, Map<String, Object>> availableDateTimeSlots = new HashMap<>();
        Map<String, Object> roomDetails = new HashMap<>();
        for (Rooms room : rooms) {
            roomDetails.put("seatCapacity", room.getSeatCapacity());
            roomDetails.put("amenities", room.getAmenities());


            if (room.getBookedDate().containsKey(Date)) {
                Map<String, List<String>> bookedDate = room.getBookedDate();

                List<String> timeSlots = bookedDate.getOrDefault(Date, new ArrayList<>());
                List<String> temp = new ArrayList<>();

                slots.stream().forEach(timeSlot -> {
                            if (!timeSlots.contains(timeSlot)) {
                                temp.add(timeSlot);
                            }
                        });
                roomDetails.put("slots", temp);
                availableDateTimeSlots.put(room.getRoomName(), roomDetails);
            }
            else{
                roomDetails.put("slots", slots); // Available time slots
                availableDateTimeSlots.put(room.getRoomName(),roomDetails );
                }
            };
        return availableDateTimeSlots;
    }

}
