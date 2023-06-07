package com.project.my.module.sns.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.my.module.sns.service.ChatServiceApi;
import com.project.my.module.userRole.entity.ChatMessagerEntity;
import com.project.my.module.userRole.entity.ChatRoomEntity;
import com.project.my.module.userRole.entity.FriendmanagerEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatControllerApi {
    private final ChatServiceApi chatServiceApi;

    @GetMapping("/getChatRoom")
    public ArrayList<ChatRoomEntity> getChatRoom(@RequestParam("userEmail") String userEmail) {
        ArrayList<ChatRoomEntity> chatroom = chatServiceApi.getChatroom(userEmail);
        System.out.println("컨트롤러 진입");
        return chatroom;
    }

    @GetMapping("/getChatAlarm")
    public ArrayList<ChatRoomEntity> getChatAlarm(@RequestParam("userEmail") String userEmail){
        ArrayList<ChatRoomEntity> chatAlarm = chatServiceApi.getChatAlram(userEmail);
        return chatAlarm;
    }

    @GetMapping("/getChatList")
    public ArrayList<ChatMessagerEntity> getChatList(@RequestParam("roomId") int roomId,
                                                     @RequestParam("lastCheck") int lastCheck,
                                                     @RequestParam("userEmail") String userEmail){
        ArrayList<ChatMessagerEntity> chatList = chatServiceApi.getChatList(roomId, lastCheck, userEmail);
        return chatList;
    }

    @GetMapping("/getRecentChat")
    public ArrayList<ChatMessagerEntity> getRecentChat(@RequestParam("userEmail") String userEmail){
        ArrayList<ChatMessagerEntity> recentChat = chatServiceApi.getRecentChat(userEmail);
        return recentChat;
    }
    
    @GetMapping("/chatSubmit")
    public String chatSubmit(@RequestParam("roomId") int roomId,
                           @RequestParam("chatName") String chatName,
                           @RequestParam("chatContent") String chatContent){
        log.info("컨트롤러 진입" + roomId + chatName + chatContent);
        System.out.println(roomId + chatName + chatContent);
        String ss = chatServiceApi.chatSubmit(roomId, chatName, chatContent);
        return ss;
    }

    @GetMapping("/getFriendList")
    public ArrayList<FriendmanagerEntity> getFriendList(@RequestParam("userEmail") String userEmail){
        ArrayList<FriendmanagerEntity> getFriendList = chatServiceApi.getFriendList(userEmail);
        return getFriendList;
    }

    @GetMapping("/creatChatRoom")
    public int creatChatRoom(@RequestParam("userEmail") String userEmail,@RequestParam("freindEmail") String freindEmail){
        int roomid = chatServiceApi.creatChatRoom(userEmail, freindEmail);
        return roomid;
    }

}
