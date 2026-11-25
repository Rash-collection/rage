/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author rash4
 */
public class Test {
    static record User(String name, String title){
    }
    public static void write(){
        var mapper = new ObjectMapper();
        var user = new User("Dalley", "Sama rama");
        System.out.println(user);
        try{
            var value = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
            System.out.println(value);
        }catch(JsonProcessingException e){
            
        }
    }
    public static void read(){
        var mapper = new ObjectMapper();
        String value =  """
                        {
                            "name" : "Sexy",
                            "title" : "Boi"
                        }
                        """;
        System.out.println(value);
        try{
            var user = mapper.readValue(value, User.class);
            System.out.println(user);
        }catch(JsonProcessingException e){
            
        }
    }
}
