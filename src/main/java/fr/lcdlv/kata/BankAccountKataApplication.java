package fr.lcdlv.kata;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan( basePackages = {"fr.lcdlv.kata.domain"} )
public class BankAccountKataApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountKataApplication.class, args);
	}

}
