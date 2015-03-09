/*
Supporting class for Gson
*/


package router1;

import spark.Spark.*;
import spark.*;

import com.google.gson.Gson;


// Not used until "/helloJSON" works
public class JsonTransformer implements ResponseTransformer {

    private Gson gson = new Gson();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }

}
