package levy.daniel.application.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * CLASSE Personne :<br/>
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
public class Personne {

	// ************************ATTRIBUTS************************************/

	/**
	 * "null".
	 */
	public static final String NULL = "null";
	
	/**
	 * ", "
	 */
	public static final String VIRGULE_ESPACE = ", ";
	
	
	/**
	 * 
	 */
	private Long id;
	
	/**
	 * 
	 */
	private String prenom;
	
	/**
	 * 
	 */
	private int age;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(Personne.class);

	// *************************METHODES************************************/



	/**
	* CONSTRUCTEUR D'ARITE NULLE.
	*/
	public Personne() {
		this(null, null, 0);
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * CONSTRUCTEUR .
	 *
	 * @param pPrenom
	 * @param pAge
	 */
	public Personne(
			final String pPrenom
				, final int pAge) {
		this(null, pPrenom, pAge);
	}



	/**
	 * CONSTRUCTEUR .
	 *
	 * @param pId
	 * @param pPrenom
	 * @param pAge
	 */
	public Personne(
			final Long pId
				, final String pPrenom
					, final int pAge) {

		super();
		this.id = pId;
		this.prenom = pPrenom;
		this.age = pAge;

	}

	
		
	/**
	* {@inheritDoc}
	*/
	@Override
	public final String toString() {

		final StringBuilder stb = new StringBuilder();
		
		stb.append("Personne [");

		stb.append("Id = ");
		if (this.getId() != null) {			
			stb.append(this.getId());
		} else {
			stb.append(NULL);
		}

		stb.append(VIRGULE_ESPACE);
		
		stb.append("prenom = ");
		if (this.getPrenom() != null) {			
			stb.append(this.getPrenom());
		} else {
			stb.append(NULL);
		}

		stb.append(VIRGULE_ESPACE);

		stb.append("Age = ");
		stb.append(this.getAge());
		
		stb.append(']');
		
		return stb.toString();

	} // Fin de toString().________________________________________________



	/**
	 * Getter
	 *
	 * @return <code>this.id</code> : Long
	 */
	public Long getId() {	
		return this.id;	
	}


	
	/**
	 * Setter .
	 *
	 * @param pId : Long :
	 * valeur à passer à <code>this.id</code>.
	 */
	public void setId(final Long pId) {	
		this.id = pId;	
	}


	
	/**
	 * Getter
	 *
	 * @return <code>this.prenom</code> : String
	 */
	public String getPrenom() {	
		return this.prenom;	
	}



	
	/**
	 * Setter .
	 *
	 * @param pPrenom : String :
	 * valeur à passer à <code>this.prenom</code>.
	 */
	public void setPrenom(final String pPrenom) {	
		this.prenom = pPrenom;	
	}



	
	/**
	 * Getter
	 *
	 * @return <code>this.age</code> : int
	 */
	public int getAge() {	
		return this.age;	
	}



	
	/**
	 * Setter .
	 *
	 * @param pAge : int :
	 * valeur à passer à <code>this.age</code>.
	 */
	public void setAge(final int pAge) {	
		this.age = pAge;	
	}

	
	
}
