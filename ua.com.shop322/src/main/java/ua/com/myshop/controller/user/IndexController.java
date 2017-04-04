package ua.com.myshop.controller.user;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.com.myshop.entity.User;
import ua.com.myshop.service.MailSendingService;
import ua.com.myshop.service.UserService;
import ua.com.myshop.validator.UserValidator;

@Controller
@SessionAttributes("user")
public class IndexController {

	@Autowired
	private UserService userService;
	
//	@Autowired
//	private MailSendingService mailSendingService;
	
	@InitBinder("user")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new UserValidator(userService));
	}

	@RequestMapping("/")
	public String index(Principal principal) {
		if(principal!=null){
			System.out.println(principal.getName());
		}
		return "user-index";
	}

	@RequestMapping("/admin")
	public String admin() {
		return "admin-admin";
	}

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("user", new User());
		return "user-registration";
	}

	@PostMapping("/registration")
	public String save(@ModelAttribute("user") @Valid User user, BindingResult br, SessionStatus status) {
		if(br.hasErrors()) return "user-registration";
		userService.save(user);
//		mailSendingService.sendMail("Hello "+user.getName(), user.getEmail(), "��������� �� ���� Shoe Store ������� ������!");
		status.setComplete();
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String login() {
		return "user-login";
	}
}
