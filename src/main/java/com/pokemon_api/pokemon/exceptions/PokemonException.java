package com.pokemon_api.pokemon.exceptions;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class PokemonException extends RuntimeException {
  @JsonProperty(value = "message")
  private final String message;

  public PokemonException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
