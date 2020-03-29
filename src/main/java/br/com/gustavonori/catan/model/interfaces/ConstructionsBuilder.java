package br.com.gustavonori.catan.model.interfaces;

import br.com.gustavonori.catan.model.services.PlayerService;

public interface ConstructionsBuilder {

    void build(PlayerService playerService);

    void specificAction(PlayerService playerService);
}
