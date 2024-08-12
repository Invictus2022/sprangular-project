package com.sprangular.sprangular.SprangularController;

import com.sprangular.sprangular.SprangularModel.Model;
import com.sprangular.sprangular.SprangularService.ServiceImplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ControllerTest {

    @Mock
    private ServiceImplement service;

    @InjectMocks
    private Controller controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {
        Model user = new Model();
        when(service.registerUser(user)).thenReturn(new ResponseEntity<>("User Successfully added", HttpStatus.OK));

        ResponseEntity<String> response = controller.registerUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User Successfully added", response.getBody());
    }

    @Test
    void testFindAllUser() {
        List<Model> users = Arrays.asList(new Model(), new Model());
        when(service.findAllUser()).thenReturn(new ResponseEntity<>(users, HttpStatus.OK));

        ResponseEntity<List<Model>> response = controller.findAllUser();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetUserByID() {
        Model user = new Model();
        when(service.getUserByID(1)).thenReturn(new ResponseEntity<>(user, HttpStatus.OK));

        ResponseEntity<Model> response = controller.getUserByID(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testDeleteUser() {
        when(service.deleteUser(1)).thenReturn(new ResponseEntity<>("Id successfully deleted", HttpStatus.OK));

        ResponseEntity<String> response = controller.deleteUser(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Id successfully deleted", response.getBody());
    }
}
