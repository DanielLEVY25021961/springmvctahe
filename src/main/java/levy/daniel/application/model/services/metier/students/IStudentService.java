package levy.daniel.application.model.services.metier.students;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import levy.daniel.application.model.persistence.metier.students.entities.jpa.impl.StudentEntityJPA;

/**
 * INTERFACE IStudentService :<br/>
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
 * @since 1 déc. 2019
 *
 */
public interface IStudentService {
	
	
	
	/**
	 * Sauvegarde dans le stockage une Entité (objet métier) et la retourne.
	 * <ul>
	 * <li>jette une MauvaisParametreRunTimeException 
	 * si l'objet métier passé en paramètre == null.</li>
	 * <li>jette une MauvaisParametreRunTimeException 
	 * si l'identifiant METIER de l'objet passé en paramètre est blank.</li>
	 * <li>jette une MauvaisParametreRunTimeException 
	 * en cas de <strong>doublon</strong> ou de 
	 * <strong>violation de contrainte</strong>.</li>
	 * <li>jette une MauvaisParametreRunTimeException si le stockage 
	 * est impossible.</li>
	 * <li>délègue au DAO la tâche de stocker l'Objet Métier 
	 * et le retourne.</li>
	 * </ul>
	 *
	 * @param pObject : StudentEntityJPA : objet métier à stocker.
	 * 
	 * @return : StudentEntityJPA : objet métier stocké.
	 * 
	 * @throws Exception 
	 */
	StudentEntityJPA save(StudentEntityJPA pObject) throws Exception;

	
	
	/**
	 * recherche un objet métier dans le stockage via son IDENTIFIANT METIER 
	 * et le retourne.
	 * <ul>
	 * <li>retourne null si pObject == null.</li>
	 * <li>retourne null si l'IDENTIFIANT METIER de pObject est blank.</li>
	 * <li>délègue au DAO la tâche de récupérer l'objet métier 
	 * dans le stockage via son IDENTIFIANT METIER.</li>
	 * </ul>
	 *
	 * @param pObject : StudentEntityJPA : 
	 * l'objet métier à rechercher dans le stockage.
	 * 
	 * @return : StudentEntityJPA : l'objet métier trouvé dans le stockage.
	 * 
	 * @throws Exception 
	 */
	StudentEntityJPA findByIdMetier(StudentEntityJPA pObject) throws Exception;
	
	
	
	/**
	 * retourne optionnellement l'objet métier d'ID en base pId.
	 * <ul>
	 * <li>jette une MauvaisParametreRunTimeException si pId == null.</li>
	 * </ul>
	 *
	 * @param pId : Long : ID en base de l'objet métier.
	 * 
	 * @return : Optional&lt;StudentEntityJPA&gt;
	 * 
	 * @throws Exception 
	 */
	Optional<StudentEntityJPA> findById(Long pId) throws Exception;

	
	
	/**
	 * retourne la Page de numéro (0-based) pNumeroPage et de taille pSize
	 * contenant les objets métier dont l'ID METIER contient pMotCle.<br/>
	 * Par exemple : pMotCle = 'ard' remontera les Gerard, Gérard, Panhard, ...
	 * <ul>
	 * <li>ATTENTION : pMotCle est <strong>sensible à la casse</strong>. 
	 * <br/>Par exemple, 'd' ne remonte pas la même chose que 'D'.</li>
	 * <li><i>Ne pas mettre de <strong>%</strong> autour 
	 * de la valeur passée à pMotCle. SPRING DATA s'en charge</i>.</li>
	 * <li>retourne tous les enregistrements paginés si pMotCle est blank 
	 * (null ou vide "").</li>
	 * <li>jette une MauvaisParametreRunTimeException 
	 * si pSize &lt; 1.</li>
	 * <li>délègue au DAO la tâche de récupérer tous les enregistrements 
	 * <i>contenant</i> le mot-clé dans le stockage.</li>
	 * <li>jette une MauvaisParametreRunTimeException 
	 * si le mot-clé n'est pas trouvé.</li>
	 * </ul>
	 *
	 * @param pMotCle : String : 
	 * l'expression que les ID METIER de l'objet métier doivent contenir.
	 * <br/>Par exemple, "ard" retournera les "Gérard".
	 * <br/>SENSIBLE A LA CASSE.
	 * @param pNumeroPage : int : numéro de page (0-based).
	 * @param pSize : int : nombre d'enregistrements dans la Page.
	 * 
	 * @return : org.springframework.data.domain.Page&lt;StudentEntityJPA&gt;
	 * 
	 * @throws Exception
	 */
	Page<StudentEntityJPA> findByNameContains(
			String pMotCle
				, int pNumeroPage
					, int pSize) throws Exception;
	
	
	
