package com.schbus;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ReserveDto {
    private String user;
    private String bus;
    private String date;
    private String seat;

}
