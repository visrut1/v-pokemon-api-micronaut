package com.pokemon_api.pokemon;

import java.util.List;
import java.util.Optional;

import com.pokemon_api.pokemon.exceptions.EmptyValue;
import com.pokemon_api.pokemon.exceptions.EntityAlreadyExist;
import com.pokemon_api.pokemon.exceptions.EntityNotFound;
import com.pokemon_api.pokemon.forms.PokemonCreationForm;
import com.pokemon_api.pokemon.forms.PokemonUpdationForm;
import com.pokemon_api.power.Power;
import com.pokemon_api.power.PowerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static java.util.Optional.empty;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

class PokemonServiceShould {
  private PokemonRepository pokemonRepository;
  private PokemonService pokemonService;
  private Pokemon pokemon1;
  private Pokemon pokemon2;
  private Power power1;
  private Power power2;

  @BeforeEach
  void setUp() {
    power1 = new Power(1, "grass");
    power2 = new Power(2, "electric");

    pokemon1 = new Pokemon(1, "Bulbasaur", power1, "imageUrl");
    pokemon2 = new Pokemon(2, "Pikachu", power2, "imageUrl");

    pokemonRepository = Mockito.mock(PokemonRepository.class);
    PowerService powerService = Mockito.mock(PowerService.class);

    pokemonService = new PokemonService(pokemonRepository, powerService);
  }

  @Test
  void shouldGet() {
    Mockito.when(pokemonRepository.findAll()).thenReturn(List.of(pokemon1, pokemon2));
    List<Pokemon> pokemonList = pokemonService.get();
    Mockito.verify(pokemonRepository).findAll();
    Assertions.assertThat(pokemonList)
        .containsExactlyInAnyOrderElementsOf(List.of(pokemon2, pokemon1));
  }

  @Test
  void shouldGetById() {
    Mockito.when(pokemonRepository.findById(anyInt())).thenReturn(Optional.ofNullable(pokemon1));
    Pokemon pokemonFromService = pokemonService.getById(1);
    Mockito.verify(pokemonRepository).findById(anyInt());
    Assertions.assertThat(pokemonFromService).isEqualTo(pokemon1);
  }

  @Test
  void shouldCreate() {
    PokemonCreationForm pokemonCreationForm = new PokemonCreationForm("Bulbasaur", "grass");
    Mockito.when(pokemonRepository.save(Mockito.any())).thenReturn(pokemon1);
    var returnedPokemon = pokemonService.create(pokemonCreationForm);
    Mockito.verify(pokemonRepository).save(Mockito.any());
    Assertions.assertThat(returnedPokemon).isEqualTo(pokemon1);
  }

  @Test
  void shouldDelete() {
    Mockito.when(pokemonRepository.findById(anyInt())).thenReturn(Optional.ofNullable(pokemon1));
    pokemonService.delete(anyInt());
    Mockito.verify(pokemonRepository).deleteById(anyInt());
  }

  @Test
  void shouldUpdate() {
    PokemonUpdationForm pokemonUpdationForm =
        new PokemonUpdationForm(1, "Bulbasaur", "grass", "image.com/1.png");

    Mockito.when(pokemonRepository.update(Mockito.any())).thenReturn(pokemon1);
    Mockito.when(pokemonRepository.findByName(anyString())).thenReturn(empty());
    Mockito.when(pokemonRepository.findById(anyInt())).thenReturn(Optional.ofNullable(pokemon1));

    var returnedPokemon = pokemonService.update(pokemonUpdationForm);

    Mockito.verify(pokemonRepository).update(Mockito.any());
    Mockito.verify(pokemonRepository).findById(anyInt());
    Mockito.verify(pokemonRepository).findByNameIgnoreCase(anyString());

    Assertions.assertThat(returnedPokemon).isEqualTo(pokemon1);
  }

  @Test
  void shouldThrowExceptionOnEmptyName() {
    PokemonCreationForm pokemonCreationForm = new PokemonCreationForm("", "");
    Assertions.assertThatThrownBy(
        () -> {
          pokemonService.create(pokemonCreationForm);
        });
    Assertions.catchThrowableOfType(
        () -> pokemonService.create(pokemonCreationForm), EmptyValue.class);
  }

  @Test
  void shouldThrowExceptionOnEmptyPower() {
    PokemonCreationForm pokemonCreationForm = new PokemonCreationForm("pikachu", "");
    Assertions.assertThatThrownBy(
        () -> {
          pokemonService.create(pokemonCreationForm);
        });
    Assertions.catchThrowableOfType(
        () -> pokemonService.create(pokemonCreationForm), EmptyValue.class);
  }

  @Test
  void shouldThrowExceptionIfPokemonAlreadyExistOnCreate() {
    Mockito.when(pokemonRepository.findByName(anyString()))
        .thenReturn(Optional.ofNullable(pokemon1));

    PokemonCreationForm pokemonCreationForm = new PokemonCreationForm("Bulbasaur", "imageUrl");
    Assertions.catchThrowableOfType(
        () -> pokemonService.create(pokemonCreationForm), EntityAlreadyExist.class);

    Mockito.verify(pokemonRepository).findByName(anyString());
  }

  @Test
  void shouldReturnPokemonByName() {
    Mockito.when(pokemonRepository.findByNameIgnoreCase(anyString()))
        .thenReturn(Optional.ofNullable(pokemon1));

    Pokemon returnedPokemon = pokemonService.getByName(anyString());

    Mockito.verify(pokemonRepository).findByNameIgnoreCase(anyString());
    Assertions.assertThat(returnedPokemon).isEqualTo(pokemon1);
  }

  @Test
  void shouldThrowExceptionWhenNotFoundByName() {
    Mockito.when(pokemonRepository.findByNameIgnoreCase(anyString())).thenReturn(Optional.empty());
    Assertions.catchThrowableOfType(
        () -> pokemonService.getByName(anyString()), EntityNotFound.class);
    Mockito.verify(pokemonRepository).findByNameIgnoreCase(anyString());
  }
}
