package cl.curso.atenciones_service.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PATIENTS")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PATIENT")
    private Long idPatient;

    @Column(name = "PATIENT_RUT", nullable = false, unique = true, length = 12)
    @NotBlank(message = "El RUT del paciente es obligatorio")
    @Pattern(
        regexp = "^\\d{1,2}\\.\\d{3}\\.\\d{3}-[0-9Kk]$",
        message = "El RUT debe tener el formato 12.345.678-9"
    )
    private String rutPatient;

    @Column(name = "PATIENT_NAME", nullable = false, length = 100)
    @NotBlank(message = "El nombre del paciente es obligatorio")
    private String namePatient;

    @Column(name = "PATIENT_LAST_NAME", nullable = false, length = 100)
    @NotBlank(message = "El apellido del paciente es obligatorio")
    private String lastNamePatient;

    @Column(name = "PATIENT_EMAIL", nullable = false, unique = true, length = 150)
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico no tiene un formato válido")
    private String emailPatient;

    @Column(name = "PATIENT_PHONE", nullable = false, length = 20)
    @NotBlank(message = "El teléfono es obligatorio")
    private String phonePatient;

    @Column(name = "PATIENT_ADDRESS", nullable = false, length = 200)
    @NotBlank(message = "La dirección es obligatoria")
    private String addressPatient;

    @Column(name = "PATIENT_CITY", nullable = false, length = 100)
    @NotBlank(message = "La ciudad es obligatoria")
    private String cityPatient;

    @Column(name = "PATIENT_REGION", nullable = false, length = 100)
    @NotBlank(message = "La región es obligatoria")
    private String regionPatient;

    @Column(name = "PATIENT_PREVISION", nullable = false, length = 50)
    @NotBlank(message = "La previsión es obligatoria")
    private String previsionPatient;

    @Column(name = "PATIENT_BIRTH_DATE", nullable = false)
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate birthDatePatient;

    @Column(name = "EMERGENCY_CONTACT", nullable = false, length = 20)
    @NotBlank(message = "El contacto de emergencia es obligatorio")
    private String emergencyContactPatient;

    @Valid
    @JsonManagedReference("patient-histories")
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicalHistory> medicalHistories = new ArrayList<>();

    @Valid
    @JsonManagedReference("patient-consultations")
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicalConsultation> medicalConsultations = new ArrayList<>();

    protected Patient() { }

    public Long getIdPatient() { return idPatient; }
    public String getRutPatient() { return rutPatient; }
    public String getNamePatient() { return namePatient; }
    public String getLastNamePatient() { return lastNamePatient; }
    public String getEmailPatient() { return emailPatient; }
    public String getPhonePatient() { return phonePatient; }
    public String getAddressPatient() { return addressPatient; }
    public String getCityPatient() { return cityPatient; }
    public String getRegionPatient() { return regionPatient; }
    public String getPrevisionPatient() { return previsionPatient; }
    public LocalDate getBirthDatePatient() { return birthDatePatient; }
    public String getEmergencyContactPatient() { return emergencyContactPatient; }
    public List<MedicalHistory> getMedicalHistories() { return medicalHistories; }
    public List<MedicalConsultation> getMedicalConsultations() { return medicalConsultations; }

    public void setRutPatient(String rutPatient) { this.rutPatient = rutPatient; }
    public void setNamePatient(String namePatient) { this.namePatient = namePatient; }
    public void setLastNamePatient(String lastNamePatient) { this.lastNamePatient = lastNamePatient; }
    public void setEmailPatient(String emailPatient) { this.emailPatient = emailPatient; }
    public void setPhonePatient(String phonePatient) { this.phonePatient = phonePatient; }
    public void setAddressPatient(String addressPatient) { this.addressPatient = addressPatient; }
    public void setCityPatient(String cityPatient) { this.cityPatient = cityPatient; }
    public void setRegionPatient(String regionPatient) { this.regionPatient = regionPatient; }
    public void setPrevisionPatient(String previsionPatient) { this.previsionPatient = previsionPatient; }
    public void setBirthDatePatient(LocalDate birthDatePatient) { this.birthDatePatient = birthDatePatient; }
    public void setEmergencyContactPatient(String emergencyContactPatient) { this.emergencyContactPatient = emergencyContactPatient; }

    public void setMedicalHistories(List<MedicalHistory> medicalHistories) {
        this.medicalHistories.clear();
        if (medicalHistories != null) medicalHistories.forEach(this::addMedicalHistory);
    }

    public void addMedicalHistory(MedicalHistory medicalHistory) {
        medicalHistories.add(medicalHistory);
        medicalHistory.setPatient(this);
    }

    public void setMedicalConsultations(List<MedicalConsultation> medicalConsultations) {
        this.medicalConsultations.clear();
        if (medicalConsultations != null) medicalConsultations.forEach(this::addMedicalConsultation);
    }

    public void addMedicalConsultation(MedicalConsultation consultation) {
        medicalConsultations.add(consultation);
        consultation.setPatient(this);
    }
}
