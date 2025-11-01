package com.example.transactions.service;

import com.example.transactions.entity.Address;
import com.example.transactions.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public Address saveAddressRequiredTransaction(Address address) {
        return addressRepository.save(address);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Address saveAddressRequiredNewTransaction(Address address) {
        return addressRepository.save(address);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Address saveAddressSupportsTransaction(Address address) {
        return addressRepository.save(address);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public Address saveAddressMandatoryTransaction(Address address) {
        return addressRepository.save(address);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Address saveAddressNotSupportedTransaction(Address address) {
        return addressRepository.save(address);
    }

    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }
}
