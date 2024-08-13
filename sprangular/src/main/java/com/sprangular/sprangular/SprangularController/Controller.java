package com.sprangular.sprangular.SprangularController;


import com.sprangular.sprangular.SprangularService.ServiceImplement;
import com.sprangular.sprangular.SprangularModel.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sprangular")
public class Controller {


    private ServiceImplement Service;

    @Autowired
    public Controller(ServiceImplement service){
        this.Service = service;
    }

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody Model user){
       return Service.registerUser(user);
    }

    @GetMapping()
    public ResponseEntity<List<Model>> findAllUser(){
        return Service.findAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Model> getUserByID(@PathVariable int id){
        return Service.getUserByID(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        return Service.deleteUser(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") int id, @RequestBody Model model){
        return Service.updateUser(id,model);
    }
}
