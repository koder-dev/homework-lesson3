package org.homework;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws IOException {
        //Завдання 1
        Task1 task1 = new Task1();
        try {
            System.out.println(MethodsInvoker.invoke(task1));
        } catch (InvocationTargetException | IllegalAccessException e) {
            System.out.println("Task 1 invocation failed");
        }

        //Завдання 2
        TextContainer container = new TextContainer("hello world!");
        try {
            MethodsInvoker.invokeSaveMethod(container);
        } catch (InvocationTargetException | IllegalAccessException e) {
            System.out.println("Save method invocation failed");
        }

        //Завдання 3
       try (FileWriter fw = new FileWriter("person.txt")) {
           Data personData = new Data("User", "user@mail.com", "12345");
           Class<Data> personClass = Data.class;
           Field[] fields = personClass.getDeclaredFields();
           for (Field field : fields) {
               if (field.isAnnotationPresent(Save.class)) {
                   field.setAccessible(true);
                   if (field.getGenericType().equals(String.class)) {
                       fw.write(field.get(personData) + "\n");
                   }
               }

           }
       } catch (IllegalAccessException e) {
           System.out.println("Save method invocation failed");
       }

        try (FileReader fr = new FileReader("person.txt")) {
           char[] buffer = new char[2];
           StringBuilder result = new StringBuilder();
           while (fr.read(buffer) > 1) {
               result.append(new String(buffer));
           }
           System.out.println(result);
       }

    }
}

class Data {
    @Save
    private String name;

    @Save
    private String email;

    private String password;

    public Data(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Data{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}