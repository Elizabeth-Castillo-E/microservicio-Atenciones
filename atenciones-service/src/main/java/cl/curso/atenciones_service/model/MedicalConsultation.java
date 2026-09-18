package cl.curso.atenciones_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

@Entity
@Table(name = "MEDICAL_CONSULTATIONS")
public class MedicalConsultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MEDICAL_CONSULTATION")
    private Long idMedicalConsultation;

    @Column(name = "CONSULTATION_DATE", nullable = false)
    @NotNull(message = "La fecha de atención es obligatoria")
    private LocalDate dateMedicalConsultation;

    @Column(name = "PROFESSIONAL_RUT", nullable = false, length = 12)
    @NotBlank(message = "El RUT del profesional es obligatorio")
    @Pattern(
        regexp = "^\\d{1,2}\\.\\d{3}\\.\\d{3}-[0-9Kk]$",
        message = "El RUT debe tener el formato 12.345.678-9"
    )
    private String professionalRut;

    @Column(name = "PROFESSIONAL_NAME", nullable = false, length = 100)
    @NotBlank(message = "El nombre del profesional es obligatorio")
    private String professionalName;

    @Column(name = "PROFESSIONAL_LAST_NAME", nullable = false, length = 100)
    @NotBlank(message = "El apellido del profesional es obligatorio")
    private String professionalLastName;

    @Column(name = "PROFESSIONAL_SPECIALTY", nullable = false, length = 100)
    @NotBlank(message = "La especialidad es obligatoria")
    private String professionalSpecialty;

    @Column(name = "CONSULTATION_REASON", nullable = false, length = 500)
    @NotBlank(message = "El motivo de la consulta es obligatorio")
    private String reasonMedicalConsultation;

    @Column(name = "DIAGNOSIS", nullable = false, length = 1000)
    @NotBlank(message = "El diagnóstico es obligatorio")
    private String diagnosis;

    @Column(name = "TREATMENT", nullable = false, length = 1000)
    @NotBlank(message = "El tratamiento es obligatorio")
    private String treatment;

    @JsonBackReference("patient-consultations")
    @ManyToOne(optional = false)
    @JoinColumn(name = "PATIENT_ID", nullable = false)
    private Patient patient;

    protected MedicalConsultation() { }

    public Long getIdMedicalConsultation() { return idMedicalConsultation; }
    public LocalDate getDateMedicalConsultation() { return dateMedicalConsultation; }
    public String getProfessionalRut() { return professionalRut; }
    public String getProfessionalName() { return professionalName; }
    public String getProfessionalLastName() { return professionalLastName; }
    public String getProfessionalSpecialty() { return professionalSpecialty; }
    public String getReasonMedicalConsultation() { return reasonMedicalConsultation; }
    public String getDiagnosis() { return diagnosis; }
    public String getTreatment() { return treatment; }
    public Patient getPatient() { return patient; }
    public void setDateMedicalConsultation(LocalDate value) { dateMedicalConsultation = value; }
    public void setProfessionalRut(String value) { professionalRut = value; }
    public void setProfessionalName(String value) { professionalName = value; }
    public void setProfessionalLastName(String value) { professionalLastName = value; }
    public void setProfessionalSpecialty(String value) { professionalSpecialty = value; }
    public void setReasonMedicalConsultation(String value) { reasonMedicalConsultation = value; }
    public void setDiagnosis(String value) { diagnosis = value; }
    public void setTreatment(String value) { treatment = value; }
    public void setPatient(Patient value) { patient = value; }
}
