package br.com.gustavonori.catan.model.constructions.develpment;

import br.com.gustavonori.catan.model.constructions.ConstructionsBuilder;
import br.com.gustavonori.catan.model.elements.Element;
import br.com.gustavonori.catan.model.elements.Elements;
import br.com.gustavonori.catan.model.player.Player;
import br.com.gustavonori.catan.model.player.PlayerService;
import br.com.gustavonori.catan.model.player.RemovingElementException;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static br.com.gustavonori.catan.model.elements.Elements.*;

@Entity
@Table(name = "DEVELOPMENT_CARD")
public class DevelopmentCard implements ConstructionsBuilder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    protected String name;
    protected String description;
    protected int points;
    protected int picked;
    @Transient
    protected boolean flipped;
    @Transient
    public static Map<Elements, Integer> elementsToBuild = (Map.of(
            SHEEP,1,
            WHEAT,1,
            ROCK,1
    ));

    public DevelopmentCard() {

    }

    public DevelopmentCard(DevelopmentCard developmentCard) {
        this.id = developmentCard.getId();
        this.name = developmentCard.getName();
        this.description = developmentCard.getDescription();
        this.points = developmentCard.getPoints();
        this.picked = developmentCard.getPicked();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPicked() {
        return picked;
    }

    public void setPicked(int picked) {
        this.picked = picked;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    public boolean checkElements(List<Element> elements) {
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

    @Override
    public void build(PlayerService playerService) {
        Player player = playerService.getPlayer();
        if (checkElements(player.getElements())) {
            try {
                playerService.removingElements(elementsToBuild);
            } catch (RemovingElementException removingElementException) {
                removingElementException.printStackTrace();
            }
            player.getDevelopmentCards().add(this);
        }
    }

    @Override
    public void specificAction(PlayerService playerService) {

    }
}
