package com.rays.p10.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rays.p10.dto.UserDTO;



/**
 * @author Asus
 *
 * @param <F>
 * @param <T>
 * @param <S>
 */
public class BaseCtl<F extends BaseForm,T extends BaseDTO, S extends BaseServiceInt<T>>{

	
	/**
	 * Form operations
	 */
	protected static final String OP_SAVE = "Save";
	protected static final String OP_NEW = "New";
	protected static final String OP_DELETE = "Delete";
	protected static final String OP_CANCEL = "Cancel";
	protected static final String OP_ERROR = "Error";
	protected static final String OP_NEXT = "Next";
	protected static final String OP_PREVIOUS = "Previous";
	protected static final String OP_LOGOUT = "Logout";
	protected static final String OP_GO = "Go";
	protected static final String OP_GET = "Get";

	
	@Autowired
	protected S baseServiceInt = null;
	
	public BaseCtl() {
	
	System.out.println("inside BaseCtl----------------------------");
	}
	
	
	@GetMapping("/check")
	public String check() {
		
		System.out.println("inside Check of BaseCtl");
		
		return "Run BaseCtl";
		
	}
	
	
	/**
	 * Contains context of logged-in user
	 */
	protected UserContext userContext = null;

	/**
	 * Get user context from session
	 * 
	 * @param session
	 */
	@ModelAttribute
	public void setUserContext(HttpSession session) {
		userContext = (UserContext) session.getAttribute("userContext");
		if(userContext == null){
			UserDTO dto  = new UserDTO();
			dto.setEmail("ep@gmail.com");
			
			dto.setOrgId(007L);
			dto.setOrgName("Root ORG");
			userContext = new UserContext(dto);
		}
	
	}

