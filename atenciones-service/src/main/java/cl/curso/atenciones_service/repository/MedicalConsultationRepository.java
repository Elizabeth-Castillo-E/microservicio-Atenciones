package cl.curso.atenciones_service.repository;

import cl.curso.atenciones_service.model.MedicalConsultation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalConsultationRepository extends JpaRepository<MedicalConsultation, Long> {
    List<MedicalConsultation> findAllByPatient_IdPatient(Long patientId);
}
