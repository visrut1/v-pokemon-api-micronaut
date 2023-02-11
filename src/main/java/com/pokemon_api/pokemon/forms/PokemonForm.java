package com.pokemon_api.pokemon.forms;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PokemonForm {
  private final String name;
  private final String powerName;

  @JsonCreator
  public PokemonForm(
      @JsonProperty("name") String name, @JsonProperty("powerName") String powerName) {
    this.name = name;
    this.powerName = powerName;
  }

  public String getName() {
    return name;
  }

  public String getPowerName() {
    return powerName;
  }
}
