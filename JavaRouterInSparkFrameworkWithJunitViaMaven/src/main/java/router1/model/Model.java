/* 
PURPOSE:        Data access object.
                See "CountryRouter.java"for all comments, work history, todo, etc.

AUTHOR:         M. Rais

NOTE:  
	*) String internalPath = System.getProperty("user.dir");
*/

package router1;

import com.google.gson.JsonObject;


class Model {
	static JsonObject getDataStructure() {
		JsonObject jsonObject = new JsonObject();
		jsonObject = JsonRead.convertFileToJSON ("src/main/java/router1/model/resources/json/codeCountries.json");
		return jsonObject;
	}
}
