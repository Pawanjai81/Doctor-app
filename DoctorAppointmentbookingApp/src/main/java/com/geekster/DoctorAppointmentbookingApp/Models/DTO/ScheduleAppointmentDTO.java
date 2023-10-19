package com.geekster.DoctorAppointmentbookingApp.Models.DTO;

import com.geekster.DoctorAppointmentbookingApp.Models.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleAppointmentDTO {
    AuthenticationInputDTO authInfo;
    Appointment appointment;
}
