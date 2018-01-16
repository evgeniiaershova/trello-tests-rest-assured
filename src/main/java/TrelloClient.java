import annotations.*;
import io.restassured.specification.RequestSenderOptions;

import static io.restassured.http.ContentType.JSON;

@ServiceDomain("https://api.trello.com/1/")
@QueryParameters({
        @QueryParameter(name = "key", value = "3445103a21ddca2619eaceb0e833d0db"),
        @QueryParameter(name = "token", value = "a9b951262e529821308e7ecbc3e4b7cfb14a24fef5ea500a68c69d374009fcc0")
})
public class TrelloClient {

    public static final String BOARDS = "/boards";

    @ContentType(JSON) @Resource(BOARDS)
    static RestMethod boardsGet;

    @ContentType(JSON) @Resource(BOARDS)
    static RestMethod boardsPost;

    @ContentType(JSON) @Resource("/boards/{board_id}")
    static RestMethod getBoardById;

    @ContentType(JSON) @Resource("/boards/{board_id}/cards")
    static RestMethod getBoardCardsList;

    @ContentType(JSON) @Resource("/boards/{board_id}/cards/{short_card_id}")
    static RestMethod getBoardCardById;

    @ContentType(JSON) @Resource("/members/{user_name}/boards")
    static RestMethod getAllMemberBoards;

    @ContentType(JSON) @Resource("/members")
    static RestMethod membersGet;

    @ContentType(JSON) @Resource("/cards")
    static RestMethod deleteACardFromBoard;

    @ContentType(JSON) @Resource("/cards/{card_id}/actions/comments")
    static RestMethod postNewCommentToCard;

    @QueryParameter(name = "test", value = "test")
    @ContentType(JSON) @Resource("/cards/{card_id}")
    static RestMethod getCardByUniqueId;

    @ContentType(JSON) @Resource("/cards/{card_id}")
    static RequestSenderOptions getCard;
}
