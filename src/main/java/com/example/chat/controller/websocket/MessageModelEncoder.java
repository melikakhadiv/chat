
package com.example.chat.controller.websocket;

import com.example.chat.model.entity.Chat;
import com.google.gson.Gson;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;


public class MessageModelEncoder implements Encoder.Text<Chat>{
    
    Gson gson = new Gson();

    @Override
    public String encode(Chat chat) throws EncodeException {
        return gson.toJson(chat);
    }

    @Override
    public void init(EndpointConfig config) {
        }

    @Override
    public void destroy() {
        }


}
