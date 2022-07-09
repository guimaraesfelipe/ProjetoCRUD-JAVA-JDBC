package br.com.crud.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;
import br.com.crud.factory.ConnectionFactory;
import br.com.crud.model.Produto;

//Parametrização para que o JAVA se molde a estrutura relacional do banco de dados 
public class ProdutoDAO {

	/*CRUD
	 *c: CREATE
	 *r: READ
	 *u: UPDATE
	 *d: DELETE 
	 */
	
	//CREATE
	public void save(Produto produto) throws SQLException {
		
		//Query SQL para INSERT de dados no banco
		String query = "INSERT INTO produtos(descricao, fabricante, valor, datacadastro) VALUES (?, ?, ?, ?)";
		
		Connection connection = null;
		PreparedStatement pstm = null;
		
		try {
			
			//Cria uma conexão com o banco de dados utilizando o metodo da ConnectionFactory
			connection = ConnectionFactory.createConnectionToMySQL();
			
			//Cria uma PreparedStatement para execução da query
			pstm = (PreparedStatement) connection.prepareStatement(query);
			pstm.setString(1, produto.getDescricao());
			pstm.setString(2, produto.getFabricante());
			pstm.setFloat(3, produto.getValor());
			pstm.setDate(4, new Date(produto.getDatacadastro().getTime()));
			
			//Executar a query
			pstm.execute();
			System.out.println("Produto cadastrado com sucesso!");
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			
			//Fechar conexões
			try {
				if (pstm != null) {
					pstm.close();
				}
				
				if (connection != null) {
					connection.close();
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}	
		}
	}
	
	//READ
	public List<Produto> get(){
		
		String query = "SELECT * FROM produtos";
		
		List<Produto> produtos = new ArrayList<Produto>();
		
		Connection connection = null;
		PreparedStatement pstm = null;
		
		//Classe que recupera os dados do banco de dados
		ResultSet rset = null;
		
		try {
			connection = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) connection.prepareStatement(query);
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				Produto produto = new Produto();
				
				produto.setId(rset.getInt("id"));
				produto.setDescricao(rset.getString("descricao"));
				produto.setFabricante(rset.getString("fabricante"));
				produto.setValor(rset.getFloat("valor"));
				produto.setDatacadastro(rset.getDate("datacadastro"));
				
				produtos.add(produto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rset != null) {
					rset.close();
				}
				
				if (pstm != null) {
					pstm.close();
				}
				
				if (connection != null) {
					connection.close();
				}	
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return produtos;
		
	}
	
	//UPDATE
	public void update(Produto produto) {
		
		String query = "UPDATE produtos SET descricao = ?, fabricante = ?, valor = ?, datacadastro = ? WHERE id = ?";
		
		Connection connection = null;
		PreparedStatement pstm = null;
		
		try {
			//Criar conexão com o banco de dados
			connection = ConnectionFactory.createConnectionToMySQL();
			
			//Criar classe para execução da query
			pstm = (PreparedStatement) connection.prepareStatement(query);
			
			//Adicionar os valores para atualização
			pstm.setString(1, produto.getDescricao());
			pstm.setString(2, produto.getFabricante());
			pstm.setFloat(3, produto.getValor());
			pstm.setDate(4, new Date(produto.getDatacadastro().getTime()));
			
			//ID do registro que será atualizado
			pstm.setInt(5, produto.getId());
			
			//Executar query
			pstm.execute();
			System.out.println("Atualização realizada com sucesso!");
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				
				if (connection != null) {
					connection.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//DELETE
	public void deleteByID(int id) {
		String query = "DELETE FROM produtos WHERE id = ?";
		
		Connection connection = null;
		PreparedStatement pstm = null;
		
		try {
			//Criar conexão com o banco de dados
			connection = ConnectionFactory.createConnectionToMySQL();
			
			//Criar classe para execução da query
			pstm = (PreparedStatement) connection.prepareStatement(query);
			
			//Adicionar os valores para atualização
			pstm.setInt(1, id);
			
			//Executar query
			pstm.execute();
			System.out.println("Produto deletado com sucesso!");
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				
				if (connection != null) {
					connection.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
