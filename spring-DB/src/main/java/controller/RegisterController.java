package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.AlreadyExistingmemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

@Controller
public class RegisterController {
	
	private MemberRegisterService memberRegisterService;
	public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
		this.memberRegisterService=memberRegisterService;
	}
	@RequestMapping("/register/step1")
	public String handlerStep1() {
		return"register/step1";
	}
	@RequestMapping(value="/register/step2",method=RequestMethod.POST)
	public String handleStep2(HttpServletRequest request,Model model) {
		String agreeParam=request.getParameter("agree");
		if(agreeParam==null||!agreeParam.equals("true")) {
			return "register/step1";
		}
		model.addAttribute("formData",new RegisterRequest());
		return "register/step2";
	}
	@RequestMapping(value="/register/step2",method=RequestMethod.GET)
	public String handleStep2() {
		return "redirect:/register/step1";
	}
	@RequestMapping(value="/register/step3",method=RequestMethod.POST)
	public String handleStep3(@ModelAttribute("formData")RegisterRequest regReq) {
		try {
			memberRegisterService.regist(regReq);
			return "/register/step3";
		}catch(AlreadyExistingmemberException e) {
			return "register/step2";
		}
		
		
	}

}
