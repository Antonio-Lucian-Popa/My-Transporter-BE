package com.asusoftware.myTransporter.address.mappers;

import com.asusoftware.myTransporter.address.model.Address;
import com.asusoftware.myTransporter.address.model.dto.AddressDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressDtoEntity {

    Address addressToEntity(AddressDto addressDto);
}
