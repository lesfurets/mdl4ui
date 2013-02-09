package org.ez18n.apt.processor;

import junit.framework.TestCase;
import org.junit.Test;

/**
 */
public class CSVReportProcessorTest {
  @Test
  public void testProcessorCreation(){
    CSVReportProcessor proc = new CSVReportProcessor();
    TestCase.assertNotNull(proc);
  }
}
