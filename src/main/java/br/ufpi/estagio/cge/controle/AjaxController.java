package br.ufpi.estagio.cge.controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import br.ufpi.estagio.cge.extrator.AlgoritmoBusca;
import br.ufpi.estagio.cge.grafico.Dados;
import br.ufpi.estagio.cge.grafico.GraficoDispersao;
import br.ufpi.estagio.cge.grafico.GraficoLinha;
import br.ufpi.estagio.cge.grafico.GraficoPizza;
import br.ufpi.estagio.cge.modelo.Movimento;
import br.ufpi.estagio.cge.sistema.excecoes.DetalheMediaException;
import br.ufpi.estagio.cge.sistema.excecoes.DetalheServidorException;
import br.ufpi.estagio.cge.sistema.excecoes.DispersaoException;
import br.ufpi.estagio.cge.sistema.excecoes.DistribuicaoGastoException;
import br.ufpi.estagio.cge.sistema.excecoes.DownloadException;
import br.ufpi.estagio.cge.sistema.excecoes.FaixaEtariaException;
import br.ufpi.estagio.cge.sistema.excecoes.GastoSalarioException;
import br.ufpi.estagio.cge.sistema.excecoes.ListarCargosException;
import br.ufpi.estagio.cge.sistema.excecoes.ListarCategoriasException;
import br.ufpi.estagio.cge.sistema.excecoes.ListarOrgaosException;
import br.ufpi.estagio.cge.sistema.excecoes.MediaSalarioException;
import br.ufpi.estagio.cge.sistema.excecoes.PiramideException;
import br.ufpi.estagio.cge.sistema.excecoes.SomaSalarioException;
import br.ufpi.estagio.cge.sistema.exportarExcel.ExportarExcel;

/**
 * Classe responsavel por responder as requisicoes via Ajax
 * 
 * @author Irvayne Matheus
 *
 */
@RestController
public class AjaxController {
	
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	

	/**
	 * Metodo que retorna todos os cargos
	 * 
	 * @return - uma lista com todos os Cargos
	 * @throws Exception 
	 */
	@RequestMapping(value = "/listarCargos", method = RequestMethod.POST)
	public ArrayList<String> listarCargos() throws Exception {

		try {
			return AlgoritmoBusca.listarCargos();

		} catch (ListarCargosException e) {
			// TODO Auto-generated catch block
			throw new ListarCargosException(e.getMessage());
		}

	}
	
