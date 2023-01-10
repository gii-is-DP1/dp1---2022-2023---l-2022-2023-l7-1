package org.springframework.samples.petclinic.Chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MensajeService {

    @Autowired
    MensajeRepository mensajeRepository;

    @Autowired
    public MensajeService(MensajeRepository repo){
        this.mensajeRepository = repo;
    }
    
    @Transactional(readOnly = true)
    public List<Mensaje> getMensajesByUsername(String username){
        return mensajeRepository.findByUserId(username);
    }

    @Transactional
    public List<Mensaje> getAll(){
        return mensajeRepository.findAll();
    }

    @Transactional
    public void saveMensaje(Mensaje m) throws DataAccessException{
        mensajeRepository.save(m);
    }


    public Mensaje getById(Integer id) {
        return mensajeRepository.findById(id).orElse(null);
    }

    public Integer getUltimoId() {
        List<Mensaje> mensajes= mensajeRepository.findAll();
        if(mensajes.isEmpty()){
            return 1;
        }
        return mensajes.get(mensajes.size()-1).getId()+1;
    }

}
