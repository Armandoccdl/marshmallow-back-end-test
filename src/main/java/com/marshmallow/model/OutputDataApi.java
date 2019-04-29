package com.marshmallow.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OutputDataApi {

    @Size(min = 2, max = 2, message = "The list must only contain 2 elements")
    private List<Integer> finalPosition;

    private int oilPatchesCleaned;
}
