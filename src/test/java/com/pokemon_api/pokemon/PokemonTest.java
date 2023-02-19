package com.pokemon_api.pokemon;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@MicronautTest
public class PokemonTest {

  @BeforeEach
  void setUp(RequestSpecification spec) {
    spec.given().body("{\"name\": \"Pikachu\", \"powerName\": \"fire\"}").post("/pokemons");
  }

  @Test
  @DisplayName("/pokemons (GET)")
  void testGetAllPokemons(RequestSpecification spec) {
    spec.when().get("/pokemons").then().statusCode(HttpStatus.SC_OK);
  }

  @Test
  @DisplayName("/pokemons (POST)")
  void testAddPokemon(RequestSpecification spec) {
    spec.given()
        .body("{\"name\": \"TestPokemon\", \"powerName\": \"fire\"}")
        .header("Content-Type", "application/json")
        .when()
        .post("/pokemons")
        .then()
        .statusCode(HttpStatus.SC_CREATED)
        .assertThat()
        .body("id", Matchers.notNullValue())
        .body("name", Matchers.equalTo("TestPokemon"))
        .body("power.name", Matchers.equalTo("Fire"));
  }

  @Test
  @DisplayName("/pokemons/name/{name} (GET)")
  void testGetByName(RequestSpecification spec) {
    spec.given()
        .pathParam("name", "pikachu")
        .when()
        .get("/pokemons/name/{name}")
        .then()
        .statusCode(HttpStatus.SC_OK)
        .assertThat()
        .body("id", Matchers.notNullValue());
  }
}
