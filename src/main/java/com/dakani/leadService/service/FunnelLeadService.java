package com.dakani.leadService.service;

import com.dakani.leadService.error.ExpectedError;
import com.dakani.leadService.model.AnimalLeadCreateDto;
import com.dakani.leadService.model.EgenticLeadCreateDto;
import com.dakani.leadService.model.HouseLeadCreateDto;
import com.dakani.leadService.model.TeethLeadCreateDto;
import com.dakani.leadService.model.mapper.LeadMapper;
import com.dakani.leadService.persistence.entity.AnimalLead;
import com.dakani.leadService.persistence.entity.EgenticLead;
import com.dakani.leadService.persistence.entity.HouseLead;
import com.dakani.leadService.persistence.entity.TeethLead;
import com.dakani.leadService.persistence.repository.AnimalLeadRepository;
import com.dakani.leadService.persistence.repository.EgenticLeadRepository;
import com.dakani.leadService.persistence.repository.HouseLeadRepository;
import com.dakani.leadService.persistence.repository.TeethLeadRepository;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j(topic = "leadService")
public class FunnelLeadService {
    private final AnimalLeadRepository animalLeadRepository;
    private final HouseLeadRepository houseLeadRepository;
    private final TeethLeadRepository teethLeadRepository;

    @Transactional
    public Either<ExpectedError, AnimalLead> addAnimalLead(AnimalLeadCreateDto animalLeadCreateDto) {
        AnimalLead animalLead = LeadMapper.INSTANCE.animalLeadCreateDtoToAnimalLead(animalLeadCreateDto);
        return animalLeadRepository.createLead(animalLead);
    }

    public List<AnimalLead> getAnimalLeadsToPushToDialFire() {
        return animalLeadRepository.getLeadsToPushToDialFire();
    }
    public List<AnimalLead> getAnimalLeadsToPushToKlickTipp() {
        return animalLeadRepository.getLeadsToPushToKlickTipp();
    }
    @Transactional
    public Either<ExpectedError, TeethLead> addTeethLead(TeethLeadCreateDto teethLeadCreateDto) {
        TeethLead teethLead = LeadMapper.INSTANCE.teethLeadCreateDtoToTeethLead(teethLeadCreateDto);
        return teethLeadRepository.createLead(teethLead);
    }
    public List<TeethLead> getTeethLeadsToPushToDialFire() {
        return teethLeadRepository.getLeadsToPushToDialFire();
    }
    public List<TeethLead> getTeethLeadsToPushToKlickTipp() {
        return teethLeadRepository.getLeadsToPushToKlickTipp();
    }

    @Transactional
    public Either<ExpectedError, HouseLead> addHouseLead(HouseLeadCreateDto houseLeadCreateDto) {
        HouseLead houseLead = LeadMapper.INSTANCE.houseLeadCreateDtoToHouseLead(houseLeadCreateDto);
        return houseLeadRepository.createLead(houseLead);
    }
    public List<HouseLead> getHouseLeadsToPushToDialFire() {
        return houseLeadRepository.getLeadsToPushToDialFire();
    }
    public List<HouseLead> getHouseLeadsToPushToKlickTipp() {
        return houseLeadRepository.getLeadsToPushToKlickTipp();
    }

}
