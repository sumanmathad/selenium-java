package com.ReadTestData;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class JSONSimple {
    @Test
    public void ReadTestData() throws FileNotFoundException {
        FileReader reader = new FileReader("src/test/resources/Employee.json");
        Object obj = JsonParser.parseReader(reader);
        JsonObject empJSON = (JsonObject) obj;

        String fname =  empJSON.get("firstName").getAsString();
        String lname = empJSON.get("lastName").getAsString();
        // getAsString() to be used here rather than toString() because toString will convert even the " (double quotes) inside string value.
        Assert.assertEquals(fname,"Suman");
        Assert.assertEquals(lname,"Mathad");
    }
}
