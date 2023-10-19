package com.geekster.DoctorAppointmentbookingApp.Repo;

import com.geekster.DoctorAppointmentbookingApp.Models.BloodGroup;
import com.geekster.DoctorAppointmentbookingApp.Models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface IPatientRepo extends JpaRepository<Patient,Integer> {


    Patient findFirstByPatientEmail(String newEmail);

    List<Patient> findByPatientBloodGroup(BloodGroup bloodGroup);
}

