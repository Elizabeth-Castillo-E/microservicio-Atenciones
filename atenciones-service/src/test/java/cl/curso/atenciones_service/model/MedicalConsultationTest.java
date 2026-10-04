package cl.curso.atenciones_service.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class MedicalConsultationTest {

    @Test
    void testGetterAndSetters() {
        MedicalConsultation consultation = new MedicalConsultation();
        Patient patient = new Patient();
        consultation.setDateMedicalConsultation(LocalDate.of(2026, 10, 4));
        consultation.setProfessionalRut("11.111.111-1");
        consultation.setProfessionalName("Maria");
        consultation.setProfessionalLastName("Gonzalez");
        consultation.setProfessionalSpecialty("Medicina general");
        consultation.setReasonMedicalConsultation("Control anual");
        consultation.setDiagnosis("Paciente sano");
        consultation.setTreatment("Control preventivo");
        consultation.setPatient(patient);

        // El ID se genera al persistir; el modelo no tiene un setter para este campo.
        assertNull(consultation.getIdMedicalConsultation());
        assertEquals(LocalDate.of(2026, 10, 4), consultation.getDateMedicalConsultation());
        assertEquals("11.111.111-1", consultation.getProfessionalRut());
        assertEquals("Maria", consultation.getProfessionalName());
        assertEquals("Gonzalez", consultation.getProfessionalLastName());
        assertEquals("Medicina general", consultation.getProfessionalSpecialty());
        assertEquals("Control anual", consultation.getReasonMedicalConsultation());
        assertEquals("Paciente sano", consultation.getDiagnosis());
        assertEquals("Control preventivo", consultation.getTreatment());
        assertSame(patient, consultation.getPatient());
    }
}
