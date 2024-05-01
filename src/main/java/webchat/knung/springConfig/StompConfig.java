package webchat.knung.springConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class StompConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // STOMP 의 End point 를 설정합니다.
        registry.addEndpoint("/stomp/chat").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 메세지 브로커, MessageMapping 어노테이션을 설정합니다.

        // 메세지 브로커를 지정해줍니다.
        registry.enableSimpleBroker("/topic");

        // 클라이언트가 Chatting/room 으로 값을 던지면
        // @MessageMapping(room)을 가진 메서드가 받도록 설정한다.
        registry.setApplicationDestinationPrefixes("/chatting");
    }
}
