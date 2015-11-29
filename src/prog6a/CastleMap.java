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
public class CastleMap {

    private PlayerToRoomHashTable hashTable;
    public static final int castleSide = 8;//side of the square castle: 8*8 = 64 rooms
    private Room[] rooms;
    private Player[] players;
    private Player activatedPlayer;

    public CastleMap() {
        
        //sets up rooms
        rooms = new Room[castleSide*castleSide];//64 rooms
        for(int i = 0; i < castleSide*castleSide; ++i)
            rooms[i] = new Room(i,null,null,null,null);
        buildCastle();
        
        //sets up players
        players = new Player[2];
        players[0] = new Player("Mary", rooms[(castleSide-1)/2]);
        players[1] = new Player("Tom", rooms[(castleSide-1)/2]);
        
        activatedPlayer = players[0];
        
        //sets up hash table
        hashTable = new PlayerToRoomHashTable(rooms);//hash table with 64 buckets 
        for(int i = 0; i < players.length; ++i){
            hashTable.insert(players[i]);
        }
    }
    
    /*
     * set connections of rooms
    */
    private void buildCastle(){
        //4 corners of the castle
        rooms[0].setFront(rooms[8]);rooms[0].setRight(rooms[1]);
        rooms[castleSide-1].setLeft(rooms[castleSide-2]);rooms[castleSide-1].setFront(rooms[2*castleSide-1]);
        rooms[castleSide*(castleSide-1)].setRight(rooms[castleSide*(castleSide-1)+1]);rooms[castleSide*(castleSide-1)].setRear(rooms[castleSide*(castleSide-2)]);
        rooms[rooms.length-1].setLeft(rooms[rooms.length-2]);rooms[rooms.length-1].setRear(rooms[rooms.length-1-castleSide]);
        //4 sides of the castle
        for(int i = 1; i < castleSide - 1; ++i){
            //bottom side
            rooms[i].setLeft(rooms[i-1]);
            rooms[i].setRight(rooms[i+1]);
            rooms[i].setFront(rooms[i+castleSide]);
            //left side
            rooms[i*castleSide].setRight(rooms[i*castleSide+1]);
            rooms[i*castleSide].setFront(rooms[i*castleSide+castleSide]);
            rooms[i*castleSide].setRear(rooms[i*castleSide-castleSide]);
            //top side
            rooms[i+castleSide*(castleSide-1)].setLeft(rooms[i+castleSide*(castleSide-1)-1]);
            rooms[i+castleSide*(castleSide-1)].setRight(rooms[i+castleSide*(castleSide-1)+1]);
            rooms[i+castleSide*(castleSide-1)].setRear(rooms[i+castleSide*(castleSide-1)-castleSide]);
            //right side
            rooms[i*castleSide+castleSide-1].setLeft(rooms[i*castleSide+castleSide-2]);
            rooms[i*castleSide+castleSide-1].setFront(rooms[i*castleSide+2*castleSide-1]);
            rooms[i*castleSide+castleSide-1].setRear(rooms[i*castleSide-1]);
        }
        //center of the castle
        if(castleSide > 2){
            for(int i = castleSide+1; i < 2*castleSide-1; ++i){
                for(int j = i; j < i+castleSide*(castleSide-2); j+=castleSide ){
                    rooms[j].setLeft(rooms[j-1]);
                    rooms[j].setRight(rooms[j+1]);
                    rooms[j].setFront(rooms[j+castleSide]);
                    rooms[j].setRear(rooms[j-castleSide]);
                }
            }
        }
    }
    
    /*
     * Makes player move, and updates hash table
    */
    public boolean movePlayer(Player player, int direction){
        boolean moveSuccess = false;
        if(direction == 0){//moves to left room
            if(player.moveToLeftRoom()){
                player.moveToRightRoom();
                hashTable.remove(player);
                player.moveToLeftRoom();
                hashTable.insert(player);
                moveSuccess = true;
            }
        }else if(direction == 1){//moves to right room
            if(player.moveToRightRoom()){
                player.moveToLeftRoom();
                hashTable.remove(player);
                player.moveToRightRoom();
                hashTable.insert(player);
                moveSuccess = true;
            }
        }else if(direction == 2){//moves to front room
            if(player.moveToFrontRoom()){
                player.moveToRearRoom();
                hashTable.remove(player);
                player.moveToFrontRoom();
                hashTable.insert(player);
                moveSuccess = true;
            }
        }else if(direction == 3){//moves to rear room
            if(player.moveToRearRoom()){
                player.moveToFrontRoom();
                hashTable.remove(player);
                player.moveToRearRoom();
                hashTable.insert(player);
                moveSuccess = true;
            }
        }
        return moveSuccess;
    }
    
    public Player[] getPlayerArr(){
        return players;
    }
    
    public Player getActivatedPlayer(){
        return activatedPlayer;
    }
    
    public void setActivatedPlayer(Player p){
        activatedPlayer = p;
    }
}
