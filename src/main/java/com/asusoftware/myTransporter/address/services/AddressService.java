package com.asusoftware.myTransporter.address.services;

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
        return addressRepository.findAllCounty(county);
    }
}
