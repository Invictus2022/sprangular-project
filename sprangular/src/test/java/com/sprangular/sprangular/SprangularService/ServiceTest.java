package com.sprangular.sprangular.SprangularService;

import com.sprangular.sprangular.SprangularModel.Model;
import com.sprangular.sprangular.SprangularRepository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiceTest {

    @Mock
    private Repository sprangularRepo;

    @InjectMocks
    private Service service;

    private Model testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = new Model();
        testUser.setId(1);
        testUser.setFirstname("John Doe");
        testUser.setLastname("johndoe@example.com");
        testUser.setUsername("Sprangular");
        testUser.setPassword("12345");
    }

    @Test
    void testFindAllUser_Success() {
        List<Model> userList = Arrays.asList(testUser);
        when(sprangularRepo.findAll()).thenReturn(userList);

        ResponseEntity<List<Model>> response = service.findAllUser();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(testUser, response.getBody().get(0));

        verify(sprangularRepo, times(1)).findAll();
    }

    @Test
    void testFindAllUser_EmptyList() {
        when(sprangularRepo.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Model>> response = service.findAllUser();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());

        verify(sprangularRepo, times(1)).findAll();
    }

    @Test
    void testRegisterUser_Success() {
        when(sprangularRepo.save(any(Model.class))).thenReturn(testUser);

        ResponseEntity<String> response = service.registerUser(testUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User Successfully added", response.getBody());

        verify(sprangularRepo, times(1)).save(testUser);
    }

    @Test
    void testRegisterUser_Failure() {
        when(sprangularRepo.save(any(Model.class))).thenThrow(new RuntimeException("Database Error"));

        ResponseEntity<String> response = service.registerUser(testUser);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Unable to create user", response.getBody());

        verify(sprangularRepo, times(1)).save(testUser);
    }

    @Test
    void testGetUserByID_Found() {
        when(sprangularRepo.findById(1)).thenReturn(Optional.of(testUser));

        ResponseEntity<Model> response = service.getUserByID(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testUser, response.getBody());

        verify(sprangularRepo, times(1)).findById(1);
    }

    @Test
    void testGetUserByID_NotFound() {
        when(sprangularRepo.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<Model> response = service.getUserByID(1);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());

        verify(sprangularRepo, times(1)).findById(1);
    }

    @Test
    void testDeleteUser_Found() {
        when(sprangularRepo.findById(1)).thenReturn(Optional.of(testUser));
        doNothing().when(sprangularRepo).deleteById(1);

        ResponseEntity<String> response = service.deleteUser(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Id successfuly deleted", response.getBody());

        verify(sprangularRepo, times(1)).findById(1);
        verify(sprangularRepo, times(1)).deleteById(1);
    }

    @Test
    void testDeleteUser_NotFound() {
        when(sprangularRepo.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<String> response = service.deleteUser(1);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("No such id found in database", response.getBody());

        verify(sprangularRepo, times(1)).findById(1);
        verify(sprangularRepo, times(0)).deleteById(anyInt());
    }
}
