package org.ez18n.apt.processor;

import junit.framework.TestCase;
import org.junit.Test;

/**
 */
public class GwtXmlProcessorTest {
  @Test
  public void testProcessorCreation(){
    GwtXmlProcessor proc = new GwtXmlProcessor();
    TestCase.assertNotNull(proc);
  }
}
