package br.com.ab.sbootdemo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
		userRepository.save(user);
		return "redirect:/userView";		
	}
	
	@RequestMapping("/users")
	public ModelAndView userList() {
		ModelAndView mav = new ModelAndView("index");
		Iterable<UserModel> users = userRepository.findAll();
		mav.addObject("users", users);
		return mav;
	}
	
	@RequestMapping("/{id}")
	public ModelAndView userDetail(@PathVariable("id") long id) {
		UserModel userModel = userRepository.findById(id);
		ModelAndView mav = new ModelAndView("userDetail");
		mav.addObject("user", userModel);
		return mav;
	}
}
