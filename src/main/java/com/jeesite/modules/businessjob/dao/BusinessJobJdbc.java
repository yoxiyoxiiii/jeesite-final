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

    public EmployeeDto findByOfficeCode(String officeCode,String roleCode) {
        String sql = "SELECT e.emp_code as user_code , e.emp_name,e.office_code FROM js_sys_employee e LEFT JOIN js_sys_user_role ur ON e.emp_code = ur.user_code WHERE ur.role_code = :roleCode AND e.office_code IN (:office_codes) limit 1";
        String[] split = officeCode.split(",");
        List<String> codes = Arrays.asList(split);
        Map<String, Object> params  = new HashMap<>();
        params.put("office_codes",codes);
        params.put("roleCode", roleCode);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        List<EmployeeDto> list = namedParameterJdbcTemplate.query(sql, params, new RowMapper<EmployeeDto>() {
            @Override
            public EmployeeDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return EmployeeDto.builder().emp_code(rs.getString("user_code"))
                        .emp_name(rs.getString("emp_name"))
                        .officeCode(rs.getString("office_code"))
                        .build();
            }
        });
        return list.stream().findFirst().orElse(new EmployeeDto());
    }
}
