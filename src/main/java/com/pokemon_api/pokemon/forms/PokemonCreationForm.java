package com.pokemon_api.pokemon.forms;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PokemonCreationForm extends PokemonForm {

  @JsonCreator
  public PokemonCreationForm(
      @JsonProperty("name") String name, @JsonProperty("powerName") String powerName) {
    super(name, powerName);
  }
}
