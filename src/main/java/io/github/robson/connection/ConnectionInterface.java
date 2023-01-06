package io.github.robson.connection;

import io.github.robson.model.Contato;

import java.util.List;

public interface ConnectionInterface {
    List<Contato> GetAll() ;
    Contato Get(String id);
    void Insert(Contato contato);
    void Update(Contato contato);
    void Delete(String id);
}
