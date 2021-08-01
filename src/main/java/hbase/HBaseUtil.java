package hbase;

import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class HBaseUtil {


    public static Connection getHbaseConnection(Configuration conf) throws IOException {
        return ConnectionFactory.createConnection(conf);
    }

    public static void createTable(Connection hbaseConnection,TableInfo tableInfo) throws IOException {
        //获取表的管理类
        Admin admin = hbaseConnection.getAdmin();

        String tableName = tableInfo.getTableName();
        //定义表
        TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(TableName.valueOf("tableName"));

        //定义列簇
        ColumnFamilyDescriptorBuilder userInfo = ColumnFamilyDescriptorBuilder.
                newBuilder(Bytes.toBytes("user_info"));
        userInfo.setMaxVersions(3); //设置版本信息
        tableDescriptorBuilder.setColumnFamily(userInfo.build());

        //命名空间不存在则创建
        if(tableName.contains(":")){
            String nameSpace = tableName.substring(0,tableName.indexOf(":"));
            try {
                NamespaceDescriptor namespaceDescriptor = admin.getNamespaceDescriptor(nameSpace);
                System.out.println("namespaceDescriptor : " + namespaceDescriptor.getName());
            }catch (NamespaceNotFoundException e){
                admin.createNamespace(NamespaceDescriptor.create(nameSpace).build());
            }
        }

        //执行建表操作
        admin.createTable(tableDescriptorBuilder.build());
        admin.close();
    }

    public static   void deleteTable(Connection hbaseConnection,String tableName) throws IOException {
        Admin admin = hbaseConnection.getAdmin();
        admin.disableTable(TableName.valueOf(tableName));
        admin.deleteTable(TableName.valueOf(tableName));
        admin.close();
    }

    //插入数据
    public static void insertData(Connection hbaseConnection,String tableName, Student student) throws IOException {
        //插入数据设置
        //rowKey
        Put put = new Put(student.getName().getBytes());
        //这里可以使用设计模式解藕
        //参数：1.列族名  2.列名  3.值
        put.addColumn("info".getBytes(), "student_id".getBytes(), student.getStudent_id().getBytes()) ;
        put.addColumn("info".getBytes(), "class".getBytes(), student.getStudent_class().getBytes()) ;
        put.addColumn("score".getBytes(), "understanding".getBytes(), student.getUnderstanding().getBytes());
        put.addColumn("score".getBytes(), "programming".getBytes(), student.getProgramming().getBytes());
        //获取表
        TableName tablename = TableName.valueOf(tableName);
        Table table = hbaseConnection.getTable(tablename);
        //插入数据
        table.put(put);
        table.close();
    }

    // 根据rowkey查询数据
    public static Student getStudentByRowKey(Connection hbaseConnection,String tableName,String rowKey) throws IOException {
        Table table = hbaseConnection.getTable(TableName.valueOf(tableName));
        Get get = new Get(rowKey.getBytes());
        Student student = new Student();
        student.setName(rowKey);
        //先判断是否有此条数据;有则进行数据封装
        if(!get.isCheckExistenceOnly()){
            Result result = table.get(get);
            for (Cell cell : result.rawCells()){
                String colName = Bytes.toString(cell.getQualifierArray(),cell.getQualifierOffset(),cell.getQualifierLength());
                String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                //这里可以使用设计模式解藕
                if(colName.equals("student_id")){
                    student.setStudent_id(value);
                }
                if(colName.equals("class")){
                    student.setStudent_class(value);
                }
                if (colName.equals("understanding")){
                    student.setUnderstanding(value);
                }
                if (colName.equals("programming")){
                    student.setProgramming(value);
                }
            }
        }
        return student;
    }

    public static void deleteByRowKey(Connection hbaseConnection,String tableName, String rowKey) throws IOException {
        Table table = hbaseConnection.getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        table.delete(delete);
    }



}
