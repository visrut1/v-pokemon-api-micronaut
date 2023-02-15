package com.pokemon_api.pokemon;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

@MicronautTest
public class PokemonTest {
  @Test
  void testGetAllPokemons(RequestSpecification spec) {
    spec.when().get("/pokemons").then().statusCode(HttpStatus.SC_OK);
  }
}
