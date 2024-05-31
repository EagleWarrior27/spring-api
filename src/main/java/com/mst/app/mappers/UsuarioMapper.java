package com.mst.app.mappers;

import com.mst.app.persistence.entities.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario usuarioModelToUsuarioEntity(com.mst.app.models.Usuario usuarioModel);

    com.mst.app.models.Usuario usuarioEntityToClienteModel(Usuario clienteEntity);

}
