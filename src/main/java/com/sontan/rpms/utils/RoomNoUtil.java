package com.sontan.rpms.utils;

public class RoomNoUtil {

    public static String getRoomNo(int block,int unit,int floor,int room){
        String block1=String.valueOf(block);
        if(block1.length()<2){
            block1=0+block1;
        }
        String room1=String.valueOf(room);
        if(room1.length()<2){
            room1=0+room1;
        }
        String floor1=String.valueOf(floor);
        if(floor1.length()<2){
            floor1=0+floor1;
        }
        return block1+unit+floor1+room1;
    }
}
