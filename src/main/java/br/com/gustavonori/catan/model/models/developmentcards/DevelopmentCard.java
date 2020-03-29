package br.com.gustavonori.catan.model.models.developmentcards;

import br.com.gustavonori.catan.model.interfaces.develpment.DevelopmentCards;
import br.com.gustavonori.catan.model.models.player.Player;
import br.com.gustavonori.catan.model.models.developmentcards.pointcards.*;
import br.com.gustavonori.catan.model.models.developmentcards.progresscards.InventionCard;
import br.com.gustavonori.catan.model.models.developmentcards.progresscards.MonopolyCard;
import br.com.gustavonori.catan.model.models.developmentcards.progresscards.RoadBuilderCard;
import br.com.gustavonori.catan.model.models.elements.Elements;
import br.com.gustavonori.catan.model.services.PlayerService;
import br.com.gustavonori.catan.model.models.player.RemovingElementException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.com.gustavonori.catan.model.models.elements.Elements.*;

public class DevelopmentCard implements DevelopmentCards {
    protected String name;
    protected String description;
    protected int points;
    protected boolean picked;
    protected boolean flipped;

    private Map<Elements, Integer> elementsToBuild = (Map.of(
            SHEEP,1,
            WHEAT,1,
            ROCK,1
    ));
    private static Map<Integer, DevelopmentCard> developmentCardsMap = new HashMap<>();
     static {
         developmentCardsMap.put(1, new KnightCard());
         developmentCardsMap.put(2, new KnightCard());
         developmentCardsMap.put(3, new KnightCard());
         developmentCardsMap.put(4, new KnightCard());
         developmentCardsMap.put(5, new KnightCard());
         developmentCardsMap.put(6, new KnightCard());
         developmentCardsMap.put(7, new KnightCard());
         developmentCardsMap.put(8, new KnightCard());
         developmentCardsMap.put(9, new KnightCard());
         developmentCardsMap.put(10, new KnightCard());
         developmentCardsMap.put(11, new KnightCard());
         developmentCardsMap.put(12, new KnightCard());
         developmentCardsMap.put(13, new KnightCard());
         developmentCardsMap.put(14, new KnightCard());
         developmentCardsMap.put(15, new CathedralCard());
         developmentCardsMap.put(16, new ChamberCard());
         developmentCardsMap.put(17, new LibraryCard());
         developmentCardsMap.put(18, new UniversityCard());
         developmentCardsMap.put(19, new InventionCard());
         developmentCardsMap.put(20, new InventionCard());
         developmentCardsMap.put(21, new MonopolyCard());
         developmentCardsMap.put(22, new MonopolyCard());
         developmentCardsMap.put(23, new RoadBuilderCard());
         developmentCardsMap.put(24, new RoadBuilderCard());
         developmentCardsMap.put(25, new MarketCard());
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPoints() {
        return points;
    }

    public boolean isPicked() {
        return picked;
    }

    public void setPicked() { this.picked = true; }

    public void setFlipped() { this.flipped = true; }

    public boolean isFlipped() {
        return flipped;
    }

    public  Map<Elements, Integer> getElementsToBuild() {
        return elementsToBuild;
    }

    public DevelopmentCard get(int id) {
        if (developmentCardsMap.get(id).isPicked())
            return null;
        else
            return developmentCardsMap.get(id);
    }

    public List<Integer> developmentCardsAvailable(){
         List<Integer> idsList = new ArrayList<>();
         developmentCardsMap.forEach((id, card) -> {
             if(get(id) != null) {
                 idsList.add(id);
             }
         });
         return idsList;
    }

    @Override
    public void build(PlayerService playerService) {
        Player player = playerService.getPlayer();
        try {
            playerService.removingElements(elementsToBuild);
        } catch (RemovingElementException removingElementException) {
            removingElementException.printStackTrace();
        }
        player.getDevelopmentCards().add(this);
        this.setPicked();
    }

    public void specificAction(PlayerService playerService) {

    }

    public void doTheFlip(PlayerService playerService) {
        setFlipped();
        specificAction(playerService);
    }
}
