package tn.esprit.spring.kaddem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

public class DepartementServicetest {

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        // Initialisation de tout ce dont vous avez besoin pour le test
    }

    @Test
    public void testRetrieveAllDepartements() {
        // Créez une liste de départements simulée pour le test
        List<Departement> departements = new ArrayList<>();
        // Ajoutez des éléments à la liste simulée

        // Définissez le comportement simulé du repository
        when(departementRepository.findAll()).thenReturn(departements);

        // Appelez la méthode à tester
        List<Departement> result = departementService.retrieveAllDepartements();

        // Effectuez les assertions
        assertEquals(departements, result);
    }

    @Test
    public void testAddDepartement() {
        // Créez un département simulé pour le test
        Departement departement = new Departement();
        // Définissez le comportement simulé du repository
        when(departementRepository.save(departement)).thenReturn(departement);

        // Appelez la méthode à tester
        Departement result = departementService.addDepartement(departement);

        // Effectuez les assertions
        assertEquals(departement, result);
    }

    @Test
    public void testRetrieveDepartement() {
        // Créez un département simulé pour le test
        Departement departement = new Departement();
        int idDepart = 1;
        departement.setIdDepart(idDepart);

        // Définissez le comportement simulé du repository
        when(departementRepository.findById(idDepart)).thenReturn(Optional.of(departement));

        // Appelez la méthode à tester
        Departement result = departementService.retrieveDepartement(idDepart);

        // Effectuez les assertions
        assertEquals(departement, result);
    }

    // Ajoutez d'autres méthodes de test pour les autres fonctionnalités du service
}
