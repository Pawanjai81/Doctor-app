package com.geekster.DoctorAppointmentbookingApp.Repo;

import com.geekster.DoctorAppointmentbookingApp.Models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppoinmentRepo extends JpaRepository<Appointment,Integer> {
}
