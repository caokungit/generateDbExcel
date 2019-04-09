package com.subGrove.vo.list;

public class IndexSheetVoList {
    private String tableName;
    private String tableCom;
    private String remark;

    public IndexSheetVoList() {
        super();
    }

    public IndexSheetVoList(String tableName, String tableCom, String remark) {
        super();
        this.tableName = tableName;
        this.tableCom = tableCom;
        this.remark = remark;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableCom() {
        return tableCom;
    }

    public void setTableCom(String tableCom) {
        this.tableCom = tableCom;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "IndexDetail [tableName=" + tableName + ", tableCom=" + tableCom + ", remark="
                + remark + "]";
    }
}
