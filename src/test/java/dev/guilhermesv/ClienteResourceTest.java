package dev.guilhermesv;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import dev.guilhermesv.dto.cliente.ClienteDTO;
import dev.guilhermesv.dto.cliente.ClienteResponseDTO;
import dev.guilhermesv.service.cliente.ClienteService;
import io.quarkus.test.junit.QuarkusTest;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.http.ContentType;

@QuarkusTest
public class ClienteResourceTest {

    @Inject
    ClienteService clienteService;

    private static final String URL_BASE = "/api/v1";
    private static final String ENDPOINT = "/people";
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
        ClienteDTO entity = new ClienteDTO(
            "Teste",
            "Teste",
            "Teste",
            "Teste",
            "01/01/2001",
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
        ClienteDTO cliente = new ClienteDTO(
            "Teste",
            "Teste",
            "Teste",
            "Teste",
            "01/01/2001",
            1L
        );

        Long id = clienteService.persist(cliente).id();

        // Criando outra pessoa para atuailzacao
        ClienteDTO clienteUpdate = new ClienteDTO(
            "Teste Updated",
            "Teste Updated",
            "Teste Updated",
            "Teste Updated",
            "02/02/2002",
            2L
        );

        given()
          .contentType(ContentType.JSON)
          .body(clienteUpdate)
          .when().put(URL+"/{id}",id)
          .then()
             .statusCode(204);

        // Verificando se os dados foram atualizados no banco de dados
        ClienteResponseDTO clienteResponse = clienteService.findById(id);
        assertThat(clienteResponse.primeironome(), is("Teste Updated"));
        assertThat(clienteResponse.sobrenome(), is("Teste Updated"));
        assertThat(clienteResponse.cpf(), is("Teste Updated"));
        assertThat(clienteResponse.rg(), is("Teste Updated"));
        assertThat(clienteResponse.dataDeNascimento(), is(convertStringToDate("02/02/2002")));
        assertThat(clienteResponse.email(), is("admin@gmail.com"));

    }

    @Test
    public void testDelete() {
        // Adicionando uma pessoa no banco de dados
        ClienteDTO pf = new ClienteDTO(
            "Teste",
            "Teste",
            "Teste",
            "Teste",
            "01/01/2001",
            1L
        );
        Long id = clienteService.persist(pf).id();

        given()
          .when().delete(URL + "/{id}", id)
          .then()
             .statusCode(204);

        // verificando se a pessoa fisica foi excluida
        ClienteResponseDTO addressResponseDTO = null;
        try {
            addressResponseDTO =  clienteService.findById(id);
        } catch (Exception e) {

        }
        finally {
            assertNull(addressResponseDTO);   
        }
    }

    @Test
    public void testCountEndpoint() {
        
        Long count = clienteService.count();

        given()
          .when().get(URL + "/count")
          .then()
             .statusCode(200)
             .body(equalTo(count.toString()));
    }

    public LocalDateTime convertStringToDate(String date){
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDateTime dateTime = LocalDate.parse(date, parser).atStartOfDay();
        return dateTime;
    }
}