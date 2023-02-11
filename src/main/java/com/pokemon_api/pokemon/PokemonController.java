package com.pokemon_api.pokemon;

import com.pokemon_api.pokemon.forms.PokemonCreationForm;
import com.pokemon_api.pokemon.forms.PokemonUpdationForm;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import java.util.List;

@Controller("/pokemons")
public class PokemonController {

  PokemonService pokemonService;

  public PokemonController(PokemonService pokemonService) {
    this.pokemonService = pokemonService;
  }

  @Get
  public HttpResponse<List<Pokemon>> fetchAll() {
    return HttpResponse.ok(pokemonService.get());
  }

  @Get("/{id}")
  public HttpResponse<Pokemon> fetch(@PathVariable("id") int id) {
    return HttpResponse.ok(pokemonService.getById(id));
  }

  @Post
  public HttpResponse<Pokemon> create(@Body PokemonCreationForm pokemonCreationForm) {
    return HttpResponse.created(pokemonService.create(pokemonCreationForm));
  }

  @Put
  public HttpResponse<Pokemon> update(@Body PokemonUpdationForm pokemonUpdationForm) {
    return HttpResponse.accepted().body(pokemonService.update(pokemonUpdationForm));
  }

  @Delete("/{id}")
  public HttpResponse<Pokemon> delete(@PathVariable("id") int id) {
    return HttpResponse.noContent().body(pokemonService.delete(id));
  }
}
