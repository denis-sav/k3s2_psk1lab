package com.example.k3s2_psk1lab.rest;

import lombok.*;
import com.example.k3s2_psk1lab.rest.contracts.AthleteDto;
import com.example.k3s2_psk1lab.entities.Athlete;
import com.example.k3s2_psk1lab.persistence.AthletesDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/athletes")
public class AthletesController {

    @Inject
    @Setter @Getter
    private AthletesDAO athletesDAO;

//    curl -X GET http://localhost:8080/k3s2_psk1lab/api/athletes/1 -H "Accept: application/json"
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Long id) {
        Athlete player = athletesDAO.findOne(id);
        if (player == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        AthleteDto athleteDto = new AthleteDto();
        athleteDto.setName(player.getName());
        athleteDto.setJerseyNumber(player.getJerseyNumber());

        return Response.ok(athleteDto).build();
    }
/*
curl -X PUT http://localhost:8080/k3s2_psk1lab/api/athletes/3 -H "Content-Type: application/json" -d "{\"name\": \"Daumtas\",\"surname\": \"Testuoja\",\"jerseyNumber\": 1,\"team\": null,\"competitions\": []}"



 */
    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Long playerId,
            AthleteDto playerData) {
        try {
            Athlete existingPlayer = athletesDAO.findOne(playerId);
            if (existingPlayer == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingPlayer.setName(playerData.getName());
            existingPlayer.setSurname(playerData.getSurname());
            existingPlayer.setJerseyNumber(playerData.getJerseyNumber());
            existingPlayer.setTeam(playerData.getTeam());
            existingPlayer.setCompetitions(playerData.getCompetitions());
            athletesDAO.update(existingPlayer);
            return Response.ok(existingPlayer).build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

/*
curl -X POST http://localhost:8080/k3s2_psk1lab/api/athletes -H "Content-Type: application/json" -d "{\"name\": \"Jane\", \"surname\": \"Smith\", \"jerseyNumber\": 99, \"team\": null, \"competitions\": []}"
 */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(AthleteDto athleteData) {
        Athlete newAthlete = new Athlete();
        newAthlete.setName(athleteData.getName());
        newAthlete.setSurname(athleteData.getSurname());
        newAthlete.setJerseyNumber(athleteData.getJerseyNumber());
        newAthlete.setTeam(athleteData.getTeam());
        newAthlete.setCompetitions(athleteData.getCompetitions());
        athletesDAO.persist(newAthlete);
        return Response.status(Response.Status.CREATED).entity(newAthlete).build();
    }

}