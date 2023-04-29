package dev.guilhermesv;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import dev.guilhermesv.dto.endereco.CidadeDTO;
import dev.guilhermesv.dto.endereco.CidadeResponseDTO;
import dev.guilhermesv.service.endereco.CidadeService;
import io.quarkus.test.junit.QuarkusTest;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.http.ContentType;

@QuarkusTest
public class CidadeResourceTest {

    @Inject
    CidadeService cidadeService;

    private static final String URL_BASE = "/api/v1";
    private static final String ENDPOINT = "/cidades";
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
        CidadeDTO entity = new CidadeDTO(
            "Teste",
            1
        );

        given()
          .contentType(ContentType.JSON)
          .body(entity)
          .when().post(URL)
          .then()
             .statusCode(201)
             .body(
                "id", notNullValue(),
                "name", is("Teste"),
                "state", is("Acre")
            );
    }

    @Test
    public void testUpdate() {
        // Adicionando uma pessoa no banco de dados
        CidadeDTO state = new CidadeDTO(
            "Teste",
            1
        );
        Long id = cidadeService.persist(state).id();

        // Criando outra pessoa para atuailzacao
        CidadeDTO stateUpdate = new CidadeDTO(
            "Teste Updated",
            2
        );

        given()
          .contentType(ContentType.JSON)
          .body(stateUpdate)
          .when().put(URL+"/{id}",id)
          .then()
             .statusCode(204);

        // Verificando se os dados foram atualizados no banco de dados
        CidadeResponseDTO cidadeResponse = cidadeService.findById(id);
        assertThat(cidadeResponse.nome(), is("Teste Updated"));
        assertThat(cidadeResponse.estado(), is("Alagoas"));
    }

    @Test
    public void testDelete() {
        // Adicionando uma pessoa no banco de dados
        CidadeDTO pf = new CidadeDTO(
            "Teste",
            1
        );
        Long id = cidadeService.persist(pf).id();

        given()
          .when().delete(URL + "/{id}", id)
          .then()
             .statusCode(204);

        // verificando se a pessoa fisica foi excluida
        CidadeResponseDTO CidadeResponseDTO = null;
        try {
            CidadeResponseDTO =  cidadeService.findById(id);
        } catch (Exception e) {

        }
        finally {
            assertNull(CidadeResponseDTO);   
        }
    }

    @Test
    public void testCountEndpoint() {
        
        Long count = cidadeService.count();

        given()
          .when().get(URL + "/count")
          .then()
             .statusCode(200)
             .body(equalTo(count.toString()));
    }
}