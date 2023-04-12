package it.unibo.ds.presentation;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Locale;

public class RequestDeserializer implements JsonDeserializer<Request<?>> {
    @Override
    public Request<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if(json instanceof JsonObject){
            JsonObject jsonObject=json.getAsJsonObject();
            String method= jsonObject.get("method").getAsString();


            try{
                if(method.equals("register")){
                    return new RegisterRequest(context.deserialize(jsonObject.get("argument"), User.class));
                }
                else{
                    return new AuthorizeRequest(context.deserialize(jsonObject.get("argument"), Credentials.class));
                }
            }catch (IllegalArgumentException ex){
                throw new JsonParseException("Invalid Request: "+json, ex);
            }
        }
        throw new JsonParseException("Invalid Request: "+json);
    }
}
