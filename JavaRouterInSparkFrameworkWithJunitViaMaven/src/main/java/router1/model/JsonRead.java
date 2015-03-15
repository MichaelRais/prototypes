/*
PURPOSE:        Access object for data structure.  Loads data.
                See "CountryRouter.java"for all comments, work history, todo, etc.


  REF:  http://blog.sodhanalibrary.com/2014/04/read-json-with-java-using-google-json.html#.VPu4T977X-k
        http://stackoverflow.com/questions/5863870/how-should-a-model-be-structured-in-mvc
*/

package router1;

// For file load 
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// For convertFileToJSON method
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
//import com.google.gson.stream.JsonReader;  //Note:  Reader is for performance use-case not applicable here

// For printJson Method
import java.io.StringReader;
import java.util.Map.Entry;
import java.util.Set;


class JsonRead {

    static JsonObject convertFileToJSON (String fileName) {
        //Load file
        JsonObject jsonObject = new JsonObject();
        JsonParser parser = new JsonParser();

        try {
          JsonElement jsonElement = parser.parse(new FileReader(fileName));
          jsonObject = jsonElement.getAsJsonObject();
        } catch (FileNotFoundException e) {
            //No action
        } catch (IOException e) {
            //No action
        }

        JsonArray data = jsonObject.getAsJsonArray("countries");
        System.out.println("Loading " + data.size() + " records.");
        for (JsonElement je : data) {
          printJson(je);
        }
        if (data.size() > 0)
          System.out.println("Load request complete.");
        /*
        for (i=0, i < data.size(); i++) {
            System.out.println("data: " + data[n]);
        }*/

        return jsonObject;
    }

    private static void printJson(JsonElement jsonElement) {
        
        // Check whether jsonElement is JsonObject or not
        if (jsonElement.isJsonObject()) {
            Set<Entry<String, JsonElement>> ens = ((JsonObject) jsonElement).entrySet();
            if (ens != null) {
                // Iterate JSON Elements with Key values
                for (Entry<String, JsonElement> en : ens) {
                    System.out.println(en.getKey() + " : " + en.getValue());
                }
            }
        } 
    }
}
 