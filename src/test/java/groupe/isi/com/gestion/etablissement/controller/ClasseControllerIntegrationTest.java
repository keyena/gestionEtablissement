package groupe.isi.com.gestion.etablissement.controller;

import groupe.isi.com.gestion.etablissement.dto.ClasseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.context.ActiveProfiles;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ClasseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void creerClasse_DevraitRetournerClasseCreee() throws Exception {
        // Arrange
        ClasseDto classeDto = new ClasseDto();
        classeDto.setNom("L3 GL");
        classeDto.setNiveau("Licence 3");
        classeDto.setAnneeScolaire("2023-2024");
        classeDto.setCapacite(40);

        // Act & Assert
        mockMvc.perform(post("/api/classes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(classeDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("L3 GL"))
                .andExpect(jsonPath("$.niveau").value("Licence 3"));
    }

    @Test
    void trouverToutesLesClasses_DevraitRetournerListe() throws Exception {
        mockMvc.perform(get("/api/classes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void trouverClasseParId_DevraitRetournerClasse() throws Exception {
        // D'abord créer une classe
        ClasseDto classeDto = new ClasseDto();
        classeDto.setNom("L3 GL");
        classeDto.setNiveau("Licence 3");
        classeDto.setAnneeScolaire("2023-2024");
        classeDto.setCapacite(40);

        String response = mockMvc.perform(post("/api/classes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(classeDto)))
                .andReturn().getResponse().getContentAsString();

        ClasseDto classeCreee = objectMapper.readValue(response, ClasseDto.class);

        // Ensuite chercher cette classe par son ID
        mockMvc.perform(get("/api/classes/" + classeCreee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("L3 GL"));
    }
} 