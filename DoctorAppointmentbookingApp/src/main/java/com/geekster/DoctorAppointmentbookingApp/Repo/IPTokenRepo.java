package com.geekster.DoctorAppointmentbookingApp.Repo;

import com.geekster.DoctorAppointmentbookingApp.Models.PatientAuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPTokenRepo extends JpaRepository<PatientAuthenticationToken, Integer> {
    PatientAuthenticationToken findFirstByTokenValue(String tokenValue);
}
