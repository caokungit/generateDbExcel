package com.subGrove.mapper;

import java.util.List;

import com.subGrove.dto.FieldInfoDto;

public interface TableInfoMapper {
    List<FieldInfoDto> selectAllTable();
}
