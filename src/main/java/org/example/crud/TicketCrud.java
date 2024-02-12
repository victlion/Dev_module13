package org.example.crud;

import jakarta.persistence.Query;
import org.example.HibernateUtil;
import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TicketCrud {
    public void update(Long id ,Long client_id , String from_planet_id,String to_planet_id) {
        PlanetCrud planetCrud = new PlanetCrud();
        ClientCrud clientCrud = new ClientCrud();

        Client client = clientCrud.read(client_id);
        Planet fromPlanet = planetCrud.read(from_planet_id);
        Planet toPlanet = planetCrud.read(to_planet_id);
        Ticket ticket = read(id);

        if(client != null && fromPlanet != null && toPlanet != null && ticket!=null){
            Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            Ticket ticketService = new Ticket();
            ticketService.setId(id);
            ticketService.setClient(client);
            ticketService.setFromPlanet(fromPlanet);
            ticketService.setToPlanet(toPlanet);

            Ticket t = session.merge(ticketService);
            session.persist(t);

            transaction.commit();
            session.close();
        }
    }

    public void create(Long client_id , String from_planet_id,String to_planet_id) {
        PlanetCrud planetCrud = new PlanetCrud();
        ClientCrud clientCrud = new ClientCrud();

        Client client = clientCrud.read(client_id);
        Planet fromPlanet = planetCrud.read(from_planet_id);
        Planet toPlanet = planetCrud.read(to_planet_id);

        if(client != null && fromPlanet != null && toPlanet != null){
            Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            Ticket ticketService = new Ticket();
            ticketService.setClient(client);
            ticketService.setFromPlanet(fromPlanet);
            ticketService.setToPlanet(toPlanet);

            Ticket t = session.merge(ticketService);
            session.persist(t);

            transaction.commit();
            session.close();
        }
    }

    public Ticket read(Long id) {
        Ticket result = null;
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query selectQuery = session.createQuery("FROM Ticket WHERE id = :ticketId");
        selectQuery.setParameter("ticketId", id);
        List<Ticket> tickets = selectQuery.getResultList();

        if (tickets != null && !tickets.isEmpty()) {
            for (Ticket ticket : tickets) {
                result = ticket;
            }
        } else {
            result = null;
        }
        transaction.commit();
        session.close();
        return result;
    }

    public void delete(Long id) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Ticket ticket = session.get(Ticket.class, id);
        if (ticket != null) {
            session.delete(ticket);
            transaction.commit();
        }
        session.close();
    }
}