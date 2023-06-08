package com.project.my.module.sns.service;

import java.util.ArrayList;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.my.module.userRole.entity.ChatMessagerEntity;
import com.project.my.module.userRole.entity.ChatRoomEntity;
import com.project.my.module.userRole.entity.FriendmanagerEntity;
import com.project.my.module.userRole.repository.ChatRepository;

import io.swagger.v3.core.util.Json;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatServiceApi {
    private final ChatRepository chatRepository;

    //채팅방 리스트를 가져옴
    @Transactional
    public ArrayList<ChatRoomEntity> getChatroom(String userEmail) {
        System.out.println("서비스 진입");
        ArrayList<ChatRoomEntity> chatroom = chatRepository.getChatRoomList(userEmail);
        for(ChatRoomEntity chatRoomEntity : chatroom){
            String userEamil1 = chatRoomEntity.getUserEmail();
            String userImage = chatRepository.getUserImage(userEamil1);
            String userNickName = chatRepository.getuserName(userEamil1);
            chatRoomEntity.setUserImage(userImage);
            chatRoomEntity.setUserNickName(userNickName);
        }
        return chatroom;
    }

    //채팅방 각각의 확인하지 않은 메세지의 수를 가져옴
    public ArrayList<ChatRoomEntity> getChatAlram(String userEmail){
        ArrayList<ChatRoomEntity> anotherChatAlarm = chatRepository.getChatRoomList(userEmail);
        ArrayList<ChatRoomEntity> myChatAlarm = chatRepository.getChatAlarm(userEmail);
        for(int i=0;myChatAlarm.size()>i;i++){
            for(int j=0; anotherChatAlarm.size()>j;j++){
                if(myChatAlarm.get(i).getRoomId() == anotherChatAlarm.get(j).getRoomId()){
                    int roomAlarm = anotherChatAlarm.get(j).getLastCheck() - myChatAlarm.get(i).getLastCheck();
                    if(roomAlarm > 0){
                        anotherChatAlarm.get(j).setLastCheck(roomAlarm);
                        break;
                    }else{
                        anotherChatAlarm.get(j).setLastCheck(0);
                        break;
                    }
                }
            }
        }
        return anotherChatAlarm;
    }
    
    //채팅내역을 불러옴
    @Transactional
    public ArrayList<ChatMessagerEntity> getChatList(int roomId, int lastCheck, String userEmail){
        ArrayList<ChatMessagerEntity> chatList;
        if(lastCheck == 0){
            chatList = chatRepository.firstgetChatList(roomId);
        }else{
            chatList = chatRepository.getChatList(roomId, lastCheck);
            chatRepository.updateLastCheck(lastCheck, roomId, userEmail);
        }
        return chatList;
    }

    @Transactional
    public ArrayList<ChatMessagerEntity> getRecentChat(String userEmail){
        ArrayList<ChatRoomEntity> chatroom = chatRepository.getChatRoomList(userEmail);
        ArrayList<ChatMessagerEntity> recentChat = new ArrayList<ChatMessagerEntity>();
        for(int i = 0;chatroom.size() > i;i++){
            recentChat.addAll(i, chatRepository.getRecentChat(chatroom.get(i).getRoomId()));
        }
        return recentChat;
    }

    //채팅을 칠떄 DB에 insert함
    @Transactional
    public String chatSubmit(int roomId, String chatName, String chatContent){
        int maxNum;
        try {
            maxNum = chatRepository.getChatMaxNum(roomId) + 1;
        } catch (Exception e) {
            maxNum = 1;
        }
        chatRepository.chatSubmit(roomId, maxNum, chatName, chatContent);
        return Integer.toString(maxNum);
    }

    public ArrayList<FriendmanagerEntity> getFriendList(String userEmail){
        ArrayList<FriendmanagerEntity> friendList = chatRepository.getFriendList(userEmail);
        for(FriendmanagerEntity friendmanagerEntity : friendList){
            String friendEmail = friendmanagerEntity.getFriendEmail();
            String friendImage = chatRepository.getUserImage(friendEmail);
            String friendName = chatRepository.getuserName(friendEmail);
            friendmanagerEntity.setUserImage(friendImage);
            friendmanagerEntity.setFriendName(friendName);
        }
        return friendList;
    }

    public ArrayList<FriendmanagerEntity> getAcceptList(String userEmail){
        ArrayList<FriendmanagerEntity> acceptList = chatRepository.getAcceptList(userEmail);
        for(FriendmanagerEntity friendmanagerEntity : acceptList){
            String friendEmail = friendmanagerEntity.getFriendEmail();
            String friendImage = chatRepository.getUserImage(friendEmail);
            String friendName = chatRepository.getuserName(friendEmail);
            friendmanagerEntity.setUserImage(friendImage);
            friendmanagerEntity.setFriendName(friendName);
        }
        return acceptList;
    }

    public int creatChatRoom(String userEmail, String friendEmail){
        try {
            int check = chatRepository.checkChatRoom(userEmail, friendEmail);
            return check;
        } catch (Exception e) {
            int roomId = chatRepository.getChatRoomMaxNum() + 1;
            chatRepository.creatChatRoom(roomId, userEmail);
            chatRepository.creatChatRoom(roomId, friendEmail);
            return roomId;
        }
        // int check = chatRepository.checkChatRoom(userEmail, friendEmail);

        // int roomId = chatRepository.getChatRoomMaxNum() + 1;
        // chatRepository.creatChatRoom(roomId, userEmail);
        // chatRepository.creatChatRoom(roomId, friendEmail);
    }

}
