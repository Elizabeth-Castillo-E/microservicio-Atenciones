package cl.curso.atenciones_service.repository;

import cl.curso.atenciones_service.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByEmailPatientIgnoreCase(String emailPatient);
}
