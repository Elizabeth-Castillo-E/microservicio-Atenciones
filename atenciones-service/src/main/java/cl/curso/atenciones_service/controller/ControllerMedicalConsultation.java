package cl.curso.atenciones_service.controller;

import cl.curso.atenciones_service.model.MedicalConsultation;
import cl.curso.atenciones_service.model.MedicalHistory;
import cl.curso.atenciones_service.model.Patient;
import cl.curso.atenciones_service.service.MedicalService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
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
    public List<Patient> getPatients() { return medicalService.getAllPatients(); }

    @GetMapping("/patients/{id}")
    public Patient getPatientById(@PathVariable @Positive(message = "El ID debe ser mayor que cero") Long id) {
        return medicalService.getPatientById(id).orElseThrow(() -> notFound("Paciente", id));
    }

    @PostMapping("/patients")
    public ResponseEntity<Patient> createPatient(@Valid @RequestBody Patient patient) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicalService.savePatient(patient));
    }

    @PutMapping("/patients/{id}")
    public Patient updatePatient(@PathVariable @Positive(message = "El ID debe ser mayor que cero") Long id,
                                 @Valid @RequestBody Patient patient) {
        return medicalService.updatePatient(id, patient);
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable @Positive(message = "El ID debe ser mayor que cero") Long id) {
        medicalService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/histories")
    public List<MedicalHistory> getHistories() { return medicalService.getAllMedicalHistories(); }

    @GetMapping("/histories/{id}")
    public MedicalHistory getHistoryById(@PathVariable @Positive(message = "El ID debe ser mayor que cero") Long id) {
        return medicalService.getMedicalHistoryById(id).orElseThrow(() -> notFound("Historia clínica", id));
    }

    @PostMapping({"/patients/{patientId}/history", "/patients/{patientId}/histories"})
    public ResponseEntity<MedicalHistory> createHistory(@PathVariable @Positive(message = "El ID debe ser mayor que cero") Long patientId,
                                                         @Valid @RequestBody MedicalHistory history) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicalService.saveMedicalHistory(patientId, history));
    }

    @GetMapping("/patients/{patientId}/histories")
    public List<MedicalHistory> getPatientHistories(
            @PathVariable @Positive(message = "El ID debe ser mayor que cero") Long patientId) {
        return medicalService.getMedicalHistoriesByPatientId(patientId);
    }

    @PutMapping("/histories/{id}")
    public MedicalHistory updateHistory(@PathVariable @Positive(message = "El ID debe ser mayor que cero") Long id,
                                        @Valid @RequestBody MedicalHistory history) {
        return medicalService.updateMedicalHistory(id, history);
    }

    @PutMapping("/patients/{patientId}/histories/{historyId}")
    public MedicalHistory updatePatientHistory(
            @PathVariable @Positive(message = "El ID del paciente debe ser mayor que cero") Long patientId,
            @PathVariable @Positive(message = "El ID del historial debe ser mayor que cero") Long historyId,
            @Valid @RequestBody MedicalHistory history) {
        return medicalService.updateMedicalHistory(patientId, historyId, history);
    }

    @DeleteMapping("/histories/{id}")
    public ResponseEntity<Void> deleteHistory(@PathVariable @Positive(message = "El ID debe ser mayor que cero") Long id) {
        medicalService.deleteMedicalHistory(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/patients/{patientId}/histories/{historyId}")
    public ResponseEntity<Void> deletePatientHistory(
            @PathVariable @Positive(message = "El ID del paciente debe ser mayor que cero") Long patientId,
            @PathVariable @Positive(message = "El ID del historial debe ser mayor que cero") Long historyId) {
        medicalService.deleteMedicalHistory(patientId, historyId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/attentions")
    public List<MedicalConsultation> getAttentions() { return medicalService.getAllMedicalConsultations(); }

    @GetMapping("/attentions/{id}")
    public MedicalConsultation getAttentionById(@PathVariable @Positive(message = "El ID debe ser mayor que cero") Long id) {
        return medicalService.getMedicalConsultationById(id).orElseThrow(() -> notFound("Consulta médica", id));
    }

    @PostMapping("/patients/{patientId}/attentions")
    public ResponseEntity<MedicalConsultation> createAttention(@PathVariable @Positive(message = "El ID debe ser mayor que cero") Long patientId,
                                                                @Valid @RequestBody MedicalConsultation consultation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicalService.saveMedicalConsultation(patientId, consultation));
    }

    @GetMapping("/patients/{patientId}/attentions")
    public List<MedicalConsultation> getPatientAttentions(
            @PathVariable @Positive(message = "El ID debe ser mayor que cero") Long patientId) {
        return medicalService.getMedicalConsultationsByPatientId(patientId);
    }

    @GetMapping("/patients/{patientId}/attentions/{attentionId}")
    public MedicalConsultation getPatientAttention(
            @PathVariable @Positive(message = "El ID del paciente debe ser mayor que cero") Long patientId,
            @PathVariable @Positive(message = "El ID de la consulta debe ser mayor que cero") Long attentionId) {
        return medicalService.getMedicalConsultationByPatientId(patientId, attentionId);
    }

    @PutMapping("/attentions/{id}")
    public MedicalConsultation updateAttention(@PathVariable @Positive(message = "El ID debe ser mayor que cero") Long id,
                                               @Valid @RequestBody MedicalConsultation consultation) {
        return medicalService.updateMedicalConsultation(id, consultation);
    }

    @PutMapping("/patients/{patientId}/attentions/{attentionId}")
    public MedicalConsultation updatePatientAttention(
            @PathVariable @Positive(message = "El ID del paciente debe ser mayor que cero") Long patientId,
            @PathVariable @Positive(message = "El ID de la consulta debe ser mayor que cero") Long attentionId,
            @Valid @RequestBody MedicalConsultation consultation) {
        return medicalService.updateMedicalConsultation(patientId, attentionId, consultation);
    }

    @DeleteMapping("/attentions/{id}")
    public ResponseEntity<Void> deleteAttention(@PathVariable @Positive(message = "El ID debe ser mayor que cero") Long id) {
        medicalService.deleteMedicalConsultation(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/patients/{patientId}/attentions/{attentionId}")
    public ResponseEntity<Void> deletePatientAttention(
            @PathVariable @Positive(message = "El ID del paciente debe ser mayor que cero") Long patientId,
            @PathVariable @Positive(message = "El ID de la consulta debe ser mayor que cero") Long attentionId) {
        medicalService.deleteMedicalConsultation(patientId, attentionId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new LinkedHashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(Map.of("mensaje", "La solicitud contiene campos inválidos", "errores", errors));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleStatus(ResponseStatusException exception) {
        return ResponseEntity.status(exception.getStatusCode()).body(Map.of(
            "mensaje", exception.getReason() == null ? "Error en la solicitud" : exception.getReason(),
            "estado", exception.getStatusCode().value()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleUnexpectedError(Exception exception) {
        Throwable cause = exception;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        String message;
        if (cause.getMessage() != null && cause.getMessage().contains("ORA-00001")) {
            message = "El registro ya existe. Verifique que los datos no estén duplicados.";
        } else {
            message = cause.getMessage() == null ? "Error interno sin detalle" : cause.getMessage();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
            "mensaje", message,
            "tipo", cause.getClass().getSimpleName()
        ));
    }

    private ResponseStatusException notFound(String resource, Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, resource + " no encontrado: " + id);
    }
}
