package spring;

import java.sql.Timestamp;
import java.util.Date;

public class Member {
	private String name;
	private Long id;
	private String password;
	private String email;
	private Date regdate;
	public Member() {}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Member(String email,String password, String name, Date regdate) {
		this.email=email;
		this.password=password;
		this.name=name;
		this.regdate=regdate;
	}
	public void changePassword(String oldPassword,String newPassword) {
		if(password.equals(newPassword)) {
		this.password=newPassword;
		}
	}
	public boolean matchPassword(String password) {
		return this.password.equals(password);
	}
}
