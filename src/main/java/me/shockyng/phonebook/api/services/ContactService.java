package me.shockyng.phonebook.api.services;

import me.shockyng.phonebook.api.daos.ContactDao;
import me.shockyng.phonebook.api.dtos.ContactDTO;
import me.shockyng.phonebook.api.models.Contact;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContactService {

    private static final Logger logger = LoggerFactory.getLogger(ContactService.class);

    private final ModelMapper mapper = new ModelMapper();

    @Inject
    private ContactDao dao;

    public ContactDTO getCostumer(Long id) {
        logger.info("getContact(id) called");
        ContactDTO contactDto = mapper.map(dao.getById(id), ContactDTO.class);

        logger.info("Contact mapped to dto");
        return contactDto;
    }

    public List<ContactDTO> getAllCostumers() {
        logger.info("getAllContacts() called");
        List<Contact> allContacts = dao.getAll();

        if (!allContacts.isEmpty()) {
            List<ContactDTO> costumersDto = allContacts
                    .stream()
                    .map(c -> mapper.map(c, ContactDTO.class))
                    .collect(Collectors.toList());

            logger.info("Mapping fetched contacts to Dto");
            return costumersDto;
        }

        logger.info("There is not contacts at the database");
        return new ArrayList<>();
    }

    public ContactDTO createCostumer(ContactDTO contactDTO) {
        logger.info("createContact(contacts) called");
        ContactDTO contactDto1 = mapper.map(dao.save(mapper.map(contactDTO, Contact.class)), ContactDTO.class);

        logger.info("Contact mapped to Dto");
        return contactDto1;
    }

    public ContactDTO updateCostumer(Long id, ContactDTO contactDTO) {
        logger.info("updateContact(id, contact) called");

        Contact contact = mapper.map(contactDTO, Contact.class);
        logger.info("ContactDto mapped to contact");

        logger.info("Setting contact id to be updated");
        contact.setId(getCostumer(id).getId());

        return mapper.map(dao.update(contact), ContactDTO.class);
    }

    public void deleteCostumer(Long id) {
        logger.info("deleteContact(id) called");
        dao.deleteById(id);
    }
}
