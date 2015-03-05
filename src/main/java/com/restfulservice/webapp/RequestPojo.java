package com.restfulservice.webapp;

public class RequestPojo
{
  private String hostname;
  private String path;
  private String port;
  private String query;
  
  public RequestPojo(String hostname_port, String path, String query)
  {
  }
  
  private void optimizeRequestFormat()
  {
  }

  public String getHostname() {
  	return hostname;
  }
  
  public void setHostname(String hostname) {
  	this.hostname = hostname;
  }
  
  public String getPath() {
  	return path;
  }
  
  public void setPath(String path) {
  	this.path = path;
  }
  
  public String getPort() {
  	return port;
  }
  
  public void setPort(String port) {
  	this.port = port;
  }
  
  public String getQuery() {
  	return query;
  }
  
  public void setQuery(String query) {
  	this.query = query;
  }

}
