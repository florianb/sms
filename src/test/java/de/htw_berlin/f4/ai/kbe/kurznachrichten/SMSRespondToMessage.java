package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

@RunWith(JUnit4.class)
public class SMSRespondToMessage
{
  
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
  //public Long respondToMessage(String userName, String message, Long 
  
  @Test
  thisIsAlwaysTrue()
  {
    assert(true);
  }
}