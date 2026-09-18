package cl.curso.atenciones_service.controller;
import cl.curso.atenciones_service.model.MedicalConsultation ;
import cl.curso.atenciones_service.model.MedicalHistory;
import cl.curso.atenciones_service.model.Patient;
import cl.curso.atenciones_service.service.MedicalService;
import jakarta.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/medical-service")

public class ControllerMedicalConsultation {
    
     private final MedicalService medicalService;

    public ControllerMedicalConsultation(MedicalService medicalService) {
        this.medicalService = medicalService;
    }

    @GetMapping("/patients")
    public List<Patient> getPatients() {
        return medicalService.findAllPatients();
    }

   @GetMapping("/patients/{id}")
public ResponseEntity<?> getPatientById(
        @PathVariable("id")
        @Positive(message = "El ID debe ser mayor que cero")
        long id
) {
    Patient patient = medicalService.findPatientById(id);

    if (patient != null) {
        return ResponseEntity.ok(patient);
    }

    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(Map.of(
            "mensaje", "Lo sentimos, creo que su paciente es imaginario, no lo encontramos",
            "id", id
        ));
    }

    @GetMapping("/histories")
    public List<MedicalHistory> getHistories() {
        return medicalService.findAllMedicalHistories();
    }

    @GetMapping("/attentions")
    public List<MedicalConsultation> getAttentions() {
        return medicalService.findAllMedicalConsultations();
    }

    @GetMapping("/patients/{id}/history")
public ResponseEntity<?> getPatientHistory(
        @PathVariable("id")
        @Positive(message = "El ID debe ser mayor que cero")
        long id
) {
    MedicalHistory history = medicalService.findHistoryByPatientId(id);

    if (history != null) {
        return ResponseEntity.ok(history);
    }

    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(Map.of(
            "mensaje", "No se encontró una historia clínica para el paciente",
            "id", id
        ));
}
    @GetMapping("/patients/{id}/attentions")
    public ResponseEntity<?> getPatientAttentions(
            @PathVariable("id")
            @Positive(message = "El ID debe ser mayor que cero")
            long id
    ) {
        List<MedicalConsultation> consultations = medicalService.findConsultationsByPatientId(id);

        if (!consultations.isEmpty()) {
            return ResponseEntity.ok(consultations);
        }

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(Map.of(
                "mensaje", "No se encontraron consultas médicas para el paciente",
                "id", id
            ));
    }
@ExceptionHandler(ResponseStatusException.class)
public ResponseEntity<Map<String, Object>> handleNotFound(
        ResponseStatusException exception
) {
    return ResponseEntity
        .status(exception.getStatusCode())
        .body(Map.of(
            "mensaje", exception.getReason() != null
                ? exception.getReason()
                : "No se encontró el recurso solicitado",
            "estado", exception.getStatusCode().value()
        ));
}
    
}

