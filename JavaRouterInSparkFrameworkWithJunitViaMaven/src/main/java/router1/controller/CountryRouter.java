/* 
PURPOSE:    Router object

PROJECT:    A simple RESTful prototype of a microservice built in Spark framework, using JUnit in Maven via Surefire plug-in.

COMMENTS:   
    *) Already convinced SparkJava is awesome - http://sparkjava.com
    *) As far as this prototype goes, I've kept it super demo-friendly by using GET even when PUT/DELETE is appropriate to be RESTful; that and something about not using JSON which makes sense for more complex API's.
            +) REF:  http://restcookbook.com/HTTP%20Methods/put-vs-post/
            +) REF:  http://restcookbook.com/Mediatypes/json/
            +) REF:  http://restcookbook.com/Basics/hateoas/

WORK HISTORY (author):
    20150314(M. Rais): v0.8 - Added more OO convention.  Cleaned up comments.  
    20150310(M. Rais): v0.7-alpha - First prototype demonstrator.   

TODO:
    *)  Add more JUnit tests
    *)  Handle content-type/URLencoding.
    *)  Review POM.xml
    *)  Package into Jar 

FIXME: 
    *)  n/a
 
USAGE:
    *) See https://github.com/MichaelRais/prototypes_frameworks/

*/

package router1;

import spark.Spark.*;
import spark.*;


/*
 * A simple RESTful example showing howto create, get, update and delete country resources.
 */
public class CountryRouter {
 
    public static void main(String[] args) {

        // Gets a country resource.  Returns the code:name
        // Resource is requested by query parameters e.g. (/get/${code})
        Spark.get("/v0/get/:code", (req, res) -> {
             return Countries.getCountryFromCode(req, res);
        });

        // Creates a new country resource.  Allows for individual records to load.  Return the code:name
        // Resource is sent as query parameters e.g. /load/${code}/${name}
        Spark.get("/v0/load/:code/:name", (req, res) -> {  // If not a prototype I'd use "Spark.put" - not GET - and related REST expectations.
            return Countries.setCountryFromCode(req, res);
        });

        Spark.get("/v0/delete/:code", (req, res) -> {   // If not a prototype I'd use "Spark.delete" - not GET - and related REST expectations.
            return Countries.deleteCodeCountry(req, res);
        }); 

        // Gets all available (loaded) codes and countries.
        Spark.get("/v0/codescountries", (req, res) -> {
            return Countries.getCodesCountries(req, res);
        });

        // Initialize microservice by loading in full data model.
        Spark.get("/v0/initload", (req, res) -> {
            // If we wanted automatic load, then Countries could get a constructor and on instantiation we'd initload - don't see case for it here.
            return Countries.initLoad(req, res);
        });

        // Handle unexpected request strings
        Spark.exception(IllegalArgumentException.class, (e, request, response) -> {
            // works because it's an existing type of exception - doesn't need to get "thrown" - triggered by exception.
            response.status(404);
            response.body("Resource not found");
        });   
        
    }
}
