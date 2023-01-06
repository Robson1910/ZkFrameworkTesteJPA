package io.github.robson.connection;

import io.github.robson.model.Contato;
import org.zkoss.zul.Messagebox;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ConnectionDAO implements ConnectionInterface {

    @Override
    public List<Contato> GetAll() {
        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
            EntityManager em = emf.createEntityManager();
            String jpql = "SELECT c FROM Contato c";
            List<Contato> contatosList =  em.createQuery(jpql, Contato.class).getResultList();
            em.close();
            emf.close();
            return contatosList;
        }catch (Exception e){
            Messagebox.show("Erro ao executar a Lista: " + e );
            return null;
        }
    }

    @Override
    public Contato Get(String id) {
        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
            EntityManager em = emf.createEntityManager();
            Contato contato = em.find(Contato.class,id);
            em.close();
            emf.close();
            return contato;
        }catch (Exception e){
            Messagebox.show("Erro ao trazer contato: " + e );
            return null;
        }
    }

    @Override
    public void Insert(Contato contato) {
        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(contato);
            em.getTransaction().commit();
            em.close();
            emf.close();
            Messagebox.show("The file has been create successfully.");
        }catch (Exception e){
            Messagebox.show("Erro ao inserir contato: " + e );
        }
    }

    @Override
    public void Update(Contato contato) {
        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(contato);
            em.getTransaction().commit();
            em.close();
            emf.close();
            Messagebox.show("The file has been update successfully.");
        }catch (Exception e){
            Messagebox.show("Erro ao editar contato: " + e );
        }
    }

    @Override
    public void Delete(String id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
            EntityManager em = emf.createEntityManager();
            Contato contato = em.find(Contato.class,id);
            em.getTransaction().begin();
            em.remove(contato);
            em.getTransaction().commit();
            em.close();
            emf.close();
            Messagebox.show("The file has been remove successfully.");
        }catch (Exception e){
            Messagebox.show("Erro ao remover contato: " + e );
        }
    }
}
