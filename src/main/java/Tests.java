import dataloaders.Boards;
import dataloaders.PropertyLoader;
import dataloaders.ResourcesLoader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class Tests {
   public final String CARD_UNIQUE_ID = "5a27e722e2f04f3ab6924931";
   public final String BOARD_ID = "5a27e3b62fef5d3a74dca48a";

   private RequestSpecification requestSpec;
   private ResponseSpecification responseSpec;
   private PropertyLoader commonData;
   private ResourcesLoader resources;
   private Boards boards;

   @BeforeClass
   public void init(){
      commonData = new PropertyLoader();
      resources = new ResourcesLoader();

      boards = new Boards();
      RequestSpecBuilder requestBuilder = new RequestSpecBuilder()
              .setContentType(ContentType.JSON)
              .setBaseUri(commonData.getBaseUrl())
              .log(LogDetail.URI).log(LogDetail.METHOD)
              .addFilter(new ResponseLoggingFilter(LogDetail.STATUS))
              .addFilter(new ResponseLoggingFilter(LogDetail.BODY))
              .addQueryParam("key", commonData.getApiKey())
              .addQueryParam("token", commonData.getToken());
      requestSpec = requestBuilder.build();

      ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder()
              .expectStatusCode(200)
              .expectContentType(ContentType.JSON);
      responseSpec = responseBuilder.build();

      RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
   }

   @Test
   public void createNewBoardTest(){
      String boardName = "Lorem ipsum board " + random(12, true, true);
      String body = "{\"name\":\"" + boardName + "\"}";
      given()
              .spec(requestSpec)
              .body(body)
      .when()
              .post(boards.boards)
      .then()
              .spec(responseSpec);
   }

   @Test
   public void getBoardById() {

      Response response = given()
            .spec(requestSpec)
            .pathParam("board_id", BOARD_ID)
     .when()
            .get(boards.boards_id);

     response.then()
            .spec(responseSpec)
            .body("id", equalTo(BOARD_ID));
}

   @Test
   public void getBoardCardsList() {
      given()
              .spec(requestSpec)
              .pathParam("board_id", BOARD_ID)
       .when()
              .get("/boards/{board_id}/cards")
       .then()
              .spec(responseSpec)
              .body("name.size()", equalTo(6));
   }

   @Test
   public void getCardByShortId() {
      given()
              .spec(requestSpec)
              .pathParam("board_id",BOARD_ID)
              .pathParam("short_card_id", "1")
       .when()
              .get("/boards/{board_id}/cards/{short_card_id}")
       .then()
              .spec(responseSpec)
              .body("name", equalTo("Lorem ipsum dolor sit amet"));
   }

   @Test
   public void postNewCommentToCard() {
      String newComment = "New comment" + random(7, true, false);

      given()
              .spec(requestSpec)
              .pathParam("card_id",CARD_UNIQUE_ID)
              .body("{\"text\": \"" + newComment + "\"}")
       .when()
              .post("/cards/{card_id}/actions/comments")
       .then()
              .spec(responseSpec)
              .body("data.text", containsString(newComment));
   }

   @Test
   public void getAllUserBoards() {
      given()
              .spec(requestSpec)
              .pathParam("user_name", "jdiframwork")
       .when()
              .get("/members/{user_name}/boards")
       .then()
              .spec(responseSpec)
              .body("name.size()", greaterThan(4));
   }

   @Test
   public void getCardByUniqueId() {
      given()
              .spec(requestSpec)
              .queryParam("fields", "url", "shortUrl")
              .pathParam("card_id", CARD_UNIQUE_ID)
         .when()
              .get("/cards/{card_id}")
         .then()
              .spec(responseSpec)
              .body("url", containsString("https://trello.com/c/SSFPAlkB/1-lorem-ipsum-dolor-sit-amet"))
              .body("shortUrl", containsString("https://trello.com/c/SSFPAlkB"))
              .body("id", equalTo(CARD_UNIQUE_ID))
              .body("keySet().size()", is(3));
   }

}


