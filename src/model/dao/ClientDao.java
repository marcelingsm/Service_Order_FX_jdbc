package model.dao;

import java.util.List;
import model.entites.Client;

public interface ClientDao {
    void insert(Client obj);
    void update(Client obj);
    void deleteById(Integer cpf);
    Client findById(Integer cpf);
    List<Client> findAll();       
}
