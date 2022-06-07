package com.rays.p10.ctl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rays.p10.common.BaseCtl;
import com.rays.p10.common.DropDownList;
import com.rays.p10.common.ORSResponse;
import com.rays.p10.common.attachment.AttachmentDTO;
import com.rays.p10.common.attachment.AttachmentServiceInt;
import com.rays.p10.common.mail.EmailDTO;
import com.rays.p10.common.mail.EmailServiceImpl;
import com.rays.p10.dto.RoleDTO;
import com.rays.p10.dto.UserDTO;
import com.rays.p10.form.ChangePassForm;
import com.rays.p10.form.ForgetPasswordForm;
import com.rays.p10.form.UserForm;
import com.rays.p10.services.RoleServiceInt;
import com.rays.p10.services.UserServiceInt;

@RestController
@RequestMapping("/User")
public class UserCtl extends BaseCtl<UserForm, UserDTO, UserServiceInt>{

	@Autowired
	RoleServiceInt roleServiceInt;
	
	@Autowired
	UserServiceInt userServiceInt;
	
	@Autowired
	AttachmentServiceInt attachmentService;
	
	/**
	 * Send email
	 */
	@Autowired
	public EmailServiceImpl emailSender;
	
	public UserCtl() {
	System.out.println("inside UserCtl--------------");
	}
	
	
	
	@GetMapping("/preload")
	public ORSResponse preload() {
		
		System.out.println("inside preload of UserCtl");
		ORSResponse res = new ORSResponse(true);
		RoleDTO dto = new RoleDTO();
		UserDTO udto=new UserDTO();
		//dto.setStatus(RoleDTO.ACTIVE);
		List<DropDownList> list = roleServiceInt.search(dto, userContext);
		res.addResult("rList", list); 
		
		List<DropDownList> ulist=userServiceInt.search(udto, userContext);
		
		res.addResult("uemail", ulist);
		
		return res;
	}
	
	


	@PostMapping("/changepass")
	public ORSResponse changePassword(@RequestBody @Valid ChangePassForm form, BindingResult br) {

		System.out.println("inside changepass of UserCtl +++++++"+form.getOldPassword()+ form.getNewPassword());
		ORSResponse res=validate(br);
		
		if(!res.isSuccess()) {
			return res;
		}
		
		if(userContext==null)
		{
			res.setSuccess(false);
			res.addMessage("You are not logged-in");
			return res;
		}
		
		
		UserDTO dto=userContext.getUserDTO();
		//System.out.println(" "+ dto.getEmail()  + form.getOldPassword() + form.getNewPassword());
		System.out.println("email==="+dto.getEmail());
	
		UserDTO changeDto=baseServiceInt.changePassword(form.getLoginId(), form.getOldPassword(), form.getNewPassword(), userContext);
		
		if(changeDto==null) {
			res.setSuccess(false);
			res.addMessage("invalid old password");
			return res;
		}
		
		res.setSuccess(true);
		res.addMessage("Password has been changed");

		return res;
		
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
		System.out.println("form.getLogin(====" + form.getLogin());

		UserDTO fDTO = baseServiceInt.forgotPassword(form.getLogin());
		System.out.println("password is===== " + fDTO.getPassword());
		if (fDTO == null) {
			res.setSuccess(false);
			res.addMessage("LoginId / Email not found.");
			return res;
		} else {
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
	 * Uploads profile picture of given user id
	 * 
	 * @param userId
	 * @param file
	 * @param req
	 * @return
	 */
	@PostMapping("/profilePic/{userId}")
	public ORSResponse uploadPic(@PathVariable Long userId, @RequestParam("file") MultipartFile file,
			HttpServletRequest req) {

		System.out.println("User ID id --------------" + userId);

		UserDTO userDTO = baseServiceInt.findById(userId, userContext);

		System.out.println("@@@@@@@@@@@@@@@@@"  + userDTO.getModifiedBy());
		AttachmentDTO doc = new AttachmentDTO(file);

		doc.setDescription("Profile picture");
		System.out.println(doc.getDescription() + "description");

		doc.setPath(req.getServletPath());
		System.out.println(doc.getPath() + "path-----");
		doc.setUserId(userId);
		System.out.println(doc.getUserId() + "id-----");

		if (userDTO.getImageId() != null && userDTO.getImageId() > 0) {
			doc.setId(userDTO.getImageId());
		}
		System.out.println("before calling save");
		Long imageId = attachmentService.save(doc, userContext);
		System.out.println("after save");
		// Update new image id
		if (userDTO.getImageId() == null || userDTO.getImageId() == 0) {
			userDTO.setImageId(imageId);
			baseServiceInt.update(userDTO, userContext);
		}

		ORSResponse res = new ORSResponse();
		res.setSuccess(true);
		res.addResult("imageId", imageId);

		return res;
	}

	
	/**
	 * Downloads profile picture of given user id
	 * 
	 * @param userId
	 * @param response
	 */
	@GetMapping("/profilePic/{userId}")
	public @ResponseBody void downloadPic(@PathVariable Long userId, HttpServletResponse response) {

		UserDTO userDTO = baseServiceInt.findById(userId, userContext);
		AttachmentDTO attachmentDTO = attachmentService.findById(userDTO.getImageId(), userContext);
		try {
			if (attachmentDTO != null) {
				response.setContentType(attachmentDTO.getType());
				OutputStream out = response.getOutputStream();
				out.write(attachmentDTO.getDoc());
				out.close();
			} else {
				response.getWriter().write("ERROR: File not found");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
