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
		System.out.println("In user controller");
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
	public String addUser(@ModelAttribute("user") User p)
	{
		
		boolean error=false;
		System.out.println("In addUsers"+p.getUsername() +" "+ p.getPassword());
		//if(p.getId() == 0){
			//new user, add it
			error=this.userService.addUser(p);
			
			if(error)
			{
				return "invalid-username";
			}
			else
			{
				return "register-success";
			}
		//}else{
			//existing user, call update
			//this.userService.updateUser(p);
		//}
		
	}
	
	@RequestMapping("/remove/{userId}")
    public String removeUser(@PathVariable("userId") int id){
		
        this.userService.removeUser(id);
        return "redirect:/users";
    }
 
    @RequestMapping("/edit/{userId}")
    public String editUser(@PathVariable("userId") int id, Model model){
        model.addAttribute("user", this.userService.getUserById(id));
        model.addAttribute("listUsers", this.userService.listUsers());
        return "user";
    }
    
    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String redirect() {
       return "redirect:signUp";
    }
   
    
    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String finalPage(@ModelAttribute("user") User p) {
       return "signup";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
       return "redirect:users";
    }
    
    @RequestMapping(value= "/user/login", method = RequestMethod.POST)
	public String loginUser(Model model, @ModelAttribute("user") User p){
    	
   // 	System.out.println("in login");
    	boolean retVal=this.userService.validateUser(p);
		int userId=0;
    	if(!retVal)
    	{
    	//	System.out.println("Doesn't exist");
    		return "redirect:/invalid-login";
    	}
    	else
    	{
    	//	System.out.println("Exists");
    		System.out.println("User Type is" + p.getUserType());
    		userId=p.getUserId();
    		model.addAttribute("userId",userId);
    		model.addAttribute("username",p.getUsername());
    		model.addAttribute("firstName",p.getFirstName());

    		if(p.getUserType()=="Admin")
    		{
    			return "adminDashboard";
    		}
    		else if((p.getUserType()).equals("Owner"))
    		{
    			model.addAttribute("petCount",this.userService.getPetCount(userId));
    			model.addAttribute("contactInfo",this.userService.isContactInfoSet(userId));
    			return "ownerDashboard";
    		}
    		else 
    		{
    			
    			return "caretakerDashboard";
    		}
    	}
		
	}
    
    @RequestMapping(value= "/user/signup", method = RequestMethod.POST)
  	public String signupUser(Model model, @ModelAttribute("user") User p){
      	
     // 	System.out.println("in login");
    	
    	if(p.getFirstName()=="" || p.getLastName()=="")
    	{
    		model.addAttribute("invalidInput","Error: First and last name cannot be empty");
    		return "signup";
    	}
    	if(p.getUserType()=="")
    	{
    		model.addAttribute("invalidInput","Error: User role cannot be empty");
    		return "signup";
    	}
    	if(p.getPassword()=="")
    	{
    		model.addAttribute("invalidInput","Error: Password cannot be empty");
    		return "signup";
    	}
    	if(p.getUsername()=="")
    	{
    		model.addAttribute("invalidInput","Error: username cannot be empty");
    		return "signup";
    	}
      	boolean retVal=this.userService.addUser(p);
  		
      	if(retVal)
      	{
      		model.addAttribute("invalidInput","Error: Username exists");
      		return "signup";
      	}
      	else
      	{
      	//	System.out.println("Exists");
      		return "register-success";
      	}
  		
  	}
      
    
    @RequestMapping(value = "/invalid-login", method = RequestMethod.GET)
    public String dispLoginError() {
       return "loginError";
    }
    
    @RequestMapping(value= "/user/dash", method = RequestMethod.POST)
   	public String displayDashboard(Model model, 
   			@ModelAttribute("pet") User u1,
   			@ModelAttribute("contactInfo") User u2){
    		User u=null;
      // 	System.out.println("in login");
    		if(u1.getUserId()>0)
    			u = this.userService.getUserById(u1.getUserId());
    		else if(u2.getUserId()>0)
    			u = this.userService.getUserById(u2.getUserId());
       	//	System.out.println("Exists");
       		System.out.println("User Type is" + u1.getUserType());
       		
  
       		int userId=u.getUserId();
       		model.addAttribute("userId",userId);
       		model.addAttribute("username",u.getUsername());
       		model.addAttribute("firstName",u.getFirstName());

       		if(u.getUserType()=="Admin")
       		{
       			return "adminDashboard";
       		}
       		else if((u.getUserType()).equals("Owner"))
       		{
       			model.addAttribute("petCount",this.userService.getPetCount(userId));
       			model.addAttribute("contactInfo",this.userService.isContactInfoSet(userId));
       			return "ownerDashboard";
       		}
       		else 
       		{
       			
       			return "caretakerDashboard";
       		}
       	}
   		
   	
}