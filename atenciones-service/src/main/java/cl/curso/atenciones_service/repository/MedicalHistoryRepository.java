package cl.curso.atenciones_service.repository;

import cl.curso.atenciones_service.model.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {
    List<MedicalHistory> findAllByPatient_IdPatient(Long patientId);
}
