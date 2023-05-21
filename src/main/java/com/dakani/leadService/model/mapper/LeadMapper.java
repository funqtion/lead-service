package com.dakani.leadService.model.mapper;

import com.dakani.leadService.boundary.client.finanzen.model.FinanzenLeadDto;
import com.dakani.leadService.model.AnimalLeadCreateDto;
import com.dakani.leadService.model.EgenticLeadCreateDto;
import com.dakani.leadService.model.HouseLeadCreateDto;
import com.dakani.leadService.model.TeethLeadCreateDto;
import com.dakani.leadService.persistence.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LeadMapper {
    LeadMapper INSTANCE = Mappers.getMapper(LeadMapper.class);

    EgenticLead egenticLeadCreateDtoToEgenticLead(EgenticLeadCreateDto egenticLeadCreateDto);

    FinanzenLead finanzenLeadDtoToFinanzenLead(FinanzenLeadDto finanzenLeadDto);

    AnimalLead animalLeadCreateDtoToAnimalLead(AnimalLeadCreateDto animalLeadCreateDto);
    TeethLead teethLeadCreateDtoToTeethLead(TeethLeadCreateDto teethLeadCreateDto);
    HouseLead houseLeadCreateDtoToHouseLead(HouseLeadCreateDto houseLeadCreateDto);

}
