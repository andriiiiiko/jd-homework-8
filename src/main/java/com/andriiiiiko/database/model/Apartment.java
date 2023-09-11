package com.andriiiiiko.database.model;

/**
 * Represents an Apartment entity with a unique number, area, and associated building.
 */
public record Apartment(int number, double area, Building building) {

}
