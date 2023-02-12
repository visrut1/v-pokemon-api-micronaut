package com.pokemon_api.power;

import javax.persistence.*;

@Entity
@Table(name = "power")
public class Power {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;

  @Column(nullable = false, unique = true)
  String name;

  public Power() {}

  public Power(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
