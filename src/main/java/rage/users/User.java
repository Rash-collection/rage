/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rage.users;

import java.time.Instant;

/**
 *
 * @author rash4
 */
public record User(String username, String password, Instant since)
        implements Comparable<User>{
    public User(String username, String password, Instant since){
        this.username = username;
        this.password = password;
        this.since = since;
    }
    public User(String username, String password){
        this(username, password, Instant.now());
    }
    @Override public int compareTo(User o) {
        return this.username.compareTo(o.username);
    }
}
