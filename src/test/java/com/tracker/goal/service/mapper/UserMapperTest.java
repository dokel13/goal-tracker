package com.tracker.goal.service.mapper;

import com.tracker.goal.domain.Role;
import com.tracker.goal.domain.User;
import com.tracker.goal.entity.UserEntity;
import org.junit.Test;

import static com.tracker.goal.domain.User.builder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

public class UserMapperTest {

    private final UserMapper mapper = new UserMapper();

    private final User domain = builder()
            .id(0)
            .role(Role.USER)
            .email("email")
            .password("password")
            .name("name")
            .build();


    private final UserEntity entity = new UserEntity(
            "email",
            "password",
            "name",
            "USER");

    @Test
    public void mapDomainFromEntityShouldReturnDomain() {
        assertThat(mapper.mapDomainFromEntity(entity), is(domain));
    }

    @Test
    public void mapDomainFromEntityShouldReturnNull() {
        assertThat(mapper.mapDomainFromEntity(null), is(nullValue()));
    }

    @Test
    public void mapEntityFromDomainShouldReturnEntity() {
        assertThat(mapper.mapEntityFromDomain(domain), is(entity));
    }

    @Test
    public void mapEntityFromDomainShouldReturnNull() {
        assertThat(mapper.mapEntityFromDomain(null), is(nullValue()));
    }
}
