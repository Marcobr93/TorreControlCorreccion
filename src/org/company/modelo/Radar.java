package org.company.modelo;

import java.util.*;

/**
 * Created by thinway on 7/5/17.
 */
public class Radar {

    final int MILISECONDS_IN_SECOND = 1000;
    final int SECONDS_IN_HOUR = 3600;
    final int METERS_IN_KM = 1000;

    private ArrayList<Flight> flights;

    public Radar() {
        flights = new ArrayList<>();
    }


    public void addflight(Flight flight) {
        if( flight != null){
            flights.add(flight);
        }
    }

    public void eliminarVuelo(){
        Scanner scanner = new Scanner(System.in);
        String codigoVuelo;

        Iterator<Flight> itFlights = flights.iterator();

        showFlightList();
        System.out.println();

            System.out.println("Introduzca el código de vuelo que desea borrar");
            codigoVuelo = scanner.next();

        while (itFlights.hasNext()){
            Flight flight = itFlights.next();

            if (codigoVuelo.equals(flight.getFlightCode())){
                itFlights.remove();
            }
        }
    }

    public void flightList() {

        updateFlightsPositions();

        showFlightList();
    }

    private void showFlightList() {
        for (Flight flight :
                flights) {
            System.out.println(flight);
        }
    }

    private void updateFlightsPositions() {
        Date newDate;
        long timeElapsed;
        double speedMetersPerSecond;
        double distanceTraveled;

        Iterator<Flight> itFlights = flights.iterator(); // Con ITERATOR sustituimos el foreach y podemos eliminar
                                                        // elementos de una colección
/*
        for (Flight flight :
                flights) {
*/
        while (itFlights.hasNext()){
            Flight flight = itFlights.next();

            newDate = new Date();

            timeElapsed = (newDate.getTime() - flight.getControlDateTime().getTime()) / MILISECONDS_IN_SECOND;
            flight.setControlDateTime(newDate);
            speedMetersPerSecond = flight.getSpeed() * MILISECONDS_IN_SECOND / SECONDS_IN_HOUR;
            distanceTraveled = speedMetersPerSecond * timeElapsed / METERS_IN_KM;
            // New position
            flight.setDistanceToUs(flight.getDistanceToUs() - distanceTraveled);

            if (flight.getDistanceToUs() == 0.0){
                itFlights.remove();
            }

           /*  En un foreach no podemos eliminar un elemento del arraylist, ya que recorre los elementos del array
            y no encuentra el elemento eliminado y lanzará una excepción.
            Para solucionar esto utilizamos los ITERATOR

            if (flight.getDistanceToUs() == 0.0){
                flights.remove(flight);
            }
            */
        }

        Collections.sort( flights ); // Ordenación siguiendo la interfaz Comparable (método: compareTo)
    }

    public void flightListByAirline() {
        // Ordenación siguiendo la interfaz Comparator (método: compare)
        Collections.sort( flights, new Flight() );

        showFlightList();
    }

    public void ordenacionPorCodigoVuelo(){
        Collections.sort(flights, Flight.comparadorPorCodigoVuelo);

        showFlightList();
    }

    public void flightListBySpeed() {
        Collections.sort( flights, Flight.compareBySpeed );

        showFlightList();
    }
}
