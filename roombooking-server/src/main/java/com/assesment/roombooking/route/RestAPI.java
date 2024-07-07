package com.assesment.roombooking.route;

import com.assesment.roombooking.services.BookRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/")
public class RestAPI {

    @Autowired
    BookRoom bookRoom;

    @GetMapping("/getRooms")
    public List connected(){
        return bookRoom.getRooms();
    }

    @GetMapping("/CheckAvailability")
    public Map<String, Map<String, Object>> checkAvailable(@RequestParam String Date){
        return bookRoom.SearchByDate(Date);
    }

    @PostMapping("/bookroom")
    public String bookRoom(@RequestParam String RoomName,@RequestParam String Date , @RequestParam List<String> TimeSlot){
        return bookRoom.bookRoom(RoomName,Date,TimeSlot);
    }

}
