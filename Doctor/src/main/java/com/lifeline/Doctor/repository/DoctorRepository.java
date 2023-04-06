package com.lifeline.Doctor.repository;

import com.lifeline.Doctor.entity.Doctor;
import com.lifeline.Doctor.service.DoctorService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}
