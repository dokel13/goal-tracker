package com.tracker.goal.service.impl;

import com.tracker.goal.domain.User;
import com.tracker.goal.entity.UserEntity;
import com.tracker.goal.exception.ServiceRuntimeException;
import com.tracker.goal.repository.UserRepository;
import com.tracker.goal.service.UserService;
import com.tracker.goal.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Optional.ofNullable;

@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final UserMapper mapper;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("email - " + email);
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            String message = "Login exception! User doesn`t exist!";
            log.info(message);
            throw new UsernameNotFoundException(message);
        }

        return mapper.mapDomainFromEntity(user);
    }

    @Transactional
    @Override
    public User register(User user) {
        ofNullable(userRepository.findByEmail(user.getEmail())).ifPresent(user1 -> {
            String message = "Registration exception! User already exists!";
            log.info(message);
            throw new ServiceRuntimeException(message);
        });
        user.setPassword(encoder.encode(user.getPassword()));

        return mapper.mapDomainFromEntity(userRepository.save(mapper.mapEntityFromDomain(user)));
    }

    @Override
    public User findById(String id) {
        UserEntity user = userRepository.findById(Integer.parseInt(id)).orElse(null);

        return mapper.mapDomainFromEntity(user);
    }
}
