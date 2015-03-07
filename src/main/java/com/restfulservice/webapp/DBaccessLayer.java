package com.restfulservice.webapp;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

public class DBaccessLayer
{
  private Configuration conf;
  private HBaseAdmin admin;
  
  public DBaccessLayer () throws MasterNotRunningException, ZooKeeperConnectionException
  {
    Configuration conf = HBaseConfiguration.create();
    HBaseAdmin admin = new HBaseAdmin(conf);
  }
  
  public Queryresponse prepareDBaccess(RequestPojo query) throws IOException
  {
    try
    {
      //HTableDescriptor desc = new HTableDescriptor("test_table");
      //HColumnDescriptor cf = new HColumnDescriptor("cf".getBytes());
      //desc.addFamily(cf);
      //admin.createTable(desc); 
      HTable table = new HTable(conf, "test_table");
      Put put = new Put(Bytes.toBytes("test-key"));
      put.add(Bytes.toBytes("cf"), Bytes.toBytes("q"), Bytes.toBytes("value"));
      table.put(put);
    }
    finally
    {
      admin.close();
    }	
    return null;
  }

  public void prepareUpdateDBaccess(UpdateRequestPojo query) throws IOException
  {
    if (admin.tableExists("urlInfo"))
    {
    	
    }
    else
    {
      try
      {
        HTableDescriptor desc = new HTableDescriptor("urlInfo");
        HColumnDescriptor cf = new HColumnDescriptor("cf".getBytes());
        desc.addFamily(cf);
        admin.createTable(desc); 
        HTable table = new HTable(conf, "urlInfo");
        Put put = new Put(Bytes.toBytes("test-key"));
        put.add(Bytes.toBytes("cf"), Bytes.toBytes("q"), Bytes.toBytes("value"));
        table.put(put);
      }
      finally
      {
        admin.close();
      }
    }
  }
}