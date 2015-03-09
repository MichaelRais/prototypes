/* 
    A simple RESTful prototype of a microservice built in Spark framework, 
    using J-Unit in Maven via Surefire plug-in.
    Still filling in comments, and sanding rough edges.  

    Already convinced SparkJava is awesome - http://sparkjava.com

    ToDo;
        *)  All responses must be ReST
        *)  Finish up more J-Unit tests
        *)  Confirm structure / Finish cleaning up
        *)  POM.xml needs update - remove unused references
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
     * Map holding the countrys
     * HASHMAP TUTORIAL:  http://java67.blogspot.com/2013/02/10-examples-of-hashmap-in-java-programming-tutorial.html
     * IMPORTANT: A hashmap is internally A) search on hash B) Then search on key if dupes, so hashing is handled natively to the datatype.  
     */
    private static Map<String, String> country = new HashMap<String, String>();
    
    public static void main(String[] args) {
        
        Spark.get("/get/:code", (req, res) -> {
            String code = req.params(":code");
            String name = country.get(code);
            Map<String, String> countryJSON = new HashMap<String, String>();

            countryJSON.put(code, name); 
            JsonTransformer jsonXFormer = new JsonTransformer();
            //return jsonXFormer.render("{Country: " + name + "}");  //no point unless you pass back an obj - just an excercise.
            res.status(200); // 200 OK
            return jsonXFormer.render(countryJSON);
        });

        // Creates a new country resource, will return the ID to the created resource
        // Code is sent as query parameters e.g. /countries/${code}/${name}
        Spark.get("/load/:code/:name", (req, res) -> {
            //Random random = new Random();

            String code = req.params(":code");
            String name = req.params(":name");

            country.put(code, name);
            //int id = random.nextInt(Integer.MAX_VALUE);
            //country.put(String.valueOf(id), code);

            res.status(201); // 201 Created
            return code;
        });

        // Gets all available country resources (id's)
        Spark.get("/countrycodes", (req, res) -> {
                String ids = "";
                for (String id : country.keySet()) {
                   ids += id + ", "; 
                }
                if (ids.length()  > 0 )
                    ids = ids.substring(0,ids.length()-2);
                return ids;
        });

        Spark.get("/initload/", (req, res) -> {
            JsonObject jsonObjCountry = new JsonObject();
            JsonObject jsonObject = new JsonObject();
            jsonObject = JsonRead.convertFileToJSON ("src/main/java/router1/model/resources/json/codeCountries.json");
            
            JsonArray data = jsonObject.getAsJsonArray("countries");
                for (JsonElement je : data) {
                    jsonObjCountry =  je.getAsJsonObject();
                    country.put(jsonObjCountry.get("code").getAsString(), jsonObjCountry.get("country").getAsString());
                }
            return "Loaded";
        });

        Spark.exception(IllegalArgumentException.class, (e, request, response) -> {
            // works because it's an existing type of exception - doesn't need to get "thrown" - triggered by exception.
            response.status(404);
            response.body("Resource not found");
        });   
        
    }
}
