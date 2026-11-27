/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rage.users;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;
import javax.imageio.ImageIO;
import static rage.roots.Basic.DATA;

/**
 *
 * @author rash4
 */
@JsonPropertyOrder({
    "username",
    "password",
    "avatarPath",
    "since"
})
public class User implements Comparable<User>{
    public static final Path GUEST_AVATAR = Path.of("Media", "Avatars", "guest.jpg");
    private String username;
    private String password;
    private Path avatarPath;
    private Instant since;
    @JsonIgnore
    private BufferedImage avatar;
    public User(){}
    public User(String username, String password, Path avatar, Instant since){
        this.username = username;
        this.password = password;
        this.since = since;
        this.avatarPath = avatar;
        try {
            this.avatar = ImageIO.read(DATA.resolve(this.avatarPath).toFile());
        } catch (IOException ex) {
            System.getLogger(User.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    public User(String username, String password){
        this(username, password, GUEST_AVATAR, Instant.now());
    }
    @JsonCreator
    public User(
        @JsonProperty("username") String username,
        @JsonProperty("password") String password,
        @JsonProperty("avatarPath") String avatarPath,
        @JsonProperty("since") Instant since
    ) {
        this(username, password, Path.of(avatarPath), since);
    }
    @Override public int compareTo(User o) {
        return this.username.compareTo(o.username);
    }
    public static void initUsers(){
        final var usry = new User[]{
            new User("Guest", "guest"),
            new User("Rash", "rash4"),
            new User("Imo", "IMadd"),
            new User("ZYX", "xyz"),
        };
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(DATA.resolve("users.json").toFile(), usry);
//            String json = mapper
//                    .writerWithDefaultPrettyPrinter()
//                    .writeValueAsString(usry);
//            System.out.println(json);
        } catch (JsonProcessingException ex) {
            System.getLogger(User.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex) {
            System.getLogger(User.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @JsonProperty("avatarPath")
    public String getAvatarPathJson() {
        return avatarPath.toString().replace("\\", "/");
    }
    @JsonIgnore
    public Path getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(Path avatarPath) {
        this.avatarPath = avatarPath;
    }

    public Instant getSince() {
        return since;
    }

    public void setSince(Instant since) {
        this.since = since;
    }
    @JsonIgnore
    public BufferedImage getAvatar() {
        return avatar;
    }
    @JsonIgnore
    public void setAvatar(BufferedImage avatar) {
        this.avatar = avatar;
    }
    @Override public String toString(){
        return ("{username=\'%s\', password=\'%s\', avatarPath=\'%s\', since=\'%s\'}")
                .formatted(this.username, this.password, this.avatarPath, this.since);
    }
}
