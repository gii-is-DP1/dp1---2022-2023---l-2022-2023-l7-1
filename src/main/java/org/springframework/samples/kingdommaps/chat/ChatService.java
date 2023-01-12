package org.springframework.samples.kingdommaps.chat;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository repo){
        this.chatRepository = repo;
    }
    
    @Transactional
    public Chat getByPartidaId(Integer id){
        return chatRepository.findByPartidaId(id);
    }

    @Transactional
    public List<Chat> getAll(){
        return chatRepository.findAll();
    }

    @Transactional
    public void save(Chat c) throws DataAccessException{
        chatRepository.save(c);
    }

    @Transactional
    public void edit(Chat c) throws DataAccessException{
        Chat toUpdate = chatRepository.findById(c.getId()).orElse(null);
        if(toUpdate != null) {
            toUpdate.setId(c.getId());
            toUpdate.setMensajes(c.getMensajes());
            toUpdate.setPartida(c.getPartida());
            chatRepository.save(toUpdate);
        }
    }

    public void delete(Chat chat) {
        chatRepository.deleteById(chat.getId());
    }
    
}
