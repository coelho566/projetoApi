package br.com.gft.projetoApi.config;

import br.com.gft.projetoApi.model.Usuario;
import br.com.gft.projetoApi.model.UsuarioPrincipal;
import br.com.gft.projetoApi.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public MyUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if(usuario == null)
            throw  new UsernameNotFoundException("User 404");
        return new UsuarioPrincipal(usuario);
    }
}
