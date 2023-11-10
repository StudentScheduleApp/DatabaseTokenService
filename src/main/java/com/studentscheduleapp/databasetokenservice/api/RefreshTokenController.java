package com.studentscheduleapp.databasetokenservice.api;

import com.studentscheduleapp.databasetokenservice.data.repositories.RefreshTokenRepository;
import com.studentscheduleapp.databasetokenservice.data.tablemodels.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/refresh")
public class RefreshTokenController {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @GetMapping("email/{email}")
    public ResponseEntity<String> getById(@PathVariable("email") String email){
        RefreshToken rt = refreshTokenRepository.findById(email).orElse(null);
        return rt != null ? ResponseEntity.ok(rt.getToken()) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PostMapping("save")
    public ResponseEntity<Void> save(@RequestBody RefreshToken data){
        refreshTokenRepository.save(data);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("delete/{email}")
    public ResponseEntity<List<Void>> deleteById(@PathVariable("email") String email){
        refreshTokenRepository.deleteById(email);
        return ResponseEntity.ok().build();
    }
}
