package com.chat.controller.websocket;

import com.chat.model.entity.Chat;
import com.google.gson.Gson;
import jakarta.websocket.Decoder;
import jakarta.websocket.EndpointConfig;

public class MessageModelDecoder implements Decoder.Text<Chat> {
    
    Gson gson = new Gson();

    @Override
    public Chat decode(String s){
        return gson.fromJson(s, Chat.class);
    }

    @Override
    public boolean willDecode(String s) {
        return s != null;
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

}
