package cl.curso.atenciones_service.model;
import java.util.List;
public class MedicalHistory {
    
    private final long idMedicalHistory;
    private final long idPatient;
    private final String bloodType;
    private final List<String> allergies;
    private final List<String> chronicConditions;
    

    public MedicalHistory(
        long idMedicalHistory, 
        long idPatient, 
        String bloodType, 
        List<String> allergies, 
        List<String> chronicConditions) {
                this.idMedicalHistory = idMedicalHistory;
                this.idPatient = idPatient;
                this.bloodType = bloodType;
                this.allergies = allergies;
                this.chronicConditions = chronicConditions;
    }

    public long getIdMedicalHistory() {
        return idMedicalHistory;
    }

    public long getIdPatient() {
        return idPatient;
    }

    public String getBloodType() {
        return bloodType;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public List<String> getChronicConditions() {
        return chronicConditions;
    }
}