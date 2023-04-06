package com.lifeline.Doctor.service;

import com.lifeline.Doctor.entity.Doctor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.util.List;

@Service
public interface DoctorService {

    Doctor add(Doctor doctor, MultipartFile profile);
    List<Doctor> listDoctors();
    Doctor getByID(long id);
    Doctor update(long id, Doctor doctor);
    String delete(long id);
}
