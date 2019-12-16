package levy.daniel.application.controllers.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * CLASSE FormulaireHtmlController :<br/>
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
@Controller(value = "FormulaireHtmlController")
@RequestMapping("/FormulaireHtmlController")
public class FormulaireHtmlController {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(FormulaireHtmlController.class);

	// *************************METHODES************************************/



	/**
	* CONSTRUCTEUR D'ARITE NULLE.
	*/
	public FormulaireHtmlController() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * "/FormulaireHtmlController/doNothing" .
	 *
	 */
	@PostMapping(value = "/doNothing")
	@ResponseBody
	public void doNothing() {
		/**/
	}
	
	
	
} // FIN DE LA CLASSE FormulaireHtmlController.------------------------------
