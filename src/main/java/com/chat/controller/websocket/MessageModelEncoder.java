
package com.chat.controller.websocket;

import com.chat.model.entity.Chat;
import com.google.gson.Gson;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;


public class MessageModelEncoder implements Encoder.Text<Chat>{
    
    Gson gson = new Gson();

    @Override
    public String encode(Chat chat) {
        return gson.toJson(chat);
    }

    @Override
    public void init(EndpointConfig config) {
        }

    @Override
    public void destroy() {
        }


}
