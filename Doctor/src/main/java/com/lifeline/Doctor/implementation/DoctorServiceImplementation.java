package com.lifeline.Doctor.implementation;

import com.lifeline.Doctor.entity.Doctor;
import com.lifeline.Doctor.exceptionhandle.CustomException;
import com.lifeline.Doctor.repository.DoctorRepository;
import com.lifeline.Doctor.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import static java.nio.file.Paths.get;
import static java.nio.file.Files.copy;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.nio.file.Path;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class DoctorServiceImplementation implements DoctorService {
    @Autowired
    private DoctorRepository repository;
    private final String UPLOAD_PATH="F:\\LIFELINE\\Back End\\Doctor\\src\\main\\resources\\static";
    @Override
    public Doctor add(Doctor doctor, MultipartFile profile) throws CustomException {
        try
        {
            String contentType = profile.getContentType();
            if(contentType.toLowerCase().equals("image/jpg") || contentType.toLowerCase().equals("image/jpeg") || contentType.toLowerCase().equals("image/png"))
            {
                String filename = new Date().getTime()+profile.getOriginalFilename();
                Path fileStorage = get(UPLOAD_PATH, filename).toAbsolutePath().normalize();
                copy(profile.getInputStream(), fileStorage,REPLACE_EXISTING);
                doctor.setPhoto(filename);
                Doctor doctor1 = repository.save(doctor);
                return doctor1;
            }
            else
            {
                throw new CustomException("Please Enter Valid Photo");
            }

        }
        catch (Exception e)
        {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public List<Doctor> listDoctors() {
        List<Doctor> getAll = repository.findAll();
        if(getAll.isEmpty())
        {
            throw new CustomException("No Record Found");
        }
        return getAll;
    }

    @Override
    public Doctor getByID(long id) {
        Optional<Doctor> isExist = repository.findById(id);
        if(isExist.isEmpty())
        {
            throw new CustomException("No Doctor Found");
        }
        return isExist.get();
    }

    @Override
    public Doctor update(long id, Doctor doctor) throws CustomException {
        try
        {
            Optional<Doctor> isExist = repository.findById(id);
            if(isExist.isEmpty())
            {
                throw new CustomException("No Doctor Found");
            }
            else
            {
                Doctor doc = isExist.get();
                return repository.save(doc);
            }
        }
        catch(CustomException c)
        {
            throw new CustomException("Not found Doctor Details");
        }
    }

    @Override
    public String delete(long id) {
        try
        {
            Optional<Doctor> isExist = repository.findById(id);
            if(isExist.isEmpty())
            {
                throw new CustomException("No Doctor Found");
            }
            else
            {
                repository.deleteById(id);
                return "Record Deleted Successfully";
            }
        }
        catch(CustomException c)
        {
            throw new CustomException("Not found Doctor Details");
        }
    }
}
