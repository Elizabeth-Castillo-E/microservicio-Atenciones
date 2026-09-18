package cl.curso.atenciones_service.model;
import java.time.LocalDate;
import java.util.List;

public class Patient {
    private final long idPatient;
    private final String rutPatient;
    private final String namePatient;
    private final String lastNamePatient;    
    private final String emailPatient;
    private final String phonePatient;
    private final String addressPatient;
    private final String cityPatient;
    private final String regionPatient;
    private final String previsionPatient;
    private final LocalDate birthDatePatient;
    private final String emergencyContactPatient;
    private final MedicalHistory medicalHistory;
    private final List<MedicalConsultation> medicalConsultations;

    public Patient(
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
        List<MedicalConsultation> medicalConsultations) {
            this.idPatient = idPatient;
            this.namePatient = namePatient;
            this.lastNamePatient = lastNamePatient;
            this.rutPatient = rutPatient;
            this.emailPatient = emailPatient;
            this.phonePatient = phonePatient;
            this.addressPatient = addressPatient;
            this.cityPatient = cityPatient;
            this.regionPatient = regionPatient;
            this.previsionPatient = previsionPatient;
            this.birthDatePatient = birthDatePatient;
            this.emergencyContactPatient = emergencyContactPatient;
            this.medicalHistory = medicalHistory;
            this.medicalConsultations = medicalConsultations;
        }

   

    public long getIdPatient() {
        return idPatient;
    }

    public String getNamePatient() {
        return namePatient;
    }
    public String getLastNamePatient() {
        return lastNamePatient;
    }
    public String getRutPatient() {
        return rutPatient;
    }
    public String getEmailPatient() {
        return emailPatient;
    }
    public String getPhonePatient() {
        return phonePatient;
    }
    public String getAddressPatient() {
        return addressPatient;
    }
    public String getCityPatient() {
        return cityPatient;
    }
    public String getRegionPatient() {
        return regionPatient;
    }
    public String getPrevisionPatient() {
        return previsionPatient;
    }
    public LocalDate getBirthDatePatient() {
        return birthDatePatient;
    }
    public String getEmergencyContactPatient() {
        return emergencyContactPatient;
    }
    public MedicalHistory getMedicalHistory() {
        return medicalHistory;
    }
    public List<MedicalConsultation> getMedicalConsultations() {
        return medicalConsultations;
    }

}
