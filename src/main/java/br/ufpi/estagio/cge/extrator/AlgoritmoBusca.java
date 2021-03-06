package br.ufpi.estagio.cge.extrator;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import br.ufpi.estagio.cge.dao.CargoDAO;
import br.ufpi.estagio.cge.dao.CategoriaDAO;
import br.ufpi.estagio.cge.dao.InformacoesCargoDAO;
import br.ufpi.estagio.cge.dao.InformacoesCategoriaDAO;
import br.ufpi.estagio.cge.dao.InformacoesOrgaoDAO;
import br.ufpi.estagio.cge.dao.MovimentoDAO;
import br.ufpi.estagio.cge.dao.OrgaoDAO;
import br.ufpi.estagio.cge.dao.ServidorDAO;
import br.ufpi.estagio.cge.grafico.Dados;
import br.ufpi.estagio.cge.grafico.GraficoDispersao;
import br.ufpi.estagio.cge.grafico.GraficoLinha;
import br.ufpi.estagio.cge.grafico.GraficoPizza;
import br.ufpi.estagio.cge.modelo.Movimento;
import br.ufpi.estagio.cge.modelo.Servidor;
import br.ufpi.estagio.cge.sistema.excecoes.FaixaEtariaException;

/**
 * Classe que contem os metodos/algoritmos que realizam o processo de
 * busca/mineracao dos dado
 * 
 * @author Irvayne Matheus
 *
 */
public class AlgoritmoBusca {
	/**
	 * Metodo que lista todos os cargos
	 * 
	 * @return - lista com todos os cargos
	 * @throws Exception 
	 */
	public static ArrayList<String> listarCargos() throws Exception {

		return CargoDAO.listar();

	}

	/**
	 * Metodo que lista todas as categorias
	 * 
	 * @return - lista com todas as categorias
	 * @throws Exception 
	 */
	public static ArrayList<String> listarCategorias() throws Exception {

		return CategoriaDAO.listar();

	}

	/**
	 * Metodo que lista todos os orgaos
	 * 
	 * @return - lista com todos os orgaos
	 * @throws Exception 
	 */
	public static ArrayList<String> listarOrgaos() throws Exception {

		return OrgaoDAO.listar();

	}

	/**
	 * Metodo que realiza a ligacao entre a camada view e a camada dao da
	 * funcionalidade dispersaoSalarial
	 * 
	 * @param dados
	 *            - dados vindos da view
	 * @return - grafico gerado da dao
	 * @throws Exception 
	 */
	public static ArrayList<GraficoDispersao> graficoDispersaoSalarioPorTempo(Dados dados) throws Exception {
		if (dados.getTipo().equals("cargo"))
			return CargoDAO.graficoDispersaoSalarioPorTempo(dados);
		if (dados.getTipo().equals("categoria"))
			return CategoriaDAO.graficoDispersaoSalarioPorTempo(dados);
		if (dados.getTipo().equals("orgao"))
			return OrgaoDAO.graficoDispersaoSalarioPorTempo(dados);

		return null;

	}

