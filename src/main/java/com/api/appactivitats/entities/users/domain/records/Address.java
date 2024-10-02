package com.api.appactivitats.entities.users.domain.records;

// Address class based on the Spanish, more specifically Catalan, system.
public record Address(String street, int number, int floor, int door, String postalCode, String county, String city, String country){}
