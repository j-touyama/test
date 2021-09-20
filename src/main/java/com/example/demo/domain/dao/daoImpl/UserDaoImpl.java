package com.example.demo.domain.dao.daoImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public String getUser(int id) throws Exception {
		String sql = "SELECT name FROM test WHERE id = ?";
		Map<String, Object> map = jdbc.queryForMap(sql, id);

		String name = (String) map.get("name");

		return name;
	}

}
