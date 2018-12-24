package br.com.ab.sbootdemo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ab.sbootdemo.user.model.UserModel;
import br.com.ab.sbootdemo.user.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/userView", method=RequestMethod.GET)
	public String userView() {
		return "user/userView";
	}

	@RequestMapping(value="/userView", method=RequestMethod.POST)
	public String userView(UserModel user) {
		System.out.println("Vai salvar");
		userRepository.save(user);
		return "redirect:/userView";		
	}
	
//	@RequestMapping("/")
//	public String index() {
//		return "index";
//	}
}
