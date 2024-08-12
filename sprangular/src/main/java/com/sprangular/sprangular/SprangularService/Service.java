package com.sprangular.sprangular.SprangularService;
import com.sprangular.sprangular.SprangularRepository.Repository;
import com.sprangular.sprangular.SprangularModel.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service implements ServiceImplement {

    private Repository sprangularRepo;

    @Autowired
    public Service(Repository repo){
        this.sprangularRepo = repo;
    }

    @Override
  public ResponseEntity<List<Model>> findAllUser(){
      return new ResponseEntity<>(sprangularRepo.findAll(), HttpStatus.OK);
  }

  @Override
  public  ResponseEntity<String> registerUser(Model user){
   try {
       sprangularRepo.save(user);
           return new ResponseEntity<>("User Successfully added",HttpStatus.OK);
  }catch (Exception e){
       return new ResponseEntity<>("Unable to create user",HttpStatus.BAD_REQUEST);
   }
 }

 @Override
 public ResponseEntity<Model> getUserByID(int id){
     Optional <Model> model = sprangularRepo.findById(id);

        if(model.isPresent()){
        return new ResponseEntity<>(model.get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }
  }

    public ResponseEntity<String> deleteUser(int id){
        Optional<Model> model = sprangularRepo.findById(id);

        if (model.isPresent()){
            sprangularRepo.deleteById(id);
            return new ResponseEntity<>("Id successfuly deleted",HttpStatus.OK);
        }else {
            return  new ResponseEntity<>("No such id found in database",HttpStatus.BAD_REQUEST);
        }
    }
}