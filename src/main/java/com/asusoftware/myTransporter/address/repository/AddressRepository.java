package com.asusoftware.myTransporter.address.repository;

import com.asusoftware.myTransporter.address.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {

    @Query("SELECT DISTINCT a.country FROM Address a ORDER BY a.country ASC")
    List<String> findAllCountry();

    @Query("SELECT DISTINCT a.county FROM Address a WHERE a.country=?1 ORDER BY a.county ASC")
    List<String> findAllCounty(String countryName);

    @Query("Select a.city from Address a WHERE a.county=?1 ORDER BY a.city ASC")
    List<String> findAllCity(String countyName);

    @Query("SELECT a FROM Address a WHERE a.country=?1 AND a.county=?2 AND a.city=?3")
    Address findAddress(String country, String county, String city);
}
