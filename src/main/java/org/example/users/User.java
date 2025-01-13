package org.example.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Bot_State;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long chatId;
    private Integer SAT_SCORE;
    private Double IELTS_SCORE;
    private Bot_State State;
    private Double GPA;
    private String status_of_classes;
    private String major;
    private String country;
    private String scholarship;

}