	/**
	 * Metodo que processa as informacoes vindas da dao para montar o grafico
	 * piramidal da funcionalidade piramide hierarquica
	 * 
	 * @param dados
	 *            - dados vindos da view
	 * @return - grafico a ser exibido na view
	 * @throws Exception 
	 */
	public static String piramideHierarquica(Dados dados) throws Exception {

		if (dados.getTipo().equals("orgao")) {
			ArrayList<String> orgaos = OrgaoDAO.listar();

			ArrayList<String> datas = new ArrayList<>();
			datas.add(dados.getReferencia().concat("04"));
			for (String aux : orgaos) {
				OrgaoDAO.listarMediaSalarial(aux,"", datas,"soma_bruto");
			}
			ArrayList<String> ordenado = InformacoesOrgaoDAO.listarOrgaosDecrescente(datas.get(0));

			ArrayList<Integer> qntServidores = new ArrayList<>();
			for (String aux : ordenado) {
				int x = InformacoesOrgaoDAO.buscarQuantidade(aux, datas.get(0));
				if (x == -1)
					qntServidores.add(10);
				else
					qntServidores.add(x);
			}
			Gson json = new Gson();

			String result = "[{ " + json.toJson("name") + ":" + json.toJson("Quantidade de Servidores") + ", "
					+ json.toJson("data") + ":[[";
			for (int i = 0; i < ordenado.size(); i++) {
				result = result + json.toJson(ordenado.get(i)) + "," + qntServidores.get(i);
				if (i != ordenado.size() - 1) {
					result = result + "],[";
				}
			}
			result = result + "]]}]";

			return result;

		}

		if (dados.getTipo().equals("categoria")) {
			ArrayList<String> categorias = CategoriaDAO.listar();

			ArrayList<String> datas = new ArrayList<>();
			datas.add(dados.getReferencia().concat("04"));
			for (String aux : categorias) {
				CategoriaDAO.listarMediaSalarial(aux, datas, "soma_bruto");
			}
			ArrayList<String> ordenado = InformacoesCategoriaDAO.listarCategoriasDecrescente(datas.get(0));

			ArrayList<Integer> qntServidores = new ArrayList<>();
			for (String aux : ordenado) {
				int x = InformacoesCategoriaDAO.buscarQuantidade(aux, datas.get(0));
				if (x == -1)
					qntServidores.add(10);
				else
					qntServidores.add(x);
			}

			Gson json = new Gson();
			String result = "[{ " + json.toJson("name") + ":" + json.toJson("Quantidade de Servidores") + ", "
					+ json.toJson("data") + ":[[";
			for (int i = 0; i < ordenado.size(); i++) {
				result = result + json.toJson(ordenado.get(i)) + "," + qntServidores.get(i);
				if (i != ordenado.size() - 1) {
					result = result + "],[";
				}
			}
			result = result + "]]}]";

			return result;

		}

		return null;

	}


