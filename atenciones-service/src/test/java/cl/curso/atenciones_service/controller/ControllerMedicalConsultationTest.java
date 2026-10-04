package cl.curso.atenciones_service.controller;

import cl.curso.atenciones_service.model.MedicalConsultation;
import cl.curso.atenciones_service.service.MedicalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ControllerMedicalConsultation.class)
class ControllerMedicalConsultationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MedicalService service;

    @Autowired
    private ObjectMapper mapper;

    private MedicalConsultation consultation;

    @BeforeEach
    void setUp() {
        // El constructor protegido permite crear una subclase para los datos de prueba.
        consultation = new MedicalConsultation() { };
        consultation.setDateMedicalConsultation(LocalDate.of(2026, 10, 4));
        consultation.setProfessionalRut("11.111.111-1");
        consultation.setProfessionalName("Maria");
        consultation.setProfessionalLastName("Gonzalez");
        consultation.setProfessionalSpecialty("Medicina general");
        consultation.setReasonMedicalConsultation("Control anual");
        consultation.setDiagnosis("Paciente sano");
        consultation.setTreatment("Control preventivo");
    }

    @Test
    void testGetAllMedicalConsultations() throws Exception {
        when(service.getAllMedicalConsultations()).thenReturn(List.of(consultation));

        mockMvc.perform(get("/medical-service/attentions"))
            .andExpect(status().isOk())
            .andExpect(content().json(mapper.writeValueAsString(List.of(consultation))));
    }

    @Test
    void testGetMedicalConsultationById() throws Exception {
        when(service.getMedicalConsultationById(50L)).thenReturn(Optional.of(consultation));

        mockMvc.perform(get("/medical-service/attentions/50"))
            .andExpect(status().isOk())
            .andExpect(content().json(mapper.writeValueAsString(consultation)));
    }

    @Test
    void testCreateMedicalConsultation() throws Exception {
        when(service.saveMedicalConsultation(eq(1L), any(MedicalConsultation.class)))
            .thenReturn(consultation);

        mockMvc.perform(post("/medical-service/patients/1/attentions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(consultation)))
            .andExpect(status().isCreated())
            .andExpect(content().json(mapper.writeValueAsString(consultation)));
        verify(service).saveMedicalConsultation(eq(1L), any(MedicalConsultation.class));
    }

    @Test
    void testUpdateMedicalConsultationExist() throws Exception {
        consultation.setDiagnosis("Diagnostico actualizado");
        when(service.updateMedicalConsultation(eq(50L), any(MedicalConsultation.class)))
            .thenReturn(consultation);

        mockMvc.perform(put("/medical-service/attentions/50")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(consultation)))
            .andExpect(status().isOk())
            .andExpect(content().json(mapper.writeValueAsString(consultation)));
        verify(service).updateMedicalConsultation(eq(50L), any(MedicalConsultation.class));
    }

    @Test
    void testDeleteMedicalConsultation() throws Exception {
        mockMvc.perform(delete("/medical-service/attentions/50"))
            .andExpect(status().isNoContent());

        verify(service).deleteMedicalConsultation(50L);
    }
}
