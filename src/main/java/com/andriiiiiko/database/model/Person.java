package com.andriiiiiko.database.model;

/**
 * Represents a Person entity with first name, last name, middle name, email, and phone number.
 */
public record Person(String firstName, String lastName, String middleName, String email, String phoneNumber) {

}
