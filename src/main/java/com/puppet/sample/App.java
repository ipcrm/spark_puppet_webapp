package com.puppet.sample;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class App
{

  public String enMsg()
  {
    return "Hello World!";
  }

  public static void main(String[] args) {

    get("/", (request, response) -> {
      response.redirect("/en");
      return null;
    });

    get("/:lang", App::helloWorld, new ThymeleafTemplateEngine());

  }

  public static ModelAndView helloWorld(Request req, Response res) {
    Map<String, Object> params = new HashMap<>();


    if (req.params(":lang").equals("en")) {
      App test = new App();
      params.put("lang", test.enMsg());
    }

    return new ModelAndView(params, "index");
  }

}
