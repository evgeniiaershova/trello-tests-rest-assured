package client;

import http.refactored.annotations.*;
import http.refactored.entities.RestMethod;

import static io.restassured.http.ContentType.JSON;

@ServiceDomain("https://api.trello.com/1/")
@QueryParameters({
        @QueryParameter(name = "key", value = "3445103a21ddca2619eaceb0e833d0db"),
        @QueryParameter(name = "token", value = "a9b951262e529821308e7ecbc3e4b7cfb14a24fef5ea500a68c69d374009fcc0")
})

public class TrelloClient {

    @ContentType(JSON) @Resource("/boards")
    public static RestMethod boards;

    @ContentType(JSON) @Resource("/boards/{board_id}")
    public static RestMethod board;

    @ContentType(JSON) @Resource("/boards/{board_id}/cards")
    public static RestMethod getBoardCardsList;

    @ContentType(JSON) @Resource("/boards/{board_id}/cards/{short_card_id}")
    public static RestMethod getBoardCardById;

    @ContentType(JSON) @Resource("/members")
    public static RestMethod membersList;

    @ContentType(JSON) @Resource("/cards")
    public static RestMethod cardsList;

    @ContentType(JSON) @Resource("/cards/{card_id}/actions/comments")
    public static RestMethod postNewCommentToCard;

    @ContentType(JSON) @Resource("/cards/{card_id}")
    public static RestMethod getCardByUniqueId;

    @ContentType(JSON) @Resource("/members/{user_name}/boards")
    public static RestMethod getAllMemberBoards;

    @Headers({@Header(name = "headerName2", value = "headerValue2"),
            @Header(name = "headerName1", value = "headerValue1")})
    @QueryParameter(name = "testParameterName", value = "TestParValue")
    @ContentType(JSON) @Resource("/cards/{card_id}")
    public static RestMethod getCard;
}
