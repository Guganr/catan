package br.com.gustavonori.catan.model.interfaces;

import br.com.gustavonori.catan.model.board.BoardBuilder;
import br.com.gustavonori.catan.model.models.elements.Elements;
import br.com.gustavonori.catan.model.services.PlayerService;

import java.util.List;
import java.util.Map;

public interface ConstructionsBuilder {

    void build(PlayerService playerService);

    void specificAction(PlayerService playerService);

    boolean checkPosition(BoardBuilder board, List<PlayerService> playerServiceList, String position);

}
