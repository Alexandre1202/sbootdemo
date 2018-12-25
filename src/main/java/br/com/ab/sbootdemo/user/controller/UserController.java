package br.com.ab.sbootdemo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.ab.sbootdemo.user.model.AddressModel;
import br.com.ab.sbootdemo.user.model.UserModel;
import br.com.ab.sbootdemo.user.repository.AddressRepository;
import br.com.ab.sbootdemo.user.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
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
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView userDetailed(@PathVariable("id") long id) {
		UserModel userModel = userRepository.findById(id);
		ModelAndView mav = new ModelAndView("user/userDetailed");
		mav.addObject("userModel", userModel);
		Iterable<AddressModel> addressModel = addressRepository.findByUserModel(userModel);
		mav.addObject("addressModel", addressModel);
		return mav;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public String userDetail(@PathVariable("id") long id, AddressModel addressModel) {
		UserModel userModel = userRepository.findById(id);
		addressModel.setUserModel(userModel);
		addressRepository.save(addressModel);
		return "redirect:/{id}";
	}

}
