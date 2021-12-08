package br.com.zup.recargacelular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.*;

@SpringBootApplication
@EnableScheduling
public class RecargaCelularApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecargaCelularApplication.class, args);
	}

}
