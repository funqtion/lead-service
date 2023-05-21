package com.dakani.leadService.boundary.rest;

import com.dakani.leadService.error.ExpectedError;
import com.dakani.leadService.model.AnimalLeadCreateDto;
import com.dakani.leadService.model.HouseLeadCreateDto;
import com.dakani.leadService.model.TeethLeadCreateDto;
import com.dakani.leadService.persistence.entity.AnimalLead;
import com.dakani.leadService.persistence.entity.HouseLead;
import com.dakani.leadService.persistence.entity.TeethLead;
import com.dakani.leadService.service.FunnelLeadService;
import com.dakani.leadService.service.TokenService;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j(topic = "leadservice")
public class FunnelLeadController {
    private final FunnelLeadService funnelLeadService;
    private final TokenService tokenService;

    @CrossOrigin(origins = "https://tier.guen-versicherung.de")
    @PostMapping("/funnels/animal-leads")
    public ResponseEntity<?> createAnimalLead(
//            @RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
            @RequestBody AnimalLeadCreateDto animalLeadRequest
    ) {
        log.info("received an Animal lead create Request");
//        if(!tokenService.isTokenValid("/funnels", auth)){
//        log.info("The Request didn't have a valid Token {}", auth);
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("403 Forbidden");
//        }

        Either<ExpectedError, AnimalLead> maybeAnimalLead = funnelLeadService.addAnimalLead(animalLeadRequest);
        if (maybeAnimalLead.isLeft()) {
            log.warn("Error creating animal lead");
            return ResponseEntity.badRequest().body(maybeAnimalLead.getLeft().getMessage());
        }

        log.info("Created animal lead with id {}", maybeAnimalLead.get().getId());

        return ResponseEntity.ok(maybeAnimalLead.get());
    }

    @CrossOrigin(origins = "https://whg.guen-versicherung.de")
    @PostMapping("/funnels/house-leads")
    public ResponseEntity<?> createHouseLead(
//            @RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
            @RequestBody HouseLeadCreateDto houseLeadRequest
    ) {
        log.info("received a House lead create Request");
//        if(!tokenService.isTokenValid("/funnels", auth)){
//        log.info("The Request didn't have a valid Token {}", auth);
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("403 Forbidden");
//        }

        Either<ExpectedError, HouseLead> maybeHouseLead = funnelLeadService.addHouseLead(houseLeadRequest);
        if (maybeHouseLead.isLeft()) {
            log.warn("Error creating house lead");
            return ResponseEntity.badRequest().body(maybeHouseLead.getLeft().getMessage());
        }

        log.info("Created house lead with id {}", maybeHouseLead.get().getId());

        return ResponseEntity.ok(maybeHouseLead.get());
    }

    @CrossOrigin(origins = "https://zahn.guen-versicherung.de")
    @PostMapping("/funnels/teeth-leads")
    public ResponseEntity<?> createTeethLead(
//            @RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
            @RequestBody TeethLeadCreateDto teethLeadRequest
    ) {
        log.info("received a Teeth lead create Request");
//        if(!tokenService.isTokenValid("/funnels", auth)){
//        log.info("The Request didn't have a valid Token {}", auth);
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("403 Forbidden");
//        }

        Either<ExpectedError, TeethLead> maybeTeethLead = funnelLeadService.addTeethLead(teethLeadRequest);
        if (maybeTeethLead.isLeft()) {
            log.warn("Error creating teeth lead");
            return ResponseEntity.badRequest().body(maybeTeethLead.getLeft().getMessage());
        }

        log.info("Created teeth lead with id {}", maybeTeethLead.get().getId());

        return ResponseEntity.ok(maybeTeethLead.get());
    }
}