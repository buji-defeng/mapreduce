package hbase;

import java.util.List;
public class TableInfo {

    private String tableName;
    private List<String> ColumnDescriptors;


    public TableInfo() {
    }

    public TableInfo(String tableName, List<String> ColumnDescriptors) {
        this.tableName = tableName;
        this.ColumnDescriptors = ColumnDescriptors;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getColumnDescriptors() {
        return ColumnDescriptors;
    }

    public void sethColumnDescriptors(List<String> ColumnDescriptors) {
        this.ColumnDescriptors = ColumnDescriptors;
    }
}
