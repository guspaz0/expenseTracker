package com.henry.expenseTracker.service.impl;

import com.henry.expenseTracker.Dto.request.UserRequestDto;
import com.henry.expenseTracker.Dto.response.UserResponseDto;
import com.henry.expenseTracker.entity.jpa.User;
import com.henry.expenseTracker.infrastructure.helpers.ApiCountriesConnectorHelper;
import com.henry.expenseTracker.repository.jpa.UserRepository;
import com.henry.expenseTracker.service.abstract_service.IUserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final ApiCountriesConnectorHelper apiCountries;

    @Override
    public List<UserResponseDto> findAll() {
        return this.userRepository.findAll()
                .stream().map(this::mapToDTO)
                .toList();
    }

    @Override
    public UserResponseDto save(UserRequestDto user) {
        return mapToDTO(this.userRepository.save(mapToEntity(user)));
    }

    @Override
    public UserResponseDto findById(Long id) throws Exception {
            return mapToDTO(this.userRepository.findById(id)
                    .orElseThrow(()-> new Exception("User id: "+id+" not found"))
            );
    }

    @Override
    public String delete(Long id) throws Exception {
        this.findById(id);
        this.userRepository.deleteById(id);
        return "User id: "+id+" deleted successfully";
    }

    @Override
    public UserResponseDto update(UserRequestDto user) throws Exception {
        this.userRepository.findById(user.getId())
                .orElseThrow(()-> new Exception("User id: "+user.getId()+" not found"));
        return mapToDTO(this.userRepository.save(mapToEntity(user)));
    }
    @SneakyThrows
    @Override
    public Optional<UserResponseDto> login(String email, String password){
        Optional<User> user = this.userRepository.findByEmail(email);
        if (user.isPresent()) {
            if(Objects.equals(user.get().getPassword(), password)) {
                return Optional.of(mapToDTO(user.get()));
            }
        }
        return Optional.empty();
    }

    public Map<String, List<?>> getCountries(){
        return this.apiCountries.getCountries();
    }

    private UserResponseDto mapToDTO(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .country(user.getCountry())
                .currency(user.getCurrency())
                .userRole(user.getUserRole())
                .build();
    }

    private User mapToEntity(UserRequestDto userRequestDTO) {
        return User.builder()
                .id(userRequestDTO.getId())
                .name(userRequestDTO.getName())
                .email(userRequestDTO.getEmail())
                .password(userRequestDTO.getPassword())
                .country(userRequestDTO.getCountry())
                .currency(userRequestDTO.getCurrency())
                .userRole(userRequestDTO.getUserRole())
                .build();
    }
}
