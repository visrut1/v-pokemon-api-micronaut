package com.pokemon_api.power;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
interface PowerRepository extends CrudRepository<Power, Integer> {
  Power getByName(String name);
}
