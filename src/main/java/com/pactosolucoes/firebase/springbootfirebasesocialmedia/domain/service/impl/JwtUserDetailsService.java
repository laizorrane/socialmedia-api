package com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.service.impl;

import com.pactosolucoes.firebase.springbootfirebasesocialmedia.api.dto.UsuarioResponseDto;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.entity.Usuario;
import com.pactosolucoes.firebase.springbootfirebasesocialmedia.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author laizorrane
 * Data Criacao: 11/08/2021 - 14:41
 */

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = userService.buscarPorEmail(email);

        if (usuario.getEmail().equals(email)) {
            return new User(email, usuario.getSenha(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Não foi encontrado usuário com o email: " + email);
        }
    }

    public void setUserService(UsuarioService userService) {
        this.userService = userService;
    }


}