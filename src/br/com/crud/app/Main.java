package br.com.crud.app;

import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;
import br.com.crud.dao.ProdutoDAO;
import br.com.crud.model.Produto;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws SQLException {
		
		ProdutoDAO produtoDao = new ProdutoDAO();
		Scanner input = new Scanner(System.in);
		String resposta;
		
		System.out.println("=================== BEM-VINDO ao CRUD com JAVA e MySQL ===================\n");
		
		do {
			System.out.println("QUAL SISTEMA DESEJA SE CONECTAR?\n");
			System.out.println("PRODUTOS - Acessa a base de dados de produtos");
			String sistema = input.nextLine();
			
				if (sistema.equalsIgnoreCase("PRODUTOS") ==  false ) {
					
					System.err.println("Sistema '" + sistema + "' invalido!\nSistemas Validos: PRODUTOS");
					
				}else {
					do {
							System.out.println("QUAL FUN��O DESEJA REALIZAR NO SISTEMA?\n");
							System.out.println("CREATE - Para cadastrar produto");
							System.out.println("READ - Para retornar os produtos cadastrados");
							System.out.println("UPDATE - Para atualizar um produto cadastrado");
							System.out.println("DELETE - Para deletar um produto cadastrado");
							
							String funcao = input.nextLine();
							
							if  (funcao.equalsIgnoreCase("CREATE") == false && funcao.equalsIgnoreCase("READ") == false && funcao.equalsIgnoreCase("UPDATE") == false && funcao.equalsIgnoreCase("DELETE") == false) {
								
								System.err.println("Fun��o '" + funcao + "' invalida!\nFun��es Validas: CREATE, READ, UPDATE ou DELETE");
								
							}else {
								//CREATE
								if (funcao.equalsIgnoreCase("CREATE")) {
									
									System.out.println("=================== Cadastro de Produtos ===================\n");
									
									Produto produto = new Produto();
									
									do {
										System.out.println("Digite a descri��o do produto: ");
										produto.setDescricao(input.nextLine());
										
										System.out.println("Digite o fabricante do produto: ");
										produto.setFabricante(input.nextLine());
										
										System.out.println("Digite o valor do produto: ");
										produto.setValor(input.nextFloat());
										input.nextLine();
										
										produto.setDatacadastro(new Date());
										
										produtoDao.save(produto);
										
										System.out.println("DESEJA CADASTRAR OUTRO PRODUTO?\nSim ou N�o");
										resposta = input.nextLine();
										
										if (resposta.equalsIgnoreCase("Sim") == false && resposta.equalsIgnoreCase("N�o") == false) {
											System.err.println("Op��o invalida!");
											break;
										}else {
											if (resposta.equalsIgnoreCase("N�o")) {
												System.out.println("At� logo!");
												System.exit(0);
											}
										}
										
									}while (true);
								//READ
								}else if (funcao.equalsIgnoreCase("READ")) {
									
									System.out.println("=================== Consultar Produtos ===================\n");
									
									for (Produto item : produtoDao.get()) {
										
										System.out.println("Produto: " + item.getDescricao() + " Fabricante: " + item.getFabricante() + " Valor: " + item.getValor());
										
									}
									
									System.exit(0);
								
								//UPDATE
								}else if (funcao.equalsIgnoreCase("UPDATE")) {
									
									System.out.println("=================== Atualizar Produtos ===================\n");
									
									Produto produto = new Produto();
									
									do {
										//Solicita o PK do produto na base de dados
										System.out.println("Digite o ID do produto que deseja atualizar: ");
										produto.setId(Integer.parseInt(input.nextLine()));
										
										//Solicita os valores para UPDATE do registro
										System.out.println("ATUALIZANDO - Digite a descri��o do produto: ");
										produto.setDescricao(input.nextLine());
										
										System.out.println("ATUALIZANDO - Digite o fabricante do produto: ");
										produto.setFabricante(input.nextLine());
										
										System.out.println("ATUALIZANDO - Digite o valor do produto: ");
										produto.setValor(input.nextFloat());
										input.nextLine();
										
										produto.setDatacadastro(new Date());
										
										produtoDao.update(produto);
										
										System.out.println("DESEJA ATUALIZAR OUTRO PRODUTO?\nSim ou N�o");
										resposta = input.nextLine();
										
										if (resposta.equalsIgnoreCase("Sim") == false && resposta.equalsIgnoreCase("N�o") == false) {
											System.err.println("Op��o invalida!");
											break;
										}else {
											if (resposta.equalsIgnoreCase("N�o")) {
												System.out.println("At� logo!");
												System.exit(0);
											}
										}
										
									}while (true);
									
								//DELETE	
								}else if (funcao.equalsIgnoreCase("DELETE")) {
									
									System.out.println("=================== Deletar Produtos ===================\n");
									
									do {
										System.out.println("Digite o ID do produto que deseja deletar: ");
										produtoDao.deleteByID(Integer.parseInt(input.nextLine()));

										System.out.println("DESEJA DELETAR OUTRO PRODUTO?\nSim ou N�o");
										resposta = input.nextLine();
										
										if (resposta.equalsIgnoreCase("Sim") == false && resposta.equalsIgnoreCase("N�o") == false) {
											System.err.println("Op��o invalida!");
											break;
										}else {
											if (resposta.equalsIgnoreCase("N�o")) {
												System.out.println("At� logo!");
												System.exit(0);
											}
										}
										
									}while (true);
								}
							}
						}while (true);
				}
			}while (true);
		}
}