	/**
	 * Search entities by form attributes
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/search", method = { RequestMethod.GET})
	public ORSResponse search( F form) {
		System.out.println("Inside Search of BaseCtl doGet+++++++++++++++++++++++++++");
		// Calculate next page number
		String operation = form.getOperation();
		int pageNo = form.getPageNo();
		System.out.println("next#########"+operation);
		System.out.println("previous#########"+operation);
		
		int pageSize=10;
		
		if (OP_NEXT.equals(operation)) {
			
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			
			pageNo--;
		}

		// 0 is first page index
		pageNo = (pageNo < 0) ? 0 : pageNo;
		System.out.println("set page no  in BaeCtl search");
		form.setPageNo(pageNo);
		
		System.out.println("getDto inside BaseCtl from RoleDto");
		
		T dto = (T) form.getDto();

		
		ORSResponse res = new ORSResponse(true);
 
List list=baseServiceInt.search(dto, pageNo, pageSize, userContext);
		
		System.out.println("pagesize--------------------"+ pageSize);
		System.out.println("list size--------------@@@ "+ list.size());
		if(list.size()==0) {
			System.out.println("list==null------------");
			res.setSuccess(false);
			res.addData("record not found");
			//System.out.println("list size--------------@@@2222222 "+ list.size());
		return res;
		}
		else {
			System.out.println("record in list-----------------");
			res.addData(list);
			return res;
		}
		
	}
	
	
	@PostMapping("/search")
	public ORSResponse searchBy(@RequestBody F form) {
		
		System.out.println("inside post searchBy baseCtl----------");
		
		String op=form.getOperation();
		
		int pageNo=form.getPageNo();
		int pageSize=10;
		if(OP_NEXT.equals(op)) {
			pageNo++;
		}
		else
			if(OP_PREVIOUS.equals(op)) {
				pageNo--;
			}
		
		pageNo=(pageNo < 0) ? 0 : pageNo;
		form.setPageNo(pageNo);
		
		T dto = (T) form.getDto();
		
		ORSResponse res=new ORSResponse(true);

		List list=baseServiceInt.search(dto, pageNo, pageSize, userContext);
		
		System.out.println("pagesize--------------------"+ pageSize);
		System.out.println("list size--------------"+ list.size());
		if(list.size()==0) {
			System.out.println("list==null------------");
			res.setSuccess(false);
			res.addData("record not found");
			
		return res;
		}
		else {
			System.out.println("record in list-----------------");
			res.addData(list);
			return res;
		}
		
		
		
	}
	
	
	/**
	 * Get entity by primary key ID
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {
		System.out.println("inside findbyId BaseCtl-----------------");
		ORSResponse res = new ORSResponse(true);
		T dto = baseServiceInt.findById(id, userContext);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

	
	/**
	 * Delete entity by primary key IDdd
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("delete/{id}")
	public ORSResponse delete(@PathVariable long id) {
		ORSResponse res = new ORSResponse(true);
		try {
			T dto = baseServiceInt.delete(id, userContext);
			res.addMessage("Record Deleted Successfully---------");
			System.out.println("Record Deleted Successfully---------");
		} catch (Exception e) {
			res.setSuccess(false);
			res.addMessage(e.getMessage());
		}
		return res;
	}
	
	@PostMapping("/save")
	public ORSResponse save(@RequestBody @Valid F form, BindingResult br) {
		
		
		
		System.out.println("inside save base ctl----------");
		System.out.println("=========="+ br);
		ORSResponse res=validate(br);
		String msg;
		if(res.isSuccess()==false) {
			return res;
		}
		try {
		T dto = (T) form.getDto();
		
		if (dto.getId() != null && dto.getId() > 0) {
			
			System.out.println("inside update of basectl");
			baseServiceInt.update(dto, userContext);
			//res.addData(uid);

				msg="Record Successfuly Updated";
			
		} else {
			System.out.println("dto id========"+ dto.getId());
//			System.out.println("&&&&&&&&----org-------&&&&&&&"+userContext.getOrgName()+
//					userContext.getOrgId()+userContext.getLoginId());
			System.out.println("call baseservice add-------------");
			baseServiceInt.add(dto, userContext);
			
			//msg="Record Successfuly Added";
			
		}
		 res.addData(dto.getId());
		//res.addData(msg);
		} catch (Exception e) {
			res.setSuccess(false);
			res.addMessage(e.getMessage());
			e.printStackTrace();
		}
		return res;
		
	}
	
	
	
	
	@RequestMapping(value = "/search/{pageNo}", method = {  RequestMethod.POST })
	public ORSResponse search(@RequestBody F form, @PathVariable int pageNo) {
		System.out.println("BaseCtl Search method run%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ");
		// 0 is first page index
		pageNo = (pageNo < 0) ? 0 : pageNo;

		T dto = (T) form.getDto();
		int pageSize=5;

		ORSResponse res = new ORSResponse(true);
		System.out.println("BaseCtl search run");
		System.out.println("PageNo is:............."+pageNo + "Page size is****************" + pageSize);

List list=baseServiceInt.search(dto, pageNo, pageSize, userContext);
		
		System.out.println("pagesize--------------------"+ pageSize);
		System.out.println("list size--------------"+ list.size());
		if(list.size()==0) {
			System.out.println("list==null------------");
			res.setSuccess(false);
			res.addData("record not found---------------");
			
		return res;
		}
		else {
			System.out.println("record in list-----------------"+list.size());
			res.addData(list);
			return res;
		}
	}
	
	
//	@RequestMapping(value = "/search/{pageNo}", method = { RequestMethod.GET })
//	public ORSResponse searchby(@RequestBody F form, @PathVariable int pageNo) {
//		System.out.println("BaseCtl Search method run------------------ ");
//		// 0 is first page index
//		pageNo = (pageNo < 0) ? 0 : pageNo;
//
//		T dto = (T) form.getDto();
//		int pageSize=5;
//
//		ORSResponse res = new ORSResponse(true);
//		System.out.println("BaseCtl search run");
//		System.out.println("PageNo is:............."+pageNo + "Page size is****************" + pageSize);
//
//List list=baseServiceInt.search(null, pageNo, pageSize, userContext);
//		
//		System.out.println("pagesize--------------------"+ pageSize);
//		System.out.println("list size--------------"+ list.size());
//		if(list.size()==0) {
//			System.out.println("list==null------------");
//			res.setSuccess(false);
//			res.addData("record not found");
//			
//		return res;
//		}
//		else {
//			System.out.println("record in list-----------------"+list.size());
//			res.addData(list);
//			return res;
//		}
//	}
	
	
	
	
	/**
	 * Gets input error messages and put into REST response
	 * 
	 * @param bindingResult
	 * @return
	 */
	public ORSResponse validate(BindingResult bindingResult) {
		ORSResponse res = new ORSResponse(true);

		System.out.println("ors response valiate method");
		
		if (bindingResult.hasErrors()) {

			res.setSuccess(false);

			Map<String, String> errors = new HashMap<String, String>();

			List<FieldError> list = bindingResult.getFieldErrors();
			// Lambda expression Java 8 feature
			list.forEach(e -> {
				errors.put(e.getField(), e.getDefaultMessage());
			});
			res.addInputErrors(errors);
		}
		return res;

	}
	
}
