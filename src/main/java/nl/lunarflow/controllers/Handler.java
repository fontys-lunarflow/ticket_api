package nl.lunarflow.controllers;

import java.util.*;
import nl.lunarflow.models.Config;
import nl.lunarflow.models.Service;
import nl.lunarflow.models.*;

/*
 * The handler is a product of deliberate but odd design.
 * It's purpose is to act as a service switch,
 * which makes the controller layer independent from the service layer,
 * and vice-versa.
 */

public class Handler {

    public static Ticket reqTicket(Config conf, Ticket ticket, Service handler) throws Exception {
        return handler.reqTicket(conf, ticket);
    }

    public static Ticket newTicket(Config conf, Ticket ticket, Service handler) throws Exception {
        return handler.newTicket(conf, ticket);
    }

    public static Ticket doneTicket(Config conf, Ticket ticket, Service handler) throws Exception {
        return handler.doneTicket(conf, ticket);
    }

    public static List<Label> listLabel(Config conf, Service handler) throws Exception {
        return handler.listLabel(conf);
    }

    public static Label newLabel(Config conf, Label label, Service handler) throws Exception {
        return handler.newLabel(conf, label);
    }

    public static Label delLabel(Config conf, Label label, Service handler) throws Exception {
        return handler.delLabel(conf, label);
    }

}
