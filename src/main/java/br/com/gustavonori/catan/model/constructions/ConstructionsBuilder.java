package br.com.gustavonori.catan.model.constructions;

import br.com.gustavonori.catan.model.player.Player;
import br.com.gustavonori.catan.model.player.PlayerService;

public interface ConstructionsBuilder {

    void build(PlayerService player);
}
