package com.restfulservice.webappUnitTests;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;

import com.restfulservice.webapp.RequestPojo;
import com.restfulservice.webapp.Service;

import junit.framework.*;

public class ServiceLayerUnitTest extends TestCase
{
  protected String validHostname_port = "Google.com:443";
  protected String validPath = "some";
  protected String validQuery = "file.exe";
  
  protected RequestPojo RESTfulReq;
  
  protected void setUp()
  {
    RESTfulReq = new RequestPojo(validHostname_port, validPath, validQuery);
  }

  public void requestOptimizationTest() throws MasterNotRunningException, ZooKeeperConnectionException
  {
    Service serv = new Service();
    serv.optimizeRequestFormat(RESTfulReq);
    assertEquals(RESTfulReq.getHostname(), "moc.elgooG");
  }
}
