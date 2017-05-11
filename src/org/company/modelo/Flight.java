package org.company.modelo;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

/**
 * Created by thinway on 7/5/17.
 */
public class Flight implements Comparable<Flight>, Comparator<Flight> {

    private String flightCode;
    private String airline;
    private double speed;
    private double distanceToUs;
    private Date controlDateTime;

    public static Comparator<Flight> compareBySpeed = new Comparator<Flight>() {
        @Override
        public int compare(Flight f1, Flight f2) {
            return (int)(f2.getSpeed() - f1.getSpeed());
        }
    };


    // Constructores


    public Flight() {
    }

    public Flight(String flightCode, String airline, double speed, double distanceToUs, Date controlDateTime) {
        this.setFlightCode( flightCode );
        this.setAirline( airline );
        this.setSpeed( speed );
        this.setDistanceToUs( distanceToUs );
        this.setControlDateTime( controlDateTime );
    }

    public Flight(String codigoVuelo) {
    }


    // Accesores

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        if( flightCode.equals("") ){
            this.flightCode = "NOCODE";
        }else{
            this.flightCode = flightCode;
        }
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        if( airline.equals("") ) {
            this.airline = "PIRATE FLIGHT";
        }else{
            this.airline = airline;
        }
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        if( speed < 0 ){
            speed = 0.0;
        }else{
            this.speed = speed;
        }
    }

    public double getDistanceToUs() {
        return distanceToUs;
    }

    public void setDistanceToUs(double distanceToUs) {
        if( distanceToUs < 0 ){
            this.distanceToUs = 0.0;
        }else{
            this.distanceToUs = distanceToUs;
        }
    }

    public Date getControlDateTime() {
        return controlDateTime;
    }

    public void setControlDateTime(Date controlDateTime) {
        this.controlDateTime = controlDateTime;
    }

    @Override
    public String toString() {
        DecimalFormat numberFormat = new DecimalFormat("0.00");
        String dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(getControlDateTime());

        return getFlightCode() + "(" + getAirline() + ") - V: " + numberFormat.format(getSpeed()) + "Km/h - D: " +
                numberFormat.format(getDistanceToUs()) + "Km @ " + dateFormat;
    }

    @Override
    public int compareTo(Flight flight) {
        return Double.compare(this.getDistanceToUs(), flight.getDistanceToUs() );
    }

    @Override
    public int compare(Flight f1, Flight f2) {
        int res;

        res = f1.getAirline().compareToIgnoreCase(f2.getAirline());
        if( res != 0) {
            return res;
        }

//        return (int)(f1.getSpeed() - f2.getSpeed());
        return Double.compare(f1.getSpeed(), f2.getSpeed());
    }

    public static Comparator<Flight> comparadorPorCodigoVuelo = new Comparator<Flight>() {
        @Override
        public int compare(Flight f1, Flight f2) {
            int res;

            res = f1.getFlightCode().compareToIgnoreCase(f2.getFlightCode());
            if(res != 0){
                return res;
            }
            return f1.getFlightCode().compareToIgnoreCase(f2.getFlightCode());
        }
    };

    @Override
    public boolean equals(Object obj) {
        if( this == obj ) { return true; }

        if ( obj == null ) { return false; }

        if ( this.getClass() != obj.getClass() ) { return false; }

        Flight f = (Flight) obj;

        // Se consideran dos vuelos iguales si tienen el mismo código de vuelo
        return Objects.equals(this.getFlightCode(), f.getFlightCode());

//      En caso de querer una igualdad canónica deberíamos comparar todos los atributos de la clase
//                Objects.equals(this.getAirline(), f.getAirline()) &&
//                Objects.equals(this.getDistanceToUs(), f.getDistanceToUs()) &&
//                Objects.equals(this.getSpeed(), f.getSpeed());
    }
}
