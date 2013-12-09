package de.htw_berlin.f4.ai.kbe.kurznachrichten;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

@RunWith(JUnit4.class)
public class SMSGetMessageByTopic
{
  
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
  thisIsAlwaysTrue()
  {
    assert(true);
  }
}