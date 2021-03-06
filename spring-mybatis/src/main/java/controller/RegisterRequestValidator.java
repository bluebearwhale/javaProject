package controller;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.RegisterRequest;

public class RegisterRequestValidator implements Validator{
	private static final String emailRegExp="^[_A-za-z0-9-\\+]*(\\.[_A-Za-z0-9-]+)*@"+
				"[A-za-z0-9-]+(\\.[A-za-z0-9]+)*(\\.[_A-Za-z]{2,})$";
	private Pattern pattern;
	public RegisterRequestValidator() {
		pattern=Pattern.compile(emailRegExp);
	}
	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return RegisterRequest.class.isAssignableFrom(arg0);
	}
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		RegisterRequest regReq=(RegisterRequest) target;
		if(regReq.getEmail()==null||regReq.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "reqired");
			
		}else {
			Matcher matcher=pattern.matcher(regReq.getEmail());
			System.out.println(matcher.matches());
			if(!matcher.matches()) {
				errors.rejectValue("email", "bad");
			}
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","required");
		ValidationUtils.rejectIfEmpty(errors, "password", "required");
		ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "required");
		if(!regReq.getPassword().isEmpty()) {
			if(!regReq.isPasswordEqualToConfirmPassword()) {
				errors.rejectValue("confirmPassword", "nomatch");
			}
		}
	}
	

}
