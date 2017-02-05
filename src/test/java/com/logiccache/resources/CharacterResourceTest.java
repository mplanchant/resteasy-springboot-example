package com.logiccache.resources;

import com.logiccache.domain.Character;
import com.logiccache.repositories.CharacterRepository;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CharacterResourceTest {

    @Autowired
    private CharacterRepository repository;

    private Character mickey;
    private Character mrBean;
    private Character oliver;

    @Before
    public void setUp() throws Exception {
        mickey = new Character("Mickey Mouse");
        mrBean = new Character("Mr. Bean");
        oliver = new Character("Oliver Twist");

        repository.deleteAll();
        repository.save(Arrays.asList(mickey, mrBean, oliver));
    }

    @Test
    public void canFetchAll() {
        Response response = when()
                .get("/test-app/characters")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("list.size()", is(3))
                .extract().response();

        List<Character> characters = Arrays.asList(response.getBody().as(Character[].class));
        assertThat(characters).contains(mickey, mrBean, oliver);
    }
}