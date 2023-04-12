package it.unibo.ds.ws.users.impl;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpResponseException;
import it.unibo.ds.ws.AbstractController;
import it.unibo.ds.ws.User;
import it.unibo.ds.ws.users.UserApi;
import it.unibo.ds.ws.users.UserController;
import it.unibo.ds.ws.utils.Filters;

public class UserControllerImpl extends AbstractController implements UserController {

    public UserControllerImpl(String path) {
        super(path);
    }

    private UserApi getApi(Context context) {
        return UserApi.of(getAuthenticatorInstance(context));
    }

    @Override
    public void getAllUserNames(Context context) throws HttpResponseException {
        throw new Error("not implemented");
    }

    @Override
    public void postNewUser(Context context) throws HttpResponseException {
        UserApi api=getApi(context);
        var newUser=context.bodyAsClass(User.class);
        var futureResult=api.registerUser(newUser);
        asyncReplyWithBody(context, "application/json", futureResult);
    }

    @Override
    public void deleteUser(Context context) throws HttpResponseException {
        throw new Error("not implemented");
    }

    @Override
    public void getUser(Context context) throws HttpResponseException {
        UserApi api=getApi(context);
        var userId=context.pathParams("{userId}");
        var futureResult=api.getUser(userId);
        asyncReplyWithBody(context,"application/json", futureResult);
    }

    @Override
    public void putUser(Context context) throws HttpResponseException {
        throw new Error("not implemented");
    }

    @Override
    public void registerRoutes(Javalin app) {
        app.before(path("*"), Filters.ensureClientAcceptsMimeType("application", "json"));
        app.post(path("/users"), this::postNewUser);
        app.get(path("/users"), this::getUser);
    }
}
