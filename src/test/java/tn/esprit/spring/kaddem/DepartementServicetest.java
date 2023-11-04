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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        Departement departement = new Departement();
        when(departementRepository.findById(idDepart)).thenReturn(Optional.of(departement));

        // Appelez la méthode du service pour récupérer le département
        Departement result = departementService.retrieveDepartement(idDepart);

        // Vérifiez que la méthode du repository a été appelée
        verify(departementRepository, times(1)).findById(idDepart);

        // Vérifiez le résultat
        assertEquals(departement, result);
    }

//    @Test
//    public void testDeleteDepartement() {
//        // Créez un ID fictif
//        Integer idDepartement = 1;
//        Departement departement = new Departement();
//        when(departementService.retrieveDepartement(idDepartement)).thenReturn(departement);
//
//        // Appelez la méthode du service pour supprimer le département
//        departementService.deleteDepartement(idDepartement);
//
//        // Vérifiez que la méthode du service a appelé la méthode retrieveDepartement
//        verify(departementService, times(1)).retrieveDepartement(idDepartement);
//
//        // Vérifiez que la méthode du repository a été appelée pour supprimer le département
//        verify(departementRepository, times(1)).delete(departement);
//    }
}
