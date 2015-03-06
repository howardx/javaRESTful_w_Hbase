package com.restfulservice.webapp;

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

  public Queryresponse processRequest(RequestPojo query)
  {
    optimizeRequestFormat(query);
    return dba.prepareDBaccess(query);
  }

  /*
   * due to nature of Hbase
   */
  public void optimizeRequestFormat(RequestPojo query)
  {
    query.setHostname(new StringBuffer(query.getHostname()).reverse().toString());
  }
}
