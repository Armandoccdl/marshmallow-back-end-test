package com.marshmallow.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class InputData {

    @NotEmpty
    private Coordinates areaSize;

    @NotEmpty
    private Coordinates startingPosition;

    @NotEmpty
    private List<Coordinates> oilPatches;

    @NotEmpty
    private String navigationInstructions;

}
