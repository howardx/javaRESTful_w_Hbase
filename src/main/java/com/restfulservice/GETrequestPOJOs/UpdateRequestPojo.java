package com.restfulservice.GETrequestPOJOs;


public class UpdateRequestPojo extends RequestObject 
{
  private String condition;

  public UpdateRequestPojo(String hostname_port, String path, String query)
  {
    super(hostname_port, path, query);
  }

  public UpdateRequestPojo(String hostname_port, String path, String query, String condition)
  {
    super(hostname_port, path, query);
    this.condition = condition;
  }

  public String getCondition() {
  	return condition;
  }
  
  public void setCondition(String condition) {
  	this.condition = condition;
  }
}