	/**
	 * Retourne la Page de numéro pNumeroPage (0-based) 
	 * contenant pSize objets métier provenant du stockage.
	 * <ul>
	 * <li>jette une MauvaisParametreRunTimeException 
	 * si pSize &lt; 1.</li>
	 * <li>délègue au DAO la tâche de trouver les enregistrements 
	 * de la page pNumeroPage (0-based) contenant pSize 
	 * enregistrements dans le stockage.</li>
	 * </ul>
	 *
	 * @param pNumeroPage : int : numéro de page (0-based).
	 * @param pSize : int : nombre d'enregistrements dans la Page.
	 * 
	 * @return : org.springframework.data.domain.Page&lt;StudentEntityJPA&gt; 
	 * 
	 * @throws Exception 
	 */
	Page<StudentEntityJPA> findAllPagine(int pNumeroPage, int pSize) 
			throws Exception;
	
	
	
	/**
	 * retourne la liste de tous les objets métier dans le stockage.
	 *
	 * @return : List&lt;StudentEntityJPA&gt; : 
	 * liste de tous les objets métier dans le stockage.<br/>
	 * 
	 * @throws Exception 
	 */
	List<StudentEntityJPA> findAll() throws Exception;
	

	
	/**
	 * Modifie dans le stockage une Entité (objet métier) EXISTANTE 
	 * et la retourne.<br/>
	 * L'OBJET METIER PERSISTANT passé en paramètre de cette méthode 
	 * est l'Objet métier comportant déjà toutes les modifications 
	 * (<i>son ID en base ne peut être changé</i>).
	 * <ul>
	 * <li>jette une MauvaisParametreRunTimeException 
	 * si l'objet métier passé en paramètre == null.</li>
	 * <li>jette une MauvaisParametreRunTimeException si l'objet métier 
	 * passé en paramètre n'est pas persistant (pas d'ID en base).</li>
	 * <li>jette une MauvaisParametreRunTimeException 
	 * si l'objet métier passé en paramètre n'est pas déjà persistant 
	 * (ID inexistant en base).</li>
	 * <li>jette une MauvaisParametreRunTimeException 
	 * si l'identifiant METIER de l'objet passé en paramètre est blank.</li>
	 * <li>jette une MauvaisParametreRunTimeException 
	 * si les modifications engendreraient un <strong>doublon</strong> 
	 * ou une <strong>violation de contrainte</strong>.</li>
	 * <li>délègue au DAO la tâche de modifier l'Objet Métier 
	 * et le retourne.</li>
	 * </ul>
	 *
	 * @param pObject : StudentEntityJPA : 
	 * objet métier d'ID en base existant comportant 
	 * déjà les modifications à apporter.
	 * 
	 * @return : StudentEntityJPA : objet métier stocké modifié.
	 * 
	 * @throws Exception 
	 */
	StudentEntityJPA update(StudentEntityJPA pObject) throws Exception;
	
	
	
	/**
	 * détruit l'objet métier d'ID en base pId dans le stockage 
	 * et retourne un boolean.
	 * <ul>
	 * <li>jette une MauvaisParametreRunTimeException si pId == null.</li>
	 * <li>récupère l'objet métier dans le stockage en utilisant 
	 * <code>this.findById(pId)</code>.</li>
	 * <li>délègue au DAO la tâche de détruire l'objet métier 
	 * dans le stockage.</li>
	 * <li>jette une MauvaisParametreRunTimeException si le DAO 
	 * ne parvient pas à détruire l'objet existant dans le stockage.</li>
	 * <li>jette une MauvaisParametreRunTimeException si l'objet métier 
	 * d'ID en base pId n'existe pas dans le stockage.</li>
	 * </ul>
	 *
	 * @param pId : Long : ID en base.
	 * 
	 * @return boolean : true si 'lobjet métier d'ID pId a été détruit.
	 * 
	 * @throws Exception
	 */
	boolean deleteByIdStockage(Long pId) throws Exception;
	
	
	
