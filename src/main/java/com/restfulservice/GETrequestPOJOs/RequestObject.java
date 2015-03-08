package com.restfulservice.GETrequestPOJOs;

public abstract class RequestObject
{
  private String hostname;
  private String path;
  private String port;
  private String query;
  
  public RequestObject(String hostname_port, String path, String query)
  {
    String [] hostnameNport = hostname_port.split(":");
    this.hostname = hostnameNport[0];
    this.port = hostnameNport[1];
    this.path = path;
    this.query = query;
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
