package com.restfulservice.unitTests;

import com.restfulservice.GETrequestPOJOs.HbaseQueryObject;
import com.restfulservice.GETrequestPOJOs.SearchRequestPojo;

import junit.framework.*;

public class HbaseQueryObjectTest extends TestCase
{
  protected String validHostname_port = "Google.com:443";
  protected String validPath = "some";
  protected String validQuery = "file.exe";
 
  protected HbaseQueryObject query;
 
  protected void setUp()
  {
    SearchRequestPojo RESTfulReq = new SearchRequestPojo(validHostname_port, validPath, validQuery);
    query = new HbaseQueryObject(RESTfulReq);
  }

  public void requestOptimizationTest()
  {
    assertEquals("moc.elgooG", query.getRowKey());
    assertEquals("443", query.getColumnFamily());
  }
}