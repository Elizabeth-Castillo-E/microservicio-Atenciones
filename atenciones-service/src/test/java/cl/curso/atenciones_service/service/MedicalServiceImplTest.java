package cl.curso.atenciones_service.service;

import cl.curso.atenciones_service.model.MedicalConsultation;
import cl.curso.atenciones_service.model.Patient;
import cl.curso.atenciones_service.repository.MedicalConsultationRepository;
import cl.curso.atenciones_service.repository.MedicalHistoryRepository;
import cl.curso.atenciones_service.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MedicalServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private MedicalHistoryRepository historyRepository;

    @Mock
    private MedicalConsultationRepository consultationRepository;

    @Mock
    private Patient patient;

    @InjectMocks
    private MedicalServiceImpl medicalService;

    private MedicalConsultation consultation;

    @BeforeEach
    void setUp() {
        // El constructor del modelo es protected; esta subclase permite usar un objeto real.
        consultation = new MedicalConsultation() { };
        consultation.setReasonMedicalConsultation("Control anual");
        consultation.setDiagnosis("Paciente sano");
        consultation.setTreatment("Control preventivo");
    }

    @Test
    void guardarAtencion() {
        // Preparar los resultados de los repositorios simulados.
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(consultationRepository.save(consultation)).thenReturn(consultation);

        // Ejecutar la operacion Create del CRUD.
        MedicalConsultation result = medicalService.saveMedicalConsultation(1L, consultation);

        // Comprobar el resultado, la asociacion al paciente y el guardado.
        assertSame(consultation, result);
        assertSame(patient, result.getPatient());
        verify(consultationRepository).save(consultation);
    }

    @Test
    void listarAtenciones() {
        when(consultationRepository.findAll()).thenReturn(List.of(consultation));

        // Ejecutar la operacion Read del CRUD.
        List<MedicalConsultation> result = medicalService.getAllMedicalConsultations();

        assertEquals(1, result.size());
        assertSame(consultation, result.get(0));
        verify(consultationRepository).findAll();
    }

    @Test
    void testGetMedicalConsultationById() {
        when(consultationRepository.findById(50L)).thenReturn(Optional.of(consultation));

        assertEquals(Optional.of(consultation), medicalService.getMedicalConsultationById(50L));
    }

    @Test
    void testUpdateMedicalConsultationExist() {
        MedicalConsultation incoming = new MedicalConsultation() { };
        incoming.setDiagnosis("Diagnostico actualizado");
        incoming.setTreatment("Nuevo tratamiento");
        when(consultationRepository.findById(50L)).thenReturn(Optional.of(consultation));
        when(consultationRepository.save(consultation)).thenReturn(consultation);

        MedicalConsultation result = medicalService.updateMedicalConsultation(50L, incoming);

        assertSame(consultation, result);
        assertEquals("Diagnostico actualizado", result.getDiagnosis());
        assertEquals("Nuevo tratamiento", result.getTreatment());
        verify(consultationRepository).save(consultation);
    }

    @Test
    void testUpdateMedicalConsultationNoExist() {
        when(consultationRepository.findById(50L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
            () -> medicalService.updateMedicalConsultation(50L, consultation));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(consultationRepository, never()).save(any());
    }

    @Test
    void testDeleteMedicalConsultation() {
        when(consultationRepository.findById(50L)).thenReturn(Optional.of(consultation));

        medicalService.deleteMedicalConsultation(50L);

        verify(consultationRepository).delete(consultation);
    }
}
