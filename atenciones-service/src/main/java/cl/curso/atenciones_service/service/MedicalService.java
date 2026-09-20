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
    List<MedicalHistory> getMedicalHistoriesByPatientId(Long patientId);
    Optional<MedicalHistory> getMedicalHistoryById(Long id);
    MedicalHistory saveMedicalHistory(Long patientId, MedicalHistory history);
    MedicalHistory updateMedicalHistory(Long id, MedicalHistory history);
    MedicalHistory updateMedicalHistory(Long patientId, Long historyId, MedicalHistory history);
    void deleteMedicalHistory(Long id);
    void deleteMedicalHistory(Long patientId, Long historyId);

    List<MedicalConsultation> getAllMedicalConsultations();
    List<MedicalConsultation> getMedicalConsultationsByPatientId(Long patientId);
    Optional<MedicalConsultation> getMedicalConsultationById(Long id);
    MedicalConsultation getMedicalConsultationByPatientId(Long patientId, Long consultationId);
    MedicalConsultation saveMedicalConsultation(Long patientId, MedicalConsultation consultation);
    MedicalConsultation updateMedicalConsultation(Long id, MedicalConsultation consultation);
    MedicalConsultation updateMedicalConsultation(Long patientId, Long consultationId, MedicalConsultation consultation);
    void deleteMedicalConsultation(Long id);
    void deleteMedicalConsultation(Long patientId, Long consultationId);
}
