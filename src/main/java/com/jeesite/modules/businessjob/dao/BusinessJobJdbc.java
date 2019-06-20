package com.jeesite.modules.businessjob.dao;

import com.jeesite.modules.businessjob.dto.EmployeeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Repository
public class BusinessJobJdbc {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<EmployeeDto> findByOfficeCode(String officeCode, String roleCode) {
        String sql = "SELECT e.emp_code, e.emp_name,e.office_code FROM js_sys_employee e LEFT JOIN js_sys_user_role ur ON e.emp_code = ur.user_code WHERE ur.role_code = :roleCode AND e.office_code IN (:office_codes)";
        String[] split = officeCode.split(",");
        List<String> codes = Arrays.asList(split);
        Map<String, Object> params = new HashMap<>();
        params.put("office_codes", codes);
        params.put("roleCode", roleCode);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        List<EmployeeDto> list = namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper(EmployeeDto.class));
        log.info("数据上报员集合 -> {}",list);
        return list;
    }
}