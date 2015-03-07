package com.restfulservice.webapp;

import java.io.IOException;

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
import org.springframework.stereotype.Component;

@Component
public class DBpersistenceLayer
{
  public DBpersistenceLayer() {};
  
  public void executeUpdates(HTable table, UpdateRequestPojo query) throws IOException
  {
    Put put = new Put(Bytes.toBytes(query.getHostname()));
    
    /*
     * Put.add(column family, column qualifier/name, value)
     */
    put.add(Bytes.toBytes(query.getPort()),
      Bytes.toBytes(query.getPath().concat(query.getQuery())), Bytes.toBytes(query.getCondition()));
    
    table.put(put);
  }
}
