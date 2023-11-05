package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartementServicetest {

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllDepartements() {
        // Créez des données fictives pour le repository
        Departement departement1 = new Departement();
        Departement departement2 = new Departement();
        when(departementRepository.findAll()).thenReturn(Arrays.asList(departement1, departement2));

        // Appelez la méthode du service
        List<Departement> result = departementService.retrieveAllDepartements();

        // Vérifiez que la méthode du repository a été appelée
        verify(departementRepository, times(1)).findAll();

        // Vérifiez le résultat
        assertEquals(2, result.size());
    }

    @Test
    public void testAddDepartement() {
        // Créez un département fictif
        Departement departement = new Departement();
        when(departementRepository.save(any(Departement.class))).thenReturn(departement);

        // Appelez la méthode du service pour ajouter le département
        Departement result = departementService.addDepartement(departement);

        // Vérifiez que la méthode du repository a été appelée
        verify(departementRepository, times(1)).save(departement);

        // Vérifiez le résultat
        assertEquals(departement, result);
    }

    @Test
    public void testUpdateDepartement() {
        // Créez un département fictif
        Departement departement = new Departement();
        when(departementRepository.save(any(Departement.class))).thenReturn(departement);

        // Appelez la méthode du service pour mettre à jour le département
        Departement result = departementService.updateDepartement(departement);

        // Vérifiez que la méthode du repository a été appelée
        verify(departementRepository, times(1)).save(departement);

        // Vérifiez le résultat
        assertEquals(departement, result);
    }

    @Test
    public void testRetrieveDepartement() {
        // Créez un ID fictif
        Integer idDepart = 1;

        // Créez un département réel (ou utilisez un département existant de votre base de données)
        Departement departement = new Departement(); // Initialisez-le avec des valeurs appropriées

        // Enregistrez ce département dans le repository (vous devez avoir une méthode save dans votre repository)
        when(departementRepository.findById(idDepart)).thenReturn(Optional.of(departement));

        // Appelez la méthode du service pour récupérer le département
        Optional<Departement> optionalDepartement = Optional.ofNullable(departementService.retrieveDepartement(idDepart));

        // Vérifiez si le Optional contient une valeur (i.e., le département a été trouvé)
        assertTrue(optionalDepartement.isPresent());

        // Récupérez la valeur du Optional
        Departement result = optionalDepartement.get();

        // Vérifiez le résultat
        assertNotNull(result);
        assertEquals(departement, result);
    }
}
