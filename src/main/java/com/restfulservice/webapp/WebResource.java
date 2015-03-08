package com.restfulservice.webapp;

import java.io.IOException;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.restfulservice.GETrequestPOJOs.SearchRequestPojo;
import com.restfulservice.GETrequestPOJOs.UpdateRequestPojo;
import com.restfulservice.GETresponsePOJOs.URLlookupServiceResponse;

@Component
@Path("/urlinfo/1")
public class WebResource 
{
  @Autowired
  private Service serviceLayer;
  
  public WebResource ()
  {
    serviceLayer = new Service();
  };
  
  public Service getService()
  {
    return serviceLayer;
  }
  
  public void setService(Service serviceLayer)
  {
  	this.serviceLayer = serviceLayer;
  }
  
  @GET
  @Path("/healthcheck")
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  public String healthCheck()
  {
  	return "<message>The service is up!</message>"; 
  }
  
  @GET
  @Path("/{hostname_port}/{path}/{query}")
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  public URLlookupServiceResponse urlService(@DefaultValue("")
    @PathParam("hostname_port") String hostname_port,
    @PathParam("path") String path,
    @PathParam("query") String query) throws IOException
  {
	SearchRequestPojo request = new SearchRequestPojo(hostname_port, path, query);
	return this.serviceLayer.processRequest(request);
  }
  
  @GET
  @Path("/update/{hostname_port}/{path}/{query}/{condition}")
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  public String updateService(@DefaultValue("")
    @PathParam("hostname_port") String hostname_port,
    @PathParam("path") String path,
    @PathParam("query") String query,
    @PathParam("condition") String condition) throws IOException
  {
	UpdateRequestPojo request = new UpdateRequestPojo(hostname_port, path, query, condition);
	this.serviceLayer.updateRequest(request);
	
  	return "<message>The URL lookup service was updated!</message>"; 
  }
}
