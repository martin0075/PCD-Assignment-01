package it.unibo.ds.ws.tokens.impl;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpResponseException;
import it.unibo.ds.ws.AbstractController;
import it.unibo.ds.ws.Credentials;
import it.unibo.ds.ws.tokens.TokenApi;
import it.unibo.ds.ws.tokens.TokenController;
import it.unibo.ds.ws.utils.Filters;

public class TokenControllerImpl extends AbstractController implements TokenController {
    public TokenControllerImpl(String path) {
        super(path);
    }

    private TokenApi getApi(Context context) {
        return TokenApi.of(getAuthenticatorInstance(context));
    }

    @OpenApi(path = AuthService.BASE_URL + "/tokens", methods = {HttpMethod.POST}, tags = {"tokens"}, description = "Generates a token given some credentials", requestBody = @OpenApiRequestBody(
            description = "The user's credentials",
            required = true,
            content = {
                    @OpenApiContent(
                            from = Credentials.class,
                            mimeType = ContentType.JSON
                    )
            }
    ), responses = {
            @OpenApiResponse(
                    status = "200",
                    description = "The user's credentials are valid, and here's the authorization token",
                    content = {
                            @OpenApiContent(
                                    from = Token.class,
                                    mimeType = ContentType.JSON
                            )
                    }
            ),
            @OpenApiResponse(
                    status = "400",
                    description = "Bad request: some field is missing or invalid in the provided credentials"
            ),
            @OpenApiResponse(
                    status = "401",
                    description = "Unauthorized: the provided credentials are well formed, but they correspond to no user"
            )
    }, operationId = "TokenApi::createToken")
    @Override
    public void postToken(Context context) throws HttpResponseException {
        TokenApi api = getApi(context);
        var credentials = context.bodyAsClass(Credentials.class);
        var futureResult = api.createToken(credentials);
        asyncReplyWithBody(context, "application/json", futureResult);
    }

    @Override
    public void registerRoutes(Javalin app) {
        app.before(path("*"), Filters.ensureClientAcceptsMimeType("application", "json"));
        app.post(path("/"), this::postToken);
    }
}
