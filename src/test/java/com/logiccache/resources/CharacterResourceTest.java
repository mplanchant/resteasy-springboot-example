package com.logiccache.resources;

import com.logiccache.domain.Character;
import com.logiccache.repositories.CharacterRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CharacterResourceTest {

    @Autowired
    private CharacterRepository repository;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() throws Exception {
        Character mickey = new Character("Mickey Mouse");
        Character mrBean = new Character("Mr. Bean");
        Character oliver = new Character("Oliver Twist");

        repository.deleteAll();
        repository.save(Arrays.asList(mickey, mrBean, oliver));

        RestAssured.port = port;
    }


    @Test
    public void canGetASingleCharacter() throws Exception {
        given().log().all()
                .get("/test-app/characters/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("name", equalTo("Mickey Mouse"));
    }

    @Test
    public void canFetchAll() {
        Response response = given()
                .log().all()
                .get("/test-app/characters")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("list.size()", is(3))
                .extract().response();

        List<Character> characters = Arrays.asList(response.getBody().prettyPeek().as(Character[].class));
        assertThat(characters).extracting("name")
                .contains("Mickey Mouse", "Mr. Bean", "Oliver Twist")
                .doesNotContain("Paddington Bear", "Superman");
    }

}