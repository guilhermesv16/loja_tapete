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

import dev.guilhermesv.dto.endereco.CidadeDTO;
import dev.guilhermesv.dto.endereco.CidadeResponseDTO;
import dev.guilhermesv.model.endereco.CidadeModel;
import dev.guilhermesv.repository.endereco.CidadeRepository;
import dev.guilhermesv.repository.endereco.EstadoRepository;


@ApplicationScoped
public class CidadeServiceImpl implements CidadeService {

    @Inject
    private CidadeRepository cidadeRepository;
    
    @Inject
    private EstadoRepository estadoRepository;
    
    @Inject
    private Validator validator;

    @Override
    public List<CidadeResponseDTO> listAll() {
        List<CidadeModel> list = cidadeRepository.listAll();
        
        return list.stream().map(
            s -> new CidadeResponseDTO(s)
        ).collect(Collectors.toList());
    }

    @Override
    public CidadeResponseDTO findById(Long id) {
        CidadeModel entity = cidadeRepository.findById(id);     
        if(entity == null)
            throw new NotFoundException("Estado not found.");
        return new CidadeResponseDTO(entity);
    }
    
    @Override
    public List<CidadeResponseDTO> findByName(String name) {
        List<CidadeModel> list = cidadeRepository.findByName(name);
        
        return list.stream().map(
            s -> new CidadeResponseDTO(s)
        ).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CidadeResponseDTO persist(CidadeDTO receivedModel) throws ConstraintViolationException{
        validate(receivedModel);
        
        CidadeModel entity = new CidadeModel();
        entity.setNome(receivedModel.nome());
        entity.setEstado(estadoRepository.findById(receivedModel.idEstado()));
        cidadeRepository.persist(entity);
        
        return new CidadeResponseDTO(entity);
    }

    @Override
    @Transactional
    public CidadeResponseDTO update(Long id, CidadeDTO receivedModel) throws ConstraintViolationException{
        validate(receivedModel);

        CidadeModel entity = cidadeRepository.findById(id);
        entity.setNome(receivedModel.nome());      
        entity.setEstado(estadoRepository.findById(receivedModel.idEstado()));
        
        return new CidadeResponseDTO(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        cidadeRepository.deleteById(id);
    }

    @Override
    public long count() {
        return cidadeRepository.count();
    }
    
    private void validate(CidadeDTO entity) throws ConstraintViolationException {
        Set<ConstraintViolation<CidadeDTO>> violations = validator.validate(entity);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

}
