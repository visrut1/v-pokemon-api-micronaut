package com.pokemon_api.pokemon;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
interface PokemonRepository extends CrudRepository<Pokemon, Integer> {

  void deleteById(int id);

  Pokemon getById(int id);
}
