/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rage.roots;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author rash4
 */
public final class Basic {
    private Basic(){}
    public static final String NAME = "Rage";
    public static final Path DATA = Path.of(
            System.getProperty("user.home"),
            "AppData", "Local", NAME
    );
    public static Path resolve(String... paths) {
        Path result = DATA.resolve(Path.of("", paths)).normalize();
        if (!result.startsWith(DATA.normalize()))
            throw new IllegalArgumentException("Path escapes DATA directory!");
        return result;
    }
    public static void initializeRootDir(){
        try {
            Files.createDirectories(DATA);
        } catch (IOException ex) {
            System.getLogger(Basic.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
