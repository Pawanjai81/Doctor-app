# <h1 align="center"> Doctor Appointment booking Application </h1>
___ 
<p align="center">
    <img alt="Java" src="https://img.shields.io/badge/Java->=8-darkblue.svg" />
    <img alt="Maven" src="https://img.shields.io/badge/maven-4.0-brightgreen.svg" />
    <img alt="Spring Boot" src="https://img.shields.io/badge/Spring Boot-3.1.3-brightgreen.svg" />
    <img alt="H2 Database" src="https://img.shields.io/badge/H2%20Database-embedded-orange.svg" />
    <img alt = "GPL v3" src="https://img.shields.io/badge/License-GPLv3-blue.svg" />
    
</p>


---

<p align="left">

## Overview

The doctor Appoint booking Application is a Spring Boot-based system designed for patient to book the appintment to the doctor. It provides a set of RESTful API endpoints for tasks such as creating, retrieving, updating, and deleting patient and doctor records. This application offers features for patient to filtering the doctor as required specilist doctor to patient.

## Technologies Used

- **Framework:** Spring Boot
- **Language:** Java
- **Build Tool:** Maven
- **Database:** H2 (embedded)

## Data Flow

### Controller

The Controller layer handles incoming HTTP requests and delegates them to the appropriate services. It defines API endpoints for various operations:

1. **Admin controller:**
	-@GetMapping("patients")
	-@PostMapping("doctor")
	-@GetMapping("patients/bloodGroup/{bloodGroup}")
2. **Doctor controller:** 
	- @GetMapping("doctors")
	-@GetMapping("doctor/{id}")
3. **Patient controller:** 
	-@PostMapping("patient/signup")
	-@PostMapping("patient/signIn")
	-@DeleteMapping("patient/signOut")
	-@PostMapping("patient/appointment/schedule")
	-@DeleteMapping("patient/appointment/{appointmentId}/cancel")\
	-@GetMapping("doctors/qualification/{qual}/or/specialization/{spec}")

```java
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
    }```
}
```
```java
@RestController
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @GetMapping("doctors")
    public List<Doctor> getAllDoctors(@Valid @RequestBody AuthenticationInputDTO authInfo)
    {
        return doctorService.getAllDoctors(authInfo);
    }


    @GetMapping("doctor/{id}")
    public Doctor getDoctorById(@PathVariable Integer id)
    {
        return doctorService.getDoctorById(id);
    }

}
```

```java
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

### Services

The Services layer implements business logic, data processing, and interactions with the data repository. Key responsibilities include:

- Validating input data.
- Performing CRUD operations on patients and doctor's data.
- Handling the shedule do doctor and patient.

```java
@Service
public class RoomService {
    @Autowired
    IRoomRepo roomRepo;

    // Get all rooms
    public Iterable<Room> getAllRooms() {
        return roomRepo.findAll();
    }

    // ...
}
```

### Repository

The Repository layer manages data access to the embedded H2 database. It handles database operations, including Create, Read, Update, and Delete (CRUD) for room data.

```java
@Repository
public interface IAppoinmentRepo extends JpaRepository<Appointment,Integer> {
}
```

```java
@Repository
public interface IDoctorRepo extends JpaRepository<Doctor,Integer> {
    List<Doctor> findByDocQualificationOrDocSpecialization(Qualification qual, Specialization spec);
}
```

```java
@Repository
public interface IPatientRepo extends JpaRepository<Patient,Integer> {
    Patient findFirstByPatientEmail(String newEmail);
    List<Patient> findByPatientBloodGroup(BloodGroup bloodGroup);
}
```

```java
@Repository
public interface IPTokenRepo extends JpaRepository<PatientAuthenticationToken, Integer> {
    PatientAuthenticationToken findFirstByTokenValue(String tokenValue);
}
```

## Database Design

The project's database design includes tables for docotor, patient, admin, appointment, patient Authentication with Atributes such as:


### admin Table
The "Admin" table stores admin-related information, including admin IDs, name,  Email, and password . Timestamps for record creation and modification are not included in this embedded database.

| Column Name          | Data Type    | 
| -------------------- | ------------ | 
| adminId              | BIGINT       | 
| adminName            | VARCHAR      | 
| adminEmail           | VARCHAR      | 
| adminPassword        | VARCHAR      | 


### Appointment Table
The "Appointment" table stores appointment-related information, including appointment IDs, appointment Desc,  Appointment Time ,appont Shedule. Timestamps for record creation and modification are not included in this embedded database.

| Column Name          | Data Type    | 
| -------------------- | ------------ | 
| appointmentId        | BIGINT       | 
| appointmentDesc      | VARCHAR      | 
| appCreationTime      | LOCALDATETIME| 
| appScheduleTime      | LOCALDATETIME      | 

### Doctor Table
The "Appointment" table stores Doctor's-related information, including Doctor IDs, Doctor Name,  Doctor fees ,docSpecialization, docQualification, docContact. Timestamps for record creation and modification are not included in this embedded database.

| Column Name        | Data Type            | 
| ------------------ | -------------------- | 
| docId              | BIGINT               | 
| docName            | VARCHAR              | 
| docFee             | INT                  | 
| docSpecialization  | Specialization(ENUM) | 
| docQualification   | Qualification        |
| docContact         | VARCHAR              |  

### patient Table
The "Appointment" table stores patient-related information, including patient Ids, patientName ,  patientEmail ,patientPassword, patientGender, patientBloodGroup, patientContact, patientDateOFBirth. Timestamps for record creation and modification are not included in this embedded database.

| Column Name        | Data Type            | 
| ------------------ | -------------------- | 
| patientId          | BIGINT               | 
| patientName        | VARCHAR              | 
| patientEmail       | VARCHAR              | 
| patientPassword   | VARCHAR              | 
| patientGender      | Gender(ENUM)         |
| patientBloodGroup  | BloodGroup(ENUM)     |  
| patientContact     | VARCHAR              | 
| patientDateOFBirth | LocalDateTime        | 


## Project Summary

The Hotel Management Application is a Spring Boot-based system designed for efficient room data management. It offers a set of RESTful API endpoints for various room-related operations, including creating, retrieving, updating, and deleting room records.

### Key Technologies Used

- **Framework:** Spring Boot
- **Language:** Java
- **Build Tool:** Maven
- **Database:** H2 (embedded)

### Data Flow

#### Controller

The Controller layer handles incoming HTTP requests and routes them to the appropriate services. It defines API endpoints for operations such as adding, retrieving, updating, and deleting room records.

#### Services

The Services layer implements core business logic and data processing, including input validation, CRUD operations on room data, and handling price updates and availability tracking.

#### Repository

The Repository layer manages data access to the embedded in memory mysql database, performing Create, Read, Update, and Delete (CRUD) operations for room data.




### Key Features

- RESTful API endpoints for admincontroller, patient controller and Doctor controller.
- Data validation to ensure data integrity.
- Clean code separation with a layered architecture (Controller, Services, Repository).
- Efficient data storage and retrieval using an embedded mysql.

The Hotel Management Application serves as a practical example of Spring Boot application development, demonstrating best practices in API design and patient,and doctor data management. It offers a solid foundation for building and extending doctor appointment booking systems in various applications.



<!-- Acknowledgments -->
## Acknowledgments
Special thanks to the Spring Boot and Java communities for providing valuable tools and resources.

<!-- Contact -->
## Contact
For questions or feedback, please contact [Pawan jaiswal](mailto:pawanjai81@gmail.com).


