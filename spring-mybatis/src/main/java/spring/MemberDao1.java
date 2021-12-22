package spring;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MemberDao1 {
	private static long nextId=0;
	private JdbcTemplate jdbcTemplate;
	
	public MemberDao1(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	public Member selectByEmail(String email) {
		List<Member> result=jdbcTemplate.query("select * from MEMBER where Email=?",
				new MemberRowMapper(),email);
		return result.isEmpty() ? null: result.get(0);
	}
	public Member selectById(Long id) {
		List<Member> result=jdbcTemplate.query("select * from MEMBER where ID=?",
				new MemberRowMapper(),id);
		return result.isEmpty() ? null: result.get(0);
	}
	public List<Member> selectAll(){
		List<Member>result=jdbcTemplate.query(
				"select * from MEMBER",
				new MemberRowMapper());
		return result;
				
	}
	public int count() {
		Integer count=jdbcTemplate.queryForObject("select count(*) from MEMBER",Integer.class);
		return count;
	}
	public void update(Member member) {
		jdbcTemplate.update("update MEMBER set NAME=?,PASSWORD=? where EMAIL=?",
				member.getName(),member.getPassword(),member.getEmail());
	}
	public void insert(final Member member) {
		KeyHolder keyHolder=new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
				PreparedStatement pstmt=conn.prepareStatement("insert into MEMBER (ID, EMAIL, PASSWORD, NAME, REGDATE) "
						+"values (MEMBER_SEQ.nextval,?,?,?,?)",
						new String[] {"ID"});
		pstmt.setString(1, member.getEmail());
		pstmt.setString(2, member.getPassword());
		pstmt.setString(3, member.getName());
		pstmt.setTimestamp(4, new Timestamp(member.getTimestamp().getTime()));
		return pstmt;
			}
		},keyHolder);
		Number keyValue =keyHolder.getKey();
		member.setId(keyValue.longValue());
		
	}
	public List<Member> selectByRegdate(Date from,Date to){
		List<Member> results=jdbcTemplate.query("select * from MEMBER where REGDATE between ? and ?"
				+ "order by REGDATE desc", new MemberRowMapper(),from,to);
		return results;
	}
}
