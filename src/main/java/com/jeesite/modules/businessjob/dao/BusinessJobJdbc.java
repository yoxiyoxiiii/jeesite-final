package com.jeesite.modules.businessjob.dao;

import com.jeesite.modules.businessjob.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BusinessJobJdbc {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<EmployeeDto> findByOfficeCode(String officeCode) {
        String sql = "select e.emp_code as user_code,e.emp_name from js_sys_employee e where office_code in (:office_codes)";
        String[] split = officeCode.split(",");
        List<String> codes = Arrays.asList(split);
        Map<String, Object> params  = new HashMap<>();
        params.put("office_codes",codes);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        List<EmployeeDto> list = namedParameterJdbcTemplate.query(sql, params, new RowMapper<EmployeeDto>() {
            @Override
            public EmployeeDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return EmployeeDto.builder().emp_code(rs.getString("user_code")).emp_name(rs.getString("emp_name")).build();
            }
        });
        return list;
    }
}
