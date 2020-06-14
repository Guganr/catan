package br.com.gustavonori.catan.model.board.positions;

import br.com.gustavonori.catan.model.builders.BuildPoints;
import br.com.gustavonori.catan.model.builders.CityBuilder;
import br.com.gustavonori.catan.model.builders.Constructions;
import br.com.gustavonori.catan.model.builders.VillageBuilder;

public class Intersection {
    private int id;
    BuildPoints build;

    public Intersection(int id) {
        this.id = id;
    }

    public Intersection() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
