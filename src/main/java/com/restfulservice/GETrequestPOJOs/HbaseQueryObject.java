package com.restfulservice.GETrequestPOJOs;

public class HbaseQueryObject implements DBqueryObject
{
  private byte [] rowKey;
  private byte [] columnFamily;
  private byte [] columnQualifier;
  private byte [] value;
  private RequestObject request;
  
  public HbaseQueryObject(RequestObject req)
  {
    request = req;
    convertRequest2Query();
  }
 
  @Override
  public void convertRequest2Query()
  {
    optimizeRequestFormat();
    
    rowKey = request.getHostname().getBytes();
    columnFamily = request.getPort().getBytes();
    columnQualifier = request.getPath().concat(request.getQuery()).getBytes();
    
    if (request instanceof UpdateRequestPojo)
    {
      value = ((UpdateRequestPojo) request).getCondition().getBytes();
    }
  }

  /*
   * due to nature of Hbase
   */
  public void optimizeRequestFormat()
  {
    request.setHostname(new StringBuffer(request.getHostname()).reverse().toString());
  }
  
  public byte[] getRowKey() {
  	return rowKey;
  }
  
  public void setRowKey(byte[] rowKey) {
  	this.rowKey = rowKey;
  }
  
  public byte[] getColumnFamily() {
  	return columnFamily;
  }
  
  public void setColumnFamily(byte[] columnFamily) {
  	this.columnFamily = columnFamily;
  }
  
  public byte[] getColumnQualifier() {
  	return columnQualifier;
  }
  
  public void setColumnQualifier(byte[] columnQualifier) {
  	this.columnQualifier = columnQualifier;
  }

  public byte[] getValue() {
  	return value;
  }
  
  public void setValue(byte[] value) {
  	this.value = value;
  }
  
  public RequestObject getRequest() {
  	return request;
  }
  
  public void setRequest(RequestObject request) {
  	this.request = request;
  }
}
