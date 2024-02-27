package com.studentscheduleapp.databasetokenservice.api;

import com.studentscheduleapp.databasetokenservice.data.repositories.RefreshTokenRepository;
import com.studentscheduleapp.databasetokenservice.data.tablemodels.RefreshToken;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RefreshTokenController {

    private static final Logger log = LogManager.getLogger(RefreshTokenController.class);
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @GetMapping("${mapping.refresh.getByEmail}/{email}")
    public ResponseEntity<String> getById(@PathVariable("email") String email) {
        RefreshToken rt = refreshTokenRepository.findById(email).orElse(null);
        log.info("get refreshToken with email: " + email);
        return ResponseEntity.ok(rt == null ? null : rt.getToken());
    }

    @PostMapping("${mapping.refresh.save}")
    public ResponseEntity<Void> save(@RequestBody RefreshToken data) {
        if (data.getEmail() == null || data.getEmail().isEmpty()) {
            log.warn("bad request: refreshToken email is null or empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (data.getToken() == null || data.getToken().isEmpty()) {
            log.warn("bad request: refreshToken token is null or empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (data.getEmail().length() > 255) {
            log.warn("bad request: refreshToken email length > 255");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (data.getToken().length() > 255) {
            log.warn("bad request: refreshToken token length > 255");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        RefreshToken d = refreshTokenRepository.save(data);
        log.info("save refreshToken with email: " + d.getEmail());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("${mapping.refresh.delete}/{email}")
    public ResponseEntity<List<Void>> deleteById(@PathVariable("email") String email) {
        try {
            refreshTokenRepository.deleteById(email);
            log.info("delete refreshToken with email: " + email);
        } catch (EmptyResultDataAccessException e) {
            log.warn("delete refreshToken with email: " + email + " failed: entity not exists");
        }
        return ResponseEntity.ok().build();
    }
}
