package spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.dao.PetDAO;
import spring.model.Pet;

@Service
public class PetServiceImpl implements PetService {
	
	private PetDAO petDAO;

	public void setPetDAO(PetDAO petDAO) {
		this.petDAO = petDAO;
	}

	@Override
	@Transactional
	public boolean addPet(Pet p) {
		boolean error=false;
		error=this.petDAO.addPet(p);
		return error;
	}

	@Override
	@Transactional
	public void updatePet(Pet p) {
		this.petDAO.updatePet(p);
	}

	@Override
	@Transactional
	public List<Pet> listPets() {
		return this.petDAO.listPets();
	}

	@Override
	@Transactional
	public boolean validatePet(Pet p)
	{
		return this.petDAO.validatePet(p);
	}
	@Override
	@Transactional
	public Pet getPetById(int id) {
		return this.petDAO.getPetById(id);
	}

	@Override
	@Transactional
	public void removePet(int id) {
		this.petDAO.removePet(id);
	}
	
	@Override
	@Transactional
	public int getPetCount(int id)
	{
		return this.petDAO.getPetCount(id);
	}
	
	@Override
	@Transactional
	public boolean isContactInfoSet(int id)
	{
		return this.petDAO.isContactInfoSet(id);
	}
	
}