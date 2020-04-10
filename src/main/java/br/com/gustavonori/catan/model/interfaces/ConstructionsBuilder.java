package br.com.gustavonori.catan.model.interfaces;

import br.com.gustavonori.catan.model.models.elements.Elements;
import br.com.gustavonori.catan.model.services.PlayerService;

import java.util.Map;

public interface ConstructionsBuilder {

    void build(PlayerService playerService);

    void specificAction(PlayerService playerService);
}
