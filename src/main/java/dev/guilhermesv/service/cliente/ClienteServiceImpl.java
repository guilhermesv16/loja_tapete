package dev.guilhermesv.service.cliente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import dev.guilhermesv.dto.cliente.ClienteDTO;
import dev.guilhermesv.dto.cliente.ClienteResponseDTO;
import dev.guilhermesv.model.cliente.ClienteModel;
import dev.guilhermesv.repository.cliente.ClienteRepository;
import dev.guilhermesv.repository.usuario.UsuarioRepository;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    @Inject
    private ClienteRepository clienteRepository;

    @Inject
    private UsuarioRepository usuarioRepository;
        
    @Inject
    private Validator validator;

    @Override
    public List<ClienteResponseDTO> listAll() {
        List<ClienteModel> list = clienteRepository.listAll();
        
        return list.stream().map(
            s -> new ClienteResponseDTO(s)
        ).collect(Collectors.toList());
    }

    @Override
    public ClienteResponseDTO findById(Long id){
        ClienteModel entity = clienteRepository.findById(id);     
        if(entity == null)
            throw new NotFoundException("State not found.");
        return new ClienteResponseDTO(entity);
    }
    
    @Override
    public List<ClienteResponseDTO> findByName(String name){
        List<ClienteModel> list = clienteRepository.findByName(name);
        
        return list.stream().map(
            s -> new ClienteResponseDTO(s)
        ).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ClienteResponseDTO persist(ClienteDTO receivedModel) throws ConstraintViolationException {
        validate(receivedModel);
        
        ClienteModel entity = new ClienteModel();
        entity.setPrimeiroNome(receivedModel.primeiroNome());
        entity.setSobrenome(receivedModel.sobrenome());
        entity.setCpf(receivedModel.cpf());
        entity.setRg(receivedModel.rg());
        entity.setDataDeNascimento(convertStringToDate(receivedModel.dataDeNascimento()));
        entity.setUsuarioModel(usuarioRepository.findById(receivedModel.idUsuario()));
        // entity.setAddress(findAllById(receivedModel.idAddresses()));
        
        clienteRepository.persist(entity);
        
        return new ClienteResponseDTO(entity);
    }

    @Override
    @Transactional
    public ClienteResponseDTO update(Long id, ClienteDTO receivedModel) throws ConstraintViolationException {
        validate(receivedModel);

        ClienteModel entity = clienteRepository.findById(id);
        entity.setPrimeiroNome(receivedModel.primeiroNome());
        entity.setSobrenome(receivedModel.sobrenome());
        entity.setCpf(receivedModel.cpf());
        entity.setRg(receivedModel.rg());
        entity.setDataDeNascimento(convertStringToDate(receivedModel.dataDeNascimento()));
        entity.setUsuarioModel(usuarioRepository.findById(receivedModel.idUsuario()));

        return new ClienteResponseDTO(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public long count() {
        return clienteRepository.count();
    }
    
    private void validate(ClienteDTO entity) throws ConstraintViolationException {
        Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(entity);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    public LocalDateTime convertStringToDate(String date){
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDateTime dateTime = LocalDate.parse(date, parser).atStartOfDay();
        return dateTime;
    }
    
}
