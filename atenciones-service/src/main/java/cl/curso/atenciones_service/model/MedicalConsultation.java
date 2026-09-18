package cl.curso.atenciones_service.model;
import java.time.LocalDate;


public class MedicalConsultation {
    private final long idMedicalConsultation;
    private final long idPatient;
    private final LocalDate dateMedicalConsultation;
    private final String professionalRut;
    private final String professionalName;
    private final String professionalLastName;
    private final String professionalSpecialty;
    private final String reasonMedicalConsultation;
    private final String diagnosis;
    private final String treatment;

    public MedicalConsultation(
        long idMedicalConsultation, 
        long idPatient, 
        LocalDate dateMedicalConsultation, 
        String professionalRut,
        String professionalName,
        String professionalLastName,
        String professionalSpecialty,
        String reasonMedicalConsultation, 
        String diagnosis, 
        String treatment) {
            this.idMedicalConsultation = idMedicalConsultation;
            this.idPatient = idPatient;
            this.dateMedicalConsultation = dateMedicalConsultation;
            this.professionalRut = professionalRut;
            this.professionalName = professionalName;
            this.professionalLastName = professionalLastName;
            this.professionalSpecialty = professionalSpecialty;
            this.reasonMedicalConsultation = reasonMedicalConsultation;
            this.diagnosis = diagnosis;
            this.treatment = treatment;
        }

    public long getIdMedicalConsultation() {
        return idMedicalConsultation;
    }

    public long getIdPatient() {
        return idPatient;
    }

    public LocalDate getDateMedicalConsultation() {
        return dateMedicalConsultation;
    }
    public String getProfessionalRut() {
        return professionalRut;
    }

    public String getProfessionalName() {
        return professionalName;
    }

    public String getProfessionalLastName() {
        return professionalLastName;
    }

    public String getProfessionalSpecialty() {
        return professionalSpecialty;
    }
    public String getReasonMedicalConsultation() {
        return reasonMedicalConsultation;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }
}