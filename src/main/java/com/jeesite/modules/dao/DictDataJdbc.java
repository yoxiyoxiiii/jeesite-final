package com.jeesite.modules.dao;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DictDataJdbc {
    @Data
    @Builder
    public static class DictDataDto {
      private String id;
      private String dictValue;
      private String dictType;
      private String extend_s1;
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public DictDataDto getDictDataDto(String dictType, String value) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        Map<String, Object> params = new HashMap<>();
        params.put("dictType", dictType);
        params.put("dictValue", value);
        String sql = "select dict_code,extend_s1,dict_value as dictValue ,dict_type as dictType  from js_sys_dict_data as jsd where jsd.dict_value = :dictValue and jsd.dict_type = :dictType";
      return namedParameterJdbcTemplate.query(sql, params, new ResultSetExtractor<DictDataDto>() {
           @Override
           public DictDataDto extractData(ResultSet rs) throws SQLException, DataAccessException {
               boolean next = rs.next();
               if (next) {
                   return DictDataDto.builder()
                           .dictType(rs.getString("dictType"))
                           .extend_s1(rs.getString("extend_s1"))
                           .dictValue(rs.getString("dictValue"))
                           .id(rs.getString("dict_code"))
                           .build();
               }
               return DictDataDto.builder().build();
           }
       });
    }

}