	public static ArrayList<GraficoLinha> somaSalarial(Dados dados) throws Exception {

		ArrayList<GraficoLinha> grafico = new ArrayList<>();
		if (dados.getTipoSalario().equals("liquido")) {
			dados.setTipoSalario("soma_liquido");
		} else {
			dados.setTipoSalario("soma_bruto");

		}

		if (dados.getTipo().equals("cargo")) {
			ArrayList<String> inputCargo = new ArrayList<>();
			ArrayList<String> inputCategoria = new ArrayList<>();
			ArrayList<String> inputOrgao = new ArrayList<>();
			inputCargo.add(dados.getInput1());
			inputCategoria.add(dados.getInput2());
			inputOrgao.add(dados.getInput3());

			if (dados.getInput4() != null) {
				inputCargo.add(dados.getInput4());
				inputCategoria.add(dados.getInput5());
				inputOrgao.add(dados.getInput6());

				if (dados.getInput7() != null) {
					inputCargo.add(dados.getInput7());
					inputCategoria.add(dados.getInput8());
					inputOrgao.add(dados.getInput9());
					grafico.add(CargoDAO.listarSomaSalarial(inputCargo.get(0), gerarDatas(dados.getReferencia()),
							dados.getTipoSalario(), inputCategoria.get(0), inputOrgao.get(0)));
					grafico.add(CargoDAO.listarSomaSalarial(inputCargo.get(1), gerarDatas(dados.getReferencia()),
							dados.getTipoSalario(), inputCategoria.get(1), inputOrgao.get(1)));
					grafico.add(CargoDAO.listarSomaSalarial(inputCargo.get(2), gerarDatas(dados.getReferencia()),
							dados.getTipoSalario(), inputCategoria.get(2), inputOrgao.get(2)));

					return grafico;

				}
				grafico.add(CargoDAO.listarSomaSalarial(inputCargo.get(0), gerarDatas(dados.getReferencia()),
						dados.getTipoSalario(), inputCategoria.get(0), inputOrgao.get(0)));
				grafico.add(CargoDAO.listarSomaSalarial(inputCargo.get(1), gerarDatas(dados.getReferencia()),
						dados.getTipoSalario(), inputCategoria.get(1), inputOrgao.get(1)));
				return grafico;
			}

			grafico.add(CargoDAO.listarSomaSalarial(inputCargo.get(0), gerarDatas(dados.getReferencia()),
					dados.getTipoSalario(), inputCategoria.get(0), inputOrgao.get(0)));
		} else if (dados.getTipo().equals("categoria")) {
			ArrayList<String> inputCategoria = new ArrayList<>();
			inputCategoria.add(dados.getInput1());
			if (dados.getInput2() != null) {
				inputCategoria.add(dados.getInput2());
				if (dados.getInput3() != null) {
					inputCategoria.add(dados.getInput3());

					grafico.add(CategoriaDAO.listarSomaSalarial(inputCategoria.get(0),
							gerarDatas(dados.getReferencia()), dados.getTipoSalario()));
					grafico.add(CategoriaDAO.listarSomaSalarial(inputCategoria.get(1),
							gerarDatas(dados.getReferencia()), dados.getTipoSalario()));
					grafico.add(CategoriaDAO.listarSomaSalarial(inputCategoria.get(2),
							gerarDatas(dados.getReferencia()), dados.getTipoSalario()));
					return grafico;

				}
				grafico.add(CategoriaDAO.listarSomaSalarial(inputCategoria.get(0), gerarDatas(dados.getReferencia()),
						dados.getTipoSalario()));
				grafico.add(CategoriaDAO.listarSomaSalarial(inputCategoria.get(1), gerarDatas(dados.getReferencia()),
						dados.getTipoSalario()));

				return grafico;

			}
			grafico.add(CategoriaDAO.listarSomaSalarial(inputCategoria.get(0), gerarDatas(dados.getReferencia()),
					dados.getTipoSalario()));
			return grafico;

		} else if (dados.getTipo().equals("orgao")) {
			ArrayList<String> inputOrgao = new ArrayList<>();
			ArrayList<String> inputCategoria = new ArrayList<>();
			inputOrgao.add(dados.getInput1());
			inputCategoria.add(dados.getInput2());

			if (dados.getInput3() != null) {
				inputOrgao.add(dados.getInput3());
				inputCategoria.add(dados.getInput4());

				if (dados.getInput5() != null) {
					inputOrgao.add(dados.getInput5());
					inputCategoria.add(dados.getInput6());
					grafico.add(OrgaoDAO.listarSomaSalarial(inputOrgao.get(0), inputCategoria.get(0),
							gerarDatas(dados.getReferencia()), dados.getTipoSalario()));
					grafico.add(OrgaoDAO.listarSomaSalarial(inputOrgao.get(1), inputCategoria.get(1),
							gerarDatas(dados.getReferencia()), dados.getTipoSalario()));
					grafico.add(OrgaoDAO.listarSomaSalarial(inputOrgao.get(2), inputCategoria.get(2),
							gerarDatas(dados.getReferencia()), dados.getTipoSalario()));
					return grafico;

				}
				grafico.add(OrgaoDAO.listarSomaSalarial(inputOrgao.get(0), inputCategoria.get(0),
						gerarDatas(dados.getReferencia()), dados.getTipoSalario()));
				grafico.add(OrgaoDAO.listarSomaSalarial(inputOrgao.get(1), inputCategoria.get(1),
						gerarDatas(dados.getReferencia()), dados.getTipoSalario()));
				return grafico;
			}
			grafico.add(OrgaoDAO.listarSomaSalarial(inputOrgao.get(0), inputCategoria.get(0),
					gerarDatas(dados.getReferencia()), dados.getTipoSalario()));
			return grafico;

		}
		return grafico;
	}
	
