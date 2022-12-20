package marcelo.com.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import marcelo.com.main.entities.Sale;
import marcelo.com.main.repositories.SaleRepository;

@Service
public class SaleService {

	
	@Autowired
	SaleRepository sr;
	
	@Transactional(readOnly = true)
	public List<Sale> findSales() {
		return sr.findAll();
	}
	
	public Sale findSale(Long id) {
		return sr.findById(id).get();
		
	}
}
