package demo;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;
/*
*import static com.mongodb.client.model.Filters.*;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sun.tools.javac.util.List;
import java.util.ArrayList;
import org.bson.Document;
 */

public class SparkDemo {
  static List<MessageDto> messageList = new ArrayList<>();
  static Gson gson = new Gson();
  public static void main(String[] args) {
    port(1235);

    get("/test-endpoint", (req,res) ->
    {
      System.out.println(" ");
      System.out.println(req.queryMap("name").value());
      return "Hello " + req.queryMap("name").value();
    });

    post("/submit-message", (req, res) -> {
      String bodyString = req.body();
      System.out.println(bodyString);
      MessageDto newMessage = gson.fromJson(bodyString, MessageDto.class);
      messageList.add(newMessage);
      return messageList.size();
    });

    get("get-messages", (req,res) ->
    {
      return gson.toJson(messageList);
    });

  }
}
