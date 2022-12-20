package marcelo.com.main.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import marcelo.com.main.entities.Sale;
import marcelo.com.main.repositories.SaleRepository;

@Service
public class SaleService {

	
	@Autowired
	SaleRepository sr;
	
	@Transactional(readOnly = true)
	public Page<Sale> findSales(String minDate, String maxDate, Pageable pageable) {
		
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		
		LocalDate min = minDate.isEmpty() ? today.minusYears(1) : LocalDate.parse(minDate);
		LocalDate max = maxDate.isEmpty() ? today : LocalDate.parse(maxDate);
		
		return sr.findSales(min, max, pageable);
	}
	
}
