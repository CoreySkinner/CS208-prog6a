/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog6a;

/**
 *
 * @author Hang
 */
public class Player {
    private String name;
    private Room currentRoom;
    
    public Player(String name, Room currentRoom){
        this.name = name;
        this.currentRoom = currentRoom;
    }
    
    public boolean moveToLeftRoom(){
        if(currentRoom.getLeft() != null){
            currentRoom = currentRoom.getLeft();
            return true;
        }
        else return false;
    }
    
    public boolean moveToRightRoom(){
        if(currentRoom.getRight() != null){
            currentRoom = currentRoom.getRight();
            return true;
        }
        else return false;
    }
    
    public boolean moveToFrontRoom(){
        if(currentRoom.getFront() != null){
            currentRoom = currentRoom.getFront();
            return true;
        }
        else return false;
    }
    
    public boolean moveToRearRoom(){
        if(currentRoom.getRear() != null){
            currentRoom = currentRoom.getRear();
            return true;
        }
        else return false;
    }
    
    public String getName(){
        return name;
    }
    
    
    @Override
    public int hashCode(){
        return currentRoom.getID();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        return other.name.equals(this.name);
    }
}
