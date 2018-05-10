package com.silu.jdbcTemple;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service
public class NavigationDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	static boolean isCount = false;
	static int count = 0;

	public boolean doSort(String dirc, int order) throws Exception {
		// UP
		if (dirc.equals("1")) {
			// 非首页
			if (order > 1) {
				Object[] arr = new Object[] { order };
				Map<String, Object> ids = jdbcTemplate.queryForMap("SELECT id FROM navigation n  WHERE n.order= ? ",
						arr);

				int id = 0;
				if (ids.isEmpty()) {
					throw new Exception("没有次 记录信息");
				}

				id = ((Long) ids.get("id")).intValue();

				jdbcTemplate.update("UPDATE navigation n SET n.order=? WHERE n.order=?;",
						new Object[] { order, order - 1 });
				jdbcTemplate.update("UPDATE navigation n SET n.order= ? WHERE n.id= ? ;",
						new Object[] { order - 1, id });
				return true;
			}
		} else {
			// DOWN
			if (!isCount) {
				count = getCount();
			}
			if (order < count) {
				List<Integer> ids = jdbcTemplate.queryForList("SELECT id FROM navigation n  WHERE n.order= ? ",
						new Object[] { order }, Integer.class);
				int id = 0;
				if (ids.isEmpty()) {
					throw new Exception("没有次 记录信息");
				}
				for (Integer map : ids) {
					id = map;
				}
				jdbcTemplate.update("UPDATE navigation n SET n.order= ? WHERE n.order= ? ;",
						new Object[] { order, order + 1 });
				jdbcTemplate.update("UPDATE navigation n SET n.order= ? WHERE n.id= ? ;",
						new Object[] { order + 1, id });
				return true;
			}
		}
		return false;
	}

	private int getCount() {
		isCount = true;
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM navigation", Integer.class);
	}
}