package br.com.gustavonori.catan.model.constructions.develpment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DevelopmentCardController {

    @Autowired
    DevelopmentCardService developmentCardService;

    @RequestMapping("/cartas")
    public List<DevelopmentCard> lista(){
        return developmentCardService.listAll();
    }
}
