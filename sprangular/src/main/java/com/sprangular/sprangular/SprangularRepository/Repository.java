package com.sprangular.sprangular.SprangularRepository;

import com.sprangular.sprangular.SprangularModel.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Model,Integer> {
}
