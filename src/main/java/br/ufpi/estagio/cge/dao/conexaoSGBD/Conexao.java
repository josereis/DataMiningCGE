package br.ufpi.estagio.cge.dao.conexaoSGBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.ufpi.estagio.cge.sistema.excecoes.ConexaoException;
/**
 * Classe para realizar a conexao com o banco de dados
 * @author Irvayne Matheus
 *
 */
public class Conexao {

	static Connection conexao = null;
	
	/**
	 * Metodo que retorna uma instancia da conexao.
	 * @return - conexao ativa
	 * @throws Exception 
	 * @throws ConexaoException 
	 */
	public static Connection getConnection() throws ConexaoException, Exception{
		if (conexao == null){
           new Conexao();			
		}
		return conexao;
	}
	
	/**
	 * Relaiza a conexao com o banco. Deve ser informado as configuracoes do banco nesse metodo
	 * @throws ConexaoException 
	 * @throws Exception 
	 */
	private Conexao() throws Exception{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/infofolha_cge",
					"root", "root");System.out.println("Conexao com o banco estabelecida com sucesso !");
	    } catch (ConexaoException e){
	    	  System.out.println("Nao foi possivel conectar ao banco.");
	    	  System.out.println(e.getMessage());
	    }
	}
}
