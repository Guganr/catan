package br.com.gustavonori.catan.model.board;

import br.com.gustavonori.catan.model.board.positions.Edge;
import br.com.gustavonori.catan.model.board.positions.Intersection;
import br.com.gustavonori.catan.model.models.player.Player;
import br.com.gustavonori.catan.model.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private Board board;

    public void putRoadOnBoard(PlayerService playerService) {
    }


    public boolean isThereASequenceOfRoads(Intersection intersection, PlayerService playerService) {
        return board.isThereASequenceOfRoads(intersection, playerService);
    }
}
