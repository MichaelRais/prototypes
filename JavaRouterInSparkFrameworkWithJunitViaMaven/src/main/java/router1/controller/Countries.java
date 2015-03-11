/* 
    A simple RESTful prototype of a microservice built in Spark framework, 
    using J-Unit in Maven via Surefire plug-in.
    Still filling in comments, and sanding rough edges.  

    Already convinced SparkJava is awesome - http://sparkjava.com

    ToDo;
        *)  Confirm structure 
        *)  Finish up more JUnit tests
        *)  Handle content-type/URLencoding.
        *)  POM.xml needs update - remove unused reference
        *)  Package into Jar
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


/**
 * A simple RESTful example showing howto create, get, update and delete country resources.
 */
public class Countries {

    /*
     * Map holding the countries is "country"
     */
    private static Map<String, String> country = new HashMap<String, String>();
    
    public static void main(String[] args) {

        // Gets a country resource.  Returns the code:name
        // Resource is requested by query parameters e.g. (/get/${code})
        Spark.get("/get/:code", (req, res) -> {
            String code = req.params(":code");
            String name = country.get(code);
            Map<String, String> countryJSON = new HashMap<String, String>();

            countryJSON.put(code, name); 
            JsonTransformer jsonXFormer = new JsonTransformer(); 

            res.status(200); // 200 OK
            return jsonXFormer.render(countryJSON);
        });

        // Creates a new country resource.  Allows for individual records to load.  Return the code:name
        // Resource is sent as query parameters e.g. /load/${code}/${name}
        Spark.get("/load/:code/:name", (req, res) -> {
            String code = req.params(":code");
            String name = req.params(":name");
            Map<String, String> countryJSON = new HashMap<String, String>();

            country.put(code, name); //additive
            countryJSON.put(code, name);  //individual record
            JsonTransformer jsonXFormer = new JsonTransformer(); 

            res.status(201); // 201 Created
            return jsonXFormer.render(countryJSON);
        });

        // Gets all available (loaded) codes and countries.
        Spark.get("/codescountries", (req, res) -> {
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

        });

        // Initialize microservice by loading in full data model.
        Spark.get("/initload", (req, res) -> {
            JsonObject jsonObjCountry = new JsonObject();
            JsonObject jsonObject = new JsonObject();
            jsonObject = JsonRead.convertFileToJSON ("src/main/java/router1/model/resources/json/codeCountries.json");
            
            JsonArray data = jsonObject.getAsJsonArray("countries");
                for (JsonElement je : data) {
                    jsonObjCountry =  je.getAsJsonObject();
                    country.put(jsonObjCountry.get("code").getAsString(), jsonObjCountry.get("country").getAsString());
                }
            return "{\"Status\":\"Loaded\"}";
        });

        // Handle unexpected request strings
        Spark.exception(IllegalArgumentException.class, (e, request, response) -> {
            // works because it's an existing type of exception - doesn't need to get "thrown" - triggered by exception.
            response.status(404);
            response.body("Resource not found");
        });   
        
    }
}
