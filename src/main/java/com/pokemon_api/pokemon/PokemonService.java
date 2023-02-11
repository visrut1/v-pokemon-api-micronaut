package com.pokemon_api.pokemon;

import com.pokemon_api.pokemon.exceptions.EntityAlreadyExist;
import com.pokemon_api.pokemon.exceptions.EntityNotFound;
import com.pokemon_api.pokemon.forms.PokemonCreationForm;
import com.pokemon_api.pokemon.forms.PokemonUpdationForm;
import com.pokemon_api.power.Power;
import com.pokemon_api.power.PowerService;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Singleton
public class PokemonService {
  PokemonRepository pokemonRepository;
  PowerService powerService;

  public PokemonService(PokemonRepository pokemonRepository, PowerService powerService) {
    this.pokemonRepository = pokemonRepository;
    this.powerService = powerService;
  }

  private Pokemon getPokemonById(int id) {
    return pokemonRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFound("pokemon with id %s not exists.".formatted(id)));
  }

  public List<Pokemon> get() {
    return (List<Pokemon>) pokemonRepository.findAll();
  }

  public Pokemon getById(int id) {
    return getPokemonById(id);
  }

  @Transactional
  public Pokemon create(PokemonCreationForm pokemonCreationForm) {
    Optional<Pokemon> _pokemon = pokemonRepository.findByName(pokemonCreationForm.getName());
    if (_pokemon.isPresent())
      throw new EntityAlreadyExist(
          "Pokemon with name '%s' already exists.".formatted(_pokemon.get().getName()));
    Pokemon pokemon = new Pokemon();
    Power power = powerService.getByName(pokemonCreationForm.getPowerName());
    pokemon.setName(pokemonCreationForm.getName());
    pokemon.setPower(power);
    pokemonRepository.save(pokemon);
    pokemon.setImageUrl(
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"
            + pokemon.getId()
            + ".png");
    return pokemon;
  }

  public Pokemon delete(int id) {
    Pokemon pokemon = getPokemonById(id);
    pokemonRepository.deleteById(id);
    return pokemon;
  }

  public Pokemon update(PokemonUpdationForm pokemonUpdationForm) {
    Pokemon pokemon = getPokemonById(pokemonUpdationForm.getId());
    pokemon.setName(pokemonUpdationForm.getName());
    pokemon.setImageUrl(pokemonUpdationForm.getImageUrl());
    Power power = powerService.getByName(pokemonUpdationForm.getPowerName());
    pokemon.setPower(power);
    return pokemonRepository.update(pokemon);
  }
}
