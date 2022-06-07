package com.rays.p10.ctl;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.p10.common.BaseCtl;
import com.rays.p10.common.ORSResponse;
import com.rays.p10.common.UserContext;
import com.rays.p10.common.mail.EmailDTO;
import com.rays.p10.common.mail.EmailServiceImpl;
import com.rays.p10.dto.UserDTO;
import com.rays.p10.form.ForgetPasswordForm;
import com.rays.p10.form.LoginForm;
import com.rays.p10.form.UserForm;
import com.rays.p10.form.UserRegistrationForm;
import com.rays.p10.services.UserServiceInt;

@RestController
@RequestMapping("/Auth")
public class LoginCtl extends BaseCtl<UserForm, UserDTO, UserServiceInt>{

	
	/**
	 * Send email
	 */
	@Autowired
	public EmailServiceImpl emailSender;
	
	
	@GetMapping("test")
	public String test() {
		return "hiii from login ctl";
		
	}
	

	/**
	 * Forgot password
	 * 
	 * @param form
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("forgetPassword")
	public ORSResponse forgetPassword(@RequestBody @Valid ForgetPasswordForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);
		System.out.println("inside FP loginCtl--------------");
		System.out.println("form.getLogin(====" + form.getLogin());

		UserDTO fDTO = baseServiceInt.forgotPassword(form.getLogin());
		//System.out.println("password is===== " + fDTO.getPassword());
		if (fDTO == null) {
			res.setSuccess(false);
			res.addMessage("Email not found.");
			return res;
		} else {
			System.out.println("FDTO not null===== ");
			String code = "U-FP";
			EmailDTO dto = new EmailDTO();
			dto.addTo(fDTO.getEmail());
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("code", "U-FP");
			params.put("password",fDTO.getPassword());
			params.put("email", fDTO.getEmail());
			dto.setMessageCode(code, params);
			dto.setIsHTML(true);
			emailSender.send(dto, null);
			res.setSuccess(true);
			res.addMessage("Hello " + fDTO.getFirstName() + " " + fDTO.getLastName()
					+ " ! Your password has been sent on your email.");

		}

		return res;
	}

	


	
	/**
	 * Finds user by login id
	 * 
	 * @param loginId
	 * @return
	 */
	@GetMapping("login/{email}")
	public ORSResponse get(@PathVariable String email) {
		ORSResponse res = new ORSResponse(true);
		UserDTO dto = baseServiceInt.findByEmail(email, userContext);
		System.out.println("User " + dto);
		if (dto != null) {
			UserDTO userDTO = new UserDTO();
			userDTO.setFirstName(dto.getFirstName());
			userDTO.setLastName(dto.getLastName());
			userDTO.setEmail(dto.getEmail());
//			userDTO.setGender(dto.getGender());
			res.addData(userDTO);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}
	
	
	@PostMapping("login")
	public ORSResponse login(@RequestBody @Valid LoginForm form, BindingResult br, HttpSession session)
	{
		System.out.println("inside LoginCtl post login----------");
		System.out.println("email---- " + form.getLoginId() + "password----" + form.getPassword());
		ORSResponse res=validate(br);
		if(!res.isSuccess()) {
			return res;
		}
		
		UserDTO dto= baseServiceInt.authenticate(form.getLoginId(), form.getPassword());
		
		if(dto==null) {
			res.setSuccess(false);
			res.addMessage("invalid ID or Password");
			
			
		}
		
		else {
		UserContext uc= new UserContext(dto);
		session.setAttribute("uc", uc);
		System.out.println("user context inside login ctl------- " + uc);
		res.setSuccess(true);
		res.addData(dto);
		res.addResult("jsessionid", session.getId());
		
		System.out.println("jsessionid " + session.getId());
		}

		System.out.println("Login User context : " + session.getAttribute("uc")) ;
		
		return res;
		
	}
	
	/**
	 * Register new user
	 * 
	 * @param form
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("signUp")
	public ORSResponse signUp(@RequestBody @Valid UserRegistrationForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}

		UserDTO dto = baseServiceInt.findByEmail(form.getEmail(), userContext);
 
		if (dto != null) {
			res.setSuccess(false);
			res.addMessage("Login Id already exists");
			return res;
		}
		
		dto = new UserDTO();
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setEmail(form.getEmail());
		dto.setGender(form.getGender());
		dto.setDob(form.getDob());
		dto.setPhone(form.getPhone());
		
		baseServiceInt.register(dto);

		res.setSuccess(true);
		res.addMessage("User has been registered");
		return res;
	}


	
}
