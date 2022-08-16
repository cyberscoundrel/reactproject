package server;

import com.google.gson.Gson;
import dao.InquiryDao;
import dao.ListingDao;
import dao.MongoDao;
import demo.MessageDto;
import demo.WebSocketHandler;
import dto.InquiryDto;
import dto.ListingDto;
import mongo.MongoConnection;
import org.bson.BsonDocument;
import org.bson.BsonRegularExpression;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.print.Doc;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mongodb.client.model.Filters.or;
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

public class Server {
    static List<MessageDto> messageList = new ArrayList<>();
    static Gson gson = new Gson();
    static String staticSalt = "staticsalt";

    public static String hash512(String s)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            byte[] bytes = md.digest(s.getBytes());

            BigInteger b = new BigInteger(1, bytes);

            String hash = b.toString(16);

            while(hash.length() < 32)
            {
                hash = "0" + hash;
            }

            return hash;
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
        //return null;

    }

    public static void main(String[] args) {
        port(1235);

        //MongoConnection connection = new MongoConnection();
        ListingDao listingDao = ListingDao.getInstance();
        InquiryDao inquiryDao = InquiryDao.getInstance();
        //MongoDao<Lis>

        webSocket("/ws", WebSocketHandler.class);


        /*post("/submit-listing", (req, res) -> {
            String bodyString = req.body();
            System.out.println(bodyString);
            MessageDto newMessage = gson.fromJson(bodyString, MessageDto.class);
            messageList.add(newMessage);
            return messageList.size();
        });

        get("/listing")*/
        path("/api", () -> {
           path("/listing", () -> {
              get("/:listingid", (req, res) -> {
                  //List<ListingDto> list = listingDao.getItems();
                  req.params("listingid");
                  System.out.println(req.params("listingid"));
                  List<ListingDto> l = listingDao.get(new Document("entryId",req.params("listingid")));
                  System.out.println(l.size());
                  //gson.toJson(list);
                  //System.out.println(gson.toJson(list));
                  System.out.println("getlisting");
                  res.body(gson.toJson(l));
                  return gson.toJson(l);

              });
              get("/",(req, res) -> {
                 List<ListingDto> list = listingDao.getItems();
                 res.body(gson.toJson(list));
                 return gson.toJson(list);
              });
              get("/search/:searchterm", (req, res) -> {
                  //Document doc = new Document();
                  System.out.println(req.params("searchterm"));
                  //doc.parse(req.body());
                  Bson filter = or(
                          new Document("description",new BsonRegularExpression(req.params("searchterm"))),
                          new Document("title",new BsonRegularExpression(req.params("searchterm"))),
                          new Document("type",new BsonRegularExpression(req.params("searchterm")))
                  );
                  //doc.put("description",new BsonRegularExpression(req.params("searchterm")));
                  //System.out.println(doc.toJson());
                  //doc.put("title",new BsonRegularExpression(req.params("searchterm")));
                  //doc.put("type",new BsonRegularExpression(req.params("searchterm")));
                  List<ListingDto> l = listingDao.get(filter);
                  System.out.println(l.size());
                 return gson.toJson(l);
              });
              get("/filter", (req, res) -> {
                 return null;
              });
              post("/", (req, res) -> {
                  System.out.println("postListing");
                  UUID uuid = UUID.randomUUID();
                  System.out.println(req.body());
                  ListingDto l = gson.fromJson(req.body(), ListingDto.class);
                  l.entryId = "" + uuid;
                  Document doc = new Document();
                  if(l.password != null)
                  {
                      l.password = hash512(staticSalt + l.password);
                  }
                  else
                  {
                      doc.put("error","password invalid");
                      System.out.println("password error");
                      res.body(doc.toJson());
                      return doc.toJson();

                  }
                  //Document doc = new Document();
                  doc.put("entryId","" + uuid);
                  listingDao.put(l);
                  //listingDao.put(new ListingDto("" + uuid,"sdfouifsdajkl","dfihuj",8,"sfdadf"));
                  System.out.println("postListing");
                  res.body(doc.toJson());
                  return doc.toJson();

              });
              delete("/:listingid", (req, res) -> {
                  Document b = new Document();
                  b = Document.parse(req.body());

                  System.out.println(b.toJson());
                  List<ListingDto> l;
                  if(b.containsKey("password"))
                  {
                      System.out.println("has password");
                      l = listingDao.get(new Document("entryId",req.params("listingid")));
                      if(l.size() == 1 && l.get(0).password.equals(hash512(staticSalt + b.get("password",String.class))))
                      {
                          listingDao.delete(req.params("listingid"));
                          System.out.println("deleted" + req.params("listingid"));
                          return null;
                      }


                  }
                  //listingDao.delete(req.params("listingid"));
                  //System.out.println("deleted" + req.params("listingid"));
                  System.out.println("error could not delete listing");
                  return null;

              });
           });
           path("/inquire", () -> {
              get("/:listingid", (req, res) -> {
                  System.out.println("getINquire");
                  List<InquiryDto> l = inquiryDao.get(new Document("postID",req.params("listingid")));
                  Document d = new Document();
                  d = Document.parse(req.body());
                  if(d.containsKey("password"))
                  {
                      if(hash512(staticSalt + d.get("password", String.class)).equals(l.get(0).password))
                      {
                          System.out.println("password correct");
                          res.body(gson.toJson(l));
                          return gson.toJson(l);
                      }
                      /*for (InquiryDto i: l)
                      {
                          i.userEmail = null;
                          i.password = null;
                          i.postID = null;
                          i.inquiryID = null;

                      }*/


                  }
                  for (InquiryDto i: l)
                  {
                      i.userEmail = null;
                      i.password = null;
                      i.postID = null;
                      i.inquiryID = null;

                  }
                  System.out.println(l.size());
                  res.body(gson.toJson(l));
                 return gson.toJson(l);
              });
               get("/byemail", (req, res) -> {
                   System.out.println("getINquire");
                   //List<InquiryDto> l = inquiryDao.get(new Document("postID",req.params("listingid")));
                   Document d = new Document();
                   d = Document.parse(req.body());
                   if(d.containsKey("email") && d.containsKey("password"))
                   {
                       List<InquiryDto> l = inquiryDao.get(new Document("userEmail", d.get("email",String.class)));
                       if(hash512(staticSalt + d.getString("password")).equals(l.get(0).password))
                       {
                           System.out.println("password correct");
                           res.body(gson.toJson(l));
                           return gson.toJson(l);
                       }


                   }
                   //System.out.println(l.size());
                   //res.body(gson.toJson(l));
                   return gson.toJson(new Document("error","invalid arguments"));
               });
              post("/:listingid", (req, res) -> {
                  System.out.println("postinquire");
                  UUID uuid = UUID.randomUUID();
                  InquiryDto i = gson.fromJson(req.body(), InquiryDto.class);
                  List<ListingDto> l = listingDao.get(new Document("entryId",req.params("listingid")));
                  i.inquiryID = "" + uuid;
                  i.password = l.get(0).password;
                  inquiryDao.put(i);
                  Document doc = new Document("inquiryID", "" + uuid);
                  res.body(doc.toJson());
                 return doc.toJson();
              });
              delete("/:inquiryid", (req, res) -> {
                  Document d = new Document();
                  d = Document.parse(req.body());
                  if(d.containsKey("entryId"))
                  {
                      System.out.println("contains");
                      System.out.println(d.getString("entryId"));
                      List<ListingDto> l = listingDao.get(new Document("entryId", d.getString("entryId")));
                      if(l.size() == 1)
                      {
                          System.out.println("size");
                          if(d.containsKey("password") && l.get(0).password.equals(hash512(staticSalt + d.get("password", String.class))))
                          {
                              inquiryDao.delete(req.params("inquiryid"));
                              System.out.println("deleted" + req.params("inquiryid"));
                              return null;

                          }

                      }

                  }
                  //List<ListingDto> l = listingDao.get()
                  //inquiryDao.delete(req.params("inquiryid"));
                  //System.out.println("deleted " + req.params("inquiryid"));
                  System.out.println("error could not delete inquiry");
                  return null;
              });
           });
        });

    }
}