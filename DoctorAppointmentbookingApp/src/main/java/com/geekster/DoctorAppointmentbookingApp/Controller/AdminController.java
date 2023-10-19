package com.geekster.DoctorAppointmentbookingApp.Controller;

import com.geekster.DoctorAppointmentbookingApp.Models.BloodGroup;
import com.geekster.DoctorAppointmentbookingApp.Models.Doctor;
import com.geekster.DoctorAppointmentbookingApp.Models.Patient;
import com.geekster.DoctorAppointmentbookingApp.Service.DoctorService;
import com.geekster.DoctorAppointmentbookingApp.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Validated
@RestController
public class AdminController {
    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientService patientService;

    @GetMapping("patients")
    public List<Patient> getAllPatients()
    {
        return patientService.getAllPatients();
    }

    @PostMapping("doctor")
    public String addDoctor(@RequestBody Doctor newDoctor)
    {
        return doctorService.addDoctor(newDoctor);
    }

    @GetMapping("patients/bloodGroup/{bloodGroup}")
    public List<Patient> getAllPatientsByBloodGroup(@PathVariable BloodGroup bloodGroup)
    {
        return patientService.getAllPatientsByBloodGroup(bloodGroup);
    }
}
