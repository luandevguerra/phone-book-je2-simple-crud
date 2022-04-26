package me.shockyng.phonebook.api.resources;

import me.shockyng.phonebook.api.dtos.ContactDTO;
import me.shockyng.phonebook.api.services.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/contact")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class ContactResource {

    private static final Logger logger = LoggerFactory.getLogger(ContactResource.class);

    @Inject
    private ContactService service;

    @GET
    @Path("/{id}")
    public Response getContact(@PathParam("id") Long id) {
        logger.info("Request received to fetch a contact with id: " + id);
        try {
            return Response.ok(service.getCostumer(id)).build();
        } catch (NoResultException e) {
            return Response.ok().status(Response.Status.NO_CONTENT).build();
        }
    }

    @GET
    public Response getContacts() {
        logger.info("Request received to fetch all contact");

        List<ContactDTO> allCostumers = service.getAllCostumers();
        if (allCostumers.isEmpty()) return Response.ok(allCostumers)
                .status(Response.Status.NO_CONTENT).build();
        return Response.ok(allCostumers).build();
    }

    @POST
    public Response createContact(@Valid ContactDTO contactDTO) {
        logger.info("Request received to create a contact with payload: {}", contactDTO);
        return Response.ok(service.createCostumer(contactDTO)).status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateContact(@PathParam("id") Long id, @Valid ContactDTO contactDTO) {
        logger.info("Request received to create a contact with id: " + id);

        try {
            return Response.ok(service.updateCostumer(id, contactDTO)).build();
        } catch (NoResultException e) {
            return Response.ok().status(Response.Status.NO_CONTENT).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteContact(@PathParam("id") Long id) {
        logger.info("Request received to delete a contact with id: " + id);
        try {
            service.deleteCostumer(id);
            return Response.ok().build();
        } catch (NoResultException e) {
            return Response.ok().status(Response.Status.NO_CONTENT).build();
        }
    }

}
