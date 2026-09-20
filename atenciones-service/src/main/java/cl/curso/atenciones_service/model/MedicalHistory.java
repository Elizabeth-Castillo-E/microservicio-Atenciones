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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

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
    private String bloodType;

    @ElementCollection
    @CollectionTable(name = "MEDICAL_HISTORY_ALLERGIES", joinColumns = @JoinColumn(name = "MEDICAL_HISTORY_ID"))
    @Column(name = "ALLERGY", length = 150)
    private List<String> allergies = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "MEDICAL_HISTORY_CONDITIONS", joinColumns = @JoinColumn(name = "MEDICAL_HISTORY_ID"))
    @Column(name = "CHRONIC_CONDITION", length = 150)
    private List<String> chronicConditions = new ArrayList<>();

    @JsonBackReference("patient-histories")
    @ManyToOne(optional = false)
    @JoinColumn(name = "PATIENT_ID", nullable = false)
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
