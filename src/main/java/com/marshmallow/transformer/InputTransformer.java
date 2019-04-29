package com.marshmallow.transformer;

import com.marshmallow.model.Coordinates;
import com.marshmallow.model.InputData;
import com.marshmallow.model.InputDataApi;

import java.util.ArrayList;
import java.util.List;

public class InputTransformer {

    public static InputData fromApiToInputData(InputDataApi inputDataApi) {
        InputData inputData = new InputData();

        inputData.setAreaSize(new Coordinates(inputDataApi.getAreaSize().get(0), inputDataApi.getAreaSize().get(1)));
        inputData.setStartingPosition(new Coordinates(inputDataApi.getStartingPosition().get(0), inputDataApi.getStartingPosition().get(1)));
        inputData.setNavigationInstructions(inputDataApi.getNavigationInstructions());
        inputData.setOilPatches(parseOilPatches(inputDataApi.getOilPatches()));

        return inputData;
    }

    private static List<Coordinates> parseOilPatches(List<List<Integer>> oilPatches) {
        List<Coordinates> oilPatchesList = new ArrayList<>();

        oilPatches.stream()
                .filter(integers -> integers.size() == 2)
                .forEach(integers -> oilPatchesList.add(
                        new Coordinates(integers.get(0), integers.get(1)
                        )));

        return oilPatchesList;
    }

}
