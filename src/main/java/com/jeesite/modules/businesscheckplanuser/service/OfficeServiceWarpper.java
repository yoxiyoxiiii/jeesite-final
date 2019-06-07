package com.jeesite.modules.businesscheckplanuser.service;

import com.jeesite.modules.sys.entity.Office;
import com.jeesite.modules.sys.service.OfficeService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OfficeServiceWarpper {

    @Getter
    @Autowired
    private OfficeService officeService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Office> findListIn(List<String> officeCodes) {
        String sql = "select o.* from js_sys_office o where o.tree_level!=0 and o.office_code in (:office_codes)";
        Map<String, Object> params  = new HashMap<>();
        params.put("office_codes",officeCodes);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        List<Office> list = namedParameterJdbcTemplate.query(sql, params, new RowMapper<Office>() {
            @Override
            public Office mapRow(ResultSet rs, int rowNum) throws SQLException {
                Office office = new Office();
                office.setOfficeCode(rs.getString("office_code"));
                office.setOfficeName(rs.getString("office_name"));
                return office;
            }
        });
        return list;
    }


}
