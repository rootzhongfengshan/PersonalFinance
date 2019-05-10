package net.vv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class PersonalFinanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalFinanceApplication.class, args);
	}
}
