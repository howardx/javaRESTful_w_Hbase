package com.restfulservice.webapp;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;


public class Service
{
  private DBaccessLayer dba;
  
  public DBaccessLayer getDba()
  {
    return dba;
  }
  @Autowired
  public void setDba(DBaccessLayer dba)
  {
    this.dba = dba;
  }

  // user query
  public Queryresponse processRequest(RequestPojo query) throws IOException
  {
    optimizeRequestFormat(query);
    return dba.prepareDBaccess(query);
  }

  // update Hbase 
  public void updateRequest(UpdateRequestPojo query) throws IOException
  {
    optimizeRequestFormat(query);
    dba.prepareUpdateDBaccess(query);
  }


  /*
   * due to nature of Hbase
   */
  public void optimizeRequestFormat(RequestPojo query)
  {
    query.setHostname(new StringBuffer(query.getHostname()).reverse().toString());
  }
}