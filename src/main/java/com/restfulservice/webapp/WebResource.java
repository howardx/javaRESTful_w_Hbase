package com.restfulservice.webapp;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/urlinfo/1")
public class WebResource 
{
  private Service serviceLayer;
  
  public Service getService()
  {
    return serviceLayer;
  }
  
  @Autowired
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
  public String urlService(@DefaultValue("")
    @PathParam("hostname_port") String hostname_port,
    @PathParam("path") String path,
    @PathParam("query") String query)
  {
	RequestPojo request = new RequestPojo(hostname_port, path, query);
	this.serviceLayer.processRequest(request);
	
  	return "<test>" + query + "</test>"; 
  }
}
