package com.pokemon_api.pokemon.forms;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PokemonUpdationForm extends PokemonForm {
  private final String imageUrl;
  private final int id;

  @JsonCreator
  public PokemonUpdationForm(
      @JsonProperty("id") int id,
      @JsonProperty("name") String name,
      @JsonProperty("power") String power,
      @JsonProperty("imageUrl") String imageUrl) {
    super(name, power);
    this.imageUrl = imageUrl;
    this.id = id;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public int getId() {
    return id;
  }
}
