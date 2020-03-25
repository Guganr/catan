package br.com.gustavonori.catan.model.constructions.develpment;


import br.com.gustavonori.catan.model.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.*;

@Service
public class DevelopmentCardService {

    @Autowired
    private DevelopmentCardRepository developmentCardRepository;

    public List<DevelopmentCard> listAll() {
        return developmentCardRepository.findAll();
    }

    public List<DevelopmentCard> getADevelopmentCard(PlayerService playerService) {
        String sql = "SELECT dc FROM DevelopmentCard dc WHERE picked = 0 ORDER BY RAND()";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("pPicked", "0");
        Query query = createQuery(sql, parameters);

        DevelopmentCard developmentCard;
        List<DevelopmentCard> developmentCardList = createDevelopmentCardObject(query.getResultList());
        try {
            developmentCard = developmentCardList.get(0);
        } catch (Exception e){
            throw new RuntimeException("Não foi possível selecionar uma carta de desenvolvimento");
        }
        developmentCard.build(playerService);
        return List.of(developmentCard);
    }

    private List<DevelopmentCard> createDevelopmentCardObject(List<DevelopmentCard> resultList) {
        DevelopmentCard developmentCardItem = new DevelopmentCard();
        if (!resultList.isEmpty()) {
            developmentCardItem = resultList.get(0);
        }
        DevelopmentCard developmentCard;
        switch (developmentCardItem.getName()) {
            case "Cavaleiro":
                developmentCard = new KnightCard(developmentCardItem);
                break;
            case "Catedral":
            case "Mercado":
            case "Câmara":
            case "Biblioteca":
            case "Universidade":
                developmentCard = new PointCard(developmentCardItem);
                break;
            case "Monopólio":
                developmentCard = new Monopoly(developmentCardItem);
                break;
            case "Invenção":
                developmentCard = new Invention(developmentCardItem);
                break;
            case "Construção de estradas":
                developmentCard = new RoadBuilderCard(developmentCardItem);
                break;
            default:
                return Collections.emptyList();
        }
        return List.of(developmentCard);
    }

    private Query createQuery(String sql, Map<String, String> parameters) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("catan");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(sql);
//        if (!parameters.isEmpty())
//            parameters.forEach(query::setParameter);
        return query;
    }
}
