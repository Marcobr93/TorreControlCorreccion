package org.company.controller;

import org.company.modelo.Flight;
import org.company.modelo.Radar;

import java.util.Date;
import java.util.Scanner;

/**
 * Created by thinway on 7/5/17.
 */
public class AirportApp {

    private Radar radar;

    public AirportApp() {
        radar = new Radar();
        loadTestFlights();
    }

    private void loadTestFlights() {
        radar.addflight( new Flight("IB101", "Iberia", 450.0, 400.0, new Date()));
        radar.addflight( new Flight("RY101", "Ryanair", 1800.0, 410.5, new Date()));
        radar.addflight( new Flight("IB567", "Iberia", 1097.5, 407.5, new Date()));
        radar.addflight( new Flight("RY356", "Ryanair", 1295.5, 405.5, new Date()));
    }

    public void start() {
        int option;

        while ((option = showMenu()) != 0) {
            switch (option) {
                case 1:
                    // Add new flight
                    radar.addflight(askFlightInfo());
                    break;
                case 2:
                    radar.flightList();
                    break;
                case 3:
                    radar.flightListByAirline();
                    break;
                case 4:
                    radar.flightListBySpeed();
                    break;
            }
        }
    }

    private Flight askFlightInfo() {
        Scanner scanner = new Scanner(System.in);
        String flightCode, airline;
        double speed, distanceToUs;

        do {
            System.out.println("Código de vuelo: ");
            flightCode = scanner.next().trim().replaceAll("\\s+", " ");
        } while (flightCode.equals(""));

        scanner.nextLine();

        do {
            System.out.println("Compañía Aérea: ");
            airline = scanner.nextLine().trim().replaceAll("\\s+", " ");
        } while (airline.equals(""));

        do {
            System.out.println("Velocidad de crucero: ");
            speed = scanner.nextDouble();
        } while (speed < 0.0);

        do {
            System.out.println("Distancia a pista: ");
            distanceToUs = scanner.nextDouble();
        } while (distanceToUs < 0.0);

        return new Flight(flightCode, airline, speed, distanceToUs, new Date());

    }

    private int showMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;

        System.out.println("****************************");
        System.out.println("* 1 - Añadir vuelo         *");
        System.out.println("* 2 - Lista por proximidad *");
        System.out.println("* 3 - Lista por aerolínea  *");
        System.out.println("* 4 - Lista por velocidad  *");
        System.out.println("* 0 - Salir                *");
        System.out.println("****************************");
        System.out.println("Opción: ");

        option = scanner.nextInt();

        return option;
    }

}
