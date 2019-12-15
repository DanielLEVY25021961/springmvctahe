package levy.daniel.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * CLASSE SpringmvctaheApplication :<br/>
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
@SpringBootApplication
public class SpringmvctaheApplication implements CommandLineRunner {
	
	
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public SpringmvctaheApplication() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * Point d'entrée de l'application.
	 *
	 * @param pArgs : String[].<br/>
	 */
	public static void main(final String... pArgs) {
		
		SpringApplication.run(SpringmvctaheApplication.class, pArgs);
		
	} // Fin de main(...)._________________________________________________


		
	/**
	* {@inheritDoc}
	*/
	@Override
	public void run(final String... pArgs) throws Exception {
		
		/**/
		
	} // Fin de run(...).__________________________________________________
	
	

} // FIN DE LA CLASSE SpringmvctaheApplication.------------------------------
