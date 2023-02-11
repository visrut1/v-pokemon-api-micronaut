package com.pokemon_api.power;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import java.util.List;

@Controller("/powers")
public class PowerController {
  PowerService powerService;

  public PowerController(PowerService powerService) {
    this.powerService = powerService;
  }

  @Get()
  public HttpResponse<List<Power>> get() {
    return HttpResponse.ok(powerService.get());
  }

  @Post
  public HttpResponse<Power> post(@Body Power power) {
    return HttpResponse.created(powerService.create(power));
  }
}
