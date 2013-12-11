package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import org.junit.rules.ExpectedException;
import org.junit.*;

public class SMSTest
{
  @Rule
  public ExpectedException thrown = ExpectedException.none();
      
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
  //public Long createMessage(String userName, String message, String topic)
  @Test
  public void testCreateOneMessage() {
    
  }
  
  @Test
  public void testCreateMessageWithMoreThan255Chars() {
    thrown.expect(IllegalArgumentException.class);
  }
  
  @Test
  public void testCreateMessageWithLessThan10Chars() {
    thrown.expect(IllegalArgumentException.class);
  }
  
  @Test
  public void testCreateMessageWithoutUser() {
    thrown.expect(IllegalArgumentException.class);
  }
  
  @Test
  public void testCreateMessageWithoutTopic() {
    thrown.expect(IllegalArgumentException.class);
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
  //public Long respondToMessage(String userName, String message, Long predecessor)
  
  @Test
  public void testRespondToMessage() {
    
  }
  
  @Test
  public void testRespondMessageWithMoreThan255Chars() {
    
  }
  
  @Test
  public void testRespondMessageWithLessThan10Chars() {
    
  }
  
  @Test
  public void testRespondWithoutPredecessor() {
    
  }
  
  @Test
  public void testRespondInvalidPredecessor() {
    
  }
  
  @Test
  public void testRespondWithParameterContainingNull() {
    
  }
  
  @Test
  public void testRespondWithoutUser() {
    
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
  //public void deleteMessage(String userName, Long messageId)
    //throws AuthorizationException
    @Test
    public void testDeleteMessage() {
    
    }
    
    @Test
    public void testDeleteMessageWithInvalidID() {
    
    }
    
    @Test
    public void testDeleteMessageWithInvalidUser() {
    
    }
    
    @Test
    public void testDeleteMessageWithParameterContainingNull() {
    
    }
    
    @Test
    public void testDeleteMessageWithUnauthorizedUser() {
    
    }
    
    @Test
    public void testDeleteMessageWithInvalidMessage() {
    
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
  //public void createTopic(String userName, String topic)
  @Test
  public void testCreateTopic() {
    
  }
  
  @Test
  public void testCreateTopicWithInvalidUser() {
    
  }
  
  @Test
  public void testCreateDuplicativeTopic() {
    
  }
  
  @Test
  public void testCreateTopicWithMoreThan70Chars() {
    
  }
  
  @Test
  public void testCreateTopicWithLessThan2Chars() {
    
  }
  
  @Test
  public void testCreateTopicWithParameterContainingNull() {
    
  }
  

  
  /**
   * Gibt alle existierende Topics zurück.
   * @return alle Bezeichner der Topics. Wenn es keine Topics gibt, soll eine leere Menge 
   * zurückgegeben werden (nicht null).
   */
  //public Set<String> getTopics()
  @Test
  public void testGetTopics() {
    
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
  //public List<List<Message>> getMessageByTopic(String topic, Date since)
  @Test
  public void testGetMessageByTopic() {
    
  }
  
  @Test
  public void testGetMessageByInvalidTopic() {
    
  }
  
  @Test
  public void testGetMessageByNullTopic() {
    
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
  //public void createUser(String userName, String city)
  @Test
  public void testCreateUser() {
    
  }
  
  @Test
  public void testCreateDuplicativeUser() {
    
  }
  
  @Test
  public void testCreateUserByNameWithMoreThan30Chars() {
    
  }
  
  @Test
  public void testCreateUserByNameWithLessThan4Chars() {
    
  }

  @Test
  public void testCreateUserWithParameterContainingNull() {
    
  }
  
  
  /**
   * Lösche den Nutzer mit dem gegebenen Nutzernamen
   * @throws IllegalArgumentException, wenn der Nutzer nicht existiert.
   * @param userName
   */
  //public void deleteUser(String userName)
  
  @Test
  public void testDeleteUser() {
    
  }
  
  @Test
  public void testDeleteInvalidUser() {
    
  }
  
  
  /**
   * Gibt alle Nutzer zurück.
   * 
   * @return Menge aller Nutzer
   */
  //public Set<User> getUsers()
  @Test
  public void testReturnAllUsers() {
    
  }
}
