package cl.curso.atenciones_service.service;

import cl.curso.atenciones_service.model.MedicalConsultation;
import cl.curso.atenciones_service.model.MedicalHistory;
import cl.curso.atenciones_service.model.Patient;
import cl.curso.atenciones_service.repository.MedicalConsultationRepository;
import cl.curso.atenciones_service.repository.MedicalHistoryRepository;
import cl.curso.atenciones_service.repository.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalServiceImpl implements MedicalService {
    private final PatientRepository patientRepository;
    private final MedicalHistoryRepository historyRepository;
    private final MedicalConsultationRepository consultationRepository;

    public MedicalServiceImpl(PatientRepository patientRepository, MedicalHistoryRepository historyRepository,
                              MedicalConsultationRepository consultationRepository) {
        this.patientRepository = patientRepository;
        this.historyRepository = historyRepository;
        this.consultationRepository = consultationRepository;
    }

    @Override public List<Patient> getAllPatients() { return patientRepository.findAll(); }
    @Override public Optional<Patient> getPatientById(Long id) { return patientRepository.findById(id); }
    @Override public Patient savePatient(Patient patient) { linkPatientRelations(patient); return patientRepository.save(patient); }
    @Override public Patient updatePatient(Long id, Patient incoming) {
        Patient current = requirePatient(id);
        copyPatientFields(current, incoming);
        return patientRepository.save(current);
    }
    @Override public void deletePatient(Long id) { patientRepository.delete(requirePatient(id)); }

    @Override public List<MedicalHistory> getAllMedicalHistories() { return historyRepository.findAll(); }
    @Override public List<MedicalHistory> getMedicalHistoriesByPatientId(Long patientId) {
        requirePatient(patientId);
        return historyRepository.findAllByPatient_IdPatient(patientId);
    }
    @Override public Optional<MedicalHistory> getMedicalHistoryById(Long id) { return historyRepository.findById(id); }
    @Override public MedicalHistory saveMedicalHistory(Long patientId, MedicalHistory history) {
        Patient patient = requirePatient(patientId);
        history.setPatient(patient);
        return historyRepository.save(history);
    }
    @Override public MedicalHistory updateMedicalHistory(Long id, MedicalHistory incoming) {
        MedicalHistory current = requireHistory(id);
        copyMedicalHistoryFields(current, incoming);
        return historyRepository.save(current);
    }
    @Override public MedicalHistory updateMedicalHistory(Long patientId, Long historyId, MedicalHistory incoming) {
        requirePatient(patientId);
        MedicalHistory current = requireHistory(historyId);
        if (!current.getPatient().getIdPatient().equals(patientId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "El historial " + historyId + " no pertenece al paciente " + patientId);
        }
        copyMedicalHistoryFields(current, incoming);
        return historyRepository.save(current);
    }
    private void copyMedicalHistoryFields(MedicalHistory current, MedicalHistory incoming) {
        current.setBloodType(incoming.getBloodType());
        current.setAllergies(incoming.getAllergies());
        current.setChronicConditions(incoming.getChronicConditions());
    }
    @Override public void deleteMedicalHistory(Long id) { historyRepository.delete(requireHistory(id)); }
    @Override public void deleteMedicalHistory(Long patientId, Long historyId) {
        requirePatient(patientId);
        MedicalHistory history = requireHistory(historyId);
        if (!history.getPatient().getIdPatient().equals(patientId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "El historial " + historyId + " no pertenece al paciente " + patientId);
        }
        historyRepository.delete(history);
    }

    @Override public List<MedicalConsultation> getAllMedicalConsultations() { return consultationRepository.findAll(); }
    @Override public List<MedicalConsultation> getMedicalConsultationsByPatientId(Long patientId) {
        requirePatient(patientId);
        return consultationRepository.findAllByPatient_IdPatient(patientId);
    }
    @Override public Optional<MedicalConsultation> getMedicalConsultationById(Long id) { return consultationRepository.findById(id); }
    @Override public MedicalConsultation getMedicalConsultationByPatientId(Long patientId, Long consultationId) {
        return requireConsultationForPatient(patientId, consultationId);
    }
    @Override public MedicalConsultation saveMedicalConsultation(Long patientId, MedicalConsultation consultation) {
        consultation.setPatient(requirePatient(patientId));
        return consultationRepository.save(consultation);
    }
    @Override public MedicalConsultation updateMedicalConsultation(Long id, MedicalConsultation incoming) {
        MedicalConsultation current = requireConsultation(id);
        copyMedicalConsultationFields(current, incoming);
        return consultationRepository.save(current);
    }
    @Override public MedicalConsultation updateMedicalConsultation(
            Long patientId, Long consultationId, MedicalConsultation incoming) {
        MedicalConsultation current = requireConsultationForPatient(patientId, consultationId);
        copyMedicalConsultationFields(current, incoming);
        return consultationRepository.save(current);
    }
    private void copyMedicalConsultationFields(MedicalConsultation current, MedicalConsultation incoming) {
        current.setDateMedicalConsultation(incoming.getDateMedicalConsultation());
        current.setProfessionalRut(incoming.getProfessionalRut());
        current.setProfessionalName(incoming.getProfessionalName());
        current.setProfessionalLastName(incoming.getProfessionalLastName());
        current.setProfessionalSpecialty(incoming.getProfessionalSpecialty());
        current.setReasonMedicalConsultation(incoming.getReasonMedicalConsultation());
        current.setDiagnosis(incoming.getDiagnosis());
        current.setTreatment(incoming.getTreatment());
    }
    @Override public void deleteMedicalConsultation(Long id) { consultationRepository.delete(requireConsultation(id)); }
    @Override public void deleteMedicalConsultation(Long patientId, Long consultationId) {
        consultationRepository.delete(requireConsultationForPatient(patientId, consultationId));
    }

    private Patient requirePatient(Long id) { return patientRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado: " + id)); }
    private MedicalHistory requireHistory(Long id) { return historyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Historia clínica no encontrada: " + id)); }
    private MedicalConsultation requireConsultation(Long id) { return consultationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta médica no encontrada: " + id)); }
    private MedicalConsultation requireConsultationForPatient(Long patientId, Long consultationId) {
        requirePatient(patientId);
        MedicalConsultation consultation = requireConsultation(consultationId);
        if (!consultation.getPatient().getIdPatient().equals(patientId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "La consulta " + consultationId + " no pertenece al paciente " + patientId);
        }
        return consultation;
    }
    private void linkPatientRelations(Patient patient) {
        patient.getMedicalHistories().forEach(history -> history.setPatient(patient));
        patient.getMedicalConsultations().forEach(consultation -> consultation.setPatient(patient));
    }
    private void copyPatientFields(Patient current, Patient incoming) {
        current.setRutPatient(incoming.getRutPatient()); current.setNamePatient(incoming.getNamePatient());
        current.setLastNamePatient(incoming.getLastNamePatient()); current.setEmailPatient(incoming.getEmailPatient());
        current.setPhonePatient(incoming.getPhonePatient()); current.setAddressPatient(incoming.getAddressPatient());
        current.setCityPatient(incoming.getCityPatient()); current.setRegionPatient(incoming.getRegionPatient());
        current.setPrevisionPatient(incoming.getPrevisionPatient()); current.setBirthDatePatient(incoming.getBirthDatePatient());
        current.setEmergencyContactPatient(incoming.getEmergencyContactPatient());
    }
}
