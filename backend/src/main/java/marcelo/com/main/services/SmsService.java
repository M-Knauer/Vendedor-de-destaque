package marcelo.com.main.services;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import marcelo.com.main.entities.Sale;
import marcelo.com.main.repositories.SaleRepository;

@Service
public class SmsService {
	
	@Autowired
	private SaleRepository sr;

	@Value("${twilio.sid}")
	private String twilioSid;

	@Value("${twilio.key}")
	private String twilioKey;

	@Value("${twilio.phone.from}")
	private String twilioPhoneFrom;

	@Value("${twilio.phone.to}")
	private String twilioPhoneTo;

	public void sendSms(Long id) {
		
		Sale sale = sr.findById(id).get();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		String date = sale.getDate().format(dtf);
		
		String msg = String.format("O vendedor %s vendeu R$%.2f, ganhando assim o destaque do mês no dia %s", sale.getSellerName(), sale.getAmount(), date); 
		
		Twilio.init(twilioSid, twilioKey);

		PhoneNumber to = new PhoneNumber(twilioPhoneTo);
		PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

		Message message = Message.creator(to, from, msg).create();

		System.out.println(message.getSid());
	}
}
