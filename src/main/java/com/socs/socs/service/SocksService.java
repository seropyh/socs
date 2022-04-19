package com.socs.socs.service;
import com.socs.socs.entity.Socks;

public interface SocksService {

 Socks addSocks(String color,int cottonPart, int quantity);
 Socks delSocks(String color,int cottonPart,int quantity);

 Integer getSocks(String color, int cottonPart,String operation);
}
