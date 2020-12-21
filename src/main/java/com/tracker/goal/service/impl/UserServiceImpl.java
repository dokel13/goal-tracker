package com.tracker.goal.service.impl;

import com.tracker.goal.domain.User;
import com.tracker.goal.entity.BadgeEntity;
import com.tracker.goal.entity.UserEntity;
import com.tracker.goal.exception.ServiceRuntimeException;
import com.tracker.goal.repository.BadgeRepository;
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

import java.util.List;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BadgeRepository badgeRepository;
    private final BCryptPasswordEncoder encoder;
    private final UserMapper mapper;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
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
    public List<String> geBadgesByUserId(Integer userId) {
        return badgeRepository.findAllByUser(userId)
                .stream().map(BadgeEntity::getLink).collect(toList());
    }

    @Override
    public void addFriend(Integer userId, Integer friendId) {
        UserEntity user = userRepository.findById(userId).get();
        List<UserEntity> friends = user.getFriends();
        UserEntity friend = userRepository.findById(friendId).get();
        boolean noneMatch = user.getFriends().stream().noneMatch(userEntity -> userEntity.equals(friend));
        if (noneMatch && !userId.equals(friendId)) {
            friends.add(friend);
            user.setFriends(friends);
            userRepository.save(user);
        } else {
            throw new ServiceRuntimeException("Cannot add friend!!!");
        }
    }
}
