package ua.com.myshop.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/female")
public class FemaleController {

	@GetMapping
	public String show(){
		return "user-female";
	}
}