	public static ArrayList<GraficoLinha> mediaSalarial(Dados dados) throws Exception {

		ArrayList<GraficoLinha> grafico = new ArrayList<>();
		if (dados.getTipoSalario().equals("liquido")) {
			dados.setTipoSalario("soma_liquido");
		} else {
			dados.setTipoSalario("soma_bruto");

		}

		if (dados.getTipo().equals("cargo")) {
			ArrayList<String> inputCargo = new ArrayList<>();
			ArrayList<String> inputCategoria = new ArrayList<>();
			ArrayList<String> inputOrgao = new ArrayList<>();
			inputCargo.add(dados.getInput1());
			inputCategoria.add(dados.getInput2());
			inputOrgao.add(dados.getInput3());

			if (dados.getInput4() != null) {
				inputCargo.add(dados.getInput4());
				inputCategoria.add(dados.getInput5());
				inputOrgao.add(dados.getInput6());

				if (dados.getInput7() != null) {
					inputCargo.add(dados.getInput7());
					inputCategoria.add(dados.getInput8());
					inputOrgao.add(dados.getInput9());
					grafico.add(CargoDAO.listarMediaSalarial(inputCargo.get(0), gerarDatas(dados.getReferencia()),
							dados.getTipoSalario(), inputCategoria.get(0), inputOrgao.get(0)));
					grafico.add(CargoDAO.listarMediaSalarial(inputCargo.get(1), gerarDatas(dados.getReferencia()),
							dados.getTipoSalario(), inputCategoria.get(1), inputOrgao.get(1)));
					grafico.add(CargoDAO.listarMediaSalarial(inputCargo.get(2), gerarDatas(dados.getReferencia()),
							dados.getTipoSalario(), inputCategoria.get(2), inputOrgao.get(2)));

					return grafico;

				}
				grafico.add(CargoDAO.listarMediaSalarial(inputCargo.get(0), gerarDatas(dados.getReferencia()),
						dados.getTipoSalario(), inputCategoria.get(0), inputOrgao.get(0)));
				grafico.add(CargoDAO.listarMediaSalarial(inputCargo.get(1), gerarDatas(dados.getReferencia()),
						dados.getTipoSalario(), inputCategoria.get(1), inputOrgao.get(1)));
				return grafico;
			}

			grafico.add(CargoDAO.listarMediaSalarial(inputCargo.get(0), gerarDatas(dados.getReferencia()),
					dados.getTipoSalario(), inputCategoria.get(0), inputOrgao.get(0)));
		} else if (dados.getTipo().equals("categoria")) {
			ArrayList<String> inputCategoria = new ArrayList<>();
			inputCategoria.add(dados.getInput1());
			if (dados.getInput2() != null) {
				inputCategoria.add(dados.getInput2());
				if (dados.getInput3() != null) {
					inputCategoria.add(dados.getInput3());

					grafico.add(CategoriaDAO.listarMediaSalarial(inputCategoria.get(0),
							gerarDatas(dados.getReferencia()), dados.getTipoSalario()));
					grafico.add(CategoriaDAO.listarMediaSalarial(inputCategoria.get(1),
							gerarDatas(dados.getReferencia()), dados.getTipoSalario()));
					grafico.add(CategoriaDAO.listarMediaSalarial(inputCategoria.get(2),
							gerarDatas(dados.getReferencia()), dados.getTipoSalario()));
					return grafico;

				}
				grafico.add(CategoriaDAO.listarMediaSalarial(inputCategoria.get(0), gerarDatas(dados.getReferencia()),
						dados.getTipoSalario()));
				grafico.add(CategoriaDAO.listarMediaSalarial(inputCategoria.get(1), gerarDatas(dados.getReferencia()),
						dados.getTipoSalario()));

				return grafico;

			}
			grafico.add(CategoriaDAO.listarMediaSalarial(inputCategoria.get(0), gerarDatas(dados.getReferencia()),
					dados.getTipoSalario()));
			return grafico;

		} else if (dados.getTipo().equals("orgao")) {
			ArrayList<String> inputOrgao = new ArrayList<>();
			ArrayList<String> inputCategoria = new ArrayList<>();
			inputOrgao.add(dados.getInput1());
			inputCategoria.add(dados.getInput2());

			if (dados.getInput3() != null) {
				inputOrgao.add(dados.getInput3());
				inputCategoria.add(dados.getInput4());

				if (dados.getInput5() != null) {
					inputOrgao.add(dados.getInput5());
					inputCategoria.add(dados.getInput6());
					grafico.add(OrgaoDAO.listarMediaSalarial(inputOrgao.get(0), inputCategoria.get(0),
							gerarDatas(dados.getReferencia()), dados.getTipoSalario()));
					grafico.add(OrgaoDAO.listarMediaSalarial(inputOrgao.get(1), inputCategoria.get(1),
							gerarDatas(dados.getReferencia()), dados.getTipoSalario()));
					grafico.add(OrgaoDAO.listarMediaSalarial(inputOrgao.get(2), inputCategoria.get(2),
							gerarDatas(dados.getReferencia()), dados.getTipoSalario()));
					return grafico;

				}
				grafico.add(OrgaoDAO.listarMediaSalarial(inputOrgao.get(0), inputCategoria.get(0),
						gerarDatas(dados.getReferencia()), dados.getTipoSalario()));
				grafico.add(OrgaoDAO.listarMediaSalarial(inputOrgao.get(1), inputCategoria.get(1),
						gerarDatas(dados.getReferencia()), dados.getTipoSalario()));
				return grafico;
			}
			grafico.add(OrgaoDAO.listarMediaSalarial(inputOrgao.get(0), inputCategoria.get(0),
					gerarDatas(dados.getReferencia()), dados.getTipoSalario()));
			return grafico;

		}
		return grafico;
	}

