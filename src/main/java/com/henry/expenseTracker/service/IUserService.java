package com.henry.expenseTracker.service;

import com.henry.expenseTracker.Dto.request.UserRequestDto;
import com.henry.expenseTracker.Dto.response.UserResponseDto;

import java.util.List;

public interface IUserService {

    List<UserResponseDto> findAll();

    UserResponseDto save(UserRequestDto user);

    UserResponseDto findById(Long id) throws Exception;

    String delete(Long id) throws Exception;

    UserResponseDto update(UserRequestDto user) throws Exception;
}
