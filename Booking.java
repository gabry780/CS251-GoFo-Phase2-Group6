package System;

import User.Player;
import User.Team;

import java.util.ArrayList;


public class Booking {

    private Playground playground;
    
    private Player player;
    
    private Time time;
    
    private ArrayList<Team.TeamMember> teamMembers;

    public Booking(Verifier verifier) {
        playground = new Playground(verifier);
        player = new Player(verifier);
        time = new Time();
    }

    public Playground getPlayground() {
        return this.playground;
    }

    public void setPlayground(Playground playground) {
        this.playground = playground;
    }

    
    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    
    public Time getTime() {
        return this.time;
    }

    
    public void setTime(Time time) {
        this.time = time;
    }

   
    public void refund() {
        Time currentTime = new Time();
        currentTime.setCurrentTime();
        if (currentTime.conflicts(getTime())) {
            int bookingDuration = getTime().getDuration();
            double price = playground.getPrice() * bookingDuration;
            double currentBalance = getPlayer().getWallet().getBalance();
            double ownerBalance = getPlayground().getOwner().getWallet().getBalance();
            getPlayground().getOwner().getWallet().setBalance(ownerBalance - (price * bookingDuration));
            getPlayer().getWallet().setBalance(currentBalance + (price * bookingDuration));
        }
    }

    
    @Override
    public String toString() {
        return "Booking duration:\n" + time + '\n' +
                "Playground was booked by player: " + player.getUserName();
    }
}
