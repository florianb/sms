package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import org.junit.*;

public class SMSTest
{
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

  

  
  /**
   * Gibt alle existierende Topics zurück.
   * @return alle Bezeichner der Topics. Wenn es keine Topics gibt, soll eine leere Menge 
   * zurückgegeben werden (nicht null).
   */
  //public Set<String> getTopics()

  
  
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

  
  
  /**
   * Lösche den Nutzer mit dem gegebenen Nutzernamen
   * @throws IllegalArgumentException, wenn der Nutzer nicht existiert.
   * @param userName
   */
  //public void deleteUser(String userName)

  
  
  /**
   * Gibt alle Nutzer zurück.
   * 
   * @return Menge aller Nutzer
   */
  //public Set<User> getUsers()

}
