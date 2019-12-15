package levy.daniel.application.model.utilitaires.reseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CLASSE Connecteur :<br/>
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
 * @since 14 juil. 2019
 *
 */
public final class Connecteur {

	// ************************ATTRIBUTS************************************/

	/**
	 * Browser.
	 */
	private static Browser browser;
	
	/**
	 * NEWLINE : String :<br/>
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE = System.getProperty("line.separator");
	
	/**
	 * "UTF-8".
	 */
	public static final String UTF8 = "UTF-8";
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(Connecteur.class);

	// *************************METHODES************************************/
	
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	private Connecteur() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * .<br/>
	 * <br/>
	 *
	 * @param pUrl : java.net.URL :  .<br/>
	 * @param pCharset : java.nio.charset.Charset :
	 * 
	 * @throws IOException 
	 */
	public static void connecter(
			final URL pUrl, final Charset pCharset) throws IOException {
		
		expliciterURL(pUrl);
		System.out.println(); // NOPMD by dan on 11/12/19 19:51
		
		final URLConnection urlConnection = pUrl.openConnection();
		String content = "";
		String line = null;
		
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		
		try {
			
			inputStream = urlConnection.getInputStream();
			inputStreamReader 
				= new InputStreamReader(inputStream, pCharset);
			bufferedReader 
				= new BufferedReader(inputStreamReader);
			
			while ((line = bufferedReader.readLine()) != null) {
				content = content + line + NEWLINE; // NOPMD by dan on 11/12/19 19:52
			}
			
			// AFFICHE LA PAGE WEB à l'URL pUrl DANS UN BROWSER. 
			browser = new Browser(pUrl.getHost(), content);
			
		} catch (Exception finalE) {
			
			throw new IOException(finalE);
			
		} finally {
			
			if (inputStream != null) {
				inputStream.close();
			}
			
			if (inputStreamReader != null) {
				inputStreamReader.close();
			}
			
			if (bufferedReader != null) {
				bufferedReader.close();
			}
						
		}
				
	} // Fin de connecter(...).____________________________________________

	
		
	/**
	 * Envoie une requête en MODE GET dans une zone de recherche d'un site web 
	 * situé à pUrl et retourne la réponse lue avec pCharset.<br/>
	 * <ul>
	 * <li>envoie la requête en MODE GET.</li>
	 * <li>la requête passe donc en <i>paramètre dans l'URL 
	 * de la requête HTTP</i>.</li>
	 * <li>ATTENTION : Si PROXY, rajouter AVANT LA CONNEXION les lignes :
	 * <ul>
	 * <li>System.setProperty("http.proxyHost", "10.77.32.65 (adresse PROXY)");</li>
	 * <li>System.setProperty("http.proxyPort", "8080");</li>
	 * </ul>
	 * </li>
	 * </ul>
	 *
	 * @param pUrl : String :
	 *  URL d'un site Web comme par exemple "http://www.google.fr:80".
	 * @param pRequete : String : 
	 * requête à envoyer au site Web comme par exmple "java.net package & RMI".<br/>
	 * Cette requête sera transmise dans la zone de recherche du site web 
	 * après encodage des caractères spéciaux et mise en forme du type 
	 * http://www.google.fr:80?q=java.net+package+%26+RMI
	 * @param pCharset : java.nio.charset.Charset : 
	 * Charset à utiliser pour lire la réponse. 
	 * 
	 * @return : String : réponse du site Web.<br/>
	 * 
	 * @throws IOException 
	 */
	public static String rechercherGetHTTP(
			final String pUrl
				, final String pRequete
					, final Charset pCharset) 
						throws IOException {
		
		String resultat = "";
		HttpURLConnection httpURLConnection = null;
		
		//encode les caractères spéciaux  
        //pour qu'ils soient interprétables dans une URL.
        //Nous devons fournir la chaîne à encoder et le type d'encodage, ici UTF-8
        String recherche = URLEncoder.encode("q", UTF8) + "=";
        recherche = recherche + URLEncoder.encode(pRequete, UTF8); // NOPMD by dan on 11/12/19 19:52
        
        //Nous nous connectons, via un objet HTTPUrlConnection
        //à la nouvelle URL, la recherche se faisant en GET, 
        //les paramètres sont dans l'URL
        System.out.println("URL de recherche : " + pUrl + "?" + recherche); // NOPMD by dan on 11/12/19 19:52
        
        final URL urlRequete = new URL(pUrl + "?" + recherche);
        
        httpURLConnection = (HttpURLConnection) urlRequete.openConnection();
        
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        
        try {
        	
        	inputStream = httpURLConnection.getInputStream();
    		inputStreamReader 
    			= new InputStreamReader(inputStream, pCharset);
    		bufferedReader 
    			= new BufferedReader(inputStreamReader);
    		
    		String line = null;
    		
    		while ((line = bufferedReader.readLine()) != null) {
    			resultat = resultat + line + NEWLINE; // NOPMD by dan on 11/12/19 19:52
    		}
    		
        } catch (Exception finalE) {
        	
			throw new IOException(finalE);
			
		} finally {
			
			if (inputStream != null) {
				inputStream.close();
			}
			
			if (inputStreamReader != null) {
				inputStreamReader.close();
			}
			
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			
		}
        
        return resultat;
						
	} // Fin de rechercherGetHTTP(...).____________________________________

	
	
