/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package rage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.util.Arrays;
import rage.roots.Basic;
import rage.users.User;

/**
 *
 * @author rash4
 */
public class Rage {

    public static void main(String[] args) {
        System.out.println("Hello World!");
//        Test.write();
//        Test.read();
        Basic.initializeRootDir();
        System.out.println(Basic.DATA);
//        User.initUsers();
        ObjectMapper mpr = new ObjectMapper();
        
        mpr.registerModule(new JavaTimeModule());
        mpr.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            final var usr = mpr.readValue(Basic.DATA.resolve("users.json").toFile(), User[].class);
            System.out.println(Arrays.toString(usr));
        } catch (IOException ex) {
            System.getLogger(Rage.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
