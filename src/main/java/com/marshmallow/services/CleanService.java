package com.marshmallow.services;

import com.marshmallow.exception.BadRequestException;
import com.marshmallow.exception.OutOfTheMapException;
import com.marshmallow.model.*;
import com.marshmallow.transformer.InputTransformer;
import com.marshmallow.transformer.OutputTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CleanService {

    /**
     * In this method we execute the main functionality of this program.
     *
     * @param inputDataApi
     * @return
     */
    public OutputDataApi clean(InputDataApi inputDataApi) {
        List<Coordinates> path = new ArrayList<>();
        InputData inputData = InputTransformer.fromApiToInputData(inputDataApi);
        OutputData outputData = new OutputData(inputData.getStartingPosition(), 0);

        String[] navigationsOrders = inputData.getNavigationInstructions().split("");

        for (String navigationsOrder : navigationsOrders) {
            moveCleaner(inputData, navigationsOrder, outputData, path);
        }

        outputData.setOilPatchesCleaned(checkOilPatch(path, inputData.getOilPatches()));
        return OutputTransformer.fromOutputToApi(outputData);
    }

    /**
     * All the logic of the movement and the calls to the check is made here
     *
     * @param inputData
     * @param navigation
     * @param outputData
     * @param path
     */
    private void moveCleaner(InputData inputData, String navigation, OutputData outputData, List<Coordinates> path) {
        Navigation navigationObject;
        int mapLimitX = inputData.getAreaSize().getX();
        int mapLimitY = inputData.getAreaSize().getY();
        int actualPositionX = outputData.getFinalPosition().getX();
        int actualPositionY = outputData.getFinalPosition().getY();

        try {
            navigationObject = Navigation.valueOf(navigation);
        } catch (IllegalArgumentException e) {
            log.error("Navigation [{}] must be a valid parameter", navigation, e);
            throw new BadRequestException(String.format("Navigation [{%s}] must be a valid parameter", navigation));
        }

        if (Navigation.N.equals(navigationObject)) {
            actualPositionY++;
            checkMapLimit(actualPositionY, mapLimitY);
            outputData.getFinalPosition().setY(actualPositionY);
        } else if (Navigation.E.equals(navigationObject)) {
            actualPositionX++;
            checkMapLimit(actualPositionX, mapLimitX);
            outputData.getFinalPosition().setX(actualPositionX);
        } else if (Navigation.S.equals(navigationObject)) {
            actualPositionY--;
            checkMapLimit(actualPositionY, mapLimitY);
            outputData.getFinalPosition().setY(actualPositionY);
        } else {
            actualPositionX--;
            checkMapLimit(actualPositionX, mapLimitX);
            outputData.getFinalPosition().setX(actualPositionX);
        }

        // We save the path, this might be useful in the future and also is better performance to
        // check the oil at the end with the path that checking every time the cleaner move.
        path.add(new Coordinates(actualPositionX, actualPositionY));
    }

    /**
     * We check that the cleaner does not go outside the map
     *
     * @param newPosition
     * @param limit
     */
    private void checkMapLimit(int newPosition, int limit) {
        if (newPosition > limit || newPosition < 0) {
            log.error("The new position is out of the designated Area");
            throw new OutOfTheMapException("The new position is out of the designated Area");
        }
    }

    /**
     * We check how many oil patches were cleaned.
     *
     * @param path
     * @param oilPatches
     * @return
     */
    private int checkOilPatch(List<Coordinates> path, List<Coordinates> oilPatches) {

        return Math.toIntExact(oilPatches.stream()
                .filter(coordinates -> path.stream()
                        .anyMatch(coordinates1 ->
                                coordinatesComparator(coordinates1, coordinates)))
                .count());
    }

    /**
     * We compare to coordinates to determine if they are equals.
     *
     * @param a
     * @param b
     * @return
     */
    private boolean coordinatesComparator(Coordinates a, Coordinates b) {
        return a.getX() == b.getX() && a.getY() == b.getY();
    }

}
