package com.advertisement.project.json;

import com.advertisement.project.model.Advertisement;
import com.advertisement.project.model.User;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonReader {

    public static void SaveJson(List<Advertisement> ob) throws IOException {
        Gson gson = new Gson();
        gson.toJson(ob, new FileWriter("DBConnector/json/js"));
    }

}
