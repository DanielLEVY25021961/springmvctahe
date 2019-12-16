package levy.daniel.application.controllers.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import levy.daniel.application.model.Personne;


/**
 * CLASSE ActionsRestController :<br/>
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
@RestController(value = "ActionsRestController")
@RequestMapping("/ActionsRestController")
public class ActionsRestController {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(ActionsRestController.class);

	// *************************METHODES************************************/

	/**
	* CONSTRUCTEUR D'ARITE NULLE.
	*/
	public ActionsRestController() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * "/ActionsRestController/retournerTexte".
	 *
	 * @return String
	 */
	@GetMapping("/retournerTexte")
	public String retournerTexte() {
		return "salutations de France";
	} // Fin de retournerTexte().__________________________________________


	
	/**
	 * "/ActionsRestController/retournerTexteAccentue".
	 *
	 * @return String
	 */
	@GetMapping("/retournerTexteAccentue")
	public String retournerTexteAccentue() {
		return "caractères accentués : je bêèéle comme ça";
	} // Fin de retournerTexteAccentue().__________________________________
	

	
	/**
	 * "/ActionsRestController/retournerTexteXml".
	 *
	 * @return
	 */
	@GetMapping(value = "/retournerTexteXml", produces = "text/xml;charset=UTF-8")
	public String retournerTexteXml() {
		return "<div><h1>caractères accentués : je bêèéle comme ça</h1><p>Observatoire de la vie publique</p></div>";
	} // Fin de retournerTexteXml()._______________________________________
	

	
	/**
	 * "/ActionsRestController/retournerTexteHtml".
	 *
	 * @return
	 */
	@GetMapping(value = "/retournerTexteHtml", produces = "text/html;charset=UTF-8")
	public String retournerTexteHtml() {
		return "<div><h1>caractères accentués : je bêèéle comme ça</h1><p>Observatoire de la vie publique</p></div>";
	} // Fin de retournerTexteHtml().______________________________________
	

	
	/**
	 * "/ActionsRestController/retournerTextePlain".
	 *
	 * @return
	 */
	@GetMapping(value = "/retournerTextePlain", produces = "text/plain;charset=UTF-8")
	public String retournerTextePlain() {
		return "<div><h1>caractères accentués : je bêèéle comme ça</h1><p>Observatoire de la vie publique</p></div>";
	} // Fin de retournerTextePlain()._____________________________________
	
	
	
	/**
	 * "/ActionsRestController/retournerJson".
	 *
	 * @return
	 */
	@GetMapping(value = "/retournerJson")
	public Map<String, Object> retournerJson() {
		
		final Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("1", "un");
		map.put("2", new int[] {4, 5});
		
		return map;
		
	} // Fin de retournerJson().___________________________________________
	
	
	
	/**
	 * "/ActionsRestController/retournerObjectJson".
	 *
	 * @return
	 */
	@GetMapping(value = "/retournerObjectJson")
	public Personne retournerObjectJson() {
		
		final Personne personne = new Personne(3L, "Carole", 61);
		
		return personne;
		
	} // Fin de retournerObjectJson()._____________________________________

	
	
}
