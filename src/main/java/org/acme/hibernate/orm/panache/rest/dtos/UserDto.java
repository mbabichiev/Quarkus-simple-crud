package org.acme.hibernate.orm.panache.rest.dtos;

import org.acme.hibernate.orm.panache.rest.models.User;

public class UserDto {

    public Long id;
    public String login;
    public String token;

    public UserDto(User user) {
        id = user.id;
        login = user.login;
        token = user.token;
    }
}
