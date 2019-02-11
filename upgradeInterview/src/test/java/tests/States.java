package tests;

import com.google.gson.*;
import static org.junit.jupiter.api.Assertions.*;


public class States {
    public static void state_validation(JsonArray states_array ){
        int count = 0;
        for (int i = 0; i < states_array.size(); i++) {
            JsonElement element = states_array.get(i);
            JsonElement value = ((JsonObject) element).get("minAge");
            if (value.getAsInt() == 19){
                count++;
                System.out.println("Name of City is: " + ((JsonObject)element).get("label").getAsString());
            }
            if (((JsonObject) element).get("minLoanAmount").getAsInt() == 3005)
                assertEquals("Georgia",((JsonObject) element).get("label").getAsString(), "Georgia is not unique this Min Loan Amount of 3005");
        }
        assertTrue(count <2, "Exist more than one state with age 19");
    }
}