	/**
	 * Envoie une requête en MODE POST dans une zone de recherche d'un site web 
	 * situé à pUrl et retourne la réponse lue avec pCharset.<br/>
	 * <ul>
	 * <li>envoie la requête en MODE POST.</li>
	 * <li>la requête passe donc dans <i>l'en-tête de la requête HTTP</i>.</li>
	 * <li>ATTENTION : Si PROXY, rajouter AVANT LA CONNEXION les lignes :
	 * <ul>
	 * <li>System.setProperty("http.proxyHost", "10.77.32.65 (adresse PROXY)");</li>
	 * <li>System.setProperty("http.proxyPort", "8080");</li>
	 * </ul>
	 * </li>
	 * </ul>
	 *
	 * @param pUrl : String :
	 *  URL d'un site Web comme par exemple "http://www.google.fr:80".
	 * @param pRequete : String : 
	 * requête à envoyer au site Web comme par exmple "java.net package & RMI".<br/>
	 * Cette requête sera transmise dans la zone de recherche du site web 
	 * après encodage des caractères spéciaux et mise en forme du type 
	 * http://www.google.fr:80?q=java.net+package+%26+RMI
	 * @param pCharset : java.nio.charset.Charset : 
	 * Charset à utiliser pour lire la réponse. 
	 * 
	 * @return : String : réponse du site Web.<br/>
	 * 
	 * @throws IOException 
	 */
	public static String rechercherPostHTTP(
			final String pUrl
				, final String pRequete
					, final Charset pCharset) 
						throws IOException {
		
		String resultat = "";
		HttpURLConnection httpURLConnection = null;
		
		//encode les caractères spéciaux  
        //pour qu'ils soient interprétables dans une URL.
        //Nous devons fournir la chaîne à encoder et le type d'encodage, ici UTF-8
        String recherche = URLEncoder.encode("q", UTF8) + "=";
        recherche = recherche + URLEncoder.encode(pRequete, UTF8); // NOPMD by dan on 11/12/19 19:52
        
        //Nous nous connectons, via un objet HTTPUrlConnection
        //à la nouvelle URL, la recherche se faisant en GET, 
        //les paramètres sont dans l'URL
        System.out.println("URL de recherche : " + pUrl + "?" + recherche); // NOPMD by dan on 11/12/19 19:51
        
        final URL urlRequete = new URL(pUrl + "?" + recherche);
        
        httpURLConnection = (HttpURLConnection) urlRequete.openConnection();
        
      //On spécifie le type de méthode POST car, par défaut le type est GET
        httpURLConnection.setRequestMethod("POST");
        
        //Pour pouvoir écrire dans l'entête, nous devons l'autoriser
        httpURLConnection.setDoOutput(true);
        
        httpURLConnection.setRequestProperty("x-forwarded-for", "on change un paramètre de l'entête HTTP");
        
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        
        try {
        	
        	inputStream = httpURLConnection.getInputStream();
    		inputStreamReader 
    			= new InputStreamReader(inputStream, pCharset);
    		bufferedReader 
    			= new BufferedReader(inputStreamReader);
    		
    		String line = null;
    		
    		while ((line = bufferedReader.readLine()) != null) {
    			resultat = resultat + line + NEWLINE; // NOPMD by dan on 11/12/19 19:52
    		}
    		
    		return resultat;
    		
        } catch (Exception finalE) {
        	
			throw new IOException(finalE);
			
		} finally {
			
			if (inputStream != null) {
				inputStream.close();
			}
			
			if (inputStreamReader != null) {
				inputStreamReader.close();
			}
			
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			
		}
						
	} // Fin de rechercherPostHTTP(...).___________________________________
	
	
	
	/**
	 * affiche les renseignements relatifs à pUrl.
	 *
	 * @param pUrl : java.net.URL : url d'un site 
	 * comme par exemple "http://www.google.fr:80".<br/>
	 */
	public static void expliciterURL(
			final URL pUrl) {
		
        System.out.println("Authority - pUrl.getAuthority() : " + pUrl.getAuthority()); // NOPMD by dan on 11/12/19 19:51
        System.out.println("Default port - pUrl.getDefaultPort() : " + pUrl.getDefaultPort()); // NOPMD by dan on 11/12/19 19:51
        System.out.println("Host - pUrl.getHost() : " + pUrl.getHost()); // NOPMD by dan on 11/12/19 19:52
        System.out.println("Port - pUrl.getPort() : " + pUrl.getPort()); // NOPMD by dan on 11/12/19 19:52
        System.out.println("Protocol - pUrl.getProtocol() : " + pUrl.getProtocol()); // NOPMD by dan on 11/12/19 19:51
        
	} // Fin de expliciterURL(...).________________________________________
	
	
	
	/**
	 * .<br/>
	 * <br/>
	 *
	 * @param args : String[] :  .<br/>
	 * @throws IOException 
	 */
	public static void main(
			final String... args) throws IOException {
		
		// SI PROXY
//		System.setProperty("http.proxyHost", "10.77.32.65");
//		System.setProperty("http.proxyPort", "8080");
		
		// SI PAS PROXY
//		System.setProperty("http.proxyHost", "");
		
//		final String siteWeb = "http://search.oracle.com//search/search";
		final String siteWeb = "http://www.google.fr:80";
//		final String siteWeb = "jdbc:postgresql://localhost:5432";
        final URL url = new URL(siteWeb);
        connecter(url, StandardCharsets.ISO_8859_1);
        String result = "";
        
      //Et nous faisons une recherche sur ce même site
        result 
        	= rechercherGetHTTP(siteWeb, "java", StandardCharsets.ISO_8859_1);
        
        //Nous mettons à jour notre page
        browser.setContent(result);
        
        //Et nous faisons une recherche sur ce même site
//        result 
//        	= rechercherGetHTTP(siteWeb, "java.net package & RMI", StandardCharsets.ISO_8859_1);
        
        //Nous mettons à jour notre page
//        browser.setContent(result);

	} // Fin de main(...)._________________________________________________


	
} // FIN DE LA CLASSE Connecteur.--------------------------------------------
