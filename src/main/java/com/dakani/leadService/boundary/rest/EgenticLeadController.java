package com.dakani.leadService.boundary.rest;

import com.dakani.leadService.model.EgenticLeadCreateDto;
import com.dakani.leadService.persistence.entity.EgenticLead;
import com.dakani.leadService.error.ExpectedError;
import com.dakani.leadService.service.EgenticLeadService;
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
public class EgenticLeadController {
    private final EgenticLeadService egenticLeadService;
    private final TokenService tokenService;
//    @GetMapping("/egentic-leads")
//    public ResponseEntity<?> getLogsByMasterSipId(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
//        if(!tokenService.isTokenValid("/", auth)){
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("403 Forbidden");
//        }
//        List<EgenticLead> leads = egenticLeadService.getEgenticLeadsToPushToDialFire();
//        log.info("Retrieved logs.");
//
//        return ResponseEntity.ok(leads);
//    }

    @PostMapping("/egentic-leads")
    public ResponseEntity<?> createEgenticLead(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
            @RequestBody EgenticLeadCreateDto egenticLeadRequest
    ) {
        log.info("received a lead create Request");
        if(!tokenService.isTokenValid("/egentic-leads", auth)){
        log.info("The Request didn't have a valid Token {}", auth);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("403 Forbidden");
        }

        Either<ExpectedError, EgenticLead> maybeEgenticLead = egenticLeadService.addEgenticLead(egenticLeadRequest);
        if (maybeEgenticLead.isLeft()) {
            log.warn("Error creating egentic lead");
            return ResponseEntity.badRequest().body(maybeEgenticLead.getLeft().getMessage());
        }

        log.info("Created egentic lead with id {}", maybeEgenticLead.get().getId());

        return ResponseEntity.ok(maybeEgenticLead.get());
    }
}