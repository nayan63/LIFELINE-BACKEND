package com.lifeline.Doctor.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lifeline.Doctor.entity.Doctor;
import com.lifeline.Doctor.exceptionhandle.CustomException;
import com.lifeline.Doctor.service.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@CrossOrigin("http://localhost:4200")
public class DoctorController {

    private static Logger LOGGER = LoggerFactory.getLogger(DoctorController.class);
    @Autowired
    private DoctorService service;

    @PostMapping(value = "/add", consumes = {"multipart/form-data"})
    public ResponseEntity<Doctor> add(@RequestPart("doctor") String doctor, @RequestPart("profile") MultipartFile profile) throws JsonProcessingException {
        try
        {
            LOGGER.info(doctor);
            ObjectMapper mapper = new ObjectMapper();
            Doctor dc = mapper.readValue(doctor,Doctor.class);
            Doctor doc = service.add(dc,profile);
            LOGGER.info("Hello");
            return new ResponseEntity<>(doc, HttpStatus.OK);
        }
        catch (JsonProcessingException c)
        {
            throw new CustomException(c.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Doctor>> list()
    {
        List<Doctor> all = service.listDoctors();
        return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Doctor> getById(@PathVariable long id)
    {
        Doctor doc = service.getByID(id);
        return new ResponseEntity<>(doc,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Doctor> update(@PathVariable long id,@RequestBody Doctor doctor)
    {
        Doctor doc = service.update(id,doctor);
        return new ResponseEntity<>(doc,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id)
    {
        String msg = service.delete(id);
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }
}
