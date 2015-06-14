package com.timesheet.domain;

import com.timesheet.domain.enums.UserRoleEnum;

import javax.persistence.*;
import java.util.List;

/**
 * Created by vitaliy on 11.06.15.
 */
@Entity
@Table(name = "roles")
public class Role {

    private Long id;

    private String roleName;

    //private List<User> userList;

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "user_roles",
//            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
//    public List<User> getUserList() {
//        return userList;
//    }

//    public void setUserList(List<User> userList) {
//        this.userList = userList;
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
