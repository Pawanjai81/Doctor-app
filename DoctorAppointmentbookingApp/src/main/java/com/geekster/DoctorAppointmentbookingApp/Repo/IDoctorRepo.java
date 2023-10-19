package com.geekster.DoctorAppointmentbookingApp.Repo;

import com.geekster.DoctorAppointmentbookingApp.Models.Doctor;
import com.geekster.DoctorAppointmentbookingApp.Models.Qualification;
import com.geekster.DoctorAppointmentbookingApp.Models.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IDoctorRepo extends JpaRepository<Doctor,Integer> {
    List<Doctor> findByDocQualificationOrDocSpecialization(Qualification qual, Specialization spec);
}
