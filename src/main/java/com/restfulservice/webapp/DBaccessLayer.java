package com.restfulservice.webapp;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.restfulservice.GETrequestPOJOs.HbaseQueryObject;

@Component
public class DBaccessLayer
{
  private Configuration conf;
  private HBaseAdmin admin;
  
  @Autowired
  private DBpersistenceLayer persistence;
  
  public DBpersistenceLayer getPersistence() {
    return persistence;
  }
  
  public void setPersistence(DBpersistenceLayer persistence) {
    this.persistence = persistence;
  }

  public DBaccessLayer ()
  {
    persistence = new DBpersistenceLayer();
    try
    {
	  DBinit();
	}
    catch (MasterNotRunningException | ZooKeeperConnectionException e)
    {
      e.printStackTrace();
	}
  }
  
  private void DBinit() throws MasterNotRunningException, ZooKeeperConnectionException
  {
    conf = HBaseConfiguration.create();
    admin = new HBaseAdmin(conf);
  }
 
  public String prepareDBaccess(HbaseQueryObject request) throws IOException
  {
    HTable table = new HTable(conf, "urlInfo");

    HColumnDescriptor hcd = table.getTableDescriptor().getFamily(request.getColumnFamily());
    if (hcd == null)
    {
      return "not found";
    }
    return persistence.executeSearch(table, request);
  }
 
  public void prepareUpdateDBaccess(HbaseQueryObject request) throws IOException
  {
    if (admin.tableExists("urlInfo"))
    {
      /*
       * creating a Htable object, will access existing Hbase table by name
       */
      HTable table = new HTable(conf, "urlInfo");
      
      HColumnDescriptor hcd = table.getTableDescriptor().getFamily(request.getColumnFamily());
      if (hcd == null)
      {
        HColumnDescriptor newColumnFamily = new HColumnDescriptor(request.getColumnFamily());
        
        admin.disableTable(table.getTableName());
        admin.addColumn(table.getTableName(), newColumnFamily);
        admin.enableTable(table.getTableName());
      }
      
      persistence.executeUpdates(table, request);
    }
    else
    {
      /*
       * new hbase table descriptor, for creating a new table in Hbase
       */
      HTableDescriptor hcd = new HTableDescriptor("urlInfo");
      HColumnDescriptor columnFamily = new HColumnDescriptor(request.getColumnFamily());
      hcd.addFamily(columnFamily);
      hcd.setReadOnly(false);
      
      admin.createTable(hcd);
      
      HTable table = new HTable(conf, "urlInfo");
      
      persistence.executeUpdates(table, request);
    }
  }
}