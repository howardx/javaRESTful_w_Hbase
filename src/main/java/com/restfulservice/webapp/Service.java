package com.restfulservice.webapp;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.restfulservice.GETrequestPOJOs.HbaseQueryObject;
import com.restfulservice.GETrequestPOJOs.SearchRequestPojo;
import com.restfulservice.GETrequestPOJOs.UpdateRequestPojo;
import com.restfulservice.GETresponsePOJOs.Queryresponse;
import com.restfulservice.GETresponsePOJOs.URLlookupServiceResponse;

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
  public URLlookupServiceResponse processRequest(SearchRequestPojo query) throws IOException
  {
    HbaseQueryObject request = new HbaseQueryObject(query);
    String DBoutput = dba.prepareDBaccess(request);
    return generateResponse(DBoutput);
  }

  private URLlookupServiceResponse generateResponse(String dBoutput)
  {
    return new URLlookupServiceResponse(dBoutput);
  }

  // update Hbase 
  public void updateRequest(UpdateRequestPojo query) throws IOException
  {
    HbaseQueryObject request = new HbaseQueryObject(query);
    dba.prepareUpdateDBaccess(request);
  }
}