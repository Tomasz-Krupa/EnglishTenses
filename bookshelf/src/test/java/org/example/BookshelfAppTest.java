package org.example;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;


import io.restassured.RestAssured;
import org.example.bookshelf.BookshelfApp;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import java.io.IOException;


public class BookshelfAppTest {
    private static final String BOOK_1 = "{\"title\":\"Java\", \"author\":\"schmidt\"," + "\"pagesSum\":152,\"yearOfPublished\":2019,\"publishingHouse\":\"Helion\"}";
    private static final String BOOK_2 = "{\"title\":\"Python\", \"author\":\"Lutz\"," + "\"pagesSum\":184,\"yearOfPublished\":2017,\"publishingHouse\":\"Helion\"}";

    private static final int APP_PORT = 8090;

    private BookshelfApp bookshelfApp;

    @BeforeAll
    public static void beforeAll() {
        RestAssured.port = APP_PORT;
    }

    @BeforeEach
    public void beforeEach() throws IOException {
        bookshelfApp = new BookshelfApp(APP_PORT);
    }

    @AfterEach
    public void afterEach() {
        bookshelfApp.stop();
    }

    @Test
    public void addMethod_correctBody_shouldReturnStatus200() throws Exception {
        with().body(BOOK_1).when().post("book/add").then().statusCode(200).body(startsWith("Book is added on"));
    }

    @Test
    public void addMethod_fieldTypeMismatch_shouldReturnStatus500() {

        String bookWithFieldTypeMismatch = "{\"title\":\"Java\", \"author\":\"schmidt\"," + "\"pagesSum\":152 pages,\"yearOfPublished\":2019,\"publishingHouse\":\"Helion\"}";

        with().body(bookWithFieldTypeMismatch).when().post("book/add").then().statusCode(500);
    }

    @Test
    public void addMethod_unexpectedField_shouldReturnStatus500() {

        String bookWithFieldTypeMismatch = "{\"title\":\"Java\", \"author\":\"schmidt\"," + "\"pagesSum\":152 pages,\"yearOfPublished\":2019,\"publishingHouse\":\"Helion\"}";

        with().body("{\"numberOfChapters\":10}").when().post("book/add").then().statusCode(500);
    }

    private long addBookAndGetId(String json){
        String responseText= with().body(json)
                .when().post("/book/add")
                .then().statusCode(200).body(startsWith("Book is added on"))
                .extract().body().asString();

        String idStr= responseText.substring(responseText.indexOf("=")+1);

        return Long.parseLong(idStr);
    }
//////////////////////
    @Test

    public void getMethod_correctBookIdParam_shouldReturnStatus200(){
        long bookId1= addBookAndGetId(BOOK_1);
        long bookId2= addBookAndGetId(BOOK_2);

        with().param("bookId", bookId1)
                .when().get("/book/get")
                .then().statusCode(200)
                .body("id", equalTo(bookId1))
                .body("title", equalTo("Java"))
                .body("author", equalTo("schmidt"))
                .body("pagesSum", equalTo(152))
                .body("yearOfPublished", equalTo(2019))
                .body("publishingHouse", equalTo("Helion"));
    }

    @Test
    public void GetMethod_noIdParameter_shouldReturnStatus400(){
        when().get("book/get").then().statusCode(400).body(equalTo("Incorrect request params"));
    }

    @Test
    public void GetMethod_wrongTypeOfBookId_shouldReturnStatus400(){
        with().param("bookId", "abc").when().get("book/get").then().statusCode(400).body(equalTo("Request param 'bookId' has to be a number"));
    }

    @Test
    public void GetMethod_bookIdDoesNotExist_shouldReturnStatus404(){
        with().param("bookId", 12345).when().get("book/get").then().statusCode(404);
    }
//////////////
    @Test
    public void GetAllMethod_0books_shouldReturnStatus200(){
        when().get("book/getAll").then().statusCode(200).body("", hasSize(0));      //  "/book/getAll" WORKS AS WELL
    }

    @Test
    public void GetAllMethod_1books_shouldReturnStatus200() {
        long bookId1 = addBookAndGetId(BOOK_1);
        when().get("/book/getAll")
                .then().statusCode(200)
                .body("",hasSize(1))
                .body("id", hasItem(bookId1))                       //w get było equalsTo(), zamiast hasItem()(
                .body("title", hasItem("Java"))
                .body("author", hasItem("schmidt"))
                .body("pagesSum", hasItem(152))
                .body("yearOfPublished", hasItem(2019))
                .body("publishingHouse", hasItem("Helion"));
    }
    @Test
    public void GetAllMethod_2books_shouldReturnStatus200() {
        long bookId1 = addBookAndGetId(BOOK_1);
        long bookId2 = addBookAndGetId(BOOK_2);
        when().get("/book/getAll")
                .then().statusCode(200)
                .body("",hasSize(2))
                .body("id", hasItems(bookId1, bookId2))
                .body("title", hasItems("Java", "Python"))
                .body("author", hasItems("schmidt", "Lutz"))
                .body("pagesSum", hasItems(152, 184))
//                .body("yearOfPublished", hasItems(2019, 2017))
                .body("yearOfPublished", contains(2019, 2017))              //można użyć hasItems() lub ogólniej contains()

                .body("publishingHouse", hasItem("Helion"));
    }

//"{\"title\":\"Python\", \"author\":\"Lutz\"," + "\"pagesSum\":184,\"yearOfPublished\":2017,\"publishingHouse\":\"Helion\"}";
}

