package test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("testCategoryRunnable")
public class TestCategoryRunnable implements Runnable {
	@Resource
    NamedParameterJdbcTemplate jdbcTemplate2;

	public void read() {
		String sql = "select * from user";
		List<Map<String, Object>> dataList = jdbcTemplate2.query(sql,
				new RowMapper() {

					@Override
					public Object mapRow(ResultSet rs, int index)
							throws SQLException {
						Map<String, Object> dataMap = new HashMap<String, Object>();
						dataMap.put("UR_ID", rs.getString("UR_ID"));
						return dataMap;
					}
				});
		System.out.println("dataList : " + dataList);
	}

	@Override
	public void run() {
		read();
	}

}
