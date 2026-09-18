package cl.curso.atenciones_service.service;

import cl.curso.atenciones_service.model.MedicalConsultation;
import cl.curso.atenciones_service.model.MedicalHistory;
import cl.curso.atenciones_service.model.Patient;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class MedicalService {

    private final List<Patient> patients;
    private final List<MedicalHistory> medicalHistories;
    private final List<MedicalConsultation> medicalConsultations;

    public MedicalService() {
       
        MedicalHistory history1 = new MedicalHistory(
                1L, 
                1L, 
                "o+", 
                List.of("Alergia a la penicilina"), 
                List.of("Hipertensión")
        );
                
        MedicalHistory history2 = new MedicalHistory(
                2L, 
                2L, 
                "o-", 
                List.of(), 
                List.of("Diabetes")
        );
        MedicalHistory history3 = new MedicalHistory(
                3L, 
                3L, 
                "o+", 
                List.of("Alergia a la penicilina"), 
                List.of("Asma", "Hipertensión", "Diabetes")
        );
        MedicalHistory history4 = new MedicalHistory(
                4L, 
                4L, 
                "o-", 
                List.of(), 
                List.of()
        );
        MedicalHistory history5 = new MedicalHistory(
                5L, 
                5L, 
                "o+", 
                List.of("Alergia a la Aspirina", "Alergia al paracetamol","Alergia a la penicilina"), 
                List.of("Hipertensión", "hipotiroidismo", "Diabetes")
        );

        medicalHistories = List.of(
        history1,
        history2,
        history3,
        history4,
        history5
        );
        /*long idMedicalConsultation, 
        long idPatient, 
        LocalDate dateMedicalConsultation, 
        String professionalRut,
        String professionalName,
        String professionalLastName,
        String professionalSpecialty,
        String reasonMedicalConsultation, 
        String diagnosis, 
        String treatment) { */

        MedicalConsultation consultation1 = new MedicalConsultation(
                1L, 
                1L,
                LocalDate.of                                (2023, 6, 15),
                "17.777.777-7",
                "Dr. Juan ",
                "Pérez",
                "Cardiología",
                "Consulta de rutina",
                "Se detectó hipertensión arterial",
                "Se recomienda dieta baja en sodio y ejercicio regular"
        );
        MedicalConsultation consultation2 = new MedicalConsultation(
                2L, 
                2L,
                LocalDate.of               (2023, 6, 20), 
                "18.888.888-8",
                "Dra. María",
                "González",
                "Endocrinología",
                "Control de diabetes",
                "Se ajustó la dosis de insulina",
                "Se indicó seguimiento mensual"
        );
        MedicalConsultation consultation3 = new MedicalConsultation(
                3L, 
                3L, 
                LocalDate.of(2023, 6, 25), 
                "19.999.999-9",
                "Dr. Pedro",
                "Ramírez",
                "Neumología",
                "Consulta por dificultad respiratoria",
                "Se diagnosticó Bronquitis crónica",
                "Se prescribió inhalador por 7 días y se indicó evitar alérgenos"
        );
        MedicalConsultation consultation4 = new MedicalConsultation(
                4L, 
                4L, 
               LocalDate.of(2023, 6, 30), 
                "20.000.000-0",
                "Dra. Laura",
                "Sánchez",
                "Gastroenterología",
                "Consulta por dolor abdominal",
                "Se diagnosticó gastritis aguda",
                "Se indicó dieta blanda y se prescribió medicamento antiácido"
        );
        MedicalConsultation consultation5 = new MedicalConsultation(
                5L, 
                5L, 
                LocalDate.of(2023, 7, 5), 
                "21.111.111-1",
                "Dr. Carlos",
                "Torres",
                "Neurología",
                "Consulta por dolor de cabeza",
                "Se diagnosticó migraña",
                "Se prescribió analgésico y se indicó descanso en un entorno oscuro"
        );
        medicalConsultations = List.of(
                consultation1,
                consultation2,
                consultation3,
                consultation4,
                consultation5
        );
       
        /*public Patient(
        Long idPatient, 
        String namePatient, 
        String lastNamePatient, 
        String rutPatient, 
        String emailPatient, 
        String phonePatient, 
        String addressPatient, 
        String cityPatient, 
        String regionPatient, 
        String previsionPatient,
        LocalDate birthDatePatient, 
        String emergencyContactPatient,
        MedicalHistory medicalHistory,
        List<MedicalConsultation> medicalConsultations) { */
        Patient patient1 = 
        new Patient(
        1L,
        "Elena",
        "Muñoz",
        "12.345.678-5",
        "elena@correo.cl",
        "+56944444444",
        "Santiago Centro 1234",
        "Santiago Centro",
        "Metropolitana",
        "fonasa",
        LocalDate.of(1985, 3, 15),
        "+56912345678",
        history1,
        List.of(consultation1)
);

Patient patient2 = 
    new Patient(
            2L,
            "Felipe",
            "Díaz",
            "9.876.543-3",
            "felipe@correo.cl",
            "+56955555555",
            "Ñuñoa",
            "Ñuñoa",
            "Metropolitana",
            "fonasa",
            LocalDate.of(1975, 9, 23),
            "+56987654321",
            history2,
            List.of(consultation2)
    );

        


Patient patient3 = new Patient(
        3L,
        "Gabriela",
        "Rojas",
        "11.111.111-1",
        "gabriela@correo.cl",
        "+56966666666",
        "Maipú",
        "Maipú",
        "Metropolitana",
        "fonasa",
        LocalDate.of(2001, 1, 8),
        "+56911111111",
        history3,
        List.of(consultation3)
);

Patient patient4 = new Patient(
        4L,
        "Héctor",
        "Vargas",
        "22.222.222-2",
        "hector@correo.cl",
        "+56977777777",
        "La Florida",
        "La Florida",
        "Metropolitana",
        "fonasa",
        LocalDate.of(1990, 5, 10),
        "+56922222222",
        history4,
        List.of(consultation4)
    );
    patients = List.of(
        patient1,
        patient2,
        patient3,
        patient4
    );

    }
    public List<Patient> findAllPatients() {
    return patients;
}

public Patient findPatientById(long id) {
    return patients.stream()
            .filter(patient -> patient.getIdPatient() == id)
            .findFirst()
            .orElseThrow(() ->
                    new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "su paciente es imaginario, este numero no existe " + id
                    )
            );
}

public List<MedicalHistory> findAllMedicalHistories() {
    return medicalHistories;
}

public List<MedicalConsultation> findAllMedicalConsultations() {
    return medicalConsultations;
}

public MedicalHistory findHistoryByPatientId(long patientId) {
    findPatientById(patientId);

    return medicalHistories.stream()
            .filter(history -> history.getIdPatient() == patientId)
            .findFirst()
            .orElseThrow(() ->
                    new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "ya le dije, su paciente no existe " + patientId
                    )
            );
}

public List<MedicalConsultation> findConsultationsByPatientId(long patientId) {
    findPatientById(patientId);

    return medicalConsultations.stream()
            .filter(consultation ->
                    consultation    .getIdPatient() == patientId
            )
            .toList();
}
}
