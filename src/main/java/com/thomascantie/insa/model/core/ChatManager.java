package com.thomascantie.insa.model.core;

import com.thomascantie.insa.controler.Dispatcher;
import com.thomascantie.insa.model.network.service.TCPMessageReceiverService;
import com.thomascantie.insa.view.ChatSession;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe de gestion des sessions de chat
 *
 * @author Thomas Cantié
 * @author Andy Piszyna
 * @see ChatSession
 */
public class ChatManager {

	/**
	 * Instance courante du gestionnaire
	 */
	private static ChatManager instance;
	/**
	 * Pseudo de l'utilisateur local
	 */
	private String pseudo;
	/**
	 * Adresse IP locale
	 */
	private InetAddress ip;
	/**
	 * Numéro du port local d'écoute
	 */
	private int listeningPort;
	/**
	 * Table de correspondance pseudo distant <-> session de chat
	 */
	private Map<String, ChatSession> map;

	/**
	 * Constructeur
	 */
	private ChatManager() {
		this.map = new HashMap<String, ChatSession>();
	}

	/**
	 * Accesseur en lecture
	 *
	 * @return l'instance courante du gestionnaire
	 */
	public static synchronized ChatManager getInstance() {
		if (instance == null)
			instance = new ChatManager();
		return instance;
	}

	/**
	 * Accesseur en écriture
	 *
	 * @param pseudo le pseudo de l'utilisateur du gestionnaire
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	/**
	 * Accesseur en écriture
	 *
	 * @param ip l'adresse IP locale
	 */
	public void setLocalIPAddress(InetAddress ip) {
		this.ip = ip;
	}

	/**
	 * Accesseur en écriture
	 *
	 * @param port le numéro du port local d'écoute
	 */
	public void setLocalPortNumber(int port) {
		this.listeningPort = port;
	}

	/**
	 * Modificateur
	 * Ajout d'une nouvelle session de chat
	 *
	 * @param remotePseudo le pseudo de l'utilisateur distant
	 * @param remoteIP     l'adresse IP distante
	 * @param remotePort   le numéro de port distant
	 */
	public void addNewChat(String remotePseudo, String remoteIP, int remotePort) {
		this.map.put(remoteIP, new ChatSession(this.pseudo, remotePseudo, remoteIP, remotePort));
	}

	/**
	 * Evaluateur
	 *
	 * @param addr l'adresse IP distante
	 * @return true si une session de chat est déjà ouverte ; false sinon
	 */
	public boolean hasChatWith(String addr) {
		return this.map.containsKey(addr);
	}

	/**
	 * Accesseur en lecture
	 *
	 * @param addr l'adresse IP distante
	 * @return la session de chat liée à l'adresse IP
	 */
	public ChatSession getChat(String addr) {
		return this.map.get(addr);
	}

	/**
	 * Mise en écoute des messages de chat entrants sur le port d'écoute
	 */
	public void listeningIncommingMessages() {
		try {
			new TCPMessageReceiverService().listenOnPort(this.listeningPort, new Dispatcher());
			System.out.println("*** listening on : " + this.listeningPort);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
