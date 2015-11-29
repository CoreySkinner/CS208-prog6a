/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog6a;

import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Hang
 */
public class PlayerToRoomHashTable {
    private HashMap<Room, HashSet<Player>> map;
    private Room[] rooms;
    
    public PlayerToRoomHashTable(Room[] rooms){
        this.rooms = rooms;
        map = new HashMap<>();
        for(int i = 0; i < rooms.length; ++i){
            map.put(this.rooms[i], new HashSet<Player>());
        }
    }
    
    public boolean insert(Player player){
        return map.get(rooms[player.hashCode()]).add(player);
    }
    
    public boolean remove(Player player){
        return map.get(rooms[player.hashCode()]).remove(player);
    }
    
    
    public Player search(Player player){
        boolean exist =  map.get(rooms[player.hashCode()]).contains(player);
        if(exist) return player;
        else return null;
    }
    
}
