package com.pokemon_api.pokemon;

import com.pokemon_api.power.Power;
import javax.persistence.*;

@Entity
@Table(name = "pokemon")
public class Pokemon {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(unique = true, nullable = false)
  private String name;

  @ManyToOne
  @JoinColumn(referencedColumnName = "id", name = "power")
  private Power power;

  @Column(nullable = false)
  private String imageUrl = "";

  public Pokemon() {}

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
