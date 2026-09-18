package cl.curso.atenciones_service.service;

import cl.curso.atenciones_service.model.MedicalConsultation;
import cl.curso.atenciones_service.model.MedicalHistory;
import cl.curso.atenciones_service.model.Patient;

import java.util.List;
import java.util.Optional;

public interface MedicalService {
    List<Patient> getAllPatients();
    Optional<Patient> getPatientById(Long id);
    Patient savePatient(Patient patient);
    Patient updatePatient(Long id, Patient patient);
    void deletePatient(Long id);

    List<MedicalHistory> getAllMedicalHistories();
    Optional<MedicalHistory> getMedicalHistoryById(Long id);
    Optional<MedicalHistory> getMedicalHistoryByPatientId(Long patientId);
    MedicalHistory saveMedicalHistory(Long patientId, MedicalHistory history);
    MedicalHistory updateMedicalHistory(Long id, MedicalHistory history);
    void deleteMedicalHistory(Long id);

    List<MedicalConsultation> getAllMedicalConsultations();
    Optional<MedicalConsultation> getMedicalConsultationById(Long id);
    List<MedicalConsultation> getMedicalConsultationsByPatientId(Long patientId);
    MedicalConsultation saveMedicalConsultation(Long patientId, MedicalConsultation consultation);
    MedicalConsultation updateMedicalConsultation(Long id, MedicalConsultation consultation);
    void deleteMedicalConsultation(Long id);
}
