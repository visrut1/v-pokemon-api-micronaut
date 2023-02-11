package com.pokemon_api.pokemon;

import com.pokemon_api.pokemon.forms.PokemonCreationForm;
import com.pokemon_api.pokemon.forms.PokemonUpdationForm;
import com.pokemon_api.power.Power;
import com.pokemon_api.power.PowerService;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;
import java.util.List;

@Singleton
public class PokemonService {
  PokemonRepository pokemonRepository;
  PowerService powerService;

  public PokemonService(PokemonRepository pokemonRepository, PowerService powerService) {
    this.pokemonRepository = pokemonRepository;
    this.powerService = powerService;
  }

  public List<Pokemon> get() {
    return (List<Pokemon>) pokemonRepository.findAll();
  }

  public Pokemon getById(int id) {
    return pokemonRepository.getById(id);
  }

  @Transactional
  public Pokemon create(PokemonCreationForm pokemonCreationForm) {
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
    Pokemon pokemon = pokemonRepository.findById(id).orElseThrow();
    pokemonRepository.deleteById(id);
    return pokemon;
  }

  public Pokemon update(PokemonUpdationForm pokemonUpdationForm) {
    Pokemon pokemon = pokemonRepository.getById(pokemonUpdationForm.getId());
    pokemon.setName(pokemonUpdationForm.getName());
    pokemon.setImageUrl(pokemonUpdationForm.getImageUrl());
    Power power = powerService.getByName(pokemonUpdationForm.getPowerName());
    pokemon.setPower(power);
    return pokemonRepository.update(pokemon);
  }
}
