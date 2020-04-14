package br.com.gustavonori.catan.model.builders;

import br.com.gustavonori.catan.model.board.BoardBuilder;
import br.com.gustavonori.catan.model.interfaces.ConstructionsBuilder;
import br.com.gustavonori.catan.model.models.elements.Element;
import br.com.gustavonori.catan.model.models.elements.Elements;
import br.com.gustavonori.catan.model.models.player.Player;
import br.com.gustavonori.catan.model.services.PlayerService;
import br.com.gustavonori.catan.model.models.player.RemovingElementException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Integer.parseInt;

public class Constructions implements ConstructionsBuilder {
    private String name;
    private int points;
    private Map<Elements, Integer> elementsToBuild;
    private String position;

    public Constructions(String name, int points, Map<Elements, Integer> elementsToBuild) {
        this.name = name;
        this.points = points;
        this.elementsToBuild = elementsToBuild;
    }

    public String getName() { return this.name; }

    public int getPoints() {
        return points;
    }

    public Map<Elements, Integer> getElementsToBuild() {
        return elementsToBuild;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    protected boolean isRoadPosition(String position) {
        int subPosition = parseInt(position.substring(0, position.length() - 1));
        return subPosition % 2 == 0;
    }

    public boolean checkElements(List<Element> elements) {
        AtomicBoolean check = new AtomicBoolean(true);
        for (Element element : elements) {
            this.elementsToBuild.forEach((el, qty) -> {
               if (element.getName().equals(el)) {
                   if (element.getQuantity() < qty){
                       check.set(false);
                   }
               }
            });
        }
        return check.get();
    }

    @Override
    public boolean checkPosition(BoardBuilder board, List<PlayerService> playerServiceList, String position){
        AtomicBoolean check = new AtomicBoolean(true);
        if (board.getMapping().contains(position)) {
            playerServiceList.forEach(playerService -> {
                playerService.getPlayer().getConstructions().forEach((constructions) -> {
                    if (constructions.getPosition().equals(position))
                        check.set(false);
                });
            });
        } else {
            return false;
        }
        return check.get();
    }

    @Override
    public void build(PlayerService playerService) {
        Player player = playerService.getPlayer();
        if (checkElements(player.getElements())){
            try {
                playerService.removingElements(this.elementsToBuild);
            } catch (RemovingElementException removingElementException) {
                removingElementException.printStackTrace();
            }
            player.getConstructions().add(this);
        }
    }

    @Override
    public void specificAction(PlayerService playerService) {
        //Nothing to do here
    }

}