	/**
	 * détruit l'objet métier pObject dans le stockage et retourne un boolean.
	 * <ul>
	 * <li>jette une MauvaisParametreRunTimeException si pObject == null.</li>
	 * <li>récupère l'objet métier dans le stockage en utilisant 
	 * <code>this.findByIdMetier(pObject)</code>.</li>
	 * <li>délègue au DAO la tâche de détruire l'objet métier 
	 * dans le stockage.</li>
	 * <li>jette une MauvaisParametreRunTimeException si pObject 
	 * ne peut être détruit dans le stockage.</li>
	 * <li>jette une MauvaisParametreRunTimeException si pObject 
	 * ne peut être trouvé dans le stockage.</li>
	 * </ul>
	 *
	 * @param pObject : StudentEntityJPA : 
	 * Objet Métier à détruire dans le stockage.
	 * 
	 * @return boolean : 
	 * true si l'objet métier a été détruit dans le stockage.
	 * 
	 * @throws Exception
	 */
	boolean delete(StudentEntityJPA pObject) throws Exception;
	
	
	
	/**
	 * détermine si un Objet Métier existe dans le stockage.
	 * <ul>
	 * <li>retourne toujours false si pObject == null.</li>
	 * <li>recherche l'objet métier dans le stockage au moyen 
	 * de la méthode <code>this.findByIdMetier(pObject)</code>.</li>
	 * <li>retourne true si l'objet métier existe, false sinon.</li>
	 * </ul>
	 * 
	 * @param pObject : StudentEntityJPA : 
	 * Objet Métier dont on doit déterminer la présence dans le stockage.
	 * 
	 * @return : boolean : true si l'objet métier existe dans le stockage.
	 * 
	 * @throws Exception 
	 */
	boolean exists(StudentEntityJPA pObject) throws Exception;
	
	
	
	/**
	 * retourne un boolean déterminant si l'OBJET METIER 
	 * d'ID en base pId existe dans le stockage.
	 * <ul>
	 * <li>retourne toujours false si pId == null.</li>
	 * <li>délègue au DAO la tâche de trouver l'objet métier 
	 * dans le stockage.</li>
	 * <li>retourne true si l'objet métier est présent en base
	 * , false sinon.</li>
	 * <li>jette une MauvaisParametreRunTimeException si 
	 * <code>DAO.findById(pId)</code> lève une Exception.</li>
	 * </ul>
	 *
	 * @param pId : Long : ID en base de l'OBJET METIER
	 * 
	 * @return boolean : 
	 * true si l'OBJET METIER d'ID en base pId existe dans le stockage.
	 * 
	 * @throws Exception
	 */
	boolean existsByIdStockage(Long pId) throws Exception;
	

	
	/**
	 * retourne le numéro (0-based) de la page 
	 * contenant l'objet métier d'ID METIER pIdMetier 
	 * compte-tenu d'une taille de page de pSize.
	 * <ul>
	 * <li>jette une MauvaisParametreRunTimeException 
	 * si pIdMetier est blank.</li>
	 * <li>jette une MauvaisParametreRunTimeException 
	 * si pSize &lt; 1.</li>
	 * <li>jette une MauvaisParametreRunTimeException 
	 * si la page contenant l'objet métier n'est pas trouvée.</li>
	 * </ul>
	 *
	 * @param pIdMetier : String : ID METIER de l'objet métier.
	 * @param pSize : int : nombre d'enregistrements dans une page.
	 * 
	 * @return : int : 
	 * numéro (0-based) de la page contenant l'objet métier 
	 * d'ID METIER pIdMetier.
	 * 
	 * @throws Exception
	 */
	int findNumeroPage(String pIdMetier, int pSize) throws Exception;

	
	
	/**
	 * retourne un boolean déterminant si un numéro de page 
	 * pNumeroPage (0-based) existe dans le stockage 
	 * avec une taille de page de pSize.
	 * <ul>
	 * <li>teste si la page retournée est vide <code>isEmpty()</code> 
	 * car <code>DAO.findAll(Pageable)</code> retourne une 
	 * Page <strong>vide</strong> et non null si la Page n'est pas trouvée.</li>
	 * <li>jette une MauvaisParametreRunTimeException 
	 * si pSize &lt; 1.</li>
	 * </ul>
	 *
	 * @param pNumeroPage : int : 
	 * numéro (0-based) de la page dont on cherche à savoir si elle existe.
	 * @param pSize : int : nombre d'enregistrements dans une page.
	 * 
	 * @return boolean : true si la page existe.
	 * 
	 * @throws Exception
	 */
	boolean existsNumeroPage(int pNumeroPage, int pSize) throws Exception;
	
	
	
} // FIN DE L'INTERFACE IStudentService.-------------------------------------
