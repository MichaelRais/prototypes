/* 
PURPOSE:        Controller object.
                See "CountryRouter.java"for all comments, work history, todo, etc.

AUTHOR:         M. Rais
*/

package router1;

import spark.Spark.*;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;


class Countries { 

    // Map holding the countries is "country"
    private static Map<String, String> country = new HashMap<String, String>();
    
    // For a non-prototype, initLoad could be handled in the "Country()" constructor executed once on instantiation.
    static String initLoad(Request req, Response res) {
        JsonObject jsonObjCountry = new JsonObject();
        JsonObject jsonObject = new JsonObject();
        jsonObject = Model.getDataStructure();

        JsonArray data = jsonObject.getAsJsonArray("countries");
            for (JsonElement je : data) {
                jsonObjCountry =  je.getAsJsonObject();
                country.put(jsonObjCountry.get("code").getAsString(), jsonObjCountry.get("country").getAsString());
            }
        res.status(201); // 201 Created
        return "{\"Status\":\"Loaded\"}";
    }  

    static String getCountryFromCode(Request req, Response res) {
        String code = req.params(":code");
        String name = country.get(code);
        Map<String, String> countryJSON = new HashMap<String, String>();

        countryJSON.put(code, name); 
        JsonTransformer jsonXFormer = new JsonTransformer(); 

        res.status(200); // 200 OK
        return jsonXFormer.render(countryJSON);
    }

    static String setCountryFromCode(Request req, Response res) {
        String code = req.params(":code");
        String name = req.params(":name");
        Map<String, String> countryJSON = new HashMap<String, String>();

        country.put(code, name); //additive
        countryJSON.put(code, name);  //individual record
        JsonTransformer jsonXFormer = new JsonTransformer(); 

        res.status(201); // 201 Created
        return jsonXFormer.render(countryJSON);
    }

    static String deleteCodeCountry(Request req, Response res) {
        String code = req.params(":code");
        String returnJSON;
        
        if (country.get(code) != null) {
            country.remove(code);
            res.status(200); // OK.
            returnJSON = "{\"Status\":\"Deleted\"}";
        } else {
            res.status(417); // Expectation failed.
            returnJSON = "{\"Status\":\"Not Found\"}";
        }

        return returnJSON;
    }

    static String getCodesCountries(Request req, Response res) {
            /* String ids = "";
            for (String id : country.keySet()) {
               ids += id + ", "; 
            }
            if (ids.length()  > 0 )
                ids = ids.substring(0,ids.length()-2);
            return ids; */
        JsonTransformer jsonXFormer = new JsonTransformer();            
        res.status(200); // 200 OK
        return jsonXFormer.render(country);
    }

}

