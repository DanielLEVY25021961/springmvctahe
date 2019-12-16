package levy.daniel.application.controllers.web;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/GreetingController")
public class GreetingController {
	

	/**
	 * "nom".
	 */
	private static final String NOM = "nom";
	
	/**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public GreetingController() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

		
	/**
	 * "/GreetingController//versIndex"
	 * 
	 * @param pNom
	 * @param pModel
	 * @return
	 */
	@GetMapping("/versIndex")
	public String versIndex(
			@RequestParam(value = NOM, required = false, defaultValue = "Johnny") 
			final String pNom
				, final Model pModel) {
		
		pModel.addAttribute("heure", this.fournirHeure());
		pModel.addAttribute(NOM, pNom);
		
		return "index";
		
	} // Fin de versIndex(...).____________________________________________
	

	
	/**
	 * @return
	 */
	private String fournirHeure() {
		
		final LocalTime heure = LocalTime.now();
		
		final DateTimeFormatter dtf 
			= DateTimeFormatter.ofPattern("HH:mm:ss");
		
		final String heureFormatee = dtf.format(heure);
		
		return heureFormatee;
		
	} // Fin de fournirHeure().____________________________________________
	
	
		
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
			@RequestParam(value = NOM, required = false, defaultValue = "Johnny") 
			final String pNom
				, final Model pModel) {
		
		pModel.addAttribute(NOM, pNom);
		return "greeting";
		
	} // Fin de versPageGreeting(...)._____________________________________
	
	

} // FIN DE LA CLASSE GreetingController.------------------------------------
