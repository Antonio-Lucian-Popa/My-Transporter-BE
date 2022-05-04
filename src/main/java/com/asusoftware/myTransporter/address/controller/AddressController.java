package com.asusoftware.myTransporter.address.controller;

import com.asusoftware.myTransporter.address.model.dto.AddressDto;
import com.asusoftware.myTransporter.address.services.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/address")
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;

    // Find all country
    @GetMapping(path = "/findAllCountry")
    private List<String> findAllCountry() {
        return addressService.findAllCountry();
    }

    // Find all county based on the selected country
    @GetMapping(path = "/findAllCounty/{countryName}")
    private List<String> findAllCounty(@PathVariable(name = "countryName") String countryName) {
       return addressService.findAllCounty(countryName);
    }

    // Find all city based on the selected county
    @GetMapping(path = "/findAllCity/{countyName}")
    private List<String> findAllCity(@PathVariable(name = "countyName") String countyName) {
        return addressService.findAllCity(countyName);
    }

    // Find address based on the selected county
    @PostMapping(path = "/findAddress")
    private void findAddress(@RequestBody AddressDto addressDto) {
        addressService.findAddress(addressDto);
    }
}
