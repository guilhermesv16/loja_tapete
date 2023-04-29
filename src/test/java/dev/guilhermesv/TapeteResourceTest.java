package dev.guilhermesv;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import dev.guilhermesv.dto.produto.TapeteDTO;
import dev.guilhermesv.dto.produto.TapeteResponseDTO;
import dev.guilhermesv.service.produto.TapeteService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class TapeteResourceTest {

    @Inject
    TapeteService tapeteService;

    private static final String URL_BASE = "/api/v1";
    private static final String ENDPOINT = "/tapetes";
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
        TapeteDTO entity = new TapeteDTO(
            "Teste",
         //   "Teste",
            1,
            1.1,
            "INDISPONIVEL",
            "Teste"
        );

        given()
          .contentType(ContentType.JSON)
          .body(entity)
          .when().post(URL)
          .then()
             .statusCode(201)
             .body(
                "description", is("Teste"),
                "characters", is("Teste"),
                "stock", is(1),
                "price", is(1.1F),
                "status", is("INDISPONIVEL"),
                "material", is("Teste")
            );
    }

    @Test
    public void testUpdate() {
        // Adicionando uma pessoa no banco de dados
        TapeteDTO entidade = new TapeteDTO(
            "Teste",
       //     "Teste",
            1,
            1.1,
            "Teste",
            "Teste"
        );

        Long id = tapeteService.persist(entidade).id();

        // Criando outra pessoa para atuailzacao
        TapeteDTO tapeteUpdate = new TapeteDTO(
            "Teste",
       //     "Teste",
            1,
            1.1,
            "Teste",
            "Teste"
        );

        given()
          .contentType(ContentType.JSON)
          .body(tapeteUpdate)
          .when().put(URL+"/{id}",id)
          .then()
             .statusCode(204);

        // Verificando se os dados foram atualizados no banco de dados
        TapeteResponseDTO tapeteResponse = tapeteService.findById(id);
        
        assertThat(tapeteResponse.descricao(), is("Teste Updated"));
      //  assertThat(tapeteResponse.characters(), is("Alagoas"));
    }

    @Test
    public void testCountEndpoint() {
        
        Long count = tapeteService
.count();

        given()
          .when().get(URL + "/count")
          .then()
             .statusCode(200)
             .body(equalTo(count.toString()));
    }
}