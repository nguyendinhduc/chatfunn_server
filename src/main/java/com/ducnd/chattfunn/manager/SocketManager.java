package com.ducnd.chattfunn.manager;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.ducnd.chattfunn.model.database.Message;
import com.ducnd.chattfunn.model.database.UserProfile;
import com.ducnd.chattfunn.model.inter.OnlineResponse;
import com.ducnd.chattfunn.repository.user.FriendRepository;
import com.ducnd.chattfunn.repository.user.MessageRepository;
import com.ducnd.chattfunn.repository.user.UserProfileRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
public class SocketManager {
    private static final Logger LOG = LoggerFactory.getLogger(SocketManager.class);
    private SocketIOServer socketIOServer;
    private Map<Integer, SocketIOClient> listConnects;
    @Autowired
    private ObjectMapper objectMapper;
    private Executor executor;
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private FriendRepository friendRepository;


    @PostConstruct
    public void inits() {
        LOG.info("Start socket");
        listConnects = new HashMap<>();
        executor = Executors.newFixedThreadPool(5);
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
//        config.setHostname("0.0.0.0");
        config.setPort(9092);
        socketIOServer = new SocketIOServer(config);
        socketIOServer.addConnectListener(socketIOClient -> LOG.info("onConnect " + socketIOClient.getRemoteAddress().toString()));
        socketIOServer.addDisconnectListener(this::dissconnect);
        socketIOServer.addEventListener("firstConnect", String.class, this::startConnect);
        socketIOServer.addEventListener("events", String.class, this::startConnect);
        socketIOServer.addEventListener("message", String.class, this::sendMessage);
        socketIOServer.start();
    }

    private void startConnect(SocketIOClient socketIOClient, String userId, AckRequest ackRequest) {
        try {
            listConnects.put(Integer.parseInt(userId), socketIOClient);
            saveConnect(Integer.parseInt(userId), true);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }

    private void saveConnect(int userId, boolean isConnect){
        Observable.create((ObservableOnSubscribe<Boolean>)t->{
            UserProfile userProfile = userProfileRepository.findOne(userId);
            if (userProfile != null ){
                userProfile.setOnline(isConnect);
                userProfileRepository.save(userProfile);
                List<Integer> friends = friendRepository.findAllFriend(userId);

                OnlineResponse online = new OnlineResponse();
                online.setId(userId);
                online.setOnline(isConnect);
                String strOnline = objectMapper.writeValueAsString(online);
                for (Integer friend : friends) {
                    for (Integer id : listConnects.keySet()) {
                        if (friend.equals(id)){
                           listConnects.get(id).sendEvent("status", strOnline);
                        }
                    }
                }

            }

        }).subscribeOn(Schedulers.from(executor))
                .subscribe();
    }

    private void sendMessage(SocketIOClient socketIOClient, String messageJson, AckRequest ackRequest) {
        Message message = objectMapper.convertValue( messageJson, Message.class);
        int receiverId = message.getReceiverId();
        SocketIOClient client = listConnects.get(receiverId);
        if(client != null){
            client.sendEvent("message", messageJson);
        }
        saveMessage(message);
    }

    private void saveMessage(Message message){
        Observable.create((ObservableOnSubscribe<Boolean>)t->{
            messageRepository.save(message);
        }).subscribeOn(Schedulers.from(executor))
                .subscribe();
    }

    private void dissconnect(SocketIOClient socketIOClien) {
        for (Integer id : listConnects.keySet()) {
            if (listConnects.get(id) == socketIOClien){
                listConnects.remove(id);
                saveConnect(id, false);
            }
        }

    }

    public SocketIOServer getSocketIOServer() {
        return socketIOServer;
    }
}
