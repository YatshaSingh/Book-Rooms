package com.assesment.roombooking;

import com.assesment.roombooking.model.Rooms;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class RoombookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoombookingApplication.class, args);
	}

}
