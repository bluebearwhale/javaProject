package spring;

import spring.Member;
import spring.MemberDao;

public class ChangePasswordService {
	private MemberDao memberDao;
	public ChangePasswordService(MemberDao memberDao) {
		this.memberDao=memberDao;
	}
	public void changePassword(String email,String oldPwd, String newPwd) {
		Member member=memberDao.selectByEmail(email);
		if(member==null) {
			throw new MemberNotFoundException();
		}
		memberDao.update(member);
	}
}
