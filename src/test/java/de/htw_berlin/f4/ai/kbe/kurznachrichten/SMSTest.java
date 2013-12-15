package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;
import org.junit.*;

import java.util.HashSet;

public class SMSTest
{
  private SMS sms;
  
  private static String testUser = "testuser";
  private static String testUser2 = "testuser2";
  private static String invalidUser = "invalid";
  private static String testMessage = "Dies ist ein Test!";
  private static String testTopic = "test";
  private static String invalidTopic = "invalid";  
  private Long message1 = 0l;
  private Long message2 = 0l;
  
  @Before
  public void prepareSMS()
        throws IllegalArgumentException, NullPointerException {
    sms = new SMS();
    sms.createUser(testUser, "Berlin");
    sms.createUser(testUser2, "Berlin");    
    sms.createTopic(testUser, testTopic);
    message1 = sms.createMessage(testUser, testMessage, testTopic);
    message2 = sms.respondToMessage(testUser2, testMessage, message1);
  }
  
  @Rule
  public ExpectedException thrown = ExpectedException.none();
  
  public String stringWithLength(int x) {
    x = x < 1 ? 1 : x;
    
    String result = "";
    
    for (int i = 0; i < x; i++) {
      result += "A";
    }
    
    return result;
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
  //public Long createMessage(String userName, String message, String topic)
  @Test
  public void testCreateOneMessage()
        throws IllegalArgumentException, NullPointerException {
    assertEquals("There should be one message in the message-map.", 1, sms.getMessageCount());
    sms.createMessage(testUser, testMessage, testTopic);
    assertEquals("There should have been created the second message.", 2, sms.getMessageCount());
  }
  
  @Test
  public void testCreateMessageWithMoreThan255Chars()
        throws IllegalArgumentException, NullPointerException {
    sms.createMessage(testUser, stringWithLength(256), testTopic);
    thrown.expect(IllegalArgumentException.class);
  }
  
  @Test
  public void testCreateMessageWithLessThan10Chars()
        throws IllegalArgumentException, NullPointerException {
    sms.createMessage(testUser, stringWithLength(9), testTopic);
    thrown.expect(IllegalArgumentException.class);
  }
  
  @Test
  public void testCreateMessageWithInvalidUser()
        throws IllegalArgumentException, NullPointerException {
    sms.createMessage(invalidUser, testMessage, testTopic);
    thrown.expect(IllegalArgumentException.class);
  }
  
  @Test
  public void testCreateMessageWithInvalidTopic()
        throws IllegalArgumentException, NullPointerException {
    sms.createMessage(invalidUser, testMessage, invalidTopic);
    thrown.expect(IllegalArgumentException.class);
  }
  
  @Test
  public void testCreateMessageWithParameterContainingNull()
        throws IllegalArgumentException, NullPointerException {
    sms.createMessage(null, testMessage, testTopic);
    thrown.expect(NullPointerException.class);
    sms.createMessage(testUser, null, testTopic);
    thrown.expect(NullPointerException.class);
    sms.createMessage(testUser, testMessage, null);
    thrown.expect(NullPointerException.class);
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
  public void testRespondToMessage()
        throws IllegalArgumentException, NullPointerException {    
    Long response = sms.respondToMessage(testUser, "testMessage", message1);
    assertEquals("There should exist one message.", 3, sms.getMessageCount());
    assertTrue("The response should have another ID than the predecessor", message1 != response);
  }
  
  @Test
  public void testRespondMessageWithMoreThan255Chars()
        throws IllegalArgumentException, NullPointerException {
    sms.respondToMessage(testUser, stringWithLength(256), message1);
    thrown.expect(IllegalArgumentException.class);
  }
  
  @Test
  public void testRespondMessageWithLessThan10Chars()
        throws IllegalArgumentException, NullPointerException {
    sms.respondToMessage(testUser, stringWithLength(9), message1);
    thrown.expect(IllegalArgumentException.class);
  }
  
  @Test
  public void testRespondWithMissingPredecessor()
        throws IllegalArgumentException, NullPointerException {
    sms.respondToMessage(testUser, testMessage, message1 + 1000);
    thrown.expect(IllegalArgumentException.class);
  }
  
  @Test
  public void testRespondInvalidPredecessor()
        throws IllegalArgumentException, NullPointerException {
    sms.respondToMessage(testUser, testMessage, message2);
    thrown.expect(IllegalArgumentException.class);
  }
  
  @Test
  public void testRespondWithParameterContainingNull()
        throws IllegalArgumentException, NullPointerException {
    sms.respondToMessage(null, testMessage, message1);
    thrown.expect(NullPointerException.class);
    sms.respondToMessage(testUser, null, message1);
    thrown.expect(NullPointerException.class);
    sms.respondToMessage(testUser, testMessage, null);
    thrown.expect(NullPointerException.class);
  }
  
  @Test
  public void testRespondMissingUser()
        throws IllegalArgumentException, NullPointerException {
    sms.respondToMessage(invalidUser, testMessage, message1);
    thrown.expect(IllegalArgumentException.class);
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
    public void testDeleteMessage()
        throws AuthorizationException, IllegalArgumentException, NullPointerException {
      assertEquals("There should be one message in the message-map.", 1, sms.getMessageCount());
      sms.deleteMessage(testUser, message1);
      assertEquals("The standard-message should have been deleted.", 0, sms.getMessageCount());
    }
    
    @Test
    public void testDeleteMessageWithInvalidID()
        throws AuthorizationException, IllegalArgumentException, NullPointerException {
      sms.deleteMessage(testUser, message1 + 1000);
      thrown.expect(IllegalArgumentException.class);
    }
    
    @Test
    public void testDeleteMessageWithInvalidUser()
        throws AuthorizationException, IllegalArgumentException, NullPointerException {
      sms.deleteMessage(invalidUser, message1);
      thrown.expect(IllegalArgumentException.class);
    }
    
    @Test
    public void testDeleteMessageWithParameterContainingNull()
        throws AuthorizationException, IllegalArgumentException, NullPointerException {
      sms.deleteMessage(null, message1);
      thrown.expect(NullPointerException.class);
      sms.deleteMessage(testUser, null);
      thrown.expect(NullPointerException.class);
    }
    
    @Test
    public void testDeleteMessageWithUnauthorizedUser()
        throws AuthorizationException, IllegalArgumentException, NullPointerException {
      sms.deleteMessage(testUser2, message1);
      thrown.expect(AuthorizationException.class);
    }
    
    @Test
    public void testDeleteMessageWithInvalidMessage()
        throws AuthorizationException, IllegalArgumentException, NullPointerException {
      sms.deleteMessage(testUser2, message2);
      thrown.expect(IllegalArgumentException.class);
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
  public void testCreateTopic()
        throws IllegalArgumentException, NullPointerException {
    assertEquals("Before topic-creation should only exist one topic", 1, sms.getTopics().size());
    sms.createTopic(testUser, "Karneval");
    assertEquals("There should exist 2 topics", 2, sms.getTopics().size());
  }
  
  @Test
  public void testCreateTopicWithInvalidUser()
        throws IllegalArgumentException, NullPointerException {
    sms.createTopic(invalidUser, "Karneval");
    thrown.expect(IllegalArgumentException.class);
  }
  
  @Test
  public void testCreateDuplicativeTopic()
        throws IllegalArgumentException, NullPointerException {
    sms.createTopic(testUser, testTopic);
    thrown.expect(IllegalArgumentException.class);
  }
  
  @Test
  public void testCreateTopicWithMoreThan70Chars()
        throws IllegalArgumentException, NullPointerException {
    sms.createTopic(invalidUser, stringWithLength(71));
    thrown.expect(IllegalArgumentException.class);
  }
  
  @Test
  public void testCreateTopicWithLessThan2Chars()
        throws IllegalArgumentException, NullPointerException {
    sms.createTopic(invalidUser, stringWithLength(1));
    thrown.expect(IllegalArgumentException.class);
  }
  
  @Test
  public void testCreateTopicWithParameterContainingNull()
        throws IllegalArgumentException, NullPointerException {
    sms.createTopic(null, "Karneval");
    thrown.expect(NullPointerException.class);
    sms.createTopic(invalidUser, null);
    thrown.expect(NullPointerException.class);
  }
  

  
  /**
   * Gibt alle existierende Topics zurück.
   * @return alle Bezeichner der Topics. Wenn es keine Topics gibt, soll eine leere Menge 
   * zurückgegeben werden (nicht null).
   */
  //public Set<String> getTopics()
  @Test
  public void testGetTopics() {
    HashSet<String> givenTopics = sms.getTopics();
    assertEquals("There should be one topic", 1, givenTopics.size());
    assertTrue("There should exist the test-topic: " + testTopic, givenTopics.contains(testTopic));
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
  public void testGetMessageByTopic()
        throws IllegalArgumentException, NullPointerException {
    
  }
  
  @Test
  public void testGetMessageByInvalidTopic()
        throws IllegalArgumentException, NullPointerException {
    thrown.expect(IllegalArgumentException.class);
  }
  
  @Test
  public void testGetMessageByNullTopic()
        throws IllegalArgumentException, NullPointerException {
    thrown.expect(NullPointerException.class);
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
  public void testCreateUser()
        throws IllegalArgumentException, NullPointerException {
    
  }
  
  @Test
  public void testCreateDuplicativeUser()
        throws IllegalArgumentException, NullPointerException {
    thrown.expect(IllegalArgumentException.class);
  }
  
  @Test
  public void testCreateUserByNameWithMoreThan30Chars()
        throws IllegalArgumentException, NullPointerException {
    thrown.expect(IllegalArgumentException.class);
  }
  
  @Test
  public void testCreateUserByNameWithLessThan4Chars()
        throws IllegalArgumentException, NullPointerException {
    thrown.expect(IllegalArgumentException.class);
  }

  @Test
  public void testCreateUserWithParameterContainingNull()
        throws IllegalArgumentException, NullPointerException {
    thrown.expect(NullPointerException.class);
  }
  
  
  /**
   * Lösche den Nutzer mit dem gegebenen Nutzernamen
   * @throws IllegalArgumentException, wenn der Nutzer nicht existiert.
   * @param userName
   */
  //public void deleteUser(String userName)
  
  @Test
  public void testDeleteUser()
        throws IllegalArgumentException {
    
  }
  
  @Test
  public void testDeleteInvalidUser()
        throws IllegalArgumentException {
    thrown.expect(IllegalArgumentException.class);
  }
  
  
  /**
   * Gibt alle Nutzer zurück.
   * 
   * @return Menge aller Nutzer
   */
  //public HashSet<User> getUsers()
  @Test
  public void testReturnAllUsers() {
    
  }
}
