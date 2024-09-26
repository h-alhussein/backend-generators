package com.dev.generators.textrepeater;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
@RestController
public class TextRepeaterController {
    @PostMapping(value = "/txt")
    public ResponseEntity<?> repeatText(@RequestBody TextDto textDto){
        if (Objects.equals(textDto.getText(), "")||textDto.getRepeater()<=1){
            return new ResponseEntity<>("Das ist invalide Eingabe",HttpStatus.BAD_REQUEST);
        }
        StringBuilder repeatedText= new StringBuilder();
        for (int i =1 ; i<textDto.getRepeater();i++){

         repeatedText.append(textDto.getText()).append("\n");
        }
        repeatedText.append(textDto.getText());
        return new ResponseEntity<>(repeatedText.toString(), HttpStatus.OK);
    }
}
