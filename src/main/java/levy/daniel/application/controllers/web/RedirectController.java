package levy.daniel.application.controllers.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * CLASSE RedirectController :<br/>
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
@Controller(value = "RedirectController")
@RequestMapping(value = "/RedirectController")
public class RedirectController {

	// ************************ATTRIBUTS************************************/

	/**
	 * "nom".
	 */
	private static final String NOM = "nom";

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(RedirectController.class);

	// *************************METHODES************************************/



	/**
	* CONSTRUCTEUR D'ARITE NULLE.
	*/
	public RedirectController() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * "/RedirectController/redirigerVersPageIndex".
	 *
	 * @return
	 */
	@GetMapping(value = "/redirigerVersPageIndex")
	public String redirigerVersPageIndex() {
		return "index";
	} // Fin de redirigerVersPageIndex().__________________________________

	
	
	/**
	 * "/RedirectController/redirigerVersActionIndex".
	 *
	 * @param pNom
	 * @param pRedirectAttributes
	 * @return
	 */
	@GetMapping(value = "/redirigerVersActionIndex")
	public String redirigerVersActionIndex(
			@RequestParam(value = NOM, required = false, defaultValue = "Johnny") 
			final String pNom
				, final RedirectAttributes pRedirectAttributes) {
		
		pRedirectAttributes.addAttribute(NOM, pNom);
		
		return "redirect:/GreetingController/versIndex";
		
	} // Fin de redirigerVersActionIndex(...)._____________________________


		
	/**
	 * "/RedirectController/redirectionPermanenteVersActionIndex".
	 *
	 * @param pNom
	 * @param pResponse
	 */
	@GetMapping(value = "/redirectionPermanenteVersActionIndex")
	public void redirectionPermanenteVersActionIndex(
			@RequestParam(value = NOM, required = false, defaultValue = "Johnny") 
			final String pNom
				, final HttpServletResponse pResponse) {
		
		pResponse.setStatus(301);
		pResponse.addHeader("Location", "/GreetingController/versIndex");
		
	} // Fin de redirectionPermanenteVersActionIndex(...)._________________

	
	
	/**
	 * "/RedirectController/creerReponseComplete".
	 *
	 * @param pResponse
	 * @throws IOException
	 */
	@GetMapping(value = "/creerReponseComplete")
	public void creerReponseComplete(final HttpServletResponse pResponse) 
			throws IOException {
		
		pResponse.setStatus(666);
		pResponse.addHeader("header1", "quelque chose");
		pResponse.addHeader("Content-Type", "text/html;charset=UTF-8");
		final String string = "<div><h1>caractères accentués : je bêèéle comme ça</h1><p>Observatoire de la vie publique</p></div>";
		pResponse.getWriter().write(string);
		
	} // Fin de creerReponseComplete(...)._________________________________
	

}
