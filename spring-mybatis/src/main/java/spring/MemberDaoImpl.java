package spring;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class MemberDaoImpl implements MemberDao{
	private SqlSessionTemplate sqlSessionTemplate;
	public MemberDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate=sqlSessionTemplate;
	}
	@Override
	public void update(Member member) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.update("update",member);
	}

	@Override
	public void insert(Member member) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.insert("insert",member);
	}

	@Override
	public Object selectByEmail(String email) {
		// TODO Auto-generated method stub
		
		return sqlSessionTemplate.selectOne("selectByEmail",email);
	}

	@Override
	public List<Member> selectAll() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("list");
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("count");
	}

	@Override
	public List<Member> selectByRegdate(Date from, Date to) {
		// TODO Auto-generated method stub
		HashMap<String,Date> map=new HashMap<String, Date>();
		map.put("from", from);
		map.put("to", to);
		return sqlSessionTemplate.selectList("selectByRegdate",map);
	}

	@Override
	public Member selectById(Long id) {
		// TODO Auto-generated method stub
		List<Member> results=sqlSessionTemplate.selectList("selectById",id);
		
		return results.isEmpty() ? null:results.get(0);
	}

}