	/**
	 * Metodo que gera as datas de um determinado ano
	 * 
	 * @param referencia
	 *            - ano a ser gerado
	 * @return - lista com todas as datas desse ano
	 */
	private static ArrayList<String> gerarDatas(String referencia) {
		ArrayList<String> datas = new ArrayList<>();
		if (referencia.equals("2014")) {
			datas.add("201401");
			datas.add("201402");
			datas.add("201403");
			datas.add("201404");
			datas.add("201405");
			datas.add("201406");
			datas.add("201407");
			datas.add("201408");
			datas.add("201409");
			datas.add("201410");
			datas.add("201411");
			datas.add("201412");

		}
		if (referencia.equals("2015")) {
			datas.add("201501");
			datas.add("201502");
			datas.add("201503");
			datas.add("201504");
			datas.add("201505");
			datas.add("201506");
			datas.add("201507");
			datas.add("201508");
			datas.add("201509");
			datas.add("201510");
			datas.add("201511");
			datas.add("201512");

		}
		if (referencia.equals("2016")) {
			datas.add("201601");
			datas.add("201602");
			datas.add("201603");
			datas.add("201604");
		}
		return datas;
	}

	/**
	 * Metodo que realiza a ligacao entre a camada dao e a camada view da
	 * funcionalidade faixa etaria
	 * 
	 * @param faixaEtaria
	 *            - dados vindos da view
	 * @return - grafico coluna com as faixas etarias
	 * @throws Exception 
	 */
	public static String faixaEtaria(Dados faixaEtaria) throws FaixaEtariaException, Exception {
		if (faixaEtaria.getTipo().equals("cargo")) {
			return CargoDAO.faixaEtaria(faixaEtaria.getReferencia(), faixaEtaria.getInput1());
		}
		if (faixaEtaria.getTipo().equals("categoria")) {
			return CategoriaDAO.faixaEtaria(faixaEtaria.getReferencia(), faixaEtaria.getInput1());
		}
		if (faixaEtaria.getTipo().equals("orgao")) {
			return OrgaoDAO.faixaEtaria(faixaEtaria.getReferencia(), faixaEtaria.getInput1());
		}
		return null;
	}

