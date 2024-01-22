package com.studentscheduleapp.databasetokenservice.api;

import com.studentscheduleapp.databasetokenservice.data.repositories.RefreshTokenRepository;
import com.studentscheduleapp.databasetokenservice.data.tablemodels.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/refresh")
public class RefreshTokenController {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @GetMapping("${mapping.refresh.getByEmail}/{email}")
    public ResponseEntity<String> getById(@PathVariable("email") String email){
        RefreshToken rt = refreshTokenRepository.findById(email).orElse(null);
        if (rt == null){
            Logger.getGlobal().info("get refresh token failed: not found token for email " + email);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Logger.getGlobal().info("get refresh token for email " + email + " success ");
        return ResponseEntity.ok(rt.getToken());
    }
    @PostMapping("${mapping.refresh.save}")
    public ResponseEntity<Void> save(@RequestBody RefreshToken data){
        if (data.getEmail() == null || data.getEmail().isEmpty()){
            Logger.getGlobal().info("bad request: email is null or empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (data.getToken() == null || data.getToken().isEmpty()){
            Logger.getGlobal().info("bad request: token is null or empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        refreshTokenRepository.save(data);
        Logger.getGlobal().info("save refresh token for email " + data.getEmail() + " success ");
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("${mapping.refresh.delete}/{email}")
    public ResponseEntity<List<Void>> deleteById(@PathVariable("email") String email){
        refreshTokenRepository.deleteById(email);
        Logger.getGlobal().info("delete refresh token for email " + email + " success ");
        return ResponseEntity.ok().build();
    }
}
