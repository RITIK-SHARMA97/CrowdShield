package com.ritik.CrowdShield.CONFIG;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
@Configuration
@EnableWebSocketMessageBroker // allows our app to send and receive real-time messages (like live crowd alerts) using WebSockets.
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

//    @Override
//    public void registerStampEndpoints(StampEndpointRegistry registry){
//    registry.addEndpoint("/ws").setAllowedOriginalPatterns("*").withSocksJS();
//    }



}
