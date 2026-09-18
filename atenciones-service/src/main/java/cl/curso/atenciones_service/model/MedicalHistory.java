package cl.curso.atenciones_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MEDICAL_HISTORIES")
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MEDICAL_HISTORY")
    private Long idMedicalHistory;

    @Column(name = "BLOOD_TYPE", nullable = false, length = 3)
    @NotBlank(message = "El grupo sanguíneo es obligatorio")
    @Pattern(regexp = "^(A|B|AB|O)[+-]$", flags = Pattern.Flag.CASE_INSENSITIVE, message = "El grupo sanguíneo debe tener formato O+, A-, AB+, etc.")
    private String bloodType;

    @ElementCollection
    @CollectionTable(name = "MEDICAL_HISTORY_ALLERGIES", joinColumns = @JoinColumn(name = "MEDICAL_HISTORY_ID"))
    @Column(name = "ALLERGY", length = 150)
    @Size(max = 20, message = "No puede registrar más de 20 alergias")
    private List<String> allergies = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "MEDICAL_HISTORY_CONDITIONS", joinColumns = @JoinColumn(name = "MEDICAL_HISTORY_ID"))
    @Column(name = "CHRONIC_CONDITION", length = 150)
    @Size(max = 20, message = "No puede registrar más de 20 enfermedades crónicas")
    private List<String> chronicConditions = new ArrayList<>();

    @JsonBackReference("patient-history")
    @OneToOne
    @JoinColumn(name = "PATIENT_ID", nullable = false, unique = true)
    private Patient patient;

    protected MedicalHistory() { }

    public Long getIdMedicalHistory() { return idMedicalHistory; }
    public String getBloodType() { return bloodType; }
    public List<String> getAllergies() { return allergies; }
    public List<String> getChronicConditions() { return chronicConditions; }
    public Patient getPatient() { return patient; }
    public void setBloodType(String bloodType) { this.bloodType = bloodType; }
    public void setAllergies(List<String> allergies) { this.allergies = allergies == null ? new ArrayList<>() : new ArrayList<>(allergies); }
    public void setChronicConditions(List<String> chronicConditions) { this.chronicConditions = chronicConditions == null ? new ArrayList<>() : new ArrayList<>(chronicConditions); }
    public void setPatient(Patient patient) { this.patient = patient; }
}
