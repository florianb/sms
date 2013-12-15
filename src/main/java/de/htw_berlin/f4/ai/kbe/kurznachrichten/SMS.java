package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import java.util.HashSet;
import java.util.HashMap;
import java.util.Date;
import java.util.List;

public class SMS
  implements ShortMessageService
{
  private HashMap<Long, Message> origins;
  private HashMap<String, User> users;
  private HashSet<String> topics;
  
  private Long messageIDCounter, userIDCounter;
  
  public SMS() {
    origins = new HashMap();
    topics = new HashSet();
    users = new HashMap();
    userIDCounter = 0l;
    messageIDCounter = 0l;
  }
  
  protected int getMessageCount() {
    return origins.size();
  }
  
  /**
   * Erzeuge eine neue Ursprungsnachricht. Eine Nachricht ist einem Topic zugeordnet.
   * Erstelle ein Anlegedatum zu der Nachricht. 
   * @throws IllegalArgumentException, wenn die Nachricht länger als 255 Zeichen lang ist.
   * @throws IllegalArgumentException, wenn die Nachricht weniger als 10 Zeichen lang ist.
   * @throws IllegalArgumentException, wenn der Nutzer nicht existiert.
   * @throws IllegalArgumentException, wenn das Topic nicht existiert.
   * @throws NullPointerException, wenn eines der Argumente Null ist.
   * @param userName Der Name des Nutzers
   * @param message Der Text der zu erzeugenden Nachricht.
   * @return Die Id der neuen Nachricht
   */  
  public Long createMessage(String userName, String message, String topic)
  {
    if (userName == null || message == null || topic == null)
      throw new NullPointerException();
    if (message.length() < 10 || message.length() > 255)
      throw new IllegalArgumentException();
    if (!getTopics().contains(topic))
      throw new IllegalArgumentException();
    if (!userExists(userName))
      throw new IllegalArgumentException();
    
    Long newMessageID = messageIDCounter++;
    Long userID = users.get(userName).getID();
    
    origins.put(newMessageID, new Message(newMessageID, userID, message, topic));
    
    return newMessageID;
  }
  
  /**
   * Eine Nachricht als Antwort auf eine existierende Ursprungs-Nachricht. 
   * Die Nachricht ist automatisch dem Topic des Vorgängers zugeordnet.
   * 
   * @throws IllegalArgumentException, wenn die Nachricht länger als 255 Zeichen lang ist.
   * @throws IllegalArgumentException, wenn die Nachricht weniger als 10 Zeichen lang ist.
   * @throws IllegalArgumentException, wenn der Nutzer nicht existiert.
   * @throws IllegalArgumentException, wenn der Vorgänger nicht existiert.
   * @throws IllegalArgumentException, wenn der Vorgänger keine Ursprungsnachricht ist, d.h. eine 
   *  Nachricht ohne Vorgänger.
   * @throws NullPointerException, wenn eines der Argumente Null ist.
   * @param userName Der Name des Nutzers
   * @param message Der Text der zu erzeugenden Nachricht.
   * @param predecessor Die Id der Vorgänger-Nachricht.
   * @return Die Id der neuen Nachricht
   */
  public Long respondToMessage(String userName, String message, Long predecessorID)
  {
    if (userName == null || message == null || predecessorID == null)
      throw new NullPointerException();
    if (message.length() < 10 || message.length() > 255)
      throw new IllegalArgumentException();
    if (!messageExists(predecessorID))
      throw new IllegalArgumentException();
    if (!userExists(userName))
      throw new IllegalArgumentException();
    
    Message predecessor = origins.get(predecessorID);
    
    if (!predecessor.isOrigin())
      throw new IllegalArgumentException();
    
    Long newMessageID = createMessage(userName, message, predecessor.getTopic());
    Message newMessage = origins.get(newMessageID);
    origins.get(predecessor).addResponse(newMessage);
    
    return newMessageID;
  }
  

  /**
   * Lösche eine Ursprungsnachricht, d.h. eine Nachricht ohne Vorgänger.
   * Alle Nachfolger, d.h. Antwort-Nachrichten müssen dabei mitgelöscht werden.
   * 
   * @throws IllegalArgumentException, wenn keine Nachricht existiert mit der messageId.
   * @throws IllegalArgumentException, wenn die Nachricht keine Ursprungs-Nachricht ist.
   * @throws IllegalArgumentException, wenn der Nutzer nicht existiert.
   * @throws NullPointerException, wenn eines der Argumente Null ist.
   * @throws AuthorizationException, wenn der übergebene userName nicht mit dem Erzeuger der 
   *     Ursprungsnachricht übereinstimmt.
   * @param userName Der Name des Nutzers
   * @param messageId Die Id der Nachricht.
   */
  public void deleteMessage(String userName, Long messageId)
    throws AuthorizationException
  {
    if (userName == null || messageId == null)
      throw new NullPointerException();
    if (!messageExists(messageId))
      throw new IllegalArgumentException();
    if (!userExists(userName))
      throw new IllegalArgumentException();
    
    Message message = origins.get(messageId);
    
    if (!message.isOrigin())
      throw new IllegalArgumentException();
    
    origins.remove(messageId);
  }

  
  /**
   * Erzeuge neues Topic (Thema) denen Nachrichten zugeordnet werden können.
   * 
   * @throws IllegalArgumentException, wenn der Nutzer nicht existiert. 
   * @throws IllegalArgumentException, wenn das Topic schon existiert. 
   * @throws IllegalArgumentException, wenn das Topic länger als 70 oder kürzer als 2 Zeichen ist.
   * @throws NullPointerException, wenn eines der Argumente Null ist.
   * @param userName Der Name des Nutzers
   * @param topic Der eindeutige Bezeichner des Topics
   */
  public void createTopic(String userName, String topic)
  {
    if (userName == null || topic == null)
      throw new NullPointerException();
    if (topics.contains(topic))
      throw new IllegalArgumentException();
    if (topic.length() < 2 || topic.length() > 70)
      throw new IllegalArgumentException();
    if (!userExists(userName))
      throw new IllegalArgumentException();
    
    topics.add(topic);
  }
  

  
  /**
   * Gibt alle existierende Topics zurück.
   * @return alle Bezeichner der Topics. Wenn es keine Topics gibt, soll eine leere Menge 
   * zurückgegeben werden (nicht null).
   */
  public HashSet<String> getTopics()
  {
    HashSet<String> result = new HashSet(topics);
    
    return result;
  }
  
  
  /**
   * Gibt alle Nachrichten zu einem Topic zurück deren Ursprungsnachricht nach dem 
   * angegebene Datum erzeugt worden ist.
   * 
   * @throws IllegalArgumentException, wenn das Topic nicht existiert.
   * @throws NullPointerException, wenn für das Topic null übergeben wird.
   * @param topic Die zurückgegebenen Nachrichten sollen diesem Topic zugeordnet sein.
   * @param since Datum . Die zurückgegebenen Nachrichten sollen eine Ursprungsnachricht haben, die 
   *    nach diesem Datum erzeugt worden ist. Falls since null ist sollen alle Nachrichten zu dem Topic zurückgegeben
   *    werden.
   * @return Die innere Liste besteht aus einer Ursprungsnachricht (Kopf der Liste) und sowie deren Antworten (falls vorhanden)
   * Die äußere Liste ist geordnet nach dem Erstellungdatum der Ursprungsnachrichten. Wenn es keine Messages gibt, 
   * soll eine leere äußere Liste zurückgegeben werden (nicht null). Die inneren Listen dürfen nie leer sein.
   */
  public List<List<Message>> getMessageByTopic(String topic, Date since)
  {
    return null;
  }
  
  
  /**
   * Erzeuge einen neuen Nutzer.
   * 
   * @throws IllegalArgumentException, wenn der Nutzer (Name) schon existiert.
   * @throws IllegalArgumentException, wenn der Nutzername länger als 30 oder kürzer als 4 Zeichen ist.
   * @throws NullPointerException, wenn eines der Argumente Null ist.
   * @param userName Der eindeutige Names des Nutzers
   * @param city Die Stadt aus dem der Nutzer kommt.
   */
  public void createUser(String userName, String city)
  {
    if (userName == null || city == null)
      throw new NullPointerException();
    if (userName.length() < 4 || userName.length() > 30)
      throw new IllegalArgumentException();
    if (!userExists(userName))
      throw new IllegalArgumentException();
    
    users.put(userName, new User(userIDCounter++, userName, city));
  }
  
  
  /**
   * Lösche den Nutzer mit dem gegebenen Nutzernamen
   * @throws IllegalArgumentException, wenn der Nutzer nicht existiert.
   * @param userName
   */
  public void deleteUser(String userName)
  {
    if (!userExists(userName))
      throw new IllegalArgumentException();
    
    users.remove(userName);
  }
  
  
  /**
   * Gibt alle Nutzer zurück.
   * 
   * @return Menge aller Nutzer
   */
  public HashSet<User> getUsers()
  {
    HashSet<User> result = new HashSet<User>(users.values());
    
    return result;
  }
  
  public boolean userExists(String userName)
  {
    return users.containsKey(userName);
  }
  
  public boolean messageExists(Long messageID)
  {
    return origins.containsKey(messageID);
  }
}