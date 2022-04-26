package me.shockyng.phonebook.api.daos;

import me.shockyng.phonebook.api.models.Contact;

public class ContactDao extends BaseDAO<Contact, Long> {

    @Override
    protected Class<Contact> clazz() {
        return Contact.class;
    }
}
