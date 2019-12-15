package levy.daniel.application.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * CLASSE GreetingController :<br/>
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
@Controller(value = "GreetingController")
public class GreetingController {
	

	
	/**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public GreetingController() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * retourne la page 'greeting.html'
	 * <br/>
	 * http://localhost:8080/greeting?nom=Yannick%20Noah<br/>
	 * <br/>
	 * 
	 * @param pNom : String
	 * @param pModel : org.springframework.ui.Model
	 * 
	 * @return String : "greeting"
	 */
	@GetMapping("/greeting")
	public String versPageGreeting(
			@RequestParam(value = "nom", required = false, defaultValue = "Johnny") 
			final String pNom
				, final Model pModel) {
		
		pModel.addAttribute("nom", pNom);
		return "greeting";
		
	} // Fin de versPageGreeting(...)._____________________________________
	
	

} // FIN DE LA CLASSE GreetingController.------------------------------------
