package com.marshmallow.services;

import com.marshmallow.exception.BadRequestException;
import com.marshmallow.exception.OutOfTheMapException;
import com.marshmallow.model.InputDataApi;
import com.marshmallow.model.OutputDataApi;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class CleanServicesTest {

    @InjectMocks
    @Spy
    private CleanService cleanService;

    @Before
    public void createMocks() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void callMethodAllOk() {
        List<Integer> areaSize = new ArrayList<>();
        List<Integer> startingPosition = new ArrayList<>();
        List<List<Integer>> oils = new ArrayList<>();
        List<Integer> oil1 = new ArrayList<>();
        List<Integer> oil2 = new ArrayList<>();
        List<Integer> oil3 = new ArrayList<>();

        areaSize.add(5);
        areaSize.add(5);
        startingPosition.add(1);
        startingPosition.add(2);

        oil1.add(1);
        oil1.add(0);
        oil2.add(2);
        oil2.add(2);
        oil3.add(2);
        oil3.add(3);
        oils.add(oil1);
        oils.add(oil2);
        oils.add(oil3);

        InputDataApi testData = new InputDataApi();
        testData.setAreaSize(areaSize);
        testData.setStartingPosition(startingPosition);
        testData.setOilPatches(oils);
        testData.setNavigationInstructions("NNESEESWNWW");

        OutputDataApi outputDataApi = cleanService.clean(testData);

        assertNotNull(outputDataApi);
        assertEquals(1, outputDataApi.getOilPatchesCleaned());
        assertEquals(1, (int) outputDataApi.getFinalPosition().get(0));
        assertEquals(3, (int) outputDataApi.getFinalPosition().get(1));
    }

    @Test(expected = OutOfTheMapException.class)
    public void callMethodMapLimit() {
        List<Integer> areaSize = new ArrayList<>();
        List<Integer> startingPosition = new ArrayList<>();
        List<List<Integer>> oils = new ArrayList<>();
        List<Integer> oil1 = new ArrayList<>();
        List<Integer> oil2 = new ArrayList<>();
        List<Integer> oil3 = new ArrayList<>();

        areaSize.add(1);
        areaSize.add(1);
        startingPosition.add(1);
        startingPosition.add(2);

        oil1.add(1);
        oil1.add(0);
        oil2.add(2);
        oil2.add(2);
        oil3.add(2);
        oil3.add(3);
        oils.add(oil1);
        oils.add(oil2);
        oils.add(oil3);

        InputDataApi testData = new InputDataApi();
        testData.setAreaSize(areaSize);
        testData.setStartingPosition(startingPosition);
        testData.setOilPatches(oils);
        testData.setNavigationInstructions("NNESEESWNWW");

        cleanService.clean(testData);
    }

    @Test(expected = BadRequestException.class)
    public void callMethodInvalidNavigation() {
        List<Integer> areaSize = new ArrayList<>();
        List<Integer> startingPosition = new ArrayList<>();
        List<List<Integer>> oils = new ArrayList<>();
        List<Integer> oil1 = new ArrayList<>();
        List<Integer> oil2 = new ArrayList<>();
        List<Integer> oil3 = new ArrayList<>();

        areaSize.add(4);
        areaSize.add(4);
        startingPosition.add(1);
        startingPosition.add(2);

        oil1.add(1);
        oil1.add(0);
        oil2.add(2);
        oil2.add(2);
        oil3.add(2);
        oil3.add(3);
        oils.add(oil1);
        oils.add(oil2);
        oils.add(oil3);

        InputDataApi testData = new InputDataApi();
        testData.setAreaSize(areaSize);
        testData.setStartingPosition(startingPosition);
        testData.setOilPatches(oils);
        testData.setNavigationInstructions("XNNESEESWNWW");

        cleanService.clean(testData);
    }
}
