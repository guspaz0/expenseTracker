package com.henry.expenseTracker.controller.api;

import com.henry.expenseTracker.Dto.request.UserRequestDto;
import com.henry.expenseTracker.Dto.response.UserResponseDto;
import com.henry.expenseTracker.exceptions.ErrorsResponse;
import com.henry.expenseTracker.service.impl.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Users")
@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary="List all users")
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {

        return ResponseEntity.ok(userService.findAll());
    }

    @Operation(summary="Get user by id")
    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @Operation(summary="Delete user by id")
    @SneakyThrows
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok("User id: "+id+" Deleted Successfully");
    }

    @Operation(summary="Update user")
    @SneakyThrows
    @PutMapping
    public ResponseEntity<UserResponseDto> update(@RequestBody UserRequestDto user) {
        return ResponseEntity.ok(userService.update(user));
    }

    @Operation(summary="Create user")
    @ApiResponse(
            responseCode = "400",
            description = "when a field is missing or invalid we reponse this",
            content = {
                    @Content(mediaType = "application/json", schema=@Schema(implementation = ErrorsResponse.class))
            }
    )
    @PostMapping
    public ResponseEntity<UserResponseDto> save(@RequestBody UserRequestDto user) {
        return ResponseEntity.ok(userService.save(user));

    }
}
