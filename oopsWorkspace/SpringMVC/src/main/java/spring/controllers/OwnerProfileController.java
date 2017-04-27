package spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.model.OwnerProfile;
import spring.service.OwnerProfileService;

@Controller
public class OwnerProfileController {
	
	private OwnerProfileService ownerProfileService;
	
	@Autowired(required=true)
	//@Qualifier(value="ownerProfileService")
	public void setOwnerProfileService(OwnerProfileService ps){
		this.ownerProfileService = ps;
	}
		
	/*@RequestMapping(value = "/user/pet", method = RequestMethod.GET)
	public String listOwnerProfiles(Model model) {
		System.out.println("In listOwnerProfiles");
		model.addAttribute("ownerProfile", new OwnerProfile());
	//	model.addAttribute("listOwnerProfiles", this.ownerProfileService.listOwnerProfiles());
		return "pet";
	}*/
	
	//For add and update ownerProfile both
	/*@RequestMapping(value= "/ownerProfile/add", method = RequestMethod.POST)
	public String addOwnerProfile(@ModelAttribute("ownerProfile") OwnerProfile p)
	{
		
		boolean error=false;
		
		//if(p.getId() == 0){
			//new ownerProfile, add it
			error=this.ownerProfileService.addOwnerProfile(p);
			
			if(error)
			{
				return "invalid-ownerProfilename";
			}
			else
			{
				return "register-success";
			}
		//}else{
			//existing ownerProfile, call update
			//this.ownerProfileService.updateOwnerProfile(p);
		//}
		
	}
	
	@RequestMapping("/remove/{ownerProfileId}")
    public String removeOwnerProfile(@PathVariable("ownerProfileId") int id){
		
        this.ownerProfileService.removeOwnerProfile(id);
        return "redirect:/ownerProfiles";
    }
 
    @RequestMapping("/edit/{ownerProfileId}")
    public String editOwnerProfile(@PathVariable("ownerProfileId") int id, Model model){
        model.addAttribute("ownerProfile", this.ownerProfileService.getOwnerProfileById(id));
        model.addAttribute("listOwnerProfiles", this.ownerProfileService.listOwnerProfiles());
        return "ownerProfile";
    }
    
  
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
       return "redirect:ownerProfiles";
    }
    
    @RequestMapping(value= "/ownerProfile/login", method = RequestMethod.POST)
	public String loginOwnerProfile(Model model, @ModelAttribute("ownerProfile") OwnerProfile p){
    	
   // 	System.out.println("in login");
    	boolean retVal=this.ownerProfileService.validateOwnerProfile(p);
		int ownerProfileId=0;
    	if(!retVal)
    	{
    	//	System.out.println("Doesn't exist");
    		return "redirect:/invalid-login";
    	}
    	else
    	{
    	//	System.out.println("Exists");
    		System.out.println("OwnerProfile Type is" + p.getOwnerProfileType());
    		ownerProfileId=p.getOwnerProfileId();
    		model.addAttribute("ownerProfileId",ownerProfileId);
    		model.addAttribute("ownerProfilename",p.getOwnerProfilename());
    		model.addAttribute("firstName",p.getFirstName());

    		if(p.getOwnerProfileType()=="Admin")
    		{
    			return "adminDashboard";
    		}
    		else if((p.getOwnerProfileType()).equals("Owner"))
    		{
    			model.addAttribute("ownerProfileCount",this.ownerProfileService.getOwnerProfileCount(ownerProfileId));
    			model.addAttribute("contactInfo",this.ownerProfileService.isContactInfoSet(ownerProfileId));
    			return "ownerDashboard";
    		}
    		else 
    		{
    			
    			return "caretakerDashboard";
    		}
    	}
		
	}
    
    @RequestMapping(value= "/ownerProfile/signup", method = RequestMethod.POST)
  	public String signupOwnerProfile(Model model, @ModelAttribute("ownerProfile") OwnerProfile p){
      	
     // 	System.out.println("in login");
    	if(p.getOwnerProfileType()=="")
    	{
    		model.addAttribute("invalidInput","Error: OwnerProfile role cannot be empty");
    		return "signup";
    	}
    	if(p.getOwnerProfileName()=="")
    	{
    		model.addAttribute("invalidInput","Error: ownerProfilename cannot be empty");
    		return "signup";
    	}
      	boolean retVal=this.ownerProfileService.addOwnerProfile(p);
  		
      	if(retVal)
      	{
      		model.addAttribute("invalidInput","Error: OwnerProfilename exists");
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
    }*/
}