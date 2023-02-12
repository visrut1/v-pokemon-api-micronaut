package com.pokemon_api.pokemon;

import com.pokemon_api.power.Power;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "pokemon")
public class Pokemon {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @NotEmpty
  @Column(unique = true, nullable = false)
  private String name;

  @ManyToOne
  @JoinColumn(referencedColumnName = "id", name = "power")
  private Power power;

  @Column(nullable = false)
  private String imageUrl = "";

  public Pokemon() {}

  public Pokemon(int id, String name, Power power, String imageUrl) {
    this.id = id;
    this.name = name;
    this.power = power;
    this.imageUrl = imageUrl;
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

  public Power getPower() {
    return power;
  }

  public void setPower(Power power) {
    this.power = power;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
