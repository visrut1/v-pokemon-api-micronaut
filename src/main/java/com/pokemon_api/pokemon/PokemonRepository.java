package com.pokemon_api.pokemon;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
interface PokemonRepository extends CrudRepository<Pokemon, Integer> {

  void deleteById(int id);

  Optional<Pokemon> findByName(String name);
}
