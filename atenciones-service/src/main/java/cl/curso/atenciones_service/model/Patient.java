package cl.curso.atenciones_service.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

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
    @Pattern(regexp = "^\\d{1,2}\\.\\d{3}\\.\\d{3}-[0-9Kk]$", message = "El RUT debe tener el formato 12.345.678-9")
    private String rutPatient;

    @Column(name = "PATIENT_NAME", nullable = false, length = 100)
    @NotBlank(message = "El nombre del paciente es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
    private String namePatient;

    @Column(name = "PATIENT_LAST_NAME", nullable = false, length = 100)
    @NotBlank(message = "El apellido del paciente es obligatorio")
    @Size(max = 100, message = "El apellido no puede superar 100 caracteres")
    private String lastNamePatient;

    @Column(name = "PATIENT_EMAIL", nullable = false, unique = true, length = 150)
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico no tiene un formato válido")
    @Size(max = 150, message = "El correo electrónico no puede superar 150 caracteres")
    private String emailPatient;

    @Column(name = "PATIENT_PHONE", nullable = false, length = 20)
    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^\\+?[0-9]{9,15}$", message = "El teléfono debe contener entre 9 y 15 dígitos")
    private String phonePatient;

    @Column(name = "PATIENT_ADDRESS", nullable = false, length = 200)
    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 200, message = "La dirección no puede superar 200 caracteres")
    private String addressPatient;

    @Column(name = "PATIENT_CITY", nullable = false, length = 100)
    @NotBlank(message = "La ciudad es obligatoria")
    @Size(max = 100, message = "La ciudad no puede superar 100 caracteres")
    private String cityPatient;

    @Column(name = "PATIENT_REGION", nullable = false, length = 100)
    @NotBlank(message = "La región es obligatoria")
    @Size(max = 100, message = "La región no puede superar 100 caracteres")
    private String regionPatient;

    @Column(name = "PATIENT_PREVISION", nullable = false, length = 50)
    @NotBlank(message = "La previsión es obligatoria")
    @Size(max = 50, message = "La previsión no puede superar 50 caracteres")
    private String previsionPatient;

    @Column(name = "PATIENT_BIRTH_DATE", nullable = false)
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser anterior a hoy")
    private LocalDate birthDatePatient;

    @Column(name = "EMERGENCY_CONTACT", nullable = false, length = 20)
    @NotBlank(message = "El contacto de emergencia es obligatorio")
    @Pattern(regexp = "^\\+?[0-9]{9,15}$", message = "El contacto de emergencia debe contener entre 9 y 15 dígitos")
    private String emergencyContactPatient;

    @Valid
    @JsonManagedReference("patient-history")
    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private MedicalHistory medicalHistory;

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
    public MedicalHistory getMedicalHistory() { return medicalHistory; }
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

    public void setMedicalHistory(MedicalHistory medicalHistory) {
        this.medicalHistory = medicalHistory;
        if (medicalHistory != null) medicalHistory.setPatient(this);
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
