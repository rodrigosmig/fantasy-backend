package br.com.backend.fantasygame.infrastracture.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.backend.fantasygame.application.service.ServicoFormacaoImpl;
import br.com.backend.fantasygame.application.service.ServicoJogadorImpl;
import br.com.backend.fantasygame.application.service.ServicoPaisImpl;
import br.com.backend.fantasygame.application.service.ServicoPosicaoImpl;
import br.com.backend.fantasygame.application.service.ServicoTimeImpl;
import br.com.backend.fantasygame.application.service.ServicoTokenImpl;
import br.com.backend.fantasygame.application.service.ServicoUsuarioImpl;
import br.com.backend.fantasygame.domain.repository.RepositorioFormacao;
import br.com.backend.fantasygame.domain.repository.RepositorioJogador;
import br.com.backend.fantasygame.domain.repository.RepositorioPais;
import br.com.backend.fantasygame.domain.repository.RepositorioPosicao;
import br.com.backend.fantasygame.domain.repository.RepositorioTime;
import br.com.backend.fantasygame.domain.repository.RepositorioUsuario;
import br.com.backend.fantasygame.domain.service.ServicoFormacao;
import br.com.backend.fantasygame.domain.service.ServicoJogador;
import br.com.backend.fantasygame.domain.service.ServicoPais;
import br.com.backend.fantasygame.domain.service.ServicoPosicao;
import br.com.backend.fantasygame.domain.service.ServicoTime;
import br.com.backend.fantasygame.domain.service.ServicoToken;
import br.com.backend.fantasygame.domain.service.ServicoUsuario;

@Configuration
public class BeanConfiguration {
    
    @Bean
    ServicoPosicao posicaoService(RepositorioPosicao repositorioPosicao) {
        return new ServicoPosicaoImpl(repositorioPosicao);
    }

    @Bean
    ServicoFormacao formacaoService(RepositorioFormacao formationRepository) {
        return new ServicoFormacaoImpl(formationRepository);
    }

    @Bean
    ServicoPais paisService(RepositorioPais repositorioPais) {
        return new ServicoPaisImpl(repositorioPais);
    }

    @Bean
    ServicoToken tokenService() {
        return new ServicoTokenImpl();
    }

    @Bean
    ServicoUsuario userService(RepositorioUsuario repositorioUsuario, PasswordEncoder passwordEncoder, ServicoTime servicoTime) {
        return new ServicoUsuarioImpl(repositorioUsuario, passwordEncoder, servicoTime);
    }

    @Bean
    ServicoTime servicoTime(RepositorioTime repositorioTime,
                            RepositorioFormacao repositorioFormacao,
                            RepositorioJogador repositorioJogador) {
        return new ServicoTimeImpl(repositorioTime, repositorioFormacao, repositorioJogador);
    }

    @Bean
    ServicoJogador jogadorService(RepositorioJogador repositorioJogador) {
        return new ServicoJogadorImpl(repositorioJogador);
    }
}
