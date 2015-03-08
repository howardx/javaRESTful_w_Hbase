package com.restfulservice.webapp;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;

import com.restfulservice.GETrequestPOJOs.HbaseQueryObject;

@Component
public class DBpersistenceLayer
{
  public DBpersistenceLayer() {};

  public void executeUpdates(HTable table, HbaseQueryObject request) throws IOException
  {
    Put put = new Put(request.getRowKey());
    
    /*
     * Put.add(column family, column qualifier/name, value)
     */
    put.add(request.getColumnFamily(), request.getColumnQualifier(), request.getValue());
    
    table.put(put);
  }

  public String executeSearch(HTable table, HbaseQueryObject request)
  {
    Get get = new Get(request.getRowKey());
    get.addColumn(request.getColumnFamily(), request.getColumnQualifier());
    
    Result r = null;
    
    try
    {
      r = table.get(get);
	}
    catch (IOException e)
    {
      return "not found";
	}
    
    if (r != null && !r.isEmpty())
    {
      byte[] result = r.getValue(request.getColumnFamily(), request.getColumnQualifier());
      return Bytes.toString(result);
    }
    else { return "not found"; }
  }
}
