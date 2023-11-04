package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DepartementServicetest{
    @InjectMocks
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllDepartementsShouldReturnListOfDepartements() {
        // Arrange
        Departement departement1 = new Departement();
        Departement departement2 = new Departement();
        when(departementRepository.findAll()).thenReturn(Arrays.asList(departement1, departement2));

        // Act
        List<Departement> result = departementService.retrieveAllDepartements();

        // Assert
        verify(departementRepository, times(1)).findAll();
        assertEquals(2, result.size());
    }

    @Test
    public void testAddDepartementShouldReturnAddedDepartement() {
        // Arrange
        Departement departement = new Departement();
        when(departementRepository.save(any(Departement.class))).thenReturn(departement);

        // Act
        Departement result = departementService.addDepartement(departement);

        // Assert
        verify(departementRepository, times(1)).save(departement);
        assertEquals(departement, result);
    }

    @Test
    public void testUpdateDepartementShouldReturnUpdatedDepartement() {
        // Arrange
        Departement departement = new Departement();
        when(departementRepository.save(any(Departement.class))).thenReturn(departement);

        // Act
        Departement result = departementService.updateDepartement(departement);

        // Assert
        verify(departementRepository, times(1)).save(departement);
        assertEquals(departement, result);
    }

    @Test
    public void testRetrieveDepartementShouldReturnDepartementById() {
        // Arrange
        Integer idDepart = 1;
        Departement departement = new Departement();
        when(departementRepository.findById(idDepart)).thenReturn(Optional.of(departement));

        // Act
        Departement result = departementService.retrieveDepartement(idDepart);

        // Assert
        verify(departementRepository, times(1)).findById(idDepart);
        assertEquals(departement, result);
    }

    @Test
    public void testDeleteDepartementShouldCallRetrieveAndDelete() {
        // Arrange
        Integer idDepartement = 1;
        Departement departement = new Departement();
        when(departementService.retrieveDepartement(idDepartement)).thenReturn(departement);

        // Act
        departementService.deleteDepartement(idDepartement);

        // Assert
        verify(departementService, times(1)).retrieveDepartement(idDepartement);
        verify(departementRepository, times(1)).delete(departement);
    }
}
