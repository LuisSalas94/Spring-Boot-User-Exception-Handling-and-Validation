package net.javaguides.springboot.service.implementation;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.mapper.AutoUserMapper;
import net.javaguides.springboot.mapper.UserMapper;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {
        // Convert UserDto into USer JPA Entity
       //User user = UserMapper.mapToUser(userDto);
        // ModelMapping
        //User user = modelMapper.map(userDto, User.class);

        // Using MapStruct
        User user = AutoUserMapper.MAPPER.mapTouser(userDto);

        User savedUser = userRepository.save(user);
         // Convert User JPA entity to UserDto
        //UserDto saveduserDto = UserMapper.mapToUserDto(savedUser);
        // ModelMapping
        //UserDto saveduserDto = modelMapper.map(savedUser, UserDto.class);
        //MapStruct
        UserDto saveduserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);
        return saveduserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        //return UserMapper.mapToUserDto(user);
        // ModelMapping
        //return modelMapper.map(user, UserDto.class);
        // MapStruct
        return AutoUserMapper.MAPPER.mapToUserDto(optionalUser.get());
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for(User user: users) {
            // ModelMapper
            //UserDto userDto = modelMapper.map(user, UserDto.class);
            // MapStruct
            UserDto userDto = AutoUserMapper.MAPPER.mapToUserDto(user);
            userDtos.add(userDto);
        }
        return userDtos;
        //return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        // get user
        User existingUser = userRepository.findById(user.getId()).get();
        // update user
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        // save user
        User updatedUser = userRepository.save(existingUser);
        //return UserMapper.mapToUserDto(updatedUser);
        // ModelMapper
        //return modelMapper.map(updatedUser, UserDto.class);
        // MapStruct
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
