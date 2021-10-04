package com.advertisement.project.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Serializer<T> {

    private int serializationIndex = 1;
    private ObjectMapper objectMapper = new ObjectMapper();

    public void serialize(T t) throws IOException {
        //objectMapper.registerModule();
        String fileName = Arrays.stream(t.getClass().toString().replace('.', ' ').split(" "))
                .filter(s -> Character.isUpperCase(s.charAt(0)))
                .findFirst()
                .get() + serializationIndex + ".json";
        System.out.println(fileName);
        objectMapper.writeValue(new File("json/" + fileName), t);
        serializationIndex++;
    }

    public T deserialize(File file, Class<T> tClass) throws IOException {
        return objectMapper.readerFor(tClass).readValue(file);
    }

//    public List<User> getUsers(String json){
//        Gson gson = new Gson();
//        return gson.fromJson(json, new TypeToken<List<User>>(){}.getType());
//    }
//
//    public String setUsers(List<User> users){
//        return new Gson().toJson(users);
//    }
//
//    public void serializeToFile(List<User> users, String fileName) throws IOException {
//        File file = new File(fileName);
//        file.createNewFile();
//        FileWriter writer = new FileWriter(file);
//        writer.write(setUsers(users));
//        writer.close();
//    }
}
