package com.subGrove.inter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import com.subGrove.dto.TableInfoDto;
import com.subGrove.model.GetDataBase;
import com.subGrove.vo.IndexSheetVo;
import com.subGrove.vo.TableInfoSheetVo;
import com.subGrove.vo.list.IndexSheetVoList;

public class GetBodyData {
    
    public IndexSheetVo getBodyData() {

        List<TableInfoSheetVo> list = new ArrayList<>();

        Set<Entry<String, Object>> enSet = GetDataBase.Entire_DataBase.entrySet();
        for (Entry<String, Object> entry : enSet) {
            
            TableInfoSheetVo
            
            
            TableInfoDto tableInfo = (TableInfoDto) entry.getValue();
            IndexSheetVoList unit = new IndexSheetVoList(tableInfo.getTableName(),
                    tableInfo.getTableCom(), "");
            list.add(unit);
        }
        
        
        
        
        
        return list;
    }
}
