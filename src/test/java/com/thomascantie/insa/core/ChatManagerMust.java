package com.thomascantie.insa.core;

import com.thomascantie.insa.controler.ListenerSendMessage;
import com.thomascantie.insa.model.core.ChatManager;
import com.thomascantie.insa.model.core.User;
import com.thomascantie.insa.view.ChatSession;
import com.thomascantie.insa.view.ViewChat;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class ChatManagerMust {

	private static final String PSEUDO_REINHARDT = "Reinhardt";
	private static final String IP_REINHARDT = "Hammer Down !";
	private static final int PORT_REINHARDT = 4242;
	private static final String IP_ZENYATTA = "Experience tranquility";

	@Test
	public void be_a_singleton() {
		ChatManager instance1 = ChatManager.getInstance();
		ChatManager instance2 = ChatManager.getInstance();

		assertThat(instance1).isNotNull();
		assertThat(instance2).isNotNull();
		assertThat(instance1).isSameAs(instance2);
	}

	@Test
	public void add_a_new_chat() {
		ChatManager.getInstance().addNewChat(PSEUDO_REINHARDT, IP_REINHARDT, PORT_REINHARDT);

		ChatSession session = ChatManager.getInstance().getChat(IP_REINHARDT);

		assertThat(ChatManager.getInstance().hasChatWith(IP_REINHARDT)).isTrue();
		assertThat(session).isNotNull();
		assertThat(session.getView().getTo().getPseudo()).isEqualTo(PSEUDO_REINHARDT);
		assertThat(session.getView().getIpRemote()).isEqualTo(IP_REINHARDT);
		assertThat(session.getView().getRemotePort()).isEqualTo(PORT_REINHARDT);
	}

	@Test
	public void not_find_a_non_existing_chat() {
		ChatSession session = ChatManager.getInstance().getChat(IP_ZENYATTA);

		assertThat(ChatManager.getInstance().hasChatWith(IP_ZENYATTA)).isFalse();
		assertThat(session).isNull();
	}

	@Test
	public void receive_an_incoming_message_trough_the_network() throws Exception {
		ChatManager.getInstance().setLocalPortNumber(4242);
		ChatManager.getInstance().listeningIncommingMessages();

		ViewChat chat = mock(ViewChat.class);

		when(chat.getFrom()).thenReturn(new User(PSEUDO_REINHARDT));
		when(chat.getTextInput()).thenReturn(IP_REINHARDT);
		when(chat.getIpRemote()).thenReturn("127.0.0.1");
		when(chat.getRemotePort()).thenReturn(PORT_REINHARDT);

		new ListenerSendMessage(chat).process();

		verify(chat).updateTextPane(anyObject(), anyObject(), anyString());
	}

}
