package Xita;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import Dao.ProdutoOfertaDAO;
import Model.ProdutoOferta;

@ManagedBean
public class Principal {

	@ManagedProperty(value = "#{param.id}")
	private int id;
	private int option;
	private ProdutoOferta produto;
	private List<ProdutoOferta> produtoOferta;
	@Inject
	private Conversation conversation;
	private Date d = new Date();
	private int i = 0;

	FacesContext context = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) context.getExternalContext()
			.getSession(false);

	@EJB
	private ProdutoOfertaDAO produtoOfertaDAO;

	@PostConstruct
	public void init() {
		int menu = (int) (session.getAttribute("menu") != null ? session
				.getAttribute("menu") : 1);
		if (menu == 2) {
			produtoOferta = produtoOfertaDAO.listarProdutoPorTipo(new Long(2));
		} else if (menu == 3) {
			produtoOferta = produtoOfertaDAO.listarProdutoPorTipo(new Long(3));
		} else if (menu == 4) {
			produtoOferta = produtoOfertaDAO.listarProdutoPorTipo(new Long(4));
		} else if (menu == 5) {
			produtoOferta = produtoOfertaDAO.listarProdutoPorTipo(new Long(5));
		} else{
			produtoOferta = produtoOfertaDAO.listAll();
		}
	}

	public void initConversation() {
		if (!FacesContext.getCurrentInstance().isPostback()
				&& conversation.isTransient()) {
			conversation.begin();
		}
	}

	public String detalhes() {
		session.setAttribute("idPropaganda", id);
		return "detalhesProduto?faces-redirect=true";

	}

	public Date tempoRestante(long id) {
		Long mm = new Date().getTime();
		for (int i = 0; i < produtoOferta.size(); i++) {
			mm = new Long(produtoOferta.get(i).getTempoPropaganda().getTime()
					- new Date().getTime());
		}

		Date a = new Date(mm);
		i++;
		if (i == produtoOferta.size())
			i = 0;
		setD(a);
		return getD();
	}

	public String tipoDeProduto(int l) {
		if (l == 2) {
			return "Restaurantes e Bares";
		} else if (l == 3) {
			return "Sa�de e Beleza";
		} else if (l == 4) {
			return "Fitness";
		} else {
			return "Cursos e Aulas";
		}
	}

	public String todasOfertas() {
		session.setAttribute("menu", 1);
		return "principal?faces-redirect=true";
	}

	public String restaurantesBares() {
		session.setAttribute("menu", 2);
		return "principal?faces-redirect=true";
	}

	public String saudeBeleza() {
		session.setAttribute("menu", 3);
		return "principal?faces-redirect=true";
	}

	public String fitness() {
		session.setAttribute("menu", 4);
		return "principal?faces-redirect=true";
	}

	public String cursoAulas() {
		session.setAttribute("menu", 5);
		return "principal?faces-redirect=true";
	}

	public int getOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}

	public ProdutoOferta getProduto() {
		return produto;
	}

	public void setTheme(ProdutoOferta produto) {
		this.produto = produto;
	}

	public List<ProdutoOferta> getProdutoOferta() {
		return produtoOferta;
	}

	public void setProduto(ProdutoOferta produto) {
		this.produto = produto;
	}

	public void setProdutoOferta(List<ProdutoOferta> produtoOferta) {
		this.produtoOferta = produtoOferta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FacesContext getContext() {
		return context;
	}

	public void setContext(FacesContext context) {
		this.context = context;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public Date getD() {
		return d;
	}

	public void setD(Date d) {
		this.d = d;
	}

}
