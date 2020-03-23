package br.com.gustavonori.catan.model.constructions;

import br.com.gustavonori.catan.model.elements.Element;
import br.com.gustavonori.catan.model.elements.Elements;
import br.com.gustavonori.catan.model.player.Player;
import br.com.gustavonori.catan.model.player.PlayerService;
import br.com.gustavonori.catan.model.player.RemovingElementException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class Constructions implements ConstructionsBuilder {
    private String name;
    private int points;
    private Map<Elements, Integer> elementsToBuild;

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
    public void build(PlayerService playerService) {
        Player player = playerService.getPlayer();
        if (checkElements(player.getElements())) {
            try {
                playerService.removingElements(this.elementsToBuild);
            } catch (RemovingElementException removingElementException) {
                removingElementException.printStackTrace();
            }
            player.getConstructions().add(this);
        }
    }
}
