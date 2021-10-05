package com.advertisement.project.serialization;

import com.google.gson.Gson;

import java.io.*;
import java.util.Arrays;

public class Serializer<T> {
    private int serializationIndex = 1;

    public void serialize(T t) throws IOException {
        String fileName = Arrays.stream(t.getClass().toString().replace('.', ' ').split(" "))
                .filter(s -> Character.isUpperCase(s.charAt(0)))
                .findFirst()
                .get() + serializationIndex + ".ser";
        File file = new File(fileName);
        FileOutputStream fo = new FileOutputStream(file);
        ObjectOutputStream so = new ObjectOutputStream(fo);
        so.writeObject(t);
        so.flush();
        so.close();
    }

    public T deserialize(String file) throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(file);
        ObjectInputStream si = new ObjectInputStream(fi);
        T t = (T) si.readObject();
        si.close();
        return t;
    }
}
