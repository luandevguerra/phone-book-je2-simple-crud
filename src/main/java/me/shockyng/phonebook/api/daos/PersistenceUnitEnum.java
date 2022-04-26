package me.shockyng.phonebook.api.daos;

public enum PersistenceUnitEnum {

    PHONE_BOOK("PhoneBookPU");

    private String name;

    PersistenceUnitEnum(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
