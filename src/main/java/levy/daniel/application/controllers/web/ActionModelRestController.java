package levy.daniel.application.controllers.web;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import levy.daniel.application.model.Personne;


/**
 * CLASSE ActionModelRestController :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author Daniel Lévy
 * @version 1.0
 * @since 16 déc. 2019
 */
@RestController(value="ActionModelRestController")
@RequestMapping(value="/ActionModelRestController")
public class ActionModelRestController {

	// ************************ATTRIBUTS************************************/

	/**
	 * "text/plain;charset=UTF-8".
	 */
	public static final String TEXT_PLAIN = "text/plain;charset=UTF-8";
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(ActionModelRestController.class);

	// *************************METHODES************************************/

	/**
	* CONSTRUCTEUR D'ARITE NULLE.
	*/
	public ActionModelRestController() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * "/ActionModelRestController/retournerTexteApresRequeteParametree".
	 * <br/>
	 * "http://localhost:8080/ActionModelRestController/retournerTexteApresRequeteParametree?nom=Martin&age=24"<br/>
	 * <br/>
	 * 
	 * @param nom
	 * @param age
	 *
	 * @return String
	 */
	@GetMapping(value="/retournerTexteApresRequeteParametree", produces = TEXT_PLAIN)
	public String retournerTexteApresRequeteParametree(final String nom, final String age) {
		return String.format(Locale.getDefault(), "salutations de France [%1s-%2s] !", nom, age);
	} // Fin de retournerTexteApresRequeteParametree().____________________
	
	
	
	/**
	 * "/ActionModelRestController/retournerTexteApresRequeteParametreePost".
	 * <br/>
	 * "http://localhost:8080/ActionModelRestController/retournerTexteApresRequeteParametreePost?nom=Martin&age=24"<br/>
	 * Nécessite un CLIENT HTTP. Un POST est INTERDIT directement dans l'URL d'un navigateur.
	 * <br/>
	 *
	 * @param nom
	 * @param age
	 * 
	 * @return String
	 */
	@PostMapping(value="/retournerTexteApresRequeteParametreePost", produces = TEXT_PLAIN)
	public String retournerTexteApresRequeteParametreePost(final String nom, final String age) {
		return String.format(Locale.getDefault(), "salutations de France [%1s-%2s] !", nom, age);
	} // Fin de retournerTexteApresRequeteParametreePost().__________________


	
	/**
	 * "/ActionModelRestController/retournerTableauTexteApresRequeteParametree".
	 * <br/>
	 * http://localhost:8080/ActionModelRestController/retournerTableauTexteApresRequeteParametree?nom=nom1&nom=nom2&nom=nom3<br/>
	 * <br/>
	 *
	 * @param nom
	 * 
	 * @return String
	 */
	@GetMapping(value="/retournerTableauTexteApresRequeteParametree", produces = TEXT_PLAIN)
	public String retournerTableauTexteApresRequeteParametree(final String... nom) {
		return String.format(Locale.getDefault(), "salutations de France [%1s] !", String.join("-", nom));
	} // Fin de retournerTableauTexteApresRequeteParametree(...).__________

	
	
	/**
	 * "/ActionModelRestController/retournerObjetApresRequeteParametree".
	 * <br/>
	 * "http://localhost:8080/ActionModelRestController/retournerObjetApresRequeteParametree?id=7&prenom=Marcel&age=47"<br/>
	 * <br/>
	 *
	 * @param personne
	 * @return
	 */
	@GetMapping(value="/retournerObjetApresRequeteParametree")
	public Personne retournerObjetApresRequeteParametree(final Personne personne) {
		return personne;
	} // Fin de retournerObjetApresRequeteParametree(...)._________________

	
	
	/**
	 * "/ActionModelRestController/retournerObjetApresElementUrl/{elementUrl1}/toto/{elementUrl2}".
	 * <br/>
	 * "http://localhost:8080/ActionModelRestController/retournerObjetApresElementUrl/250/toto/56"<br/>
	 * <br/>
	 *
	 * @param pElementUrl1
	 * @param pElementUrl2
	 * @return
	 */
	@GetMapping(value="/retournerObjetApresElementUrl/{elementUrl1}/toto/{elementUrl2}")
	public Map<String, String> retournerObjetApresElementUrl(
			@PathVariable(name = "elementUrl1") final String pElementUrl1
				, @PathVariable(name = "elementUrl2") final String pElementUrl2) {
		
		final Map<String, String> resultat = new HashMap<String, String>();
		
		resultat.put("elementUrl1", pElementUrl1);
		resultat.put("elementUrl2", pElementUrl2);
		
		return resultat;
		
	} // Fin de retournerObjetApresElementUrl(...).________________________

	
	
	/**
	 * "/ActionModelRestController/lireTouteRequete".
	 *
	 * @param pRequest
	 * @param nom
	 * 
	 * @return String
	 */
	@GetMapping(value = "/lireTouteRequete", produces = TEXT_PLAIN)
	public String lireTouteRequete(
			final HttpServletRequest pRequest
				, final String nom) {
		
		final StringBuffer stb = new StringBuffer();
		final Enumeration<String> headerNames = pRequest.getHeaderNames();
		
		while (headerNames.hasMoreElements()) {
			final String name = headerNames.nextElement();
			stb.append(String.format("%1s : %2s", name, pRequest.getHeader(name)));
			stb.append('\n');
		}
		
		stb.append(String.format("%1s : %2s", "paramètre nom", nom));
		
		return stb.toString();
		
	} // Fin de lireTouteRequete(...)._____________________________________
	
	
	
}
