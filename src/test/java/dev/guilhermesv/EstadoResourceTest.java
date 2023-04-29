package dev.guilhermesv;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import dev.guilhermesv.dto.endereco.EstadoDTO;
import dev.guilhermesv.dto.endereco.EstadoResponseDTO;
import dev.guilhermesv.service.endereco.EstadoService;
import io.quarkus.test.junit.QuarkusTest;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.http.ContentType;

@QuarkusTest
public class EstadoResourceTest {

    @Inject
    EstadoService estadoService;

    private static final String URL_BASE = "/api/v1";
    private static final String ENDPOINT = "/estados";
    private static final String URL = URL_BASE + ENDPOINT;

    @Test
    public void testGetAll() {
        given()
          .when().get(URL)
          .then()
             .statusCode(200);
    }

    @Test
    public void testGetByIdValid() {
        given()
          .when().get(URL+"/{id}", "1")
          .then()
             .statusCode(200)
             .body(
                "id", notNullValue(),
                "name", is("Acre"),
                "acronym", is("AC")
             );
    }

    @Test
    public void testGetByIdInvalid(){
        given()
            .when().get(URL+"/{id}", "300")
            .then()
            .statusCode(404);
    }

    @Test
    public void testGetByNameValid() {
        given()
          .when().get(URL+"/search/{name}", "alagoas")
          .then()
             .statusCode(200)
             .body(
                "$.size()", is(1),
                "[0].name", is("Alagoas"),
                "[0].acronym", is("AL")
             );
    }

    @Test
    public void testGetByNameInvalid(){
        given()
            .when().get(URL+"search/{name}", "")
            .then()
            .statusCode(404);
    }

    @Test
    public void testInsertValid() {
        EstadoDTO entity = new EstadoDTO(
            "Teste",
            "TE"
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
                "acronym", is("TE")
            );
    }

    @Test
    public void testInsertAcronymInvalid() {
        EstadoDTO entity = new EstadoDTO(
            "Teste",
            null
        );

        given()
          .contentType(ContentType.JSON)
          .body(entity)
          .when().post(URL)
          .then()
             .statusCode(404)
             .body(
                "message", is ("O campo acronym deve ser informado."),
                "success", is(false)
            );
    }

    @Test
    public void testInsertNameInvalid() {
        EstadoDTO entity = new EstadoDTO(
            null,
            "TE"
        );

        given()
          .contentType(ContentType.JSON)
          .body(entity)
          .when().post(URL)
          .then()
             .statusCode(404)
             .body(
                "message", is ("O campo name deve ser informado."),
                "success", is(false)
            );
    }

    @Test
    public void testUpdate() {
        // Adicionando uma pessoa no banco de dados
        EstadoDTO estado = new EstadoDTO(
            "Teste",
            "TE"
        );
        Long id = estadoService.persist(estado).id();

        // Criando outra pessoa para atuailzacao
        EstadoDTO estadoUpdate = new EstadoDTO(
            "Teste Updated",
            "TP"
        );

        given()
          .contentType(ContentType.JSON)
          .body(estadoUpdate)
          .when().put(URL+"/{id}",id)
          .then()
             .statusCode(204);

        // Verificando se os dados foram atualizados no banco de dados
        EstadoResponseDTO estadoResponse = estadoService.findById(id);
        assertThat(estadoResponse.nome(), is("Teste Updated"));
        assertThat(estadoResponse.sigla(), is("TP"));
    }

    @Test
    public void testDelete() {
        // Adicionando uma pessoa no banco de dados
        EstadoDTO pf = new EstadoDTO(
            "Teste",
            "TE"
        );
        Long id = estadoService.persist(pf).id();

        given()
          .when().delete(URL + "/{id}", id)
          .then()
             .statusCode(204);

        // verificando se a pessoa fisica foi excluida
        EstadoResponseDTO estadoResponseDTO = null;
        try {
            estadoResponseDTO =  estadoService.findById(id);
        } catch (Exception e) {

        }
        finally {
            assertNull(estadoResponseDTO);   
        }   
     
    }

    @Test
    public void testCountEndpoint() {
        
        Long count = estadoService.count();

        given()
          .when().get(URL + "/count")
          .then()
             .statusCode(200)
             .body(equalTo(count.toString()));
    }
}