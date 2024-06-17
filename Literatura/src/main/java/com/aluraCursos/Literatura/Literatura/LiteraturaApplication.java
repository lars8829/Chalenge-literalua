package com.aluraCursos.Literatura.Literatura;

import com.aluraCursos.Literatura.Literatura.principal.Principal;
import com.aluraCursos.Literatura.Literatura.repository.AutorRepository;
import com.aluraCursos.Literatura.Literatura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	@Autowired
	private LibroRepository repository;
	@Autowired
	private AutorRepository repository2;

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository, repository2);//repository
		principal.muestraElMenu();
	}
}
