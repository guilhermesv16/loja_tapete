package dev.guilhermesv.service.produto;

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

import dev.guilhermesv.dto.produto.TapeteDTO;
import dev.guilhermesv.dto.produto.TapeteResponseDTO;
import dev.guilhermesv.model.produto.ProdutoStatus;
import dev.guilhermesv.model.produto.TapeteModel;
import dev.guilhermesv.repository.produto.TapeteRepository;

@ApplicationScoped
public class TapeteServiceImpl implements TapeteService {

    @Inject
    private TapeteRepository tapeteRepository;
    
    @Inject
    private Validator validator;

    @Override
    public List<TapeteResponseDTO> listAll() {
        List<TapeteModel> list = tapeteRepository.listAll();
        
        return list.stream().map(
            s -> new TapeteResponseDTO(s)
        ).collect(Collectors.toList());
    }

    @Override
    public TapeteResponseDTO findById(Long id) {
        TapeteModel entity = tapeteRepository.findById(id);     
        if(entity == null)
            throw new NotFoundException("State not found.");
        return new TapeteResponseDTO(entity);
    }
    
    @Override
    public List<TapeteResponseDTO> findByDescription(String name) {
        List<TapeteModel> list = tapeteRepository.findByName(name);
        
        return list.stream().map(
            s -> new TapeteResponseDTO(s)
        ).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TapeteResponseDTO persist(TapeteDTO receivedModel) throws ConstraintViolationException{
        validate(receivedModel);
        
        TapeteModel entity = new TapeteModel();
        entity.setDescricao(receivedModel.descricao());
        //entity.setCharacters(receivedModel.characters());
        entity.setEstoque(receivedModel.estoque());
        entity.setPreco(receivedModel.preco());
        entity.setStatus(validateStatus(receivedModel.status()));
        entity.setMaterial(receivedModel.material());
        
        tapeteRepository.persist(entity);
        
        return new TapeteResponseDTO(entity);
    }

    @Override
    @Transactional
    public TapeteResponseDTO update(Long id, TapeteDTO receivedModel) throws ConstraintViolationException{
        validate(receivedModel);

        TapeteModel entity = tapeteRepository.findById(id);
        entity.setDescricao(receivedModel.descricao());
        //entity.setCharacters(receivedModel.characters());
        entity.setEstoque(receivedModel.estoque());
        entity.setPreco(receivedModel.preco());
        entity.setMaterial(receivedModel.material());

        return new TapeteResponseDTO(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        tapeteRepository.deleteById(id);
    }

    @Override
    public long count() {
        return tapeteRepository.count();
    }
    
    private void validate(TapeteDTO entity) throws ConstraintViolationException {
        Set<ConstraintViolation<TapeteDTO>> violations = validator.validate(entity);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    private ProdutoStatus validateStatus(String status){
        if(status.toUpperCase().equals("DISPONIVEL")){
            return ProdutoStatus.DISPONIVEL;
        }
        else if(status.toUpperCase().equals("INDISPONIVEL")){
            return ProdutoStatus.INDISPONIVEL;
        }
        else{
            return null;
        }
    }

}
