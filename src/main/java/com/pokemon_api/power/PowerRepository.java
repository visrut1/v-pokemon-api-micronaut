package com.pokemon_api.power;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
interface PowerRepository extends CrudRepository<Power, Integer> {
  Optional<Power> findByName(String name);
}
