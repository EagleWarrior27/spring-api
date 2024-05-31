package com.mst.app.services;

import com.mst.app.mappers.UsuarioMapper;
import com.mst.app.models.Usuario;
import com.mst.app.persistence.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return usuarioRepository.findAll().stream().map(usuario -> usuarioMapper.usuarioEntityToClienteModel(usuario)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(id)
                .map(usuario -> Optional.of(usuarioMapper.usuarioEntityToClienteModel(usuario)))
                .orElse(Optional.empty());
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        return usuarioMapper.usuarioEntityToClienteModel(
                usuarioRepository.save(usuarioMapper.usuarioModelToUsuarioEntity(usuario))
        );
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
