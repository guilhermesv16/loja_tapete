package dev.guilhermesv;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import dev.guilhermesv.dto.usuario.UsuarioDTO;
import dev.guilhermesv.dto.usuario.UsuarioResponseDTO;
import dev.guilhermesv.service.usuario.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.http.ContentType;

@QuarkusTest
public class UsuarioResourceTest {

    @Inject
    UsuarioService usuarioService;

    private static final String URL_BASE = "/api/v1";
    private static final String ENDPOINT = "/usuarios";
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
          .when().get(URL+"/search/{name}", "sergipe")
          .then()
             .statusCode(200);
    }

    @Test
    public void testInsert() {
        UsuarioDTO entity = new UsuarioDTO(
            "Teste",
            "123456",
            "CLIENTE"
        );

        given()
          .contentType(ContentType.JSON)
          .body(entity)
          .when().post(URL)
          .then()
             .statusCode(201)
             .body(
                "id", notNullValue(),
                "usuarioname", is("Teste"),
                "role", is("CLIENTE")
            );
    }

    @Test
    public void testUpdate() {
        // Adicionando uma pessoa no banco de dados
        UsuarioDTO state = new UsuarioDTO(
            "Teste",
            "TE",
            "CLIENTE"
        );
        Long id = usuarioService.persist(state).id();

        // Criando outra pessoa para atuailzacao
        UsuarioDTO stateUpdate = new UsuarioDTO(
            "Teste Updated",
            "TP",
            "ADMINISTRADOR"
        );

        given()
          .contentType(ContentType.JSON)
          .body(stateUpdate)
          .when().put(URL+"/{id}",id)
          .then()
             .statusCode(204);

        // Verificando se os dados foram atualizados no banco de dados
        UsuarioResponseDTO usuarioResponse = usuarioService.findById(id);
        assertThat(usuarioResponse.nomeUsuario(), is("Teste Updated"));
        assertThat(usuarioResponse.tipoUsuario(), is("ADMINISTRADOR"));
    }

    @Test
    public void testDelete() {
        // Adicionando uma pessoa no banco de dados
        UsuarioDTO pf = new UsuarioDTO(
            "Teste",
            "TE",
            "CLIENTE"
        );
        Long id = usuarioService.persist(pf).id();

        given()
          .when().delete(URL + "/{id}", id)
          .then()
             .statusCode(204);

        // verificando se a pessoa fisica foi excluida
        UsuarioResponseDTO UsuarioResponseDTO = null;
        try {
            UsuarioResponseDTO =  usuarioService.findById(id);
        } catch (Exception e) {

        }
        finally {
            assertNull(UsuarioResponseDTO);   
        }   
     
    }

    @Test
    public void testCountEndpoint() {
        
        Long count = usuarioService.count();

        given()
          .when().get(URL + "/count")
          .then()
             .statusCode(200)
             .body(equalTo(count.toString()));
    }
}