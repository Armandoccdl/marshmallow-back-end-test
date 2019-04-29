package com.marshmallow.controller;

import com.marshmallow.model.InputDataApi;
import com.marshmallow.model.OutputDataApi;
import com.marshmallow.services.CleanService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CleanController {

    private final CleanService cleanService;

    public CleanController(CleanService cleanService) {
        this.cleanService = cleanService;
    }

    /**
     * Main Endpoint of this problem, call using http://localhost:8080/clean
     *
     * @param inputDataApi
     * @return OutputDataApi
     */
    @RequestMapping(value = "/clean", method = RequestMethod.POST)
    public OutputDataApi clean(@RequestBody InputDataApi inputDataApi) {
        return cleanService.clean(inputDataApi);
    }

}
