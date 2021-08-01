package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyDriver {

    private static final String STUDENT_NAME = "feihongtaxue";
    private static final String STUDENT_ID = "G20210735010483";
    private static final String STUDENT_CLASS = "5";
    private static final String TABLE_NAME = STUDENT_NAME + ":student";
    private static Student student = new Student(STUDENT_NAME,STUDENT_ID,STUDENT_CLASS,"100","100");

    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "jikehadoop01,jikehadoop02");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        Connection hbaseConnection = HBaseUtil.getHbaseConnection(conf);

       //建表
 //      TableInfo tableInfo = getTableInfo();
 //      HBaseUtil.createTable(hbaseConnection,tableInfo);

       //插入数据
 //       HBaseUtil.insertData(hbaseConnection,TABLE_NAME,student);

        //查询数据
        student = HBaseUtil.getStudentByRowKey(hbaseConnection, TABLE_NAME, STUDENT_NAME);
        System.out.println(student);

//        //删除数据
//        HBaseUtil.deleteByRowKey(hbaseConnection,"even", "even");

//        //删除表
//        HBaseUtil.deleteTable(hbaseConnection,"even");

        hbaseConnection.close();
    }


    private static TableInfo getTableInfo() {
        List<String> columnDescriptors = new ArrayList<>();
        columnDescriptors.add("name");
        columnDescriptors.add("info");
        columnDescriptors.add("score");
        return new TableInfo(TABLE_NAME,columnDescriptors);
    }
}
