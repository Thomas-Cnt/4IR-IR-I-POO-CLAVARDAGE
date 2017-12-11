package com.thomascantie.insa.model.core;

import com.thomascantie.insa.controler.UpdateChat;
import com.thomascantie.insa.model.network.service.TCPMessageReceiverService;
import com.thomascantie.insa.view.ChatSession;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class ChatManager {

    private static ChatManager instance;
    private String pseudo;
    private InetAddress ip;
    private int listeningPort;
    private Map<String, ChatSession> map;

    private ChatManager() {
        this.map = new HashMap<String, ChatSession>();
    }

    public static synchronized ChatManager getInstance() {
        if (instance == null)
            instance = new ChatManager();
        return instance;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setLocalIPAddress(InetAddress ip) {
        this.ip = ip;
    }

    public void setLocalPortNumber(int port) {
        this.listeningPort = port;
    }

    public void addNewChat(String remotePseudo, String remoteIP, int remotePort) {
        this.map.put(remoteIP, new ChatSession(this.pseudo, remotePseudo, this.ip.getHostAddress(), remoteIP, this.listeningPort, remotePort));
    }

    public boolean hasChatWith(String addr) {
        return this.map.containsKey(addr);
    }

    public ChatSession getChat(String addr) {
        return this.map.get(addr);
    }

    public void listeningIncommingMessages() {
        try {
            new TCPMessageReceiverService().listenOnPort(listeningPort);
            System.out.println("*** listening on : " + listeningPort);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
