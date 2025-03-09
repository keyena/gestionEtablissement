package groupe.isi.com.gestion.etablissement;

import com.fasterxml.jackson.databind.ObjectMapper;
import groupe.isi.com.gestion.etablissement.dto.ClasseDto;
import groupe.isi.com.gestion.etablissement.dto.EtudiantDto;
import groupe.isi.com.gestion.etablissement.repository.ClasseRepository;
import groupe.isi.com.gestion.etablissement.repository.EtudiantRepository;
import groupe.isi.com.gestion.etablissement.repository.EmploiDuTempsRepository;
import groupe.isi.com.gestion.etablissement.repository.CoursRepository;
import groupe.isi.com.gestion.etablissement.repository.ProfesseurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
class E2ETest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private EmploiDuTempsRepository emploiDuTempsRepository;

    @Autowired
    private CoursRepository coursRepository;

    @Autowired
    private ProfesseurRepository professeurRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        // Désactiver temporairement les contraintes de clé étrangère
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS=0").executeUpdate();
        
        try {
            // Nettoyer la base de données dans le bon ordre
            emploiDuTempsRepository.deleteAllInBatch();
            etudiantRepository.deleteAllInBatch();
            coursRepository.deleteAllInBatch();
            classeRepository.deleteAllInBatch();
            professeurRepository.deleteAllInBatch();
            
            // Vider les caches et forcer la synchronisation
            entityManager.flush();
            entityManager.clear();
        } finally {
            // Réactiver les contraintes de clé étrangère
            entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS=1").executeUpdate();
        }
    }

    @Test
    void processusComplet() throws Exception {
        // 1. Créer une classe
        ClasseDto classeDto = new ClasseDto();
        classeDto.setNom("L3 GL");
        classeDto.setNiveau("Licence 3");
        classeDto.setAnneeScolaire("2023-2024");
        classeDto.setCapacite(40);

        MvcResult classeResult = mockMvc.perform(post("/api/classes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(classeDto)))
                .andExpect(status().isOk())
                .andReturn();

        ClasseDto classeCreee = objectMapper.readValue(
            classeResult.getResponse().getContentAsString(), 
            ClasseDto.class
        );

        // 2. Créer un étudiant
        EtudiantDto etudiantDto = new EtudiantDto();
        etudiantDto.setNumeroEtudiant("ETU" + UUID.randomUUID().toString().substring(0, 8));
        etudiantDto.setNom("Doe");
        etudiantDto.setPrenom("John");
        etudiantDto.setDateNaissance(LocalDate.of(2000, 1, 1)
            .format(DateTimeFormatter.ISO_LOCAL_DATE));
        etudiantDto.setClasseId(classeCreee.getId());

        MvcResult etudiantResult = mockMvc.perform(post("/api/etudiants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(etudiantDto)))
                .andExpect(status().isOk())
                .andReturn();

        // Vérifier que l'étudiant a été créé avec succès
        EtudiantDto etudiantCree = objectMapper.readValue(
            etudiantResult.getResponse().getContentAsString(), 
            EtudiantDto.class
        );
        assertEquals(etudiantDto.getNom(), etudiantCree.getNom());
        assertEquals(etudiantDto.getPrenom(), etudiantCree.getPrenom());
        assertEquals(classeCreee.getId(), etudiantCree.getClasseId());
    }

    @Test
    void testCreateEtudiant() throws Exception {
        // Créer d'abord une classe
        ClasseDto classeDto = new ClasseDto();
        classeDto.setNom("Test Classe");
        classeDto.setNiveau("Test Niveau");
        classeDto.setAnneeScolaire("2023-2024");
        classeDto.setCapacite(30);

        MvcResult classeResult = mockMvc.perform(post("/api/classes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(classeDto)))
                .andExpect(status().isOk())
                .andReturn();

        ClasseDto classeCreee = objectMapper.readValue(
            classeResult.getResponse().getContentAsString(), 
            ClasseDto.class
        );

        // Créer l'étudiant
        EtudiantDto etudiantDto = new EtudiantDto();
        etudiantDto.setNumeroEtudiant("ETU" + UUID.randomUUID().toString().substring(0, 8));
        etudiantDto.setNom("Doe");
        etudiantDto.setPrenom("John");
        etudiantDto.setDateNaissance(LocalDate.of(2000, 1, 1)
            .format(DateTimeFormatter.ISO_LOCAL_DATE));
        etudiantDto.setClasseId(classeCreee.getId());

        MvcResult result = mockMvc.perform(post("/api/etudiants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(etudiantDto)))
                .andExpect(status().isOk())
                .andReturn();

        EtudiantDto created = objectMapper.readValue(
            result.getResponse().getContentAsString(), 
            EtudiantDto.class
        );

        assertNotNull(created.getId());
        assertEquals(etudiantDto.getNom(), created.getNom());
        assertEquals(etudiantDto.getPrenom(), created.getPrenom());
        assertEquals(etudiantDto.getNumeroEtudiant(), created.getNumeroEtudiant());
        assertEquals(classeCreee.getId(), created.getClasseId());
    }

    @AfterEach
    void tearDown() {
        // Désactiver temporairement les contraintes de clé étrangère
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS=0").executeUpdate();
        
        try {
            // Nettoyer la base de données dans le bon ordre
            emploiDuTempsRepository.deleteAllInBatch();
            etudiantRepository.deleteAllInBatch();
            coursRepository.deleteAllInBatch();
            classeRepository.deleteAllInBatch();
            professeurRepository.deleteAllInBatch();
            
            // Vider les caches et forcer la synchronisation
            entityManager.flush();
            entityManager.clear();
        } finally {
            // Réactiver les contraintes de clé étrangère
            entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS=1").executeUpdate();
        }
    }
} 