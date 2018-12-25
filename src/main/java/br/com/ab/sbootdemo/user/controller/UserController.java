package br.com.ab.sbootdemo.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String userView(@Valid UserModel user, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Invalid data-entry!");
			return "redirect:/userView";
		}
		userRepository.save(user);
		attributes.addFlashAttribute("mensagem", "User has added successfully!");
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
	public String userDetail(@PathVariable("id") long id, @Valid AddressModel addressModel, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Invalid data-entry!");
			return "redirect:/{id}";
		}
		UserModel userModel = userRepository.findById(id);
		addressModel.setUserModel(userModel);
		addressRepository.save(addressModel);
		attributes.addFlashAttribute("mensagem", "Address has added succcessfully!");
		return "redirect:/{id}";
	}

}
