package dev.guilhermesv.service.endereco;

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

import dev.guilhermesv.dto.endereco.EstadoDTO;
import dev.guilhermesv.dto.endereco.EstadoResponseDTO;
import dev.guilhermesv.model.endereco.EstadoModel;
import dev.guilhermesv.repository.endereco.EstadoRepository;


@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {

    @Inject
    private EstadoRepository estadoRepository;

    @Inject
    private Validator validator;

    @Override
    public List<EstadoResponseDTO> listAll() {
        List<EstadoModel> list = estadoRepository.listAll();
        
        return list.stream().map(
            s -> new EstadoResponseDTO(s)
        ).collect(Collectors.toList());
    }

    @Override
    public EstadoResponseDTO findById(Long id){
        EstadoModel entity = estadoRepository.findById(id);     
        if(entity == null)
            throw new NotFoundException("Estado not found.");
        return new EstadoResponseDTO(entity);
    }
    
    @Override
    public List<EstadoResponseDTO> findByName(String name){
        List<EstadoModel> list = estadoRepository.findByName(name);
        
        return list.stream().map(
            s -> new EstadoResponseDTO(s)
        ).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EstadoResponseDTO persist(EstadoDTO receivedModel) throws ConstraintViolationException {
        validate(receivedModel);
        
        EstadoModel entity = new EstadoModel();
        entity.setNome(receivedModel.nome());
        entity.setSigla(receivedModel.sigla());
        estadoRepository.persist(entity);
        
        return new EstadoResponseDTO(entity);
    }

    @Override
    @Transactional
    public EstadoResponseDTO update(Long id, EstadoDTO receivedModel) throws ConstraintViolationException {
        validate(receivedModel);

        EstadoModel entity = estadoRepository.findById(id);
        entity.setNome(receivedModel.nome());
        entity.setSigla(receivedModel.sigla());
        
        return new EstadoResponseDTO(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        estadoRepository.deleteById(id);
    }

    @Override
    public long count() {
        return estadoRepository.count();
    }
    
    private void validate(EstadoDTO entity) throws ConstraintViolationException {
        Set<ConstraintViolation<EstadoDTO>> violations = validator.validate(entity);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }
    
}
