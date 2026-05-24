package com.sbnz.model.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fact {
    private String goal;
    private String requirement;
    private String level;
    private String explanation;
}
