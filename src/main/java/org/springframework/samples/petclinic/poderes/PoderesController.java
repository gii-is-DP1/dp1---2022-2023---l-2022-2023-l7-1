package org.springframework.samples.petclinic.poderes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PoderesController {
    
    PoderesService service;

    @Autowired
    public PoderesController(PoderesService service) {
        this.service = service;
    }
}
