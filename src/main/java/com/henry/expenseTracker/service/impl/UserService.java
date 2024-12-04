package com.henry.expenseTracker.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.henry.expenseTracker.Dto.request.UserRequestDto;
import com.henry.expenseTracker.Dto.response.UserResponseDto;
import com.henry.expenseTracker.entity.User;
import com.henry.expenseTracker.repository.UserRepository;
import com.henry.expenseTracker.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public UserService(UserRepository userRepository, ObjectMapper objectMapper){
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
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

    private UserResponseDto mapToDTO(User task) {
        return objectMapper.convertValue(task, UserResponseDto.class);
    }

    private User mapToEntity(UserRequestDto taskRequestDTO) {
        return objectMapper.convertValue(taskRequestDTO, User.class);
    }
}
