package DaoImpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import Dao.ImagemProdutoDAO;
import Model.ImagemProduto;

@Stateless
public class ImagemProdutoDAOImpl implements ImagemProdutoDAO{
	@PersistenceContext
	private EntityManager em;	
	
	@Override
	public ImagemProduto persist(ImagemProduto t) {
		if (t.getId() != null) {
			return em.merge(t);
		}
		em.persist(t);
		return t;
	}

	@Override
	public void delete(Long id) {
		Query query = em.createQuery("DELETE FROM ImagemProduto i WHERE i.id = "+id);
		query.executeUpdate();
		
	}

	@Override
	public void delete(ImagemProduto t) {
		em.remove(t);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ImagemProduto> listAll() {
		Query query = em.createQuery("SELECT i FROM ImagemProduto i");
		return (List<ImagemProduto>) query.getResultList();
	}

	@Override
	public ImagemProduto buscarPorId(Long id) {
		//return em.find(ImagemProduto.class, id);
		Query query = em.createQuery("SELECT i FROM ImagemProduto i WHERE produto_id = "+id);
		return (ImagemProduto) query.getSingleResult();
	}

}