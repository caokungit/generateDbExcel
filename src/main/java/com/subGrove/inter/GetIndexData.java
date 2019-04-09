package com.subGrove.inter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.subGrove.dto.TableInfoDto;
import com.subGrove.model.GetDataBase;
import com.subGrove.vo.IndexSheetVo;
import com.subGrove.vo.list.IndexSheetVoList;

/**
 * 类名称 : GetIndexData
 * 类描述 : 获取目录数据
 * 创建人 : caokun
 * 创建日期 : 2019年4月9日
 */
public class GetIndexData {

    public IndexSheetVo getIndexData() {

        List<IndexSheetVoList> list = new ArrayList<>();

        Set<Entry<String, Object>> enSet = GetDataBase.Entire_DataBase.entrySet();
        for (Entry<String, Object> entry : enSet) {

            TableInfoDto tableInfo = (TableInfoDto) entry.getValue();
            IndexSheetVoList unit = new IndexSheetVoList(tableInfo.getTableName(),
                    tableInfo.getTableCom(), "");
            list.add(unit);
        }
        return new IndexSheetVo(list);
    }

}
