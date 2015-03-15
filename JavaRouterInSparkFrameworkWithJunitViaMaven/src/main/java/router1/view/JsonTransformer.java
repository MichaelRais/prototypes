/*
PURPOSE:        Supporting class using Gson, which generates JSON representation ("view") of data structure.
                See "CountryRouter.java"for all comments, work history, todo, etc.
*/

package router1;

import spark.Spark.*;
import spark.*;

import com.google.gson.Gson;


class JsonTransformer implements ResponseTransformer {

    private Gson gson = new Gson();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }

}
