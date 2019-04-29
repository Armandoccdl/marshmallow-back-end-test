package com.marshmallow.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class InputDataApi {

    @NotEmpty
    @Size(min = 2, max = 2, message = "The list must only contain 2 elements")
    private List<Integer> areaSize;

    @NotEmpty
    @Size(min = 2, max = 2, message = "The list must only contain 2 elements")
    private List<Integer> startingPosition;

    @NotEmpty
    private List<List<Integer>> oilPatches;

    @NotEmpty
    private String navigationInstructions;

}
