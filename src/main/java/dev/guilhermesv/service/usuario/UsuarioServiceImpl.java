package dev.guilhermesv.service.usuario;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.NotFoundException;

import dev.guilhermesv.dto.usuario.UsuarioDTO;
import dev.guilhermesv.dto.usuario.UsuarioResponseDTO;
import dev.guilhermesv.model.usuario.TipoUsuarioModel;
import dev.guilhermesv.model.usuario.UsuarioModel;
import dev.guilhermesv.repository.usuario.UsuarioRepository;


@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService{

    @Inject
    private UsuarioRepository usuarioRepository;

    @Inject
    private Validator validator;

    @Override
    public List<UsuarioResponseDTO> listAll() {
        List<UsuarioModel> list = usuarioRepository.listAll();
        
        return list.stream().map(
            s -> new UsuarioResponseDTO(s)
        ).collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO findById(Long id){
        UsuarioModel entity = usuarioRepository.findById(id);     
        if(entity == null)
            throw new NotFoundException("State not found.");
        return new UsuarioResponseDTO(entity);
    }
    
    @Override
    public List<UsuarioResponseDTO> findByName(String name){
        List<UsuarioModel> list = usuarioRepository.findByName(name);
        
        return list.stream().map(
            s -> new UsuarioResponseDTO(s)
        ).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UsuarioResponseDTO persist(UsuarioDTO receivedModel) throws ConstraintViolationException {
        validate(receivedModel);
        
        UsuarioModel entity = new UsuarioModel();
        entity.setNomeUsuario(receivedModel.nomeUsuario());
        entity.setSenha(receivedModel.senha());
        if(receivedModel.tipoUsuario().toUpperCase().equals("ADMINISTRADO")){
            entity.setTipoUsuarioModel(TipoUsuarioModel.ADMINISTRADOR);
        }else{
            entity.setTipoUsuarioModel(TipoUsuarioModel.CLIENTE);
        }
        usuarioRepository.persist(entity);
        
        return new UsuarioResponseDTO(entity);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO update(Long id, UsuarioDTO receivedModel) throws ConstraintViolationException {
        validate(receivedModel);

        UsuarioModel entity = usuarioRepository.findById(id);
        entity.setNomeUsuario(receivedModel.nomeUsuario());
        if(receivedModel.tipoUsuario().toUpperCase().equals("ADMINISTRADOR")){
            entity.setTipoUsuarioModel(TipoUsuarioModel.ADMINISTRADOR);
        }else{
            entity.setTipoUsuarioModel(TipoUsuarioModel.CLIENTE);
        }
        return new UsuarioResponseDTO(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public long count() {
        return usuarioRepository.count();
    }
    
    private void validate(UsuarioDTO entity) throws ConstraintViolationException {
        Set<ConstraintViolation<UsuarioDTO>> violations = validator.validate(entity);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }
    
}
