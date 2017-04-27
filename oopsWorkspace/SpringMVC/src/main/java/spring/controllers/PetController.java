package spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.model.Pet;
import spring.service.PetService;

@Controller
public class PetController {
	
	private PetService petService;
	
	@Autowired(required=true)
	//@Qualifier(value="petService")
	public void setPetService(PetService ps){
		this.petService = ps;
	}
		
	@RequestMapping(value = "/user/pet", method = RequestMethod.GET)
	public String listPets(Model model,@RequestParam("userId") int userId) {
	
		System.out.println("In listPets");
		
		model.addAttribute("userId",userId);
		model.addAttribute("pet", new Pet());
		model.addAttribute("listPets", this.petService.listPets());
		return "pet";
	}
	
	//For add and update pet both
	@RequestMapping(value= "/user/pet/add")
	public String addPet(Model model, @ModelAttribute("pet") Pet p)
	{
		
		boolean error=false;
	
		//if(p.getId() == 0){
			//new pet, add it
		if(p.getPetSize()=="")
    	{
    		model.addAttribute("invalidInput","Error: Pet size cannot be empty");
    		error=true;
    	}
		if(p.getPetType()=="")
    	{
    		model.addAttribute("invalidInput","Error: Pet type cannot be empty");
    		error=true;
    	}
		if(p.getPetBreed()=="")
    	{
    		model.addAttribute("invalidInput","Error: Pet breed cannot be empty");
    		error=true;
    	}
		if(p.getPetName()=="")
    	{
    		model.addAttribute("invalidInput","Error: Pet name cannot be empty");
    		error=true;
    	}	
    	if(!error)
    		error=this.petService.addPet(p);
    
    		model.addAttribute("listPets", this.petService.listPets());
			
			/*if(error)
			{
				return "invalid-petname";
			}
			else
			{
				return "register-success";
			}
			}else{
		//	existing pet, call update
			this.petService.updatePet(p);
			}
		*/
			return "pet";
			
	}

	@RequestMapping("/user/pet/remove/{petId}")
    public String removePet(@PathVariable("petId") int petId){
		
        this.petService.removePet(petId);
        return "redirect:/pets";
    }
 
    @RequestMapping("/user/pet/edit/{petId}")
    public String editPet(@PathVariable("petId") int petId, Model model){
    	System.out.println("In edit");
        model.addAttribute("pet", this.petService.getPetById(petId));
        model.addAttribute("listPets", this.petService.listPets());
        return "pet";
    }
    
  /*
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
       return "redirect:pets";
    }
    
    @RequestMapping(value= "/pet/login", method = RequestMethod.POST)
	public String loginPet(Model model, @ModelAttribute("pet") Pet p){
    	
   // 	System.out.println("in login");
    	boolean retVal=this.petService.validatePet(p);
		int petId=0;
    	if(!retVal)
    	{
    	//	System.out.println("Doesn't exist");
    		return "redirect:/invalid-login";
    	}
    	else
    	{
    	//	System.out.println("Exists");
    		System.out.println("Pet Type is" + p.getPetType());
    		petId=p.getPetId();
    		model.addAttribute("petId",petId);
    		model.addAttribute("petname",p.getPetname());
    		model.addAttribute("firstName",p.getFirstName());

    		if(p.getPetType()=="Admin")
    		{
    			return "adminDashboard";
    		}
    		else if((p.getPetType()).equals("Owner"))
    		{
    			model.addAttribute("petCount",this.petService.getPetCount(petId));
    			model.addAttribute("contactInfo",this.petService.isContactInfoSet(petId));
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