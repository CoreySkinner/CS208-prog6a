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
public class Room {
    private int roomID;
    private boolean hasTrap;
    private Room leftRoom;
    private Room rightRoom;
    private Room frontRoom;
    private Room rearRoom;
    
    public Room(int id, Room left, Room right, Room front, Room rear){
        roomID = id;
        leftRoom = left;
        rightRoom = right;
        frontRoom = front;
        rearRoom = rear;
        hasTrap = false;
    }
    
    public Room getLeft(){
        return leftRoom;
    }
    
    public Room getRight(){
        return rightRoom;
    }
    
    public Room getFront(){
        return frontRoom;
    }
    
    public Room getRear(){
        return rearRoom;
    }
    public void setLeft(Room room){
        leftRoom = room;
    }
    
    public void setRight(Room room){
        rightRoom = room;
    }
    
    public void setFront(Room room){
        frontRoom = room;
    }
    
    public void setRear(Room room){
        rearRoom = room;
    }
    
    public int getID(){
        return roomID;
    }

    public void setTrap(boolean b){
        hasTrap = b;
    }
    
    public boolean hasTrap(){
        return hasTrap;
    }
}
