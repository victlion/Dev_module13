package org.example;


import org.example.crud.ClientCrud;
import org.example.crud.PlanetCrud;
import org.example.crud.TicketCrud;
import org.example.entity.Client;

public class Main {
    public static void main(String[] args) {
        TicketCrud ticketCrud = new TicketCrud();

        ticketCrud.create(1l,"EAR","SAT");
        System.out.println(ticketCrud.read(10l));
        ticketCrud.update(9l,2l,"EAR","JUP");
        ticketCrud.delete(10l);
    }
}
