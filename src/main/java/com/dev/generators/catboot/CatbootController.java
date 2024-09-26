package com.dev.generators.catboot;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class CatbootController {

    private final CatbootService catbootService;
    @PostMapping(value = "/cat")
public ResponseEntity<?> catboot(){

    return new ResponseEntity<>(catbootService.catRepeat(), HttpStatus.OK);
}

}
