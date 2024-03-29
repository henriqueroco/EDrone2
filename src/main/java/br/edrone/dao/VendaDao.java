package br.edrone.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import br.edrone.model.Venda;

/**
 *  DAO for Venda
 */
@Stateless
public class VendaDao
{
   @PersistenceContext(unitName = "EDrone2-persistence-unit")
   private EntityManager em;

   public void create(Venda entity)
   {
      em.persist(entity);
   }

   public void deleteById(Long id)
   {
      Venda entity = em.find(Venda.class, id);
      if (entity != null)
      {
         em.remove(entity);
      }
   }

   public Venda findById(Long id)
   {
      return em.find(Venda.class, id);
   }

   public Venda update(Venda entity)
   {
      return em.merge(entity);
   }

   public List<Venda> listAll(Integer startPosition, Integer maxResult)
   {
      TypedQuery<Venda> findAllQuery = em.createQuery("SELECT DISTINCT v FROM Venda v LEFT JOIN FETCH v.itens LEFT JOIN FETCH v.cliente ORDER BY v.id", Venda.class);
      if (startPosition != null)
      {
         findAllQuery.setFirstResult(startPosition);
      }
      if (maxResult != null)
      {
         findAllQuery.setMaxResults(maxResult);
      }
      return findAllQuery.getResultList();
   }
}
