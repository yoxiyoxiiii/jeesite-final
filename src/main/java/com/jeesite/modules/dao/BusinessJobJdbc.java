package com.jeesite.modules.dao;

import com.jeesite.modules.dto.EmployeeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Repository
public class BusinessJobJdbc {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<EmployeeDto> findByOfficeCode(String officeCode, String roleCode) {
        String sql = "SELECT e.emp_code, e.emp_name,e.office_code FROM js_sys_employee e LEFT JOIN js_sys_user_role ur ON e.emp_code = ur.user_code WHERE ur.role_code = :roleCode AND e.office_code IN (:office_codes) ";
        String[] split = officeCode.split(",");
        List<String> codes = Arrays.asList(split);
        Map<String, Object> params = new HashMap<>();
        params.put("office_codes", codes);
        params.put("roleCode", roleCode);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        List<EmployeeDto> list = namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper(EmployeeDto.class));
        log.info("数据上报员集合 -> {}",list);
        //数据管理员去重
        return list.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(EmployeeDto::getOfficeCode))),ArrayList::new));
    }

    public EmployeeDto findOfficeCode(String departmentId, String dataReporter) {

        String sql = "SELECT e.emp_code, e.emp_name,e.office_code FROM js_sys_employee e LEFT JOIN js_sys_user_role ur ON e.emp_code = ur.user_code WHERE ur.role_code = :roleCode AND e.office_code = :office_code";

        Map<String, Object> params = new HashMap<>();
        params.put("office_code", departmentId);
        params.put("roleCode", dataReporter);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        return namedParameterJdbcTemplate.query(sql, params, new ResultSetExtractor<EmployeeDto>() {
            @Override
            public EmployeeDto extractData(ResultSet rs) throws SQLException, DataAccessException {
                boolean next = rs.next();
                if (next) {
                    return EmployeeDto
                            .builder()
                            .emp_code(rs.getString("emp_code"))
                            .emp_name(rs.getString("emp_name"))
                            .officeCode(rs.getString("office_code"))
                            .build();
                }
                return null;
            }
        });
    }
}