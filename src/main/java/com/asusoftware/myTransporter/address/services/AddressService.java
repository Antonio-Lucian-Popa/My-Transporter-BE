package com.asusoftware.myTransporter.address.services;

import com.asusoftware.myTransporter.address.model.Address;
import com.asusoftware.myTransporter.address.model.dto.AddressDto;
import com.asusoftware.myTransporter.address.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public List<String> findAllCountry() {
        return addressRepository.findAllCountry();
    }

    public List<String> findAllCounty(String country) {
        return addressRepository.findAllCounty(country);
    }

    public List<String> findAllCity(String county) {
        return addressRepository.findAllCity(county);
    }

    public Address findAddress(AddressDto addressDto) {
        String country = addressDto.getCountry();
        String county = addressDto.getCounty();
        String city = addressDto.getCity();
        return addressRepository.findAddress(country, county, city);
    }
}
