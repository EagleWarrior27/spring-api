package com.mst.app.services;

import com.mst.app.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UsuarioService {
    public Iterable<Usuario> findAll();

    public Page<Usuario> findAll(Pageable pageable);

    public Optional<Usuario> findById(Integer id);

    public Usuario save(Usuario usuario);

    public void deleteById(Integer id);
}
