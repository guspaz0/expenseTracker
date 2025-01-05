package com.henry.expenseTracker.service.abstract_service;

import com.henry.expenseTracker.Dto.request.UserRequestDto;
import com.henry.expenseTracker.Dto.response.UserResponseDto;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<UserResponseDto> findAll();

    UserResponseDto save(UserRequestDto user);

    UserResponseDto findById(Long id) throws Exception;

    String delete(Long id) throws Exception;

    UserResponseDto update(UserRequestDto user) throws Exception;

    Optional<UserResponseDto> login(String email, String password);
}
