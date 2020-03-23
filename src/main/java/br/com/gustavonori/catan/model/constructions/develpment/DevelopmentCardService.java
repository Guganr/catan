package br.com.gustavonori.catan.model.constructions.develpment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevelopmentCardService {

    @Autowired
    private DevelopmentCardRepository developmentCardRepository;

    public List<DevelopmentCard> listAll() {
        return developmentCardRepository.findAll();
    }
}
