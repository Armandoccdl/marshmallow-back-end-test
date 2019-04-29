package com.marshmallow.transformer;

import com.marshmallow.model.OutputData;
import com.marshmallow.model.OutputDataApi;

import java.util.ArrayList;
import java.util.List;

public class OutputTransformer {

    public static OutputDataApi fromOutputToApi(OutputData outputData) {
        List<Integer> finalPosition = new ArrayList<>();
        finalPosition.add(outputData.getFinalPosition().getX());
        finalPosition.add(outputData.getFinalPosition().getY());

        OutputDataApi outputDataApi = new OutputDataApi();
        outputDataApi.setFinalPosition(finalPosition);
        outputDataApi.setOilPatchesCleaned(outputData.getOilPatchesCleaned());
        return outputDataApi;
    }

}
