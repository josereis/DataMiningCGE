package br.ufpi.estagio.cge.controle;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.ufpi.estagio.cge.sistema.excecoes.DispersaoException;
import br.ufpi.estagio.cge.sistema.excecoes.DistribuicaoGastoException;
import br.ufpi.estagio.cge.sistema.excecoes.FaixaEtariaException;
import br.ufpi.estagio.cge.sistema.excecoes.MapaDemograficoException;
import br.ufpi.estagio.cge.sistema.excecoes.MediaSalarioException;
import br.ufpi.estagio.cge.sistema.excecoes.PiramideException;
import br.ufpi.estagio.cge.sistema.excecoes.SomaSalarioException;
/**
 * Classe responsavel por carregar as views
 * @author Irvayne Matheus
 *
 */
@RestController
public class HomeController {
	/**
	 * Metodo para carregar a view inicial
	 * @return - view index
	 */
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	/*@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		
		return "index";
	}*/
	/**
	 * Metodo para carregar a view mediaSalarial
	 * @return - view mediaSalarial
	 * @throws MediaSalarioException 
	 */
	@RequestMapping(value = "/mediaSalarial", method = RequestMethod.GET)
	public String mediaSalarial() throws MediaSalarioException{
		
		//return "mediaSalarial";
		
		throw new MediaSalarioException("Teste!");
	}
	
	@ExceptionHandler(MediaSalarioException.class)
	public ModelAndView handleMediaSalarioException(HttpServletRequest request, Exception ex){
		logger.error("Requested URL="+request.getRequestURL());
		logger.error("Exception Raised="+ex);
		
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("exception", ex);
	    modelAndView.addObject("url", request.getRequestURL());
	    
	    modelAndView.setViewName("error");
	    return modelAndView;
	}
	/**
	 * Metodo para carregar a view piramidehierarquica
	 * @return - view piramideHierarrquica
	 */
	@RequestMapping(value = "/piramideHierarquica", method = RequestMethod.GET)
	public String piramideHierarquica() throws PiramideException{
		
		return "piramideHierarquica";
	}

	@ExceptionHandler(PiramideException.class)
	public ModelAndView handlePiramideException(HttpServletRequest request, Exception ex){
		logger.error("Requested URL="+request.getRequestURL());
		logger.error("Exception Raised="+ex);
		
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("exception", ex);
	    modelAndView.addObject("url", request.getRequestURL());
	    
	    modelAndView.setViewName("error");
	    return modelAndView;
	}

	/**
	 * Metodo para carregar a view dispersaoservidores
	 * @return - view dispersaoServidores
	 */
	@RequestMapping(value = "/dispersaoServidores", method = RequestMethod.GET)
	public String curvaPadrao() throws DispersaoException {
		
		return "dispersaoServidores";
	}
	
	@ExceptionHandler(DispersaoException.class)
	public ModelAndView handleDispersaoException(HttpServletRequest request, Exception ex){
		logger.error("Requested URL="+request.getRequestURL());
		logger.error("Exception Raised="+ex);
		
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("exception", ex);
	    modelAndView.addObject("url", request.getRequestURL());
	    
	    modelAndView.setViewName("error");
	    return modelAndView;
	}

	
	/**
	 * Metodo para carregar a view mapaDemografico
	 * @return - view mapaDemografico
	 */
	@RequestMapping(value = "/mapaDemografico", method = RequestMethod.GET)
	public String mapaDemografico() throws MapaDemograficoException {
		
		return "mapaDemografico";
	}
	
	@ExceptionHandler(MapaDemograficoException.class)
	public ModelAndView handleMapaDemograficoException(HttpServletRequest request, Exception ex){
		logger.error("Requested URL="+request.getRequestURL());
		logger.error("Exception Raised="+ex);
		
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("exception", ex);
	    modelAndView.addObject("url", request.getRequestURL());
	    
	    modelAndView.setViewName("error");
	    return modelAndView;
	}

	/**
	 * Metodo para carregar a view faixaEtaria
	 * @return - view faixaEtaria
	 */
	@RequestMapping(value = "/faixa", method = RequestMethod.GET)
	public String faixaEtaria() throws FaixaEtariaException {
		
		return "faixaEtaria";
	}
	
	@ExceptionHandler(FaixaEtariaException.class)
	public ModelAndView handleFaixaEtariaException(HttpServletRequest request, Exception ex){
		logger.error("Requested URL="+request.getRequestURL());
		logger.error("Exception Raised="+ex);
		
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("exception", ex);
	    modelAndView.addObject("url", request.getRequestURL());
	    
	    modelAndView.setViewName("error");
	    return modelAndView;
	}

	
	
	/**
	 * Metodo para carregar a view porcentagemGasto
	 * @return - view porcentagemGasto
	 */
	@RequestMapping(value = "/porcentagemGasto", method = RequestMethod.GET)
	public String procentagemGasto() throws DistribuicaoGastoException {
		
		return "porcentagemGasto";
	}
	
	@ExceptionHandler(DistribuicaoGastoException.class)
	public ModelAndView handleDistribuicaoGastoException(HttpServletRequest request, Exception ex){
		logger.error("Requested URL="+request.getRequestURL());
		logger.error("Exception Raised="+ex);
		
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("exception", ex);
	    modelAndView.addObject("url", request.getRequestURL());
	    
	    modelAndView.setViewName("error");
	    return modelAndView;
	}

	
	/**
	 * Metodo para carregar a view somaSalarial
	 * @return - view somaSalarial
	 */
	@RequestMapping(value = "/somaSalarial", method = RequestMethod.GET)
	public String somaSalarial() throws SomaSalarioException {
		
		return "somaSalarial";
	}
	
	@ExceptionHandler(SomaSalarioException.class)
	public ModelAndView handleSomaSalarioException(HttpServletRequest request, Exception ex){
		logger.error("Requested URL="+request.getRequestURL());
		logger.error("Exception Raised="+ex);
		
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("exception", ex);
	    modelAndView.addObject("url", request.getRequestURL());
	    
	    modelAndView.setViewName("error");
	    return modelAndView;
	}

	
	
}
