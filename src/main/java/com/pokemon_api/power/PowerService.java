package com.pokemon_api.power;

import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class PowerService {
  PowerRepository powerRepository;

  public PowerService(PowerRepository powerRepository) {
    this.powerRepository = powerRepository;
  }

  public List<Power> get() {
    return (List<Power>) powerRepository.findAll();
  }

  public Power getByName(String name) {
    return powerRepository.getByName(name);
  }

  public Power create(Power power) {
    return powerRepository.save(power);
  }
}
