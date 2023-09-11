package com.andriiiiiko.database.model;

/**
 * Represents a Resident entity with a person and apartment association.
 */
public class Resident {

    private final Person person;
    private final Apartment apartment;

    public Resident(Person person, Apartment apartment) {
        this.person = person;
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return "\nFirst Name: " + person.firstName() + "\n" +
                "Last Name: " + person.lastName() + "\n" +
                "Middle Name: " + person.middleName() + "\n" +
                "Email Address: " + person.email() + "\n" +
                "Phone Number: " + person.phoneNumber() + "\n" +
                "Apartment Number: №" + apartment.number() + "\n" +
                "Apartment Area: " + apartment.area() + " square meters" + "\n" +
                "Section: " + apartment.building().sectionNumber() + "\n" +
                "Street: " + apartment.building().street() + "\n";
    }
}
