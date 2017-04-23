package spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.model.User;
import spring.service.UserService;

@Controller
public class UserController {
	
	private UserService userService;
	
	@Autowired(required=true)
	//@Qualifier(value="userService")
	public void setUserService(UserService ps){
		this.userService = ps;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String listUsers(Model model) {
		System.out.println("In listUsers");
		model.addAttribute("user", new User());
		model.addAttribute("listUsers", this.userService.listUsers());
		return "user";
	}
	
	//For add and update user both
	@RequestMapping(value= "/user/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User p){
		
		System.out.println("In addUsers"+p.getName() +" "+ p.getCountry());
		if(p.getId() == 0){
			//new user, add it
			this.userService.addUser(p);
		}else{
			//existing user, call update
			this.userService.updateUser(p);
		}
		
		return "redirect:/users";
		
	}
	
	@RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id){
		
        this.userService.removeUser(id);
        return "redirect:/users";
    }
 
    @RequestMapping("/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model){
        model.addAttribute("user", this.userService.getUserById(id));
        model.addAttribute("listUsers", this.userService.listUsers());
        return "user";
    }
    
    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String redirect() {
       return "redirect:signUp";
    }
   
    
    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String finalPage() {
       return "signup";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
       return "redirect:users";
    }
    
    @RequestMapping(value= "/user/login", method = RequestMethod.POST)
	public String loginUser(@ModelAttribute("user") User p){
    	
   // 	System.out.println("in login");
    	boolean retVal=this.userService.validateUser(p);
		
    	if(!retVal)
    	{
    	//	System.out.println("Doesn't exist");
    		return "redirect:/invalid-login";
    	}
    	else
    	{
    	//	System.out.println("Exists");
    		return "dashboard";
    	}
		
	}
   
    @RequestMapping(value = "/invalid-login", method = RequestMethod.GET)
    public String dispLoginError() {
       return "loginError";
    }
}