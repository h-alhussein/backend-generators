package com.dev.generators.weatherApp.applicarion;


import com.dev.generators.weatherApp.domain.WeatherResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;

@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
@RestController
public class WeatherAppController {
    @Value("${STRIPE_SECRET_KEY}")
    private String apikey;
    @GetMapping(value = "/a")
    public String getit(){
        return "success";
    }

    @PostMapping(value = "/weather")
    public ResponseEntity<?> weather(@RequestBody WeatherDto weatherDto){
        if (Objects.equals(weatherDto.getCity(), "")){
            return new ResponseEntity<>("die Stadt darf nicht leer sein", HttpStatus.CONFLICT);
        }
        String url="https://api.openweathermap.org/data/2.5/weather?q="+weatherDto.getCity()+"&units=matric&APPID="+apikey;
        RestTemplate restTemplate = new RestTemplate();





            try {
                WeatherResponse weatherResponse = restTemplate.getForObject(url, WeatherResponse.class);
                return new ResponseEntity<>(weatherResponse, HttpStatus.OK);
            }catch (HttpClientErrorException.NotFound exception){;
                return new ResponseEntity<>("Stadt nicht zu finden",HttpStatus.NOT_FOUND);

            }

        }

}
