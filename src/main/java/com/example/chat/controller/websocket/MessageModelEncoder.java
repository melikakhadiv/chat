
package com.example.chat.controller.websocket;

import com.example.chat.model.entity.Chat;
import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;


public class MessageModelEncoder implements Encoder.Text<Chat>{
    
    Gson gson = new Gson();

    @Override
    public String encode(Chat message) throws EncodeException {
        return gson.toJson(message);
    }

    @Override
    public void init(EndpointConfig config) {
        }

    @Override
    public void destroy() {
        }
    
}
