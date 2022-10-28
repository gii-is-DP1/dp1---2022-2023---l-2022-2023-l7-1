package org.springframework.samples.kingdomMaps.partida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PartidaController {
    
    private PartidaService service;

    @Autowired
    public PartidaController(PartidaService service) {
        this.service=service;
    }
}
