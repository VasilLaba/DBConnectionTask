package com.advertisement.project.main;

import com.advertisement.project.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {

        try (Writer writer = new FileWriter("Output.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(UserRepository.getAllUsers(), writer);
        }

    }
}
