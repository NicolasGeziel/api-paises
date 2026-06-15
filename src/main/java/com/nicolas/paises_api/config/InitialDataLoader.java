package com.nicolas.paises_api.config;

import com.nicolas.paises_api.pais.models.Pais;
import com.nicolas.paises_api.pais.repositories.PaisRepository;
import com.nicolas.paises_api.usuario.models.Usuario;
import com.nicolas.paises_api.usuario.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class InitialDataLoader {

    @Bean
    CommandLineRunner start(UsuarioRepository usuarioRepository, PaisRepository paisRepository) {
        return args -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if (usuarioRepository.count() == 0) {
                Usuario convidado = new Usuario();
                convidado.setLogin("convidado");
                convidado.setNome("Usuário convidado");
                convidado.setSenha(encoder.encode("manager"));
                convidado.setAdministrador(false);
                usuarioRepository.save(convidado);
                Usuario admin = new Usuario();
                admin.setLogin("admin");
                admin.setNome("Gestor");
                admin.setSenha(encoder.encode("suporte"));
                admin.setAdministrador(true);
                usuarioRepository.save(admin);
            }
            if (paisRepository.count() == 0) {
                Pais brasil = new Pais();
                brasil.setNome("Brasil");
                brasil.setSigla("BR");
                brasil.setGentilico("Brasileiro");
                paisRepository.save(brasil);
                Pais argentina = new Pais();
                argentina.setNome("Argentina");
                argentina.setSigla("AR");
                argentina.setGentilico("Argentino");
                paisRepository.save(argentina);
                Pais alemanha = new Pais();
                alemanha.setNome("Alemanha");
                alemanha.setSigla("AL");
                alemanha.setGentilico("Alemão");
                paisRepository.save(alemanha);
            }
        };
    }
}