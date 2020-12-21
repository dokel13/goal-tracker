package com.tracker.goal.service;


import com.tracker.goal.domain.Role;
import com.tracker.goal.domain.User;
import com.tracker.goal.entity.UserEntity;
import com.tracker.goal.exception.ServiceRuntimeException;
import com.tracker.goal.repository.UserRepository;
import com.tracker.goal.service.impl.UserServiceImpl;
import com.tracker.goal.service.mapper.UserMapper;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.rules.ExpectedException.none;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder encoder;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Rule
    public ExpectedException expectedException = none();

    private final User enteringUser = User.builder()
            .name("name")
            .email("email@mail.ua")
            .password("password")
            .role(Role.USER)
            .build();

    private final UserEntity existingUser = new UserEntity(
            "email@mail.ua",
            "password",
            "name",
            "USER");

    @Test
    public void loadUserByUsernameShouldReturnUser() {
        when(userRepository.findByEmail(enteringUser.getEmail())).thenReturn(existingUser);
        when(userMapper.mapDomainFromEntity(existingUser)).thenReturn(enteringUser);
        assertThat(userService.loadUserByUsername(enteringUser.getEmail()), is(enteringUser));

        verify(userRepository).findByEmail(enteringUser.getEmail());
        verify(userMapper).mapDomainFromEntity(existingUser);
    }

    @Test
    public void loadUserByUsernameShouldThrowException() {
        expectedException.expect(UsernameNotFoundException.class);
        expectedException.expectMessage("Login exception! User doesn`t exist!");
        userService.loadUserByUsername(enteringUser.getEmail());

        verify(userRepository).findByEmail(enteringUser.getEmail());
    }

    @Test
    public void registerShouldReturnUser() {
        when(userRepository.save(existingUser)).thenReturn(existingUser);
        when(userMapper.mapDomainFromEntity(existingUser)).thenReturn(enteringUser);
        when(userMapper.mapEntityFromDomain(enteringUser)).thenReturn(existingUser);
        when(encoder.encode(enteringUser.getPassword())).thenReturn(enteringUser.getPassword());
        assertThat(userService.register(enteringUser), is(enteringUser));

        verify(userRepository).findByEmail(enteringUser.getEmail());
        verify(userRepository).save(any(UserEntity.class));
        verify(userMapper).mapDomainFromEntity(existingUser);
        verify(userMapper).mapEntityFromDomain(enteringUser);
        verify(encoder).encode(enteringUser.getPassword());

    }

    @Test
    public void registerShouldThrowException() {
        expectedException.expect(ServiceRuntimeException.class);
        expectedException.expectMessage("Registration exception! User already exists!");
        when(userRepository.findByEmail(enteringUser.getEmail())).thenReturn(existingUser);
        userService.register(enteringUser);

        verify(userRepository).findByEmail(enteringUser.getEmail());
    }
}
