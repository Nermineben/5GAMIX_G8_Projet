import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = Contrat.class)
public class ContratServiceTest {

    @InjectMocks
    private ContratServiceImpl contratService;

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EtudiantRepository etudiantRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllContrats() {
        // Create a list of Contrat objects for testing
        List<Contrat> contratList = new ArrayList<>();
        // Set attributes for the Contrat objects in the list
        // ...

        when(contratRepository.findAll()).thenReturn(contratList);

        List<Contrat> retrievedContrats = contratService.retrieveAllContrats();

        assertEquals(contratList, retrievedContrats);
    }

    @Test
    public void testAddContrat() {
        // Create a Contrat object for testing
        Contrat contratToAdd = new Contrat();
        // Set attributes for the Contrat object
        contratToAdd.setDateDebutContrat(new Date());
        contratToAdd.setDateFinContrat(new Date());
        contratToAdd.setSpecialite(Specialite.IA);
        contratToAdd.setArchive(false);
        contratToAdd.setMontantContrat(500);
        // ...

        when(contratRepository.save(contratToAdd)).thenReturn(contratToAdd);

        Contrat savedContrat = contratService.addContrat(contratToAdd);

        assertEquals(contratToAdd, savedContrat);
    }

    @Test
    public void testRetrieveContrat() {
        // Create a Contrat object for testing
        Contrat contrat = new Contrat();
        // Set attributes for the Contrat object
        contrat.setDateDebutContrat(new Date());
        contrat.setDateFinContrat(new Date());
        contrat.setSpecialite(Specialite.CLOUD);
        contrat.setArchive(true);
        contrat.setMontantContrat(800);
        // ...

        when(contratRepository.findById(contrat.getIdContrat())).thenReturn(Optional.of(contrat));

        Contrat retrievedContrat = contratService.retrieveContrat(contrat.getIdContrat());

        assertEquals(contrat, retrievedContrat);
    }

    @Test
    public void testUpdateContrat() {
        // Create a Contrat object for testing
        Contrat contratToUpdate = new Contrat();
        // Set attributes for the Contrat object
        contratToUpdate.setDateDebutContrat(new Date());
        contratToUpdate.setDateFinContrat(new Date());
        contratToUpdate.setSpecialite(Specialite.RESEAUX);
        contratToUpdate.setArchive(false);
        contratToUpdate.setMontantContrat(600);
        // ...

        when(contratRepository.save(contratToUpdate)).thenReturn(contratToUpdate);

        Contrat updatedContrat = contratService.updateContrat(contratToUpdate);

        assertEquals(contratToUpdate, updatedContrat);
    }


    @Test
    public void testAffectContratToEtudiant() {
        // Create an Etudiant
        Etudiant etudiant = new Etudiant();
        etudiant.setNomE("John");
        etudiant.setPrenomE("Doe");

        // Create a Contrat object for testing
        Contrat contrat = new Contrat();
        contrat.setDateDebutContrat(new Date());
        contrat.setDateFinContrat(new Date());
        contrat.setSpecialite(Specialite.SECURITE);
        contrat.setArchive(false);
        contrat.setMontantContrat(700);

        // Set the contrats set for the etudiant
        Set<Contrat> contrats = new HashSet<>();
        contrats.add(contrat);
        etudiant.setContrats(contrats);

        when(etudiantRepository.findByNomEAndPrenomE(etudiant.getNomE(), etudiant.getPrenomE())).thenReturn(etudiant);
        when(contratRepository.findByIdContrat(contrat.getIdContrat())).thenReturn(contrat);

        Contrat assignedContrat = contratService.affectContratToEtudiant(contrat.getIdContrat(), etudiant.getNomE(), etudiant.getPrenomE());

        // Verify that the Contrat is assigned to the Etudiant
        assertNotNull(assignedContrat.getEtudiant());
        assertEquals(etudiant, assignedContrat.getEtudiant());
    }


    // Add more test cases for other methods and scenarios as needed
}
