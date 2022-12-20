package marcelo.com.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import marcelo.com.main.entities.Sale;
import marcelo.com.main.services.SaleService;

@RestController
@RequestMapping("/sales")
public class SaleController {

	@Autowired
	private SaleService ss;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.FOUND)
	public List<Sale> findSales() {
		return ss.findSales();
	}
	
	
	
}
