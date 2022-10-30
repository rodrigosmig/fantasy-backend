package br.com.backend.fantasygame.infrastracture.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.backend.fantasygame.domain.entity.User;
import br.com.backend.fantasygame.domain.repository.RepositorioUsuario;
import br.com.backend.fantasygame.domain.service.ServicoToken;
import br.com.backend.fantasygame.infrastracture.schema.UserSchema;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private ServicoToken servicoToken;

    private RepositorioUsuario repositorioUsuario;

    public JwtAuthenticationFilter(ServicoToken servicoToken, RepositorioUsuario repositorioUsuario) {
        this.servicoToken = servicoToken;
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = getToken(request);

        if (servicoToken.isValid(token)) {
            authenticate(token);
        }

        filterChain.doFilter(request, response);
    }

    private void authenticate(String token) {
        var email = servicoToken.getSubject(token);
        var user = repositorioUsuario.findByEmail(email).get();
        
        var userSchema = this.getUserSchema(user);

        var authentication = new UsernamePasswordAuthenticationToken(userSchema, null, userSchema.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getToken(HttpServletRequest request) {
        var token = request.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
            return null;
        }

        return token.substring(7, token.length());
    }

    private UserSchema getUserSchema(User user) {
        return new UserSchema(
                user.getId(), 
                user.getNome(), 
                user.getEmail(), 
                user.getSenha()
            );
    }
}
