package trash;

import dataloaders.http.annotations.*;
import dataloaders.http.annotations.Resource;
import dataloaders.http.requests.RestMethod;

import static io.restassured.http.ContentType.JSON;

@ServiceDomain("https://api.trello.com/1/")
@QueryParameters({
        @QueryParameter(name = "key", value = "3445103a21ddca2619eaceb0e833d0db"),
        @QueryParameter(name = "token", value = "a9b951262e529821308e7ecbc3e4b7cfb14a24fef5ea500a68c69d374009fcc0")
})
public class TrelloClient {

/*    @ContentType(JSON) @Resource("/boards")
    RestMethod boardsGet;

    @ContentType(JSON) @Resource("/boards")
    RestMethod boardsPost;

    @ContentType(JSON) @Resource("/boards/{board_id}")
    RestMethod getBoardById;

    @ContentType(JSON) @Resource("/boards/{board_id}/cards")
    RestMethod getBoardCardsList;

    @ContentType(JSON) @Resource("/boards/{board_id}/cards/{short_card_id}")
    RestMethod getBoardCardById;

    @ContentType(JSON) @Resource("/members")
    RestMethod membersGet;

    @ContentType(JSON) @Resource("/cards")
    RestMethod deleteACardFromBoard;

    @ContentType(JSON) @Resource("/cards/{card_id}/actions/comments")
    RestMethod postNewCommentToCard;

    @ContentType(JSON) @Resource("/cards/{card_id}")
    RestMethod getCardByUniqueId;*/

    @ContentType(JSON) @Resource("/members/{user_name}/boards")
    public static RestMethod getAllMemberBoards;

    /*@QueryParameters({
            @QueryParameter(name = "key", value = "3445103a21ddca2619eaceb0e833d0db"),
            @QueryParameter(name = "token", value = "a9b951262e529821308e7ecbc3e4b7cfb14a24fef5ea500a68c69d374009fcc0")
    })*/
    @ContentType(JSON) @Resource("/cards/{card_id}")
    public static RestMethod getCard;
}
