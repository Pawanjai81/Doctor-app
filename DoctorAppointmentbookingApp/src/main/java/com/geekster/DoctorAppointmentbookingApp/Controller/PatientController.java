package com.geekster.DoctorAppointmentbookingApp.Controller;

import com.geekster.DoctorAppointmentbookingApp.Models.DTO.AuthenticationInputDTO;
import com.geekster.DoctorAppointmentbookingApp.Models.DTO.ScheduleAppointmentDTO;
import com.geekster.DoctorAppointmentbookingApp.Models.DTO.SignInInputDTO;
import com.geekster.DoctorAppointmentbookingApp.Models.Doctor;
import com.geekster.DoctorAppointmentbookingApp.Models.Patient;
import com.geekster.DoctorAppointmentbookingApp.Models.Qualification;
import com.geekster.DoctorAppointmentbookingApp.Models.Specialization;
import com.geekster.DoctorAppointmentbookingApp.Service.AppointmentService;
import com.geekster.DoctorAppointmentbookingApp.Service.DoctorService;
import com.geekster.DoctorAppointmentbookingApp.Service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Validated
@RestController
public class PatientController {
    @Autowired
    PatientService patientService;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    DoctorService doctorService;


    //sign up
    @PostMapping("patient/signup")
    public String patientSignUp(@Valid @RequestBody Patient patient)
    {
        return patientService.patientSignUp(patient);
    }



    //sign in
    @PostMapping("patient/signIn")
    public String patientSignIn(@RequestBody SignInInputDTO signInInput)
    {
        return patientService.patientSignIn(signInInput);
    }


    //sign out
    @DeleteMapping("patient/signOut")
    public String patientSignOut(@RequestBody AuthenticationInputDTO authInfo)
    {
        return patientService.patientSignOut(authInfo);
    }


    //schedule an appointment

    @PostMapping("patient/appointment/schedule")
    public String scheduleAppointment(@RequestBody ScheduleAppointmentDTO scheduleAppointmentDTO)
    {
        return appointmentService.scheduleAppointment(scheduleAppointmentDTO.getAuthInfo(),scheduleAppointmentDTO.getAppointment());
    }

    @DeleteMapping("patient/appointment/{appointmentId}/cancel")
    public String cancelAppointment(@RequestBody AuthenticationInputDTO authInfo, @PathVariable Integer appointmentId)
    {
        return appointmentService.cancelAppointment(authInfo,appointmentId);
    }

    @GetMapping("doctors/qualification/{qual}/or/specialization/{spec}")
    public List<Doctor> getDoctorsByQualificationOrSpec(@RequestBody AuthenticationInputDTO authInfo, @PathVariable Qualification qual, @PathVariable Specialization spec)
    {
        return doctorService.getDoctorsByQualificationOrSpec(authInfo,qual,spec);
    }
}
