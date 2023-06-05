package com.project.my.module.userRole.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.project.my.module.userRole.entity.ChatMessagerEntity;
import com.project.my.module.userRole.entity.ChatRoomEntity;
import com.project.my.module.userRole.entity.FriendmanagerEntity;

@Mapper
@Repository
public interface ChatRepository {
    ArrayList<ChatRoomEntity> getChatRoomList(String userEmail);

    ArrayList<ChatRoomEntity> getChatAlarm(String userEmail);

    ArrayList<ChatMessagerEntity> firstgetChatList(@Param("roomId") int roomId);

    ArrayList<ChatMessagerEntity> getChatList(@Param("roomId") int roomId, @Param("lastCheck") int lastCheck);

    ArrayList<ChatMessagerEntity> getRecentChat(int roomId);
    
    ArrayList<FriendmanagerEntity> getFriendList(String userEmail);

    String getUserImage(String userEmail);

    String getuserName(String userEmail);

    int getChatMaxNum(int roomId);

    int getChatRoomMaxNum();

    void updateLastCheck(@Param("lastCheck") int lastCheck, @Param("roomId") int roomId, @Param("userEmail") String userEmail);

    void chatSubmit(@Param("roomId") int roomId, @Param("chatID") int chatID, @Param("chatName") String chatName, @Param("chatContent") String chatContent);

    void creatChatRoom(@Param("roomId") int roomId, @Param("userEmail") String userEmail);

    int checkChatRoom(@Param("userEmail") String userEmail, @Param("friendEmail") String friendEmail);
}
