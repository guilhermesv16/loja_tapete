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

import dev.guilhermesv.dto.endereco.EnderecoDTO;
import dev.guilhermesv.dto.endereco.EnderecoResponseDTO;
import dev.guilhermesv.model.endereco.EnderecoModel;
import dev.guilhermesv.repository.cliente.ClienteRepository;
import dev.guilhermesv.repository.endereco.CidadeRepository;
import dev.guilhermesv.repository.endereco.EnderecoRepository;



@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService {

    @Inject
    private CidadeRepository cidadeRepository;
    
    @Inject
    private EnderecoRepository enderecoRepository;
    
    @Inject
    private ClienteRepository clienteRepository;

    @Inject
    private Validator validator;

    @Override
    public List<EnderecoResponseDTO> listAll() {
        List<EnderecoModel> list = enderecoRepository.listAll();
        
        return list.stream().map(
            s -> new EnderecoResponseDTO(s)
        ).collect(Collectors.toList());
    }

    @Override
    public EnderecoResponseDTO findById(Long id) {
        EnderecoModel entity = enderecoRepository.findById(id);     
        if(entity == null)
            throw new NotFoundException("State not found.");
        return new EnderecoResponseDTO(entity);
    }
    
    @Override
    public List<EnderecoResponseDTO> findByStreet(String name) {
        List<EnderecoModel> list = enderecoRepository.findByName(name);
        
        return list.stream().map(
            s -> new EnderecoResponseDTO(s)
        ).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EnderecoResponseDTO persist(EnderecoDTO receivedModel) throws ConstraintViolationException {
        validate(receivedModel);
        
        EnderecoModel entity = new EnderecoModel();
        entity.setEndereco(receivedModel.endereco());
        entity.setComplemento(receivedModel.complemento());
        entity.setCidadeModel(cidadeRepository.findById(receivedModel.idCidade()));
        entity.setClienteModel(clienteRepository.findById(receivedModel.idCliente()));
        enderecoRepository.persist(entity);
        
        return new EnderecoResponseDTO(entity);
    }

    @Override
    @Transactional
    public EnderecoResponseDTO update(Long id, EnderecoDTO receivedModel) throws ConstraintViolationException{
        validate(receivedModel);

        EnderecoModel entity = enderecoRepository.findById(id);
        entity.setEndereco(receivedModel.endereco());
        entity.setComplemento(receivedModel.complemento());
        entity.setCidadeModel(cidadeRepository.findById(receivedModel.idCidade()));
        entity.setClienteModel(clienteRepository.findById(receivedModel.idCliente()));

        return new EnderecoResponseDTO(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        enderecoRepository.deleteById(id);
    }

    @Override
    public long count() {
        return enderecoRepository.count();
    }
    
    private void validate(EnderecoDTO entity) throws ConstraintViolationException {
        Set<ConstraintViolation<EnderecoDTO>> violations = validator.validate(entity);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

}
