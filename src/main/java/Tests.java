import dataloaders.PropertyLoader;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import trash.TrelloClient;

import static dataloaders.http.requests.ServiceInit.init;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static trash.TrelloClient.getAllMemberBoards;
import static trash.TrelloClient.getCard;


public class Tests {
   public final String CARD_UNIQUE_ID = "5a27e722e2f04f3ab6924931";
   public final String BOARD_ID = "5a27e3b62fef5d3a74dca48a";

   private RequestSpecification requestSpec;
   private ResponseSpecification responseSpec;
   private PropertyLoader commonData;


   @BeforeClass
   public void initClass(){
      init(TrelloClient.class);

   /*   commonData = new PropertyLoader();

      RequestLoggingFilter requestLogUri = new RequestLoggingFilter(LogDetail.URI);
      RequestLoggingFilter requestLogMethod = new RequestLoggingFilter (LogDetail.PARAMS);
      ResponseLoggingFilter responseLogBody = new ResponseLoggingFilter(LogDetail.BODY);
      ResponseLoggingFilter responseLogUri = new ResponseLoggingFilter(LogDetail.STATUS);

      requestSpec = new RequestSpecBuilder()
              .setContentType(ContentType.JSON)
              .setBaseUri(commonData.getBaseUrl())
              .addQueryParam("key", commonData.getApiKey())
              .addQueryParam("token", commonData.getToken())
              .addFilter(requestLogUri)
              .addFilter(requestLogMethod)
              .addFilter(responseLogBody)
              .addFilter(responseLogUri)
              .build();*/

      responseSpec = new ResponseSpecBuilder()
              .expectStatusCode(200)
              .expectContentType(ContentType.JSON)
              .build();
      }

   /*@Test
   public void createNewBoardTest(){
      String boardName = "Lorem ipsum board " + random(12, true, true);
      String body = "{\"name\":\"" + boardName + "\"}";
      given()
              .spec(requestSpec.body(body))
      .when()
              .post("/boards")

      .then()
              .spec(responseSpec);
   }

   @Test
   public void getBoardById() {

      Response response = given()
            .spec(requestSpec)
            .pathParam("id", BOARD_ID)
     .when()
            .get("/boards/{id}");

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
*/
   @Test
   public void getAllUserBoards() {
      getAllMemberBoards
              .spec
              .log().all()
              .pathParam("user_name", "jdiframwork")
       .when()
              .get(getAllMemberBoards.resource)
       .then()
              .spec(responseSpec)
              .body("name.size()", greaterThan(4));
   }
   @Test
   public void getCardByUniqueId() {


       getCard
              .spec
              .log().all()
              .queryParam("fields", "url", "shortUrl")
              .pathParam("card_id", CARD_UNIQUE_ID)
         .when()
              .get(getCard.resource)
         .then()
              .spec(responseSpec)
              .body("url", containsString("https://trello.com/c/SSFPAlkB/1-lorem-ipsum-dolor-sit-amet"))
              .body("shortUrl", containsString("https://trello.com/c/SSFPAlkB"))
              .body("id", equalTo(CARD_UNIQUE_ID))
              .body("keySet().size()", is(3));
   }

}


