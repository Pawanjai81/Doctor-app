package com.geekster.DoctorAppointmentbookingApp.Models.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInInputDTO {
    private String Email;
    private String Password;
}
