package com.thomascantie.insa.core;

import com.thomascantie.insa.model.core.ConnectionInfo;
import com.thomascantie.insa.model.core.ConnectionsManager;
import com.thomascantie.insa.model.core.User;
import com.thomascantie.insa.view.ViewConnections;
import org.junit.Before;
import org.junit.Test;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ConnectionManagerMust {

	private static final String PSEUDO_TRACER = "Tracer";
	private static final String PSEUDO_REINHARDT = "Reinhardt";
	private static final String PSEUDO_ZENYATTA = "Zenyatta";
	private static final String PSEUDO_MERCY = "Mercy";
	private static final int PORT_TRACER = 2222;
	private static final int PORT_REINHARDT = 3333;
	private static final int PORT_ZENYATTA = 4444;
	private static final int PORT_MERCY = 5555;

	@Before
	public void setUp() {
		ConnectionsManager.getInstance().getAllPseudos()
				.forEach(pseudo -> ConnectionsManager.getInstance().removeConnectionInfo(pseudo));
	}

	@Test
	public void be_a_singleton() {
		ConnectionsManager instance1 = ConnectionsManager.getInstance();
		ConnectionsManager instance2 = ConnectionsManager.getInstance();

		assertThat(instance1).isNotNull();
		assertThat(instance2).isNotNull();
		assertThat(instance1).isSameAs(instance2);
	}

	@Test
	public void add_a_new_connection() throws UnknownHostException {
		add(PSEUDO_TRACER, getLocalHost(), PORT_TRACER);

		List<String> pseudos = ConnectionsManager.getInstance().getAllPseudos();

		assertThat(pseudos)
				.hasSize(1)
				.containsExactly(PSEUDO_TRACER);
	}

	@Test
	public void list_all_pseudos() throws UnknownHostException {
		add(PSEUDO_TRACER, getLocalHost(), PORT_TRACER);
		add(PSEUDO_REINHARDT, getLocalHost(), PORT_REINHARDT);
		add(PSEUDO_ZENYATTA, getLocalHost(), PORT_ZENYATTA);

		List<String> pseudos = ConnectionsManager.getInstance().getAllPseudos();

		assertThat(pseudos)
				.hasSize(3)
				.containsExactlyInAnyOrder(PSEUDO_TRACER, PSEUDO_REINHARDT, PSEUDO_ZENYATTA);
	}

	@Test
	public void remove_an_existing_connection() throws UnknownHostException {
		add(ConnectionManagerMust.PSEUDO_TRACER, getLocalHost(), PORT_TRACER);
		add(PSEUDO_REINHARDT, getLocalHost(), PORT_REINHARDT);
		add(PSEUDO_ZENYATTA, getLocalHost(), PORT_ZENYATTA);
		add(PSEUDO_MERCY, getLocalHost(), PORT_MERCY);

		delete(ConnectionManagerMust.PSEUDO_TRACER);

		List<String> pseudos = ConnectionsManager.getInstance().getAllPseudos();

		assertThat(pseudos)
				.hasSize(3)
				.containsExactlyInAnyOrder(PSEUDO_REINHARDT, PSEUDO_ZENYATTA, PSEUDO_MERCY)
				.doesNotContain(ConnectionManagerMust.PSEUDO_TRACER);
	}

	@Test
	public void retreive_a_connection_by_a_pseudo() throws UnknownHostException {
		add(PSEUDO_REINHARDT, getLocalHost(), PORT_REINHARDT);

		ConnectionInfo info = ConnectionsManager.getInstance().getConnexionInfoFor(PSEUDO_REINHARDT);

		assertThat(ConnectionsManager.getInstance().contains(PSEUDO_REINHARDT)).isTrue();
		assertThat(info.getIPAddress()).isEqualTo(getLocalHost().getHostAddress());
		assertThat(info.getPortNumber()).isEqualTo(PORT_REINHARDT);
	}

	private static InetAddress getLocalHost() throws UnknownHostException {
		return InetAddress.getLocalHost();
	}

	private static void add(String pseudo, InetAddress addr, int port) {
		ConnectionsManager.getInstance().updateConnexionInfo(pseudo, addr, port);
	}

	private static void delete(String pseudo) {
		ConnectionsManager.getInstance().removeConnectionInfo(pseudo);
	}

}
