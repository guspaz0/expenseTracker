package com.henry.expenseTracker.controller.api;

import com.henry.expenseTracker.service.abstract_service.ModifyUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/document/user")
@AllArgsConstructor
@Tag(name="User")
public class AppUserController {

    private final ModifyUserService modifyUserService;

    @Operation(summary = "Enable or disable document user")
    @PatchMapping("/enable-or-disable")
    public ResponseEntity<Map<String, Boolean>> enableOrDisable(@RequestParam String username) {
        return ResponseEntity.ok(this.modifyUserService.enabled(username));
    }

    @Operation(summary = "Add user Role")
    @PatchMapping("/add-role")
    public ResponseEntity<Map<String, Set<String>>> addRole(@RequestParam String username, @RequestParam String role) {
        return ResponseEntity.ok(this.modifyUserService.addRole(username, role));
    }

    @Operation(summary = "Remove user Role")
    @PatchMapping("/remove-role")
    public ResponseEntity<Map<String, Set<String>>> removeRole(@RequestParam String username, @RequestParam String role) {
        return ResponseEntity.ok(this.modifyUserService.removeRole(username, role));
    }
}
