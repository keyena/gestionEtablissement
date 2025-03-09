package groupe.isi.com.gestion.etablissement.controller.integration;

import groupe.isi.com.gestion.etablissement.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void professeurCRUD() throws Exception {
        // Create
        ProfesseurDto professeurDto = new ProfesseurDto();
        professeurDto.setNom("Diallo");
        professeurDto.setEmail("diallo@isi.sn");
        professeurDto.setSpecialite("Java");

        String result = mockMvc.perform(post("/api/professeurs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(professeurDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Diallo"))
                .andReturn().getResponse().getContentAsString();

        ProfesseurDto created = objectMapper.readValue(result, ProfesseurDto.class);

        // Read
        mockMvc.perform(get("/api/professeurs/" + created.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Diallo"));

        // Update
        professeurDto.setSpecialite("Spring Boot");
        mockMvc.perform(put("/api/professeurs/" + created.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(professeurDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.specialite").value("Spring Boot"));

        // Delete
        mockMvc.perform(delete("/api/professeurs/" + created.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void processusCompletInscription() throws Exception {
        // 1. Créer un professeur
        ProfesseurDto professeurDto = new ProfesseurDto();
        professeurDto.setNom("Diallo");
        professeurDto.setEmail("diallo@isi.sn");
        String profResult = mockMvc.perform(post("/api/professeurs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(professeurDto)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        ProfesseurDto prof = objectMapper.readValue(profResult, ProfesseurDto.class);

        // 2. Créer une classe
        ClasseDto classeDto = new ClasseDto();
        classeDto.setNom("L3 GL");
        classeDto.setNiveau("Licence 3");
        String classeResult = mockMvc.perform(post("/api/classes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(classeDto)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        ClasseDto classe = objectMapper.readValue(classeResult, ClasseDto.class);

        // 3. Créer un cours
        CoursDto coursDto = new CoursDto();
        coursDto.setNomCours("Java Programming");
        coursDto.setProfesseurId(prof.getId());
        String coursResult = mockMvc.perform(post("/api/cours")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(coursDto)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        objectMapper.readValue(coursResult, CoursDto.class);

        // 4. Créer un étudiant
        EtudiantDto etudiantDto = new EtudiantDto();
        etudiantDto.setNom("Diop");
        etudiantDto.setClasseId(classe.getId());
        mockMvc.perform(post("/api/etudiants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(etudiantDto)))
                .andExpect(status().isOk());
    }
} 