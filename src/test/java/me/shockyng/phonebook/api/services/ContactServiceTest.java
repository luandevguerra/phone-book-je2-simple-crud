package me.shockyng.phonebook.api.services;

import me.shockyng.phonebook.api.daos.ContactDao;
import me.shockyng.phonebook.api.dtos.ContactDTO;
import me.shockyng.phonebook.api.models.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ContactServiceTest {

    @InjectMocks
    private final ContactService contactService = new ContactService();

    @Mock
    private ContactDao contactDao;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldReturnAnEmptyListOnceDaoReturnedAnEmptyList() {
        // scenario
        Mockito.when(contactDao.getAll()).thenReturn(new ArrayList<>());
//        Mockito.when(mapper.map()).thenReturn(new ArrayList<>());


        // action
        List<ContactDTO> allCostumers = contactService.getAllCostumers();

        // verification
        assertTrue(allCostumers.isEmpty());
    }

    @Test
    void shouldReturnAListWithOneItem() {
        // scenario
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact());

        Mockito.when(contactDao.getAll()).thenReturn(contacts);

        // action
        List<ContactDTO> allCostumers = contactService.getAllCostumers();

        // verification
        assertEquals(1, allCostumers.size());
    }
}