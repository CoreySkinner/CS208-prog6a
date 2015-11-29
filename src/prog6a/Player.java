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
    public static final int LIVES = 5;
    private String name;
    private Room currentRoom;
    private int lives;
    
    public Player(String name, Room currentRoom){
        this.name = name;
        this.currentRoom = currentRoom;
        lives = LIVES;
    }
    
    public int moveToLeftRoom(){
        if(currentRoom.getLeft() != null){
            if(!currentRoom.getLeft().hasTrap()){
                currentRoom = currentRoom.getLeft();
                return 2;
            }
            die();
            return 1;
        }
        return 0;
    }
    
    public int moveToRightRoom(){
        if(currentRoom.getRight() != null){
            if(!currentRoom.getRight().hasTrap()){
                currentRoom = currentRoom.getRight();
                return 2;
            }
            die();
            return 1;
        }
        return 0;
    }
    
    public int moveToFrontRoom(){
        if(currentRoom.getFront() != null){
            if(!currentRoom.getFront().hasTrap()){
                currentRoom = currentRoom.getFront();
                return 2;
            }
            die();
            return 1;
        }
        return 0;
    }
    /*
    0: no room to move
    1: room has trap
    2: success
    */
    public int moveToRearRoom(){
        if(currentRoom.getRear() != null){
            if(!currentRoom.getRear().hasTrap()){
                currentRoom = currentRoom.getRear();
                return 2;
            }
            die();
            return 1;
        }
        return 0;
    }
    
    public String getName(){
        return name;
    }
    
    public void die(){
        if(lives > 1) lives--;
        else lives = LIVES;
    }
    
    public int numLives(){
        return lives;
    }
    
    public void setCurrentRoom(Room room){
        currentRoom = room;
    }
    
    @Override
    public int hashCode(){
        return currentRoom.getID();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Player)) return false;
        Player temp = (Player) obj;
        return name.equals(temp.name) && currentRoom == temp.currentRoom && lives == temp.lives;
    }
}
