package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

@RunWith(JUnit4.class)
public class SMSDeleteMessage
{
  
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
  //  throws AuthorizationException
  
  @Test
  thisIsAlwaysTrue()
  {
    assert(true);
  }
}