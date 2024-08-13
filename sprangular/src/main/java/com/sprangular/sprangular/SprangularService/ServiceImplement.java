package com.sprangular.sprangular.SprangularService;

import com.sprangular.sprangular.SprangularModel.Model;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ServiceImplement {
    ResponseEntity<List<Model>> findAllUser();

    ResponseEntity<String> registerUser(Model user);

    ResponseEntity<Model> getUserByID(int id);

    ResponseEntity<String> deleteUser(int id);

    ResponseEntity<String> updateUser(int id, Model model);
}
