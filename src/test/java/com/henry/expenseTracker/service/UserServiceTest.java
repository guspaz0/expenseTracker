package com.henry.expenseTracker.service;

import com.henry.expenseTracker.Dto.request.UserRequestDto;
import com.henry.expenseTracker.Dto.response.UserResponseDto;
import com.henry.expenseTracker.entity.jpa.User;
import com.henry.expenseTracker.repository.jpa.UserRepository;
import com.henry.expenseTracker.service.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserRequestDto sampleUserRequest;

    @BeforeEach
    void setUp(){
        sampleUserRequest = UserRequestDto.builder()
                .name("Jhon Doe")
                .email("jhon.doe@asd.com")
                .password("1234")
                .build();
    }
    
    @DisplayName("List all users test")
    @Test
    public void testFindAll(){
        //given
        User sampleUser2 = User.builder()
                .id(1L)
                .name("Juan Carlos")
                .email("juan.carlos@gmail.com")
                .password("1234")
                .build();
        User sampleUser3 = User.builder()
                .id(2L)
                .name("Seba Perez")
                .email("seba.perezs@gmail.com")
                .password("1234")
                .build();
        given(userRepository.findAll())
                .willReturn(List.of(sampleUser2,sampleUser3));
        
        //when
        List<UserResponseDto> foundList = userService.findAll();
        
        //then
        assertThat(foundList).isNotNull();
        assertThat(foundList.size()).isEqualTo(2);
    }

    @DisplayName("Save user test")
    @Test
    public void testSaveUser() throws Exception {
        User sampleUser2 = User.builder()
                .id(1L)
                .name("Jhon Doe")
                .email("jhon.doe@asd.com")
                .password("1234")
                .build();
        //when
        when(userRepository.save(any(User.class))).thenReturn(sampleUser2);
        userService.save(sampleUserRequest);
        //then
        verify(userRepository,times(1)).save(any(User.class));
    }
}
