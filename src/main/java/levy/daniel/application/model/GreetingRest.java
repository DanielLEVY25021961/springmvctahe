/**
 * 
 */
package levy.daniel.application.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CLASSE GreetingRest :<br/>
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
public class GreetingRest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * 
	 */
	private Long id;
	
	/**
	 * 
	 */
	private String contenu;
	

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(GreetingRest.class);
	

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public GreetingRest() {
		this(null, null);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	/**
	 * @param pId
	 * @param pContenu
	 */
	public GreetingRest(
			final Long pId
				, final String pContenu) {
		super();
		this.id = pId;
		this.contenu = pContenu;
	}


	/**
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}


	/**
	 * @param pId the id to set
	 */
	public void setId(final Long pId) {
		this.id = pId;
	}


	/**
	 * @return the contenu
	 */
	public String getContenu() {
		return this.contenu;
	}


	/**
	 * @param pContenu the contenu to set
	 */
	public void setContenu(final String pContenu) {
		this.contenu = pContenu;
	}
	


} // FIN DE LA CLASSE GreetingRest.------------------------------------------
