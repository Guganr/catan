package br.com.gustavonori.catan.model.services;


import br.com.gustavonori.catan.model.models.developmentcards.DevelopmentCard;
import br.com.gustavonori.catan.model.models.elements.Element;
import br.com.gustavonori.catan.model.models.elements.Elements;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class DevelopmentCardService {

    DevelopmentCard developmentCardBean;
    public DevelopmentCardService(){
        developmentCardBean = new DevelopmentCard();
    }
    public boolean newDevelopmentCard(PlayerService playerService)  {
        if (checkElements(playerService.getPlayer().getElements())) {
            List<DevelopmentCard> newDevelopmentCard = getADevelopmentCard();
            if (!newDevelopmentCard.isEmpty()) {
                newDevelopmentCard.get(0).build(playerService);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean checkElements(List<Element> elements) {
        Map<Elements, Integer> elementsToBuild = developmentCardBean.getElementsToBuild();
        AtomicBoolean check = new AtomicBoolean(true);
        for (Element element : elements) {
            elementsToBuild.forEach((el, qty) -> {
                if (element.getName().equals(el)) {
                    if (element.getQuantity() < qty){
                        check.set(false);
                    }
                }
            });
        }
        return check.get();
    }

    public List<DevelopmentCard> getADevelopmentCard() {
        List<Integer> availableCards = developmentCardBean.developmentCardsAvailable();
        Collections.shuffle(availableCards);
        if (availableCards.isEmpty())
            return Collections.emptyList();
        else
            return List.of(developmentCardBean.get(availableCards.get(0)));
    }
}
