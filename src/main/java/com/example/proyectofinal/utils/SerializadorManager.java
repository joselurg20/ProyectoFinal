package com.example.proyectofinal.utils;

import java.io.*;

public class SerializadorManager {
    public static <T> boolean serialize(T c,String archivo) {
        boolean result=false;
        try(ObjectOutputStream oout =
                    new ObjectOutputStream(
                            new FileOutputStream(archivo))){
            oout.writeObject(c);
            result=true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static <T> T deserialize(String archivo) {
        T result = null;
        try(ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(archivo))){
            result= (T)ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
