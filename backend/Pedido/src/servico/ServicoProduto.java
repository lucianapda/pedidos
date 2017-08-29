package servico;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import modelo.Produto;


@Path("/produto")
public class ServicoProduto {

	private static ArrayList<Produto> produtos = new ArrayList();
	
	static {
		produtos.add(new Produto("Meia fritas", 44, 19.50));
		produtos.add(new Produto("X-Burguer", 55, 13));
		produtos.add(new Produto("Cerveja Skol", 96, 3.50));
	}
	
	/**
	 * Metodo utilizado para listar todos os produtos
	 * @return lista de produtos
	 */
	@GET
	@Produces({"application/json"})
	public ArrayList<Produto> getProdutos(){
		return produtos;
	}
	
	
	/**
	 * Retorna determinado produto a partir de sua ID
	 * @param id é o código do produto
	 * @return
	 */
	@Path("{id}")
	@GET
	@Produces("application/json")
	public Response getProduto(@PathParam("id") int id) {
		for (Produto p: produtos) {
			if (p.getCodigoProduto() == id) {
				return Response.status(200).entity(p).type(MediaType.APPLICATION_JSON).build();
			}
		}
		return Response.status(200).entity("Não há produtos com este código").type(MediaType.APPLICATION_JSON).build();
	}

	/**
	 * Metodo utilizado para inserir um novo produto
	 * @param p Objeto produto.
	 * @return
	 */
	@POST
	@Consumes({ "application/json" })
	public Response inserir(Produto p) {
		if (p == null)
			return Response.status(404).entity("Produto não informado").type(MediaType.TEXT_PLAIN).build();
		produtos.add(p);
		return Response.status(200).build();
	
	}
	

	
	
	/**
	 * Metodo utilizado para atualizar o preço de um produto
	 * @param id é o código do produto
	 * @param novoPreco novo preço do produto
	 */
	@Path("{id}")
	@PUT
	@Consumes({"application/json"})
	public void putValor(@PathParam("id") int id, double novoPreco){
		for(int i = 0; i < produtos.size(); i++){
			if(produtos.get(i).getCodigoProduto() == id){
				produtos.get(i).setValorProduto(novoPreco);;
			}
			
		}
	}
	
	@Path("{id}")
	@DELETE
	@Consumes({"application/json"})
	public void removerProduto(@PathParam("id") int id){
		for(int i = 0; i<produtos.size(); i++){
			if(produtos.get(i).getCodigoProduto() == id){
				produtos.remove(i);
			}
		}
	}
	
}
