package com.inami.todosimple.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = User.TABLE_NAME)
public class User {
    public  static  final String TABLE_NAME="user";

    private interface CreateUser{}
    private interface UpdateUser {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo username é obrigatório")
    @NotEmpty(groups = {CreateUser.class, UpdateUser.class})
    @Column(length = 60, nullable = false, unique = true)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(groups = {CreateUser.class, UpdateUser.class},message = "O campo de senha é obrigatório")
    @NotEmpty(groups = {CreateUser.class, UpdateUser.class}, message = "O campo de senha não pode estar em branco.")
    @Column(length = 255, nullable = false, unique = true)
    private String password;

    public List<Task> tasks = new ArrayList<>();

    public User(){}

    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return this.id;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;

    }
    public void setTasks(List<Task> tasks){
        this.tasks = tasks;
    }
    public List<Task> getTask(){
        return this.tasks;
    }

    @Override
    public boolean equals(Object obj) {

        if(obj == this){return true;}
        if (obj == null) return false;
        if(!(obj instanceof User)) return false;

        User otherUser = (User) obj;

        if(this.id == null){
            if(otherUser.id != null) {
                return false;
            } else if( !this.id.equals(otherUser.id)){
                return false;
            }
        }
        return Objects.equals(id, otherUser.id)
                && Objects.equals(username, otherUser.username)
                && Objects.equals(password, otherUser.password)
                && Objects.equals(tasks, otherUser.tasks);
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.getId() == null) ? 0: this.id.hashCode());
        return result;
    }
}
