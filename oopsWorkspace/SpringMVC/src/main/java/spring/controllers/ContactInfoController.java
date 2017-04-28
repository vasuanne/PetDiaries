package spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.model.ContactInfo;
import spring.service.ContactInfoService;

@Controller
public class ContactInfoController {
	
	private ContactInfoService contactInfoService;
	
	@Autowired(required=true)
	//@Qualifier(value="contactInfoService")
	public void setContactInfoService(ContactInfoService ps){
		this.contactInfoService = ps;
	}
		
	@RequestMapping(value = "/user/contactInfo", method = RequestMethod.GET)
	public String listContactInfo(Model model,@RequestParam("userId") int userId
			) {
	
		System.out.println("In listContactInfo");
		model.addAttribute("userId",userId);
		model.addAttribute("addContactInfo",true);
		model.addAttribute("contactInfo", new ContactInfo());
		List<ContactInfo> list=this.contactInfoService.listContactInfos();
		model.addAttribute("listContactInfo",list );
		
		return "contactInfo";
	}
	
	//For add and update contactInfo both
	@RequestMapping(value= "/user/contactInfo/add")
	public String addContactInfo(Model model, @ModelAttribute("contactInfo") ContactInfo p)
	{
		System.out.println("IN add contact info");
		boolean error=false;
		if(p.getId()>0)
			model.addAttribute("editContactInfo",true);
		else
		model.addAttribute("addContactInfo",true);
	
		//if(p.getId() == 0){
			//new contactInfo, add it
		
		if(p.getEmailAddr()=="")
    	{
    		model.addAttribute("invalidInput","Error: Email Address cannot be empty");
    		error=true;
    	}
		if(p.getZipcode()=="")
    	{
    		model.addAttribute("invalidInput","Error: Zipcode cannot be empty");
    		error=true;
    	}
		if(p.getPhoneNumber()=="")
    	{
    		model.addAttribute("invalidInput","Error: Phone number cannot be empty");
    		error=true;
    	}
		if(p.getState()=="")
    	{
    		model.addAttribute("invalidInput","Error: State cannot be empty");
    		error=true;
    	}	
		if(p.getCity()=="")
    	{
    		model.addAttribute("invalidInput","Error: City cannot be empty");
    		error=true;
    	}
		if(p.getStreet()=="")
    	{
    		model.addAttribute("invalidInput","Error: Street cannot be empty");
    		error=true;
    	}	
		
		System.out.println("In add"+p.getId()+" "+p.getCity());
    	if(!error)
    	{
    		
    			if(p.getId()>0)
    			{
    				this.contactInfoService.updateContactInfo(p);
    			}
    			else
    			{

	    			error=this.contactInfoService.addContactInfo(p);
	    			
    			}
    			model.addAttribute("addContactInfo",false);
    			model.addAttribute("editContactInfo",false);
    			model.addAttribute("listContactInfo", this.contactInfoService.listContactInfos());
    	}
    		
  //  		model.addAttribute("listContactInfo", this.contactInfoService.listContactInfo());
    //	}
			/*if(error)
			{
				return "invalid-contactInfoname";
			}
			else
			{
				return "register-success";
			}
			}else{
		//	existing contactInfo, call update
			this.contactInfoService.updateContactInfo(p);
			}
		*/
    	
			return "contactInfo";
			
	}

	@RequestMapping("/user/contactInfo/remove")
    public String removeContactInfo(
    		Model model,
    		@ModelAttribute("contactInfo") ContactInfo p,
    		RedirectAttributes ra){
		
		
		System.out.println("ContactInfo delete");
	//	model.addAttribute("addContactInfo",true);
        this.contactInfoService.removeContactInfo(p.getId());
        ra.addAttribute("userId",p.getUserId());
        return "redirect:/user/contactInfo";
    }
	
    //@RequestMapping(value="/user/contactInfo/edit/{id}/{userId}/{street}/{city}/{state}/{phoneNumber}/{zipcode}/{emailAddr}")
	@RequestMapping(value="/user/contactInfo/edit")
	public String editContactInfo(
    	/*	@PathVariable("id") int id, 
    		@PathVariable("userId") int userId, 
    		@PathVariable("street") String street,
    		@PathVariable("city") String city,
    		@PathVariable("state") String state,
    		@PathVariable("phoneNumber") String phoneNumber,
    		@PathVariable("zipcode") String zipcode,
    		@PathVariable("emailAddr") String emailAddr,*/
    		Model model,
    		@ModelAttribute("contactInfo") ContactInfo p)
	    	{
    		
	    		System.out.println("IN edit contact info"+p.getEmailAddr());
	    	
	    	/*	model.addAttribute("addContactInfo", false);
		        model.addAttribute("editContactInfo", true);
		        model.addAttribute("listContactInfo", this.contactInfoService.listContactInfos());
		        model.addAttribute("id", id);
		        model.addAttribute("userId", userId);
		        model.addAttribute("street", street);
		    
		        model.addAttribute("city", city);
		        model.addAttribute("state", state);
		        model.addAttribute("phoneNumber", phoneNumber);
		        model.addAttribute("zipcode", zipcode);
		        model.addAttribute("emailAddr", emailAddr);*/
	    		model.addAttribute("addContactInfo", false);
		        model.addAttribute("editContactInfo", true);
		    //    model.addAttribute("listContactInfo", this.contactInfoService.listContactInfos());
		        model.addAttribute("id", p.getId());
		        model.addAttribute("userId", p.getUserId());
		        model.addAttribute("street", p.getStreet());
		    
		        model.addAttribute("city", p.getCity());
		        model.addAttribute("state", p.getState());
		        model.addAttribute("phoneNumber", p.getPhoneNumber());
		        model.addAttribute("zipcode", p.getZipcode());
		        model.addAttribute("emailAddr", p.getEmailAddr());
	    		
		        return "contactInfo";
	    	}
    
  /*
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
       return "redirect:contactInfos";
    }
    
    @RequestMapping(value= "/contactInfo/login", method = RequestMethod.POST)
	public String loginContactInfo(Model model, @ModelAttribute("contactInfo") ContactInfo p){
    	
   // 	System.out.println("in login");
    	boolean retVal=this.contactInfoService.validateContactInfo(p);
		int contactInfoId=0;
    	if(!retVal)
    	{
    	//	System.out.println("Doesn't exist");
    		return "redirect:/invalid-login";
    	}
    	else
    	{
    	//	System.out.println("Exists");
    		System.out.println("ContactInfo Type is" + p.getContactInfoType());
    		contactInfoId=p.getContactInfoId();
    		model.addAttribute("contactInfoId",contactInfoId);
    		model.addAttribute("contactInfoname",p.getContactInfoname());
    		model.addAttribute("firstName",p.getFirstName());

    		if(p.getContactInfoType()=="Admin")
    		{
    			return "adminDashboard";
    		}
    		else if((p.getContactInfoType()).equals("Owner"))
    		{
    			model.addAttribute("contactInfoCount",this.contactInfoService.getContactInfoCount(contactInfoId));
    			model.addAttribute("contactInfo",this.contactInfoService.isContactInfoSet(contactInfoId));
    			return "ownerDashboard";
    		}
    		else 
    		{
    			
    			return "caretakerDashboard";
    		}
    	}
		
	}
    
   
      
    
    @RequestMapping(value = "/invalid-login", method = RequestMethod.GET)
    public String dispLoginError() {
       return "loginError";
    }*/
    

}