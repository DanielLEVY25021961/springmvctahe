package levy.daniel.application.model.utilitaires.formattersspring;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.format.Formatter;

/**
 * CLASSE LocalDateFormatter :<br/>
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
 * @since 2 déc. 2019
 *
 */
public class LocalDateFormatter implements Formatter<LocalDate> {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(LocalDateFormatter.class);

	// *************************METHODES************************************/	

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public LocalDateFormatter() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String print(
			final LocalDate pObject, final Locale pLocale) {
		
		final DateTimeFormatter formatter 
			= DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(pLocale);
		
		if (pObject == null) {
			return null;
		}
		
		return formatter.format(pObject);
		
	} // Fin de print(...).________________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public LocalDate parse(
			final String pText, final Locale pLocale) 
				throws ParseException {
		
		if (StringUtils.isBlank(pText)) {
			return null;
		}
				
		final DateTimeFormatter formatter 
			= DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(pLocale);
		
		final LocalDate date = LocalDate.parse(pText, formatter);
		
		return date;
		
	} // Fin de parse(...).________________________________________________
	


} // FIN DE LA CLASSE LocalDateTimeFormatter.--------------------------------
