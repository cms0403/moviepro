package com.project2.movieproject.controller;

import java.util.ArrayList;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project2.movieproject.command.Criteria;
import com.project2.movieproject.command.PageVO;
import com.project2.movieproject.command.UserVO;
import com.project2.movieproject.command.qaVO;
import com.project2.movieproject.user.EmailServiceImpl;
import com.project2.movieproject.user.OtherService;
import com.project2.movieproject.user.UserService;

@SessionAttributes("vo")
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	OtherService service;

	
	@ModelAttribute("vo")
	public UserVO setUserVO() {
		System.out.println("***********setUserVO()**********");
		return new UserVO();
	}
	
	@GetMapping("/userLogin")
	public String userLogin() {
		
		return "user/userLogin";
	}
	
	@GetMapping("/userRegist")
	public String userRegist() {
		
		return "user/userRegist";
	}
	
	@GetMapping("/userMypage")
	public String userMypage(@ModelAttribute("vo") UserVO vo, Model model) {
		String db_id = vo.getUser_id();
		System.out.println(vo.getUser_id());
		if(vo.getUser_id()==null) {
			return "redirect:/user/userLogin";
		}

		ArrayList<UserVO> userdata = userService.userdata(db_id);
		System.out.println(userdata);
		
		ArrayList<qaVO> myqalist = userService.myqa_read(db_id);
		System.out.println(myqalist);
		
		model.addAttribute("userdata", userdata);
		model.addAttribute("myqalist", myqalist);
		return "user/userMypage";
	}
	
	@GetMapping("/userQnaRegist")
	public String userQnaRegist(@ModelAttribute("vo") UserVO vo, Model model) {
		model.addAttribute("vo", vo);
		return "user/userQnaRegist";
	}
	
	@PostMapping("/qa_regist")
	public String qa_regist(qaVO vo, RedirectAttributes RA) {

		int result = userService.qa_regist(vo);
		System.out.println("sdkff");
		if(result == 1) { //??????
			RA.addFlashAttribute("msg", vo.getQa_title() + "??? ?????? ??????");
		} else { //??????
			RA.addFlashAttribute("msg", "?????? ??????, ??????????????? ???????????????.");
		}
		return "redirect:/user/userQnaRegist";
	}
	
	@GetMapping("/userPhone")
	public String userPhone() {
		return "user/userPhone";
	}
	
	@PostMapping("/user_update_phone")
	public String user_update_phone(@ModelAttribute("vo") UserVO vo, UserVO newvo, Model model, RedirectAttributes RA) {
		String db_id = vo.getUser_id();
		ArrayList<UserVO> userdata = userService.userdata(db_id);
		
		newvo.setUser_id(db_id);
		System.out.println(newvo.getUser_id());
		System.out.println(newvo.getUser_phone());
		System.out.println(newvo.getUser_newphone());
		System.out.println(userdata.get(0).getUser_phone());
		
		if(newvo.getUser_phone().equals(userdata.get(0).getUser_phone())) {
			System.out.println("??????????????? ?????? ??????");
			int result = userService.user_update(newvo);
			model.addAttribute("result", result);
			if(result == 1 ) {
				RA.addFlashAttribute("msg", vo.getUser_id() + "??? ?????????????????? ?????????????????????");
			} else {
				RA.addFlashAttribute("msg", "????????? ??????????????????. ??????????????? ???????????????");
			}
		} else {
			RA.addFlashAttribute("msg", "????????? ?????? ?????????????????? ????????????????????????. ?????? ??????????????????.");
			System.out.println("????????? ???????????? ??????");
		}

		return "redirect:/user/userPhone";
	}
	
	@GetMapping("/userEmail")
	public String userEmail() {
		return "user/userEmail";
	}
	
	@PostMapping("/user_update_email")
	public String user_update_email(@ModelAttribute("vo") UserVO vo, UserVO newvo, Model model, RedirectAttributes RA) {
		String db_id = vo.getUser_id();
		ArrayList<UserVO> userdata = userService.userdata(db_id);
		
		newvo.setUser_id(db_id);
		System.out.println(newvo.getUser_id());
		System.out.println(newvo.getUser_email());
		System.out.println(newvo.getUser_newemail());
		System.out.println(userdata.get(0).getUser_email());
		
		if(newvo.getUser_email().equals(userdata.get(0).getUser_email())) {
			System.out.println("????????? ?????? ??????");
			int result = userService.user_update(newvo);
			model.addAttribute("result", result);
			if(result == 1 ) {
				RA.addFlashAttribute("msg", vo.getUser_id() + "??? ???????????? ?????????????????????");
			} else {
				RA.addFlashAttribute("msg", "????????? ??????????????????. ??????????????? ???????????????");
			}
		} else {
			RA.addFlashAttribute("msg", "????????? ?????? ???????????? ????????????????????????. ?????? ??????????????????.");
			System.out.println("???????????? ???????????? ??????");
		}

		return "redirect:/user/userEmail";
	}
	
	@GetMapping("/userPassword")
	public String userPassword() {
		return "user/userPassword";
	}
	
	@PostMapping("/user_update_password")
	public String user_update_password(@ModelAttribute("vo") UserVO vo, UserVO newvo, Model model, RedirectAttributes RA) {
		String db_id = vo.getUser_id();
		ArrayList<UserVO> userdata = userService.userdata(db_id);
		
		newvo.setUser_id(db_id);
		System.out.println(newvo.getUser_id());
		System.out.println(newvo.getUser_password());
		System.out.println(newvo.getUser_newpassword());
		System.out.println(userdata.get(0).getUser_password());
		
		if(newvo.getUser_password().equals(userdata.get(0).getUser_password())) {
			System.out.println("???????????? ????????????");
			int result = userService.user_update(newvo);
			model.addAttribute("result", result);
			if(result == 1 ) {
				RA.addFlashAttribute("msg", vo.getUser_id() + "??? ??????????????? ?????????????????????");
			} else {
				RA.addFlashAttribute("msg", "????????? ??????????????????. ??????????????? ???????????????");
			}
		} else {
			RA.addFlashAttribute("msg", "????????? ?????? ??????????????? ????????????????????????. ?????? ??????????????????.");
			System.out.println("??????????????? ???????????? ??????");
		}

		return "redirect:/user/userPassword";
	}
	
	@GetMapping("/userQnaRead")
	public String userQnaRead(@RequestParam("qa_key") Integer qa_key, Model model) {
		ArrayList<qaVO> myqa = userService.qa_read(qa_key);
		model.addAttribute("myqa", myqa);
		
		
		return "user/userQnaRead";
	}
	
	@GetMapping("/userBirth")
	public String userBirth() {
		return "user/userBirth";
	}
	
	@PostMapping("/user_update_birth")
	public String user_update_birth(@ModelAttribute("vo") UserVO vo, UserVO newvo, Model model, RedirectAttributes RA) {
		String db_id = vo.getUser_id();
		ArrayList<UserVO> userdata = userService.userdata(db_id);
		
		newvo.setUser_id(db_id);
		System.out.println(newvo.getUser_id());
		System.out.println(newvo.getUser_birth());
		System.out.println(newvo.getUser_newbirth());
		System.out.println(userdata.get(0).getUser_birth());
		
		if(newvo.getUser_birth().equals(userdata.get(0).getUser_birth())) {
			System.out.println("???????????? ?????? ??????");
			int result = userService.user_update(newvo);
			model.addAttribute("result", result);
			if(result == 1 ) {
				RA.addFlashAttribute("msg", vo.getUser_id() + "??? ??????????????? ?????????????????????");
			} else {
				RA.addFlashAttribute("msg", "????????? ??????????????????. ??????????????? ???????????????");
			}
		} else {
			RA.addFlashAttribute("msg", "????????? ?????? ??????????????? ????????????????????????. ?????? ??????????????????.");
			System.out.println("????????? ???????????? ??????");
		}

		return "redirect:/user/userBirth";
	}
	
	//???????????? ???
	@PostMapping("/RegistForm")
	public String RegistForm(UserVO vo,
							 RedirectAttributes RA, Model model) throws Exception {
		
		int result = userService.regist(vo);
		if(result == 1) { //??????
			ArrayList<UserVO> userdata = userService.userdata(vo.getUser_id());
			model.addAttribute("vo", userdata);
			RA.addFlashAttribute("msg", vo.getUser_id() + "??? ?????? ??????, ??????????????? ?????????.");
		} else { //??????
			RA.addFlashAttribute("msg", "?????? ??????, ??????????????? ???????????????.");
		}
		return "user/usermailCheck";	//???????????? ???????????????
	}
	
	//????????? ???
	@PostMapping("/LoginForm")
	public String LoginForm(UserVO vo,
							RedirectAttributes RA,
							Model model) {
		String db_id = vo.getUser_id();
        int count_id = userService.idCheck(db_id);
		
		ArrayList<UserVO> userdata = userService.userdata(db_id);
		
		if(userdata.get(0).isUser_auth()) {
			if(count_id > 0) { //??????
				if(vo.getUser_password().equals(userdata.get(0).getUser_password())) {
					RA.addFlashAttribute("msg", db_id + "??? ?????? ?????????");
					model.addAttribute("vo", userdata);
					if(userdata.get(0).isUser_admin() == true) {
						return "redirect:/admin/adminMain1";
					}
					return "redirect:/main";
				} else {

					RA.addFlashAttribute("msg", "????????? ??????,???????????? ??????????????? ??????????????????.");
					return "redirect:/user/userLogin";
				}
			} else { //??????
				RA.addFlashAttribute("msg", "????????? ??????, ???????????? ??????????????? ???????????????.");
				return "redirect:/user/userLogin";
			}
		} else {
			model.addAttribute("vo", userdata);
			RA.addFlashAttribute("msg", "????????? ????????? ?????????????????????. ??????????????????");
			return "redirect:/user/usermailCheck";
		}
		
		
	}
	
    // ????????? ??????
    @PostMapping("/idCheck")
    @ResponseBody
    public int idCheck(UserVO vo, Model model, RedirectAttributes RA, @RequestParam("id") String id){
        int cnt = userService.idCheck(id);
        return cnt;
    }
    
    // ?????? ??????
    @PostMapping("/user_drop")
    public String user_drop(@ModelAttribute("vo") UserVO vo, Model model, RedirectAttributes RA) {
    	int drop = userService.user_delete(vo);
    	
    	if(drop > 0) { //??????
				RA.addFlashAttribute("msg", vo.getUser_id() + "?????????????????????.");
				return "redirect:/user/userLogin";
		} else { //??????
			RA.addFlashAttribute("msg", "?????? ??????, ??????????????? ???????????????.");
			return "redirect:/user/userLogin";
		}
    }
    
    @GetMapping("/userDetail")
    public String userDetail(@RequestParam("user_id") String dbid) {
    	
		ArrayList<UserVO> userdata = userService.userdata(dbid);
		System.out.println(userdata);
    	
    	return "user/userDetail";
    }
    
    @GetMapping("/userFind")
    public String userFind() {
    	return "user/userLogin";
    }
    
    
    @GetMapping("/userInfo")
    public String userInfo(Model model,Criteria cri) {
       
    
    	
      ArrayList<UserVO> userlist = userService.userlist(cri);
      int total =userService.total(cri);
      
      PageVO pageVO = new PageVO(cri,total);
      
      System.out.println(cri.getSearchType());
      System.out.println(cri.getSearchName());
      
      System.out.println(userlist);
      model.addAttribute("userlist", userlist);
      model.addAttribute("pageVO",pageVO);
       return "user/userInfo";
    }
    
    @GetMapping("/usermailCheck")
	public void emailConfirm(@ModelAttribute("vo") UserVO vo)throws Exception{
		service.sendSimpleMessage(vo.getUser_email());	
		System.out.println("?????? ?????? ????????? : "+vo.getUser_email());
	}
    
    @PostMapping("/verifyCode")
	public String verifyCode(String code, @ModelAttribute("vo") UserVO vo, SessionStatus status, RedirectAttributes RA) {
		
		System.out.println("code : "+code);
		System.out.println(vo.getUser_email());
		System.out.println("code match : "+ EmailServiceImpl.ePw.equals(code));
		if(EmailServiceImpl.ePw.equals(code)) {
			RA.addFlashAttribute("msg", vo.getUser_id() + " ???????????? ??????");
			System.out.println(vo.isUser_auth());
			userService.auth_update(vo.getUser_id());
			System.out.println(vo.isUser_auth());
			
			status.setComplete();
			return "redirect:/user/userLogin";
		} else {
			int result = userService.user_delete(vo);
			if(result > 0) { //??????
				RA.addFlashAttribute("msg", vo.getUser_id() + "???????????? ??????. ?????? ????????????.");
				return "redirect:/user/userRegist";
		} else { //??????
			RA.addFlashAttribute("msg", "?????? ??????, ??????????????? ???????????????.");
			return "redirect:/user/userLogin";
		}
		}
		
	}
    
    
}
