/**
 * 
 */
package levy.daniel.application.controllers.web;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import levy.daniel.application.model.GreetingRest;

/**
 * CLASSE GreetingRestController :<br/>
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
 * @author dan Lévy
 * @version 1.0
 * @since 15 déc. 2019
 */
@RestController(value = "GreetingRestController")
@RequestMapping("/GreetingRestController")
public class GreetingRestController {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * "Hello, %1s".
	 */
	private static final String PATTERN = "Hello, %1s";
	
	/**
	 * 
	 */
	private final AtomicLong compteur = new AtomicLong();
	

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(GreetingRestController.class);
	

	// *************************METHODES************************************/
	

	
	/**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public GreetingRestController() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * retourne un objet GreetingRest.
	 * <br/>
	 * "/GreetingRestController/greeting"
	 * http://localhost:8080/greeting?nom=Yannick%20Noah<br/>
	 * <br/>
	 * 
	 * @param pNom : String
	 * 
	 * @return GreetingRest
	 */
	@GetMapping("/greeting")
	public GreetingRest greeting(
			@RequestParam(value = "nom", required = false, defaultValue = "Johnny") 
			final String pNom) {
		
		final GreetingRest greetingRest 
			= new GreetingRest(
					compteur.getAndIncrement()
					, String.format(Locale.getDefault(), PATTERN, pNom));
		
		return greetingRest;
		
	} // Fin de versPageGreeting(...)._____________________________________
	
	

} // FIN DE LA CLASSE GreetingRestController.--------------------------------
