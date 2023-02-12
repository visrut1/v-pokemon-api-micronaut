package com.pokemon_api.power;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;

public class PowerServiceShould {
  private PowerRepository powerRepository;
  private PowerService powerService;

  private Power power1;

  private Power power2;

  @BeforeEach
  void setUp() {
    powerRepository = Mockito.mock(PowerRepository.class);
    powerService = new PowerService(powerRepository);
    power1 = new Power(1, "grass");
    power2 = new Power(2, "electric");
  }

  @Test
  void get() {
    Mockito.when(powerRepository.findAll()).thenReturn(List.of(power1, power2));
    powerService.get();
    Mockito.verify(powerRepository).findAll();
  }

  @Test
  void getByName() {
    Mockito.when(powerRepository.findByName(anyString())).thenReturn(power1);
    powerService.getByName("fire");
    Mockito.verify(powerRepository).findByName(anyString());
  }

  @Test
  void create() {
    Mockito.when(powerRepository.save(Mockito.any())).thenReturn(power1);

    var returnedPower = powerService.create(power1);

    Mockito.verify(powerRepository).save(Mockito.any());

    Assertions.assertThat(returnedPower).isEqualTo(power1);
  }
}
