package com.henry.expenseTracker.service.impl;

import com.henry.expenseTracker.Dto.request.UserRequestDto;
import com.henry.expenseTracker.Dto.response.UserResponseDto;
import com.henry.expenseTracker.entity.User;
import com.henry.expenseTracker.repository.UserRepository;
import com.henry.expenseTracker.service.IUserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream().map(this::mapToDTO)
                .toList();
    }

    @Override
    public UserResponseDto save(UserRequestDto user) {
        return mapToDTO(userRepository.save(mapToEntity(user)));
    }

    @Override
    public UserResponseDto findById(Long id) throws Exception {
            return mapToDTO(userRepository.findById(id)
                    .orElseThrow(()-> new Exception("User id: "+id+" not found"))
            );
    }

    @Override
    public String delete(Long id) throws Exception {
        this.findById(id);
        userRepository.deleteById(id);
        return "User id: "+id+" deleted successfully";
    }

    @Override
    public UserResponseDto update(UserRequestDto user) throws Exception {
        userRepository.findById(user.getId())
                .orElseThrow(()-> new Exception("User id: "+user.getId()+" not found"));
        return mapToDTO(userRepository.save(mapToEntity(user)));
    }
    @SneakyThrows
    @Override
    public Optional<UserResponseDto> login(String email, String password){
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            if(Objects.equals(user.get().getPassword(), password)) {
                return Optional.of(mapToDTO(user.get()));
            }
        }
        return Optional.empty();
    }

    private UserResponseDto mapToDTO(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    private User mapToEntity(UserRequestDto userRequestDTO) {
        return User.builder()
                .id(userRequestDTO.getId())
                .name(userRequestDTO.getName())
                .email(userRequestDTO.getEmail())
                .password(userRequestDTO.getPassword())
                .build();
    }
}
