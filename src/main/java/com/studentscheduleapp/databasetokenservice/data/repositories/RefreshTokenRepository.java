package com.studentscheduleapp.databasetokenservice.data.repositories;


import com.studentscheduleapp.databasetokenservice.data.tablemodels.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {

}
