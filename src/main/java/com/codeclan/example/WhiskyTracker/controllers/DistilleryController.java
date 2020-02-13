package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository.DistilleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DistilleryController {

    @Autowired
    private DistilleryRepository distilleryRepository;

//    @GetMapping(value = "/distilleries")
//    public ResponseEntity<List<Distillery>> getAllDistilleries() {
//        return new ResponseEntity<>(distilleryRepository.findAll(), HttpStatus.OK);
//    }

    @GetMapping(value = "/distilleries/{id}")
    public ResponseEntity getDistillery(@PathVariable Long id) {
        return new ResponseEntity(distilleryRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/distilleries/")
    public ResponseEntity<Distillery> createDistillery(@RequestBody Distillery distillery) {
        distilleryRepository.save(distillery);
        return new ResponseEntity<>(distillery, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/distilleries/{id}")
    public ResponseEntity<Distillery> updateDistillery(@RequestBody Distillery distillery, @PathVariable Long id) {
        distilleryRepository.save(distillery);
        return new ResponseEntity<>(distillery, HttpStatus.CREATED);
    }

//    @GetMapping(value = "/distilleries/region")
//    public ResponseEntity<List<Distillery>> getDistilleryByRegion(@RequestParam(name="named")String region){
//        List<Distillery> distilleries = distilleryRepository.findByRegion(region);
//        return new ResponseEntity<>(distilleries, HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/distilleries/whisky")
////    public ResponseEntity<List<Distillery>> getDistilleriesByWhiskyAge(@RequestParam(name="age")int age){
////        List<Distillery> distilleries = distilleryRepository.findByWhiskiesAgeGreaterThan(age);
////        return new ResponseEntity<>(distilleries, HttpStatus.OK);
////    }

    @GetMapping(value = "/distilleries")
    public ResponseEntity<List<Distillery>> getDistilleriesByWhiskyAge(
            @RequestParam(required = false, name = "age") Integer age,
            @RequestParam(required = false, name = "region") String region
    ) {
        List<Distillery> distilleries = null;

        if (region != null) {
             distilleries = distilleryRepository.findByRegion(region);
            return new ResponseEntity<>(distilleries, HttpStatus.OK);
        }
        if (age != null) {
            distilleries = distilleryRepository.findByWhiskiesAgeGreaterThan(age);
            return new ResponseEntity<>(distilleries, HttpStatus.OK);
        }
        else {
            distilleries = distilleryRepository.findAll();
        }
        return new ResponseEntity<>(distilleries, HttpStatus.OK);
    }


}
