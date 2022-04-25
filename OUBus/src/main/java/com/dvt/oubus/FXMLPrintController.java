/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dvt.oubus;

import com.dvt.pojo.Bus;
import com.dvt.pojo.Passenger;
import com.dvt.pojo.Seat;
import com.dvt.pojo.Ticket;
import com.dvt.pojo.Trip;
import com.dvt.pojo.User;
import com.dvt.sevices.BusService;
import com.dvt.sevices.PassengerService;
import com.dvt.sevices.SeatService;
import com.dvt.sevices.TicketService;
import com.dvt.sevices.TripService;
import com.dvt.sevices.UserSevices;
import java.net.URL;
//import java.nio.file.FileSystems;
//import java.nio.file.Path;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXMLPrintController implements Initializable {
    private Ticket ticket;
    private static final TripService S_TRIP = new TripService();
    private static final SeatService S_SEAT = new SeatService();
    private static final TicketService S_TICKET = new TicketService();
    private static final PassengerService S_PASSENGER = new PassengerService();
    private static final UserSevices S_USER = new UserSevices();
    private static final BusService S_BUS = new BusService();
    
    @FXML private TextField textTripName;
    @FXML private TextField textBus;
    @FXML private TextField textFrom;
    @FXML private TextField textTo;
    @FXML private TextField textIdTicket;
    @FXML private TextField textDeparture;
    @FXML private TextField textSeat;
    @FXML private TextField textPrice;
    @FXML private TextField textFullName;
    @FXML private TextField textPhone;
    @FXML private TextField textFeildStaff;
    @FXML private TextField textPrintDate;
    @FXML private TextField textFieldTime;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void load() throws SQLException {
        Passenger p = S_PASSENGER.getPassengerById(ticket.getPassenger_id());
        User staff = S_USER.getUserById(ticket.getStaff_id());
        Seat seat = S_SEAT.getSeatById(ticket.getSeat_id());
        Trip trip = S_TRIP.getTripById(ticket.getTrip_id());
        Bus bus = S_BUS.getBusById(trip.getId_bus());
        Date d = new Date();
        
        textTripName.setText(trip.getName());
        textBus.setText(bus.getLicense_plate());
        textFrom.setText(ticket.getDeparture());
        textTo.setText(ticket.getDestination());
        textFullName.setText(p.getName());
        textIdTicket.setText(String.valueOf(ticket.getId()));
        textFeildStaff.setText(staff.getName());
        textPhone.setText(p.getPhone_number());
        textSeat.setText(seat.getName());
        textDeparture.setText(trip.getDate());
        textFieldTime.setText(trip.getTime());
        textPrice.setText(String.valueOf(ticket.getPrice()));
        textPrintDate.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(d));
    }
    
//    public void createQRCode() throws IOException {
//        String data = "Ha noi mua thu";
//        com.google.zxing.qrcode.QRCodeWriter qrCodeWriter = new QRCodeWriter();
//        BitMatrix matrix = QRCodeWriter.encode(data, Bar.QR_CODE, 200, 0);
//
//        // Write to file image
//        String outputFile = "/path/to/output/image.png";
//        Path path = FileSystems.getDefault().getPath(outputFile);
//        com.google.zxing.client.j2se.MatrixToImageWriter.writeToPath(matrix, "PNG", path);
//    }

    /**
     * @param ticket the ticket to set
     */
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
    
}