	/**
	 * Metodo que lista todas as Categorias
	 * 
	 * @return - uma lista com todas as categorias
	 * @throws Exception 
	 */
	@RequestMapping(value = "/listarCategorias", method = RequestMethod.POST)
	public ArrayList<String> listarCategorias() throws Exception {

		try {
			return AlgoritmoBusca.listarCategorias();

		} catch (ListarCategoriasException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	/**
	 * Metodo que lista todos os Orgaos
	 * 
	 * @return - uma lista com todos os orgaos
	 * @throws Exception 
	 */
	@RequestMapping(value = "/listarOrgaos", method = RequestMethod.POST)
	public ArrayList<String> listarOrgaos() throws Exception {

		try {
			return AlgoritmoBusca.listarOrgaos();
		} catch (ListarOrgaosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Metodo que exibe o grafico de Media Salarial
	 * 
	 * @param dados
	 *            - informacoes vindas da view
	 * @return - grafico linha com a media salarial
	 * @throws Exception 
	 */
	@RequestMapping(value = "/mediaSalarial", method = RequestMethod.POST)
	public String graficoMediaSalarial(@RequestBody Dados dados) throws Exception {

		try {
			ArrayList<GraficoLinha> g = AlgoritmoBusca.mediaSalarial(dados);
			Gson json = new Gson();
			String servidores = json.toJson(g);
			return servidores;
		} catch (MediaSalarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	/**
	 * Metodo que exibe o grafico de Media Salarial
	 * 
	 * @param dados
	 *            - informacoes vindas da view
	 * @return - grafico linha com a media salarial
	 * @throws Exception 
	 */
	@RequestMapping(value = "/somaSalarial", method = RequestMethod.POST)
	public String graficoSomaSalarial(@RequestBody Dados dados) throws Exception {

		try {
			ArrayList<GraficoLinha> g = AlgoritmoBusca.somaSalarial(dados);
			Gson json = new Gson();
			String servidores = json.toJson(g);
			return servidores;
		} catch (SomaSalarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Metodo que exibe os gastos salariais mensal dos cargos/categorias/orgaos
	 * 
	 * @param dados
	 *            - informacoes vindas da view
	 * @return - grafico pizza com a distribuicao dos gastos salariais
	 * @throws Exception 
	 */
	@RequestMapping(value = "/gastoSalarial", method = RequestMethod.POST)
	public String gastoSalarial(@RequestBody Dados dados) throws Exception {

		try {
			ArrayList<GraficoPizza> g = AlgoritmoBusca.gastoSalarial(dados);
			Gson json = new Gson();
			String gasto = json.toJson(g);
			return gasto;
		} catch (GastoSalarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}


	/**
	 * Metodo que exibe uma grafico piramide com a expessura proporcional a qnt
	 * de servidores e do topo para a base ordenado por maior salario
	 * 
	 * @param dados
	 *            - Informacoes vindas da view
	 * @return - grafico piramidal com as categorias/orgaos
	 * @throws Exception 
	 */
	@RequestMapping(value = "/backend/piramide", method = RequestMethod.POST)
	public String piramideHierarquica(@RequestBody Dados dados) throws Exception {

		try {
			return AlgoritmoBusca.piramideHierarquica(dados);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Metodo que exibe a faixa etaria dos servidores em uma determinada opcao
	 * escolhida
	 * 
	 * @param dados
	 *            - Informacoes vindas da view
	 * @return - grafico coluna com a faixa etaria dos servidores
	 * @throws Exception 
	 */
	@RequestMapping(value = "/faixaEtaria", method = RequestMethod.POST)
	public String faixaEtaria(@RequestBody Dados dados) throws FaixaEtariaException, SQLException , Exception {

		try {
			return AlgoritmoBusca.faixaEtaria((dados));

		} catch (FaixaEtariaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Metodo para gerar um grafico de dispersao dos servidores de um
	 * determinado cargo/orgao/categoria em um periodo de tempo
	 * 
	 * @param dados
	 *            - Informacoes vindas na view
	 * @return - grafico de dispersao dos servidores
	 * @throws Exception 
	 */
	@RequestMapping(value = "/dispersao", method = RequestMethod.POST)
	public String graficoDispersao(@RequestBody Dados dados) throws Exception {

		try {
			ArrayList<GraficoDispersao> graf = AlgoritmoBusca.graficoDispersaoSalarioPorTempo(dados);
			Gson json = new Gson();
			String servidores = json.toJson(graf);
			return "[" + servidores.substring(1);

		} catch (DispersaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Metodo que exibe os movimentos detalhados dos servidores
	 * 
	 * @param dados
	 *            - Informacoes vindas na view
	 * @return - os movimentos de um determinado servidor
	 * @throws Exception 
	 */
	@RequestMapping(value = "/detalheServidor", method = RequestMethod.POST)
	public String detalheServidor(@RequestBody Dados dados) throws Exception {

		try {
			return AlgoritmoBusca.informacaoServidor(dados);
		} catch (DetalheServidorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Metodo que detalha os movimentos de um determinado ponto do grafico de
	 * media
	 * 
	 * @param dados
	 *            - Informacoes vindas da view
	 * @return - os movimentos que causaram o valor da media
	 * @throws Exception 
	 */
	@RequestMapping(value = "/detalheMedia", method = RequestMethod.POST)
	public String detalheMedia(@RequestBody Dados dados) throws Exception {

		try {			
			List<Movimento> movimentos=  AlgoritmoBusca.informacaoMedia(dados);
			Gson json = new Gson();
			return json.toJson(movimentos);
		} catch (DetalheMediaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Metodo para exibir os gastos brutos dos orgao/categorias
	 * 
	 * @param dados
	 *            - Informacoes vindas da view
	 * @return - grafico pizza com a distribuicao dos gastos
	 * @throws Exception 
	 */
	@RequestMapping(value = "/distribuicaoGasto", method = RequestMethod.POST)
	public String distribuicaoGasto(@RequestBody Dados dados) throws Exception {

		try {
			return AlgoritmoBusca.porcentagemGasto(dados);
		} catch (DistribuicaoGastoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Metodo que gera excel para exportacao
	 * 
	 * @param request
	 *            - dados vindos da view
	 * @return - excel gerado
	 * @throws Exception 
	 */
	@RequestMapping(value = "/download", method = RequestMethod.POST)
	public ModelAndView exportarExcel(HttpServletRequest request) throws Exception {
		try {
			return ExportarExcel.exportar(request);
		} catch (DownloadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
