package com.studentscheduleapp.databasetokenservice.data.tablemodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "refresh_tokens")
public class RefreshToken {

    @Id
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "token", nullable = false)
    private String token;


}
