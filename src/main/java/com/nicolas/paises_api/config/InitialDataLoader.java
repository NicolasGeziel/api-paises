package com.nicolas.paises_api.config;

import com.nicolas.paises_api.usuario.models.Usuario;
import com.nicolas.paises_api.usuario.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class InitialDataLoader {

    @Bean
    CommandLineRunner start(UsuarioRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                Usuario admin = new Usuario();
                admin.setLogin("admin");
                admin.setNome("Administrador do Sistema");
                admin.setSenha(encoder.encode("suporte"));
                admin.setAdministrador(true);

                repository.save(admin);
            }
        };
    }
}