	/**
	 * Metodo que gera o grafico pizza da distribuicao dos gastos
	 * 
	 * @param gastoSalarial
	 *            - dados vindos da view
	 * @return - grafico pizza a ser exibido na view
	 * @throws Exception 
	 */
	public static ArrayList<GraficoPizza> gastoSalarial(Dados gastoSalarial) throws Exception {
		ArrayList<GraficoPizza> graficoPizza = new ArrayList<>();
		if (gastoSalarial.getTipo().equals("cargo")) {

			ArrayList<String> cargos = CargoDAO.listar();
			for (String cargo : cargos) {
				double gasto = InformacoesCargoDAO.gastoSalarial(cargo, gastoSalarial.getReferencia());
				GraficoPizza g = new GraficoPizza();
				g.setName(cargo);
				g.setY(gasto);
				graficoPizza.add(g);
			}
			return graficoPizza;

		}
		if (gastoSalarial.getTipo().equals("categoria")) {
			ArrayList<String> categorias = CategoriaDAO.listar();
			for (String categoria : categorias) {
				double gasto = InformacoesCategoriaDAO.gastoSalarial(categoria, gastoSalarial.getReferencia());
				GraficoPizza g = new GraficoPizza();
				g.setName(categoria);
				g.setY(gasto);
				graficoPizza.add(g);
			}
			return graficoPizza;
		}
		if (gastoSalarial.getTipo().equals("orgao")) {

			ArrayList<String> orgoes = OrgaoDAO.listar();
			for (String orgao : orgoes) {
				double gasto = InformacoesOrgaoDAO.gastoSalarial(orgao, gastoSalarial.getReferencia());
				GraficoPizza g = new GraficoPizza();
				g.setName(orgao);
				g.setY(gasto);
				graficoPizza.add(g);
			}
			return graficoPizza;
		}

		return graficoPizza;
	}

	/**
	 * Metodo para exibir as informacoes de um servidor
	 * 
	 * @param dados
	 *            - dados vindos da view
	 * @return - informacoes do servidor
	 * @throws Exception 
	 */
	public static String informacaoServidor(Dados dados) throws Exception {

		String[] val = dados.getInput1().split("Nome");
		val = val[0].split(":");
		String matricula = val[1];
		matricula = matricula.substring(1);

		Servidor servidor = ServidorDAO.buscar(matricula.substring(0, matricula.length()-1));
		servidor.setCategoria(CategoriaDAO.buscarDescricao(servidor.getCategoria()));

		List<Movimento> movimento = MovimentoDAO.listarMovimento(matricula.substring(0, matricula.length()-1), dados.getReferencia());
		servidor.setMovimentos(movimento);

		double bruto = 0;
		double liquido = 0;

		for (Movimento aux : movimento) {
			if (aux.getTipo().equals("V")) {
				bruto = bruto + aux.getValor();
				liquido = liquido + aux.getValor();
			} else {
				liquido = liquido - aux.getValor();
			}
		}
		servidor.setSalarioLiquido(liquido);
		servidor.setSalarioBruto(bruto);

		Gson json = new Gson();
		return json.toJson(servidor);
	}

	/**
	 * Metodo que lista as informacoes em cada media, os movimentos que
	 * influenciaram nessa media
	 * 
	 * @param dados
	 *            - dados vindos da view
	 * @return - os movimentos desse ponto especifico da media
	 * @throws Exception 
	 */
	public static List<Movimento> informacaoMedia(Dados dados) throws Exception {

		String[] inf = dados.getInput1().split("--");

		List<Movimento> movimento = MovimentoDAO.listarMovimentoPorDescricao(dados.getTipo(), inf, dados.getReferencia());
		
		
		return movimento;
	}

	/**
	 * Metodo que gera o grafico pizza da distribuicao dos gastos
	 * 
	 * @param gastoSalarial
	 *            - dados vindos da view
	 * @return - grafico pizza a ser exibido na view
	 * @throws Exception 
	 */
	public static String porcentagemGasto(Dados dados) throws Exception {

		ArrayList<GraficoPizza> grafico = null;

		if (dados.getTipo().equals("cargo")) {
			grafico = CargoDAO.porcentagemGasto(dados.getReferencia());
		}
		if (dados.getTipo().equals("categoria")) {
			grafico = CategoriaDAO.porcentagemGasto(dados.getReferencia());
		}
		if (dados.getTipo().equals("orgao")) {
			grafico = OrgaoDAO.porcentagemGasto(dados.getReferencia());
		}

		Gson json = new Gson();
		return json.toJson(grafico);
	}

}
