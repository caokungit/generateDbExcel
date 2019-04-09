package com.subGrove.vo;

import java.util.List;

import com.subGrove.vo.list.IndexSheetVoList;

public class IndexSheetVo {

    private List<IndexSheetVoList> list;

    public IndexSheetVo() {
        super();
    }

    public IndexSheetVo(List<IndexSheetVoList> list) {
        super();
        this.list = list;
    }

    public List<IndexSheetVoList> getList() {
        return list;
    }

    public void setList(List<IndexSheetVoList> list) {
        this.list = list;
    }
}
