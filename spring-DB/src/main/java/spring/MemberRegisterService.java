package spring;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import spring.Member;
import spring.MemberDao;

public class MemberRegisterService {
	@Resource(name="memberDao")
	private MemberDao memberDao;
	
	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao=memberDao;
	}
	
	public MemberRegisterService() {}
	public void regist(RegisterRequest req) {
		System.out.println(req.getEmail());
		Member member=memberDao.selectByEmail(req.getEmail());
		if(member !=null) {
			throw new AlreadyExistingmemberException("dup email"+req.getEmail());
		}
		Member newmember=new Member(
				req.getEmail(),req.getPassword(),req.getName(),new Date()
				);
		memberDao.insert(newmember);
	}
}
