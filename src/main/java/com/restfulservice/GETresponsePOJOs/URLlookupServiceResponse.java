package com.restfulservice.GETresponsePOJOs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "response")
public class URLlookupServiceResponse implements Queryresponse
{
  private String message;
  
  public URLlookupServiceResponse() {};
  
  public URLlookupServiceResponse(String msg)
  {
    message = msg;
  }

  @XmlElement(name = "message")
  public String getMessage() {
  	return message;
  }
  
  public void setMessage(String msg) {
  	this.message = msg;
  }
}