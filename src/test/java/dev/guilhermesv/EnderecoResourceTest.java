package dev.guilhermesv;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import dev.guilhermesv.dto.endereco.EnderecoDTO;
import dev.guilhermesv.dto.endereco.EnderecoResponseDTO;
import dev.guilhermesv.service.endereco.EnderecoService;
import io.quarkus.test.junit.QuarkusTest;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.http.ContentType;

@QuarkusTest
public class EnderecoResourceTest {

    @Inject
    EnderecoService enderecoService;

    private static final String URL_BASE = "/api/v1";
    private static final String ENDPOINT = "/enderecos";
    private static final String URL = URL_BASE + ENDPOINT;

    @Test
    public void testGetAll() {
        given()
          .when().get(URL)
          .then()
             .statusCode(200);
    }

    @Test
    public void testGetById() {
        
        given()
          .when().get(URL+"/{id}", "1")
          .then()
             .statusCode(200);
    }

    @Test
    public void testGetByName() {
        given()
          .when().get(URL+"/search/{name}", "palmas")
          .then()
             .statusCode(200);
    }

    @Test
    public void testInsert() {
        EnderecoDTO entity = new EnderecoDTO(
            "Teste",
            "Teste",
            1L,
            1L
        );

        given()
          .contentType(ContentType.JSON)
          .body(entity)
          .when().post(URL)
          .then()
             .statusCode(201);
    }

    @Test
    public void testUpdate() {
        // Adicionando uma pessoa no banco de dados
        EnderecoDTO state = new EnderecoDTO(
            "Teste",
            "Teste",
            1L,
            1L
        );

        Long id = enderecoService.persist(state).id();

        // Criando outra pessoa para atuailzacao
        EnderecoDTO stateUpdate = new EnderecoDTO(
            "Teste Updated",
            "Teste Updated",
            2L,
            2L
        );

        given()
          .contentType(ContentType.JSON)
          .body(stateUpdate)
          .when().put(URL+"/{id}",id)
          .then()
             .statusCode(204);

        // Verificando se os dados foram atualizados no banco de dados
        EnderecoResponseDTO cityResponse = enderecoService.findById(id);
        assertThat(cityResponse.endereco(), is("Teste Updated"));
        assertThat(cityResponse.complemento(), is("Teste Updated"));
        assertThat(cityResponse.cidade(), is("Cruzeiro do Sul"));
        assertThat(cityResponse.cliente(), is("Admin"));
    }

    @Test
    public void testDelete() {
        // Adicionando uma pessoa no banco de dados
        EnderecoDTO pf = new EnderecoDTO(
            "Teste",
            "Teste",
            1L,
            1L
        );
        Long id = enderecoService.persist(pf).id();

        given()
          .when().delete(URL + "/{id}", id)
          .then()
             .statusCode(204);

        // verificando se a pessoa fisica foi excluida
        EnderecoResponseDTO enderecoResponseDTO = null;
        try {
            enderecoResponseDTO =  enderecoService.findById(id);
        } catch (Exception e) {

        }
        finally {
            assertNull(enderecoResponseDTO);   
        }
    }

    @Test
    public void testCountEndpoint() {
        
        Long count = enderecoService.count();

        given()
          .when().get(URL + "/count")
          .then()
             .statusCode(200)
             .body(equalTo(count.toString()));
    }
}