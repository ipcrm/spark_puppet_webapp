package com.puppet.sample;
import com.puppet.sample.langs.Sp;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.before;

public class App
{

  public String enMsg()
  {
    return "Hello World!";
  }

  private static String requestInfoToString(Request request) {
    StringBuilder sb = new StringBuilder();
    sb.append(request.requestMethod());
    sb.append(" " + request.url());
    sb.append(" " + request.body());
    return sb.toString();
  }

  public static void main(String[] args) {

    Spark.port(8080);
    Spark.threadPool(1000, 1000,60000);

    before((request, response) -> {
        System.out.println(requestInfoToString(request));
    });

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
    } else if (req.params(":lang").equals("sp")) {
        Sp sp = new Sp();
        params.put("lang", sp.Msg());
    } else {
        String msg = "I don't know that language ~> ";
        msg += req.params(":lang");
        params.put("lang", msg); 
    } 

    return new ModelAndView(params, "index");
  }

}
