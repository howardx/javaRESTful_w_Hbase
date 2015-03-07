package com.restfulservice.webapp;

import java.io.IOException;

import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Service
{
  @Autowired
  private DBaccessLayer dba;
  
  public Service () 
  {
    dba = new DBaccessLayer();
  };
  
  public DBaccessLayer getDba()
  {
    return dba;
  }
  
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