package demo;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

@WebSocket
public class WebSocketHandler {

  static Map<Session, Session> sessionMap = new ConcurrentHashMap<>();

  @OnWebSocketConnect
  public void connected(Session session) throws IOException {
    System.out.println("A client has connected");
  }

  @OnWebSocketClose
  public void closed(Session session, int statusCode, String reason) {
    System.out.println("A client has disconnected");
  }

  @OnWebSocketMessage
  public void message(Session session, String message) throws IOException {
    System.out.println("Got: " + message);   // Print message
  }

  public static void broadcast(String m)
  {
    sessionMap.keySet().forEach(session -> {
      try{
        session.getRemote().sendString(m);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    });
  }
}