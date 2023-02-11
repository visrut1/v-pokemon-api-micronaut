package com.pokemon_api.pokemon;

import java.util.List;

import com.pokemon_api.power.Power;
import com.pokemon_api.power.PowerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PokemonServiceIT {
  private PokemonRepository pokemonRepository;
  private PokemonService pokemonService;
  private Pokemon pokemon1;
  private Pokemon pokemon2;

  @BeforeEach
  void setUp() {
    Power power1 = new Power(1, "grass");
    Power power2 = new Power(2, "electric");

    pokemon1 = new Pokemon(1, "Bulbasaur", power1, "imageUrl");
    pokemon2 = new Pokemon(2, "Pikachu", power2, "imageUrl");

    pokemonRepository = Mockito.mock(PokemonRepository.class);
    PowerService powerService = Mockito.mock(PowerService.class);

    pokemonService = new PokemonService(pokemonRepository, powerService);
  }

  @Test()
  void shouldReturnPokemonList() {
    Mockito.when(pokemonRepository.findAll()).thenReturn(List.of(pokemon1, pokemon2));

    List<Pokemon> pokemonList = pokemonService.get();

    Mockito.verify(pokemonRepository).findAll();
    Assertions.assertThat(pokemonList)
        .containsExactlyInAnyOrderElementsOf(List.of(pokemon2, pokemon1));
  }
}
