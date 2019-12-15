package levy.daniel.application.controllers.web.metier.students;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import levy.daniel.application.model.persistence.metier.students.entities.jpa.impl.StudentEntityJPA;
import levy.daniel.application.model.services.metier.students.IStudentService;

/**
 * CLASSE StudentController :<br/>
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
/**
 * CLASSE StudentController :<br/>
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
 * @since 11 déc. 2019
 *
 */
@Controller(value="StudentController")
@RequestMapping("/StudentController")
public class StudentController {

	// ************************ATTRIBUTS************************************/

	/**
	 * "3".
	 */
	public static final String DEFAULT_SIZE = "3";
	
	/**
	 * 3.
	 */
	public static final int DEFAULT_SIZE_INT = 3;
	
	/**
	 * "student".
	 */
	public static final String STUDENT = "student";
	
	/**
	 * "numeroPageStudent".
	 */
	public static final String NUMEROPAGESTUDENT = "numeroPageStudent";
	
	/**
	 * "sizeStudent".
	 */
	public static final String SIZESTUDENT = "sizeStudent";
	
	/**
	 * "motCleStudent".
	 */
	public static final String MOTCLESTUDENT = "motCleStudent";
	
	/**
	 * IStudentService injecté par SPRING.
	 */
	@Autowired
	@Qualifier("StudentService")
	private IStudentService studentService;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(StudentController.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public StudentController() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


			
	/**
	 * Sauvegarde dans le stockage une Entité (objet métier) 
	 * et redirige vers la page listant les objets métier.<br/>
	 * redirige vers l'action <code>"/StudentController/versPageListAll"</code> 
	 * à chaque lancement de l'action <code>"/StudentController/save"</code>.
	 * <ul>
	 * <li>délègue au SERVICE la tâche de stockage d'un objet métier.</li>
	 * <li>ajoute au RedirectAttributes l'objet métier stocké 
	 * dans l'attribut <strong>'objetSaveStudent'</strong>.</li>
	 * <li>ajoute au RedirectAttributes le message dans l'attribut 
	 * <strong>'messageSaveStudent'</strong>.</li>
	 * <li>ajoute au RedirectAttributes le resultat de réalisation 
	 * du SERVICE (OK/KO) dans l'attribut 
	 * <strong>'reponseSaveStudent'</strong>.</li>
	 * <li>
	 * ajoute au RedirectAttributes les paramètres de la requête
	 * lors de la redirection 
	 * (équivalent de <code>"redirect:/StudentController/versPageListPaginee" 
	 * + "?numeroPageStudent=" + pNumeroPage 
	 * + "&sizeStudent=" + pSize;</code>.</li>
	 * <ul>
	 * <li>ajoute au RedirectAttributes le numéro de page sur 
	 * lequel revenir dans l'attribut <strong>'numeroPageStudent'</strong>.</li>
	 * <li>ajoute au RedirectAttributes la taille courante (pSize) 
	 * dans l'attribut <strong>'sizeStudent'</strong>.</li>
	 * <li>ajoute au RedirectAttributes le mot-cle courant (pMotCle) 
	 * dans l'attribut <strong>'motCleStudent'</strong>.</li>
	 * </ul>
	 * </li>
	 * </ul>
	 *
	 * @param pStudent : StudentEntityJPA : objet métier à stocker.
	 * @param pNumeroPage : int : numéro (0-based) de la page en cours.
	 * @param pSize : int : nombre d'enregistrements dans une page.
	 * @param pMotCle : String : saisie dans la zone de recherche. 
	 * @param pRedirectAttributes : 
	 * org.springframework.web.servlet.mvc.support.RedirectAttributes
	 * 
	 * @return : String : "redirect:/StudentController/versPageListAll".
	 * 
	 * @throws Exception 
	 */
	@PostMapping("/save")
	public String save(
			@ModelAttribute(name=STUDENT) final StudentEntityJPA pStudent
			, @RequestParam(name=NUMEROPAGESTUDENT, required=false, defaultValue="0") final int pNumeroPage
			, @RequestParam(name=SIZESTUDENT, required=false, defaultValue=DEFAULT_SIZE) final int pSize
			, @RequestParam(name=MOTCLESTUDENT, required=false, defaultValue="") final String pMotCle
				, final RedirectAttributes pRedirectAttributes) 
						throws Exception {
		
		StudentEntityJPA objetStocke = null;
		String message = null;
		boolean reponseService = false;
				
		try {
			
			/* délègue au SERVICE la tâche de stockage d'un objet métier. */
			objetStocke = this.studentService.save(pStudent);
			
			message = "l'objet métier a bien été stocké : " 
					+ pStudent.toString();
			
			reponseService = true;
			
		} catch (Exception e) {
			
			message = "Impossible de stocker l'objet métier : " 
					+ pStudent.toString() + ". " + e.getMessage();
			
			reponseService = false;
			
		}
		
		/* ajoute au RedirectAttributes l'objet métier stocké 
		 * dans l'attribut 'objetSaveStudent'. */
		pRedirectAttributes.addFlashAttribute(
				"objetSaveStudent", objetStocke);
		
		/* ajoute au RedirectAttributes le message 
		 * dans l'attribut 'messageSaveStudent'. */
		pRedirectAttributes.addFlashAttribute(
				"messageSaveStudent", message);
		
		/* ajoute au RedirectAttributes le resultat de réalisation 
		 * du SERVICE (OK/KO) dans l'attribut 'reponseSaveStudent'. */
		pRedirectAttributes.addFlashAttribute(
				"reponseSaveStudent", reponseService);
		
		/* ajoute au RedirectAttributes les paramètres de la requête 
		 * (équivalent de "redirect:/StudentController/versPageListPaginee" 
		 * + "?numeroPageStudent=" + pNumeroPage + "&sizeStudent=" + pSize;. */
		/* ajoute au RedirectAttributes le numéro de page sur lequel 
		 * revenir dans l'attribut 'numeroPageStudent'. */
		if (objetStocke != null) {
			
			int numeroPageAAtteindre;
			
			try {
				
				numeroPageAAtteindre 
					= this.studentService.findNumeroPage(
						objetStocke.getName(), pSize);
				
				pRedirectAttributes.addAttribute(
						NUMEROPAGESTUDENT, numeroPageAAtteindre);
				
			} catch (Exception e) {
				
				pRedirectAttributes.addAttribute(
						NUMEROPAGESTUDENT, pNumeroPage);
				
			}
						
		} else {
			
			pRedirectAttributes.addAttribute(
					NUMEROPAGESTUDENT, pNumeroPage);
			
		}
				
		/* ajoute au RedirectAttributes la taille courante (pSize) 
		 * dans l'attribut 'sizeStudent'. */
		pRedirectAttributes.addAttribute(
				SIZESTUDENT, pSize);
		
		/* ajoute au RedirectAttributes le mot-cle courant (pMotCle) 
		 * dans l'attribut 'motCleStudent'. */
		pRedirectAttributes.addAttribute(
				MOTCLESTUDENT, pMotCle);
		
		return "redirect:/StudentController/versPageListPaginee";
		
	} // Fin de save(...)._________________________________________________

	
	
	/**
	 * retourne optionnellement l'objet métier d'ID en base pId.
	 *
	 * @param pId : Long : ID en base de l'objet métier.
	 * 
	 * @return : Optional&lt;StudentEntityJPA&gt;
	 */
	@GetMapping("/findById")
	@ResponseBody
	public Optional<StudentEntityJPA> findById(
			@RequestParam(name="id", required=true) final Long pId) {
		
		try {
			
			return this.studentService.findById(pId);
			
		} catch (Exception e) {
			return null;
		}
		
	} // Fin de findById(...)._____________________________________________
	
	
	
	/**
	 * Modifie dans le stockage une Entité (objet métier) 
	 * et redirige vers la page listant les objets métier.<br/>
	 * redirige vers l'action <code>"/StudentController/versPageListAll"</code> 
	 * à chaque lancement de l'action <code>"/StudentController/update"</code>.
	 * <ul>
	 * <li>délègue au SERVICE la tâche d'update d'un objet métier.</li>
	 * <li>ajoute au RedirectAttributes l'objet métier stocké 
	 * dans l'attribut <strong>'objetUpdateStudent'</strong>.</li>
	 * <li>ajoute au RedirectAttributes le message dans l'attribut 
	 * <strong>'messageUpdateStudent'</strong>.</li>
	 * <li>ajoute au RedirectAttributes le resultat de réalisation 
	 * du SERVICE (OK/KO) dans l'attribut 
	 * <strong>'reponseUpdateStudent'</strong>.</li>
	 * <li>
	 * ajoute au RedirectAttributes les paramètres de la requête
	 * lors de la redirection 
	 * (équivalent de <code>"redirect:/StudentController/versPageListPaginee" 
	 * + "?numeroPageStudent=" + pNumeroPage 
	 * + "&sizeStudent=" + pSize;</code>.</li>
	 * <ul>
	 * <li>ajoute au RedirectAttributes le numéro de page sur 
	 * lequel revenir dans l'attribut <strong>'numeroPageStudent'</strong>.</li>
	 * <li>ajoute au RedirectAttributes la taille courante (pSize) 
	 * dans l'attribut <strong>'sizeStudent'</strong>.</li>
	 * <li>ajoute au RedirectAttributes le mot-cle courant (pMotCle) 
	 * dans l'attribut <strong>'motCleStudent'</strong>.</li>
	 * </ul>
	 * </li>
	 * </ul>
	 *
	 * @param pStudent : StudentEntityJPA : 
	 * objet métier persistant comportant les modifications.
	 * @param pNumeroPage : int : numéro (0-based) de la page en cours.
	 * @param pSize : int : nombre d'enregistrements dans une page.
	 * @param pMotCle : String : saisie dans la zone de recherche. 
	 * @param pRedirectAttributes : 
	 * org.springframework.web.servlet.mvc.support.RedirectAttributes
	 * 
	 * @return : String : "redirect:/StudentController/versPageListAll".
	 * 
	 * @throws Exception 
	 */
	@RequestMapping(value="/update", method = {RequestMethod.GET, RequestMethod.POST})
	public String update(
			@ModelAttribute(name=STUDENT) final StudentEntityJPA pStudent
				, @RequestParam(name=NUMEROPAGESTUDENT, required=false, defaultValue="0") final int pNumeroPage
				, @RequestParam(name=SIZESTUDENT, required=false, defaultValue=DEFAULT_SIZE) final int pSize
				, @RequestParam(name=MOTCLESTUDENT, required=false, defaultValue="") final String pMotCle
				, final RedirectAttributes pRedirectAttributes) 
						throws Exception {
		
		StudentEntityJPA objetStocke = null;
		String message = null;
		boolean reponseService = false;
		
		try {
			
			/* délègue au SERVICE la tâche d'update d'un objet métier. */
			objetStocke = this.studentService.update(pStudent);
			
			message = "l'objet métier a bien été modifié : " 
					+ pStudent.toString();
			
			reponseService = true;
			
		} catch (Exception e) {
			
			message = "Impossible de modifier l'objet métier : " 
					+ pStudent.toString() + ". " + e.getMessage();
			
			reponseService = false;
			
		}
		
		/* ajoute au RedirectAttributes l'objet métier stocké 
		 * dans l'attribut 'objetUpdateStudent'. */
		pRedirectAttributes.addFlashAttribute(
				"objetUpdateStudent", objetStocke);
		
		/* ajoute au RedirectAttributes le message 
		 * dans l'attribut 'messageUpdateStudent'. */
		pRedirectAttributes.addFlashAttribute(
				"messageUpdateStudent", message);
		
		/* ajoute au RedirectAttributes le resultat de réalisation 
		 * du SERVICE (OK/KO) dans l'attribut 'reponseUpdateStudent'. */
		pRedirectAttributes.addFlashAttribute(
				"reponseUpdateStudent", reponseService);
		
		
		/* ajoute au RedirectAttributes les paramètres de la requête 
		 * (équivalent de "redirect:/StudentController/versPageListPaginee" 
		 * + "?numeroPageStudent=" + pNumeroPage + "&sizeStudent=" + pSize;. */
		/* ajoute au RedirectAttributes le numéro de page sur lequel 
		 * revenir dans l'attribut 'numeroPageStudent'. */			
			pRedirectAttributes.addAttribute(
					NUMEROPAGESTUDENT, pNumeroPage);
							
		/* ajoute au RedirectAttributes la taille courante (pSize) 
		 * dans l'attribut 'sizeStudent'. */
		pRedirectAttributes.addAttribute(
				SIZESTUDENT, pSize);
		
		/* ajoute au RedirectAttributes le mot-cle courant (pMotCle) 
		 * dans l'attribut 'motCleStudent'. */
		pRedirectAttributes.addAttribute(
				MOTCLESTUDENT, pMotCle);
		
		return "redirect:/StudentController/versPageListPaginee";

	} // Fin de save(...)._________________________________________________


	
	/**
	 * Détruit dans le stockage une Entité (objet métier) 
	 * et redirige vers la page listant les objets métier.<br/>
	 * redirige vers l'action <code>"/StudentController/versPageListAll"</code> 
	 * à chaque lancement de l'action <code>"/StudentController/delete"</code>.
	 * <ul>
	 * <li>délègue au SERVICE la tâche de delete d'un objet métier.</li>
	 * <li>ajoute au RedirectAttributes l'objet métier détruit 
	 * dans l'attribut <strong>'objetDeleteStudent'</strong>.</li>
	 * <li>ajoute au RedirectAttributes le message dans l'attribut 
	 * <strong>'messageDeleteStudent'</strong>.</li>
	 * <li>ajoute au RedirectAttributes le resultat de réalisation 
	 * du SERVICE (OK/KO) dans l'attribut 
	 * <strong>'reponseDeleteStudent'</strong>.</li>
	 * <li>
	 * ajoute au RedirectAttributes les paramètres de la requête
	 * lors de la redirection 
	 * (équivalent de <code>"redirect:/StudentController/versPageListPaginee" 
	 * + "?numeroPageStudent=" + pNumeroPage 
	 * + "&sizeStudent=" + pSize;</code>.</li>
	 * <ul>
	 * <li>ajoute au RedirectAttributes le numéro de page sur 
	 * lequel revenir dans l'attribut <strong>'numeroPageStudent'</strong>.</li>
	 * <li>ajoute au RedirectAttributes la taille courante (pSize) 
	 * dans l'attribut <strong>'sizeStudent'</strong>.</li>
	 * <li>ajoute au RedirectAttributes le mot-cle courant (pMotCle) 
	 * dans l'attribut <strong>'motCleStudent'</strong>.</li>
	 * </ul>
	 * </li>
	 * </ul>
	 *
	 * @param pStudent : StudentEntityJPA : 
	 * objet métier persistant à détruire.
	 * @param pNumeroPage : int : numéro (0-based) de la page en cours.
	 * @param pSize : int : nombre d'enregistrements dans une page.
	 * @param pMotCle : String : saisie dans la zone de recherche. 
	 * @param pRedirectAttributes : 
	 * org.springframework.web.servlet.mvc.support.RedirectAttributes
	 * 
	 * @return : String : "redirect:/StudentController/versPageListAll".
	 * 
	 * @throws Exception 
	 */
	@RequestMapping(value="/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(
			@ModelAttribute(name=STUDENT) final StudentEntityJPA pStudent
			, @RequestParam(name=NUMEROPAGESTUDENT, required=false, defaultValue="0") final int pNumeroPage
			, @RequestParam(name=SIZESTUDENT, required=false, defaultValue=DEFAULT_SIZE) final int pSize
			, @RequestParam(name=MOTCLESTUDENT, required=false, defaultValue="") final String pMotCle
				, final RedirectAttributes pRedirectAttributes) 
						throws Exception {
		
		final StudentEntityJPA objetStocke = pStudent;
		String message = null;
		boolean reponseService = false;
		
		
		try {
			
			/* délègue au SERVICE la tâche d'update d'un objet métier. */
			this.studentService.delete(pStudent);
			
			message = "l'objet métier a bien été détruit : " 
					+ pStudent.toString();
			
			reponseService = true;
			
		} catch (Exception e) {
			
			message = "Impossible de détruire l'objet métier : " 
					+ pStudent.toString() + ". " + e.getMessage();
			
			reponseService = false;
			
		}
		
		/* ajoute au RedirectAttributes l'objet métier stocké 
		 * dans l'attribut 'objetDeleteStudent'. */
		pRedirectAttributes.addFlashAttribute(
				"objetDeleteStudent", objetStocke);
		
		/* ajoute au RedirectAttributes le message 
		 * dans l'attribut 'messageDeleteStudent'. */
		pRedirectAttributes.addFlashAttribute(
				"messageDeleteStudent", message);
		
		/* ajoute au RedirectAttributes le resultat de réalisation 
		 * du SERVICE (OK/KO) dans l'attribut 'reponseDeleteStudent'. */
		pRedirectAttributes.addFlashAttribute(
				"reponseDeleteStudent", reponseService);

		
		/* ajoute au RedirectAttributes les paramètres de la requête 
		 * (équivalent de "redirect:/StudentController/versPageListPaginee" 
		 * + "?numeroPageStudent=" + pNumeroPage + "&sizeStudent=" + pSize;. */
		/* ajoute au RedirectAttributes le numéro de page sur lequel 
		 * revenir dans l'attribut 'numeroPageStudent'. */
		if (this.studentService.existsNumeroPage(pNumeroPage, pSize)) {

			pRedirectAttributes.addAttribute(
					NUMEROPAGESTUDENT, pNumeroPage);
			
		} else {

			pRedirectAttributes.addAttribute(
					NUMEROPAGESTUDENT, pNumeroPage - 1);
			
		}
						
		/* ajoute au RedirectAttributes la taille courante (pSize) 
		 * dans l'attribut 'sizeStudent'. */
		pRedirectAttributes.addAttribute(
				SIZESTUDENT, pSize);
		
		/* ajoute au RedirectAttributes le mot-cle courant (pMotCle) 
		 * dans l'attribut 'motCleStudent'. */
		pRedirectAttributes.addAttribute(
				MOTCLESTUDENT, pMotCle);

		return "redirect:/StudentController/versPageListPaginee";
		
	} // Fin de save(...)._________________________________________________
	

	
	/**
	 * <strong>LISTE DE TOUS LES STUDENTS DANS LE STOCKAGE</strong><br/>
	 * retourne la page HTML <code>"/metier/students/listAllStudents.html"</code>
	 * à chaque lancement de l'action 
	 * <code>"/StudentController/versPageListAll"</code>.<br/>
	 * Le préfixe "/StudentController" figure en haut de la classe.
	 * <ul>
	 * <li>fournit un Objet Metier vide au Model pour les formulaires 
	 * de saisie des Objets métier dans l'attribut 
	 * <strong>'student'</strong> (binding).</li>
	 * <li>délègue au SERVICE la tâche de récupérer la liste 
	 * de tous les objets métier dans le stockage.</li>
	 * <li>passe au Model la liste de tous les objets métier dans le stockage 
	 * dans l'attribut <strong>'listeStudent'</strong>.</li>
	 * <li>passe au Model le message de réalisation du SERVICE 
	 * dans l'attribut <strong>'messageListAllStudent'</strong>.</li>
	 * <li>passe au Model le resultat de réalisation du SERVICE (OK/KO) 
	 * dans l'attribut <strong>'reponseServiceListAllStudent'</strong>.</li>
	 * </ul>
	 *
	 * @param pModel : org.springframework.ui.Model
	 * 
	 * @return : String : "/metier/students/listAllStudents".
	 */
	@GetMapping("/versPageListAll")
	public String versPageListAll(final Model pModel) {
		
		this.rafraichirListeAll(pModel);

		return "/metier/students/listAllStudents";
		
	} // Fin de versPageListAll(...).______________________________________
	
	
	
	/**
	 * <strong>RAFRAICHIT LA PAGE listant tous les objets métier</strong>.<br/>
	 * alimente le Model avec les valeurs rafraîchies à chaque appel.
	 * <ul>
	 * <li>fournit un Objet Metier vide au Model pour les formulaires 
	 * de saisie des Objets métier dans l'attribut 
	 * <strong>'student'</strong> (binding).</li>
	 * <li>délègue au SERVICE la tâche de récupérer la liste 
	 * de tous les objets métier dans le stockage.</li>
	 * <li>passe au Model la liste de tous les objets métier dans le stockage 
	 * dans l'attribut <strong>'listeStudent'</strong>.</li>
	 * <li>passe au Model le message de réalisation du SERVICE 
	 * dans l'attribut <strong>'messageListAllStudent'</strong>.</li>
	 * <li>passe au Model le resultat de réalisation du SERVICE (OK/KO) 
	 * dans l'attribut <strong>'reponseServiceListAllStudent'</strong>.</li>
	 * </ul>
	 *
	 * @param pModel : org.springframework.ui.Model
	 */
	private void rafraichirListeAll(
			final Model pModel) {
		
		
		List<StudentEntityJPA> listeObjetsMetier = null;
		String messageListAll = null;
		boolean reponseServiceListAll = false;
		
		/* fournit un Objet Metier vide au Model pour les formulaires 
		 * de saisie des Objets métier dans l'attributs 'student' (binding). */
		final StudentEntityJPA student = new StudentEntityJPA();
		pModel.addAttribute(STUDENT, student);
		
		try {
			
			/* délègue au SERVICE la tâche de récupérer la liste 
			 * de tous les objets métier dans le stockage. */
			listeObjetsMetier 
				= this.studentService.findAll();
			
			messageListAll = "liste des objets métier bien récupérée";
			reponseServiceListAll = true;
			
		} catch (Exception e) {
			
			messageListAll 
				= "impossible de récupérer la liste des objets métier. " 
						+ e.getMessage();
			
			reponseServiceListAll = false;
		}
		
		/* passe au Model la liste de tous les objets métier 
		 * dans le stockage dans l'attribut 'listeStudent'. */
		pModel.addAttribute("listeStudent", listeObjetsMetier);
		
		/* passe au Model le message de réalisation du SERVICE 
		 * dans l'attribut 'messageListAllStudent'. */
		pModel.addAttribute("messageListAllStudent", messageListAll);
		
		/* passe au Model le resultat de réalisation du SERVICE (OK/KO) 
		 * dans l'attribut 'reponseServiceListAllStudent'. */
		pModel.addAttribute(
				"reponseServiceListAllStudent", reponseServiceListAll);

	}

	
	/**
	 * <strong>LISTE PAGINEE DES STUDENTS DANS LE STOCKAGE</strong><br/>
	 * retourne la page HTML <code>"/metier/students/listAllStudents.html"</code>
	 * à chaque lancement de l'action 
	 * <code>"/StudentController/versPageListPaginee"</code>.<br/>
	 * Le préfixe "/StudentController" figure en haut de la classe.
	 * <ul>
	 * <li>fournit un Objet Metier vide au Model pour les formulaires 
	 * de saisie des Objets métier dans l'attribut 
	 * <strong>'student'</strong> (binding).</li>
	 * <li>délègue au SERVICE la tâche de récupérer 
	 * la Page pNumeroPage dans le stockage.</li>
	 * <li>récupère le nombre total de pages auprès de la Page.</li>
	 * <li>ne pagine que si le nombre total de pages > 1.</li>
	 * <li>instancie un tableau des numéros des pages.</li>
	 * <li>récupère le contenu (liste d'objets métier) de la page.</li>
	 * <li>passe au Model le numéro de page courant (pNumeroPage) 
	 * dans l'attribut <strong>'numeroPageCouranteStudent'</strong>.</li>
	 * <li>passe au Model la taille de page courante (pSize) 
	 * dans l'attribut <strong>'sizeCouranteStudent'</strong>.</li>
	 * <li>passe au Model le mot-clé courant () dans 
	 * l'attribut <strong>'motCleCourantStudent'</strong>.</li>
	 * <li>passe au Model la  Page (org.springframework.data.domain.Page) 
	 * dans l'attribut <strong>'pageStudent'</strong>.</li>
	 * <li>passe au Model le contenu de la  Page (liste d'objets métier) 
	 * dans l'attribut <strong>'listeStudent'</strong>.</li>
	 * <li>passe au Model le tableau des numéros des pages (0-based) 
	 * dans l'attribut <strong>'numerosPagesStudent'</strong>.</li>
	 * <li>passe au Model le nombre total de pages dans l'attribut 
	 * <strong>'nombreTotalPagesStudent'</strong>.</li>
	 * <li>passe au Model le message de réalisation du SERVICE 
	 * dans l'attribut <strong>'messageListPagineeStudent'</strong>.</li>
	 * <li>passe au Model le resultat de réalisation du SERVICE (OK/KO) 
	 * dans l'attribut <strong>'reponseServiceListPagineeStudent'</strong>.</li>
	 * </ul>
	 * 
	 * @param pNumeroPage : int : numero (0-based) de la page. 
	 * @param pSize : int : nombre d'enregistrements dans la page
	 * @param pMotCle : String : saisie dans la zone de recherche. 
	 * @param pModel : org.springframework.ui.Model
	 * 
	 * @return : String : "/metier/students/listAllStudents".
	 */
	@GetMapping("/versPageListPaginee")
	public String versPageListPaginee(
			@RequestParam(name=NUMEROPAGESTUDENT, required=false, defaultValue="0") final int pNumeroPage 
				, @RequestParam(name=SIZESTUDENT, required=false, defaultValue=DEFAULT_SIZE) final int pSize
				, @RequestParam(name=MOTCLESTUDENT, required=false, defaultValue="") final String pMotCle
					, final Model pModel) {

		this.rafraichirListePaginee(pNumeroPage, pSize, pMotCle, pModel);
		
		return "/metier/students/listAllStudents";
		
	} // Fin de versPageListAll(...).______________________________________

	
	
	/**
	 * <strong>RAFRAICHIT LA PAGE pNumeroPage (0-based)</strong>.<br/>
	 * alimente le Model avec les valeurs rafraîchies à chaque appel.
	 * <ul>
	 * <li>fournit un Objet Metier vide au Model pour les formulaires 
	 * de saisie des Objets métier dans l'attribut 
	 * <strong>'student'</strong> (binding).</li>
	 * <li>délègue au SERVICE la tâche de récupérer 
	 * la Page pNumeroPage dans le stockage.</li>
	 * <li>récupère le nombre total de pages auprès de la Page.</li>
	 * <li>ne pagine que si le nombre total de pages > 1.</li>
	 * <li>instancie un tableau des numéros des pages.</li>
	 * <li>récupère le contenu (liste d'objets métier) de la page.</li>
	 * <li>passe au Model le numéro de page courant (pNumeroPage) 
	 * dans l'attribut <strong>'numeroPageCouranteStudent'</strong>.</li>
	 * <li>passe au Model la taille de page courante (pSize) 
	 * dans l'attribut <strong>'sizeCouranteStudent'</strong>.</li>
	 * <li>passe au Model le mot-clé courant () dans 
	 * l'attribut <strong>'motCleCourantStudent'</strong>.</li>
	 * <li>passe au Model la  Page (org.springframework.data.domain.Page) 
	 * dans l'attribut <strong>'pageStudent'</strong>.</li>
	 * <li>passe au Model le contenu de la  Page (liste d'objets métier) 
	 * dans l'attribut <strong>'listeStudent'</strong>.</li>
	 * <li>passe au Model le tableau des numéros des pages (0-based) 
	 * dans l'attribut <strong>'numerosPagesStudent'</strong>.</li>
	 * <li>passe au Model le nombre total de pages dans l'attribut 
	 * <strong>'nombreTotalPagesStudent'</strong>.</li>
	 * <li>passe au Model le message de réalisation du SERVICE 
	 * dans l'attribut <strong>'messageListPagineeStudent'</strong>.</li>
	 * <li>passe au Model le resultat de réalisation du SERVICE (OK/KO) 
	 * dans l'attribut <strong>'reponseServiceListPagineeStudent'</strong>.</li>
	 * </ul>
	 *
	 * @param pNumeroPage : int : numéro de la page (0-based).
	 * @param pSize : int : nombre d'enregistrements dans la Page.
	 * @param pMotCle : String : saisie dans la zone de recherche.
	 * @param pModel : org.springframework.ui.Model.<br/>
	 */
	private void rafraichirListePaginee(
			final int pNumeroPage
			, final int pSize
			, final String pMotCle
			, final Model pModel) {
		
		Page<StudentEntityJPA> pageStudent = null;
		List<StudentEntityJPA> listeObjetsMetier = null;
		
		String messageListAll = null;
		boolean reponseServiceListAll = false;
		
		final int numeroPageCouranteStudent = pNumeroPage;
		final int sizeCouranteStudent = pSize;
		final String motCleCourantStudent = pMotCle;
		
		int nombreTotalPagesStudent = 0;
		int[] pages = null;
		
		/* fournit un Objet Metier vide au Model pour les formulaires 
		 * de saisie des Objets métier dans l'attribut 'student' (binding). */
		final StudentEntityJPA student = new StudentEntityJPA();
		pModel.addAttribute(STUDENT, student);
		
		/* passe au Model le numéro de page courant (pNumeroPage) 
		 * dans l'attribut 'numeroPageCouranteStudent'. */
		pModel.addAttribute(
				"numeroPageCouranteStudent", numeroPageCouranteStudent);
		
		/* passe au Model la taille de page courante (pSize) 
		 * dans l'attribut 'sizeCouranteStudent'. */
		pModel.addAttribute(
				"sizeCouranteStudent", sizeCouranteStudent);
		
		/* passe au Model le mot-clé courant () dans l'attribut 'motCleCourantStudent'. */
		pModel.addAttribute(
				"motCleCourantStudent", motCleCourantStudent);
		
		try {
			
			/* délègue au SERVICE la tâche de récupérer 
			 * la Page pNumeroPage dans le stockage. */
			pageStudent = this.studentService
					.findByNameContains(pMotCle, pNumeroPage, pSize);
			
			if (pageStudent != null) {
								
				/* récupère le nombre total de pages auprès de la Page. */
				nombreTotalPagesStudent = pageStudent.getTotalPages();
				
				/* récupère le contenu (liste d'objets métier) de la page. */
				listeObjetsMetier = pageStudent.getContent();
				
				/* ne pagine que si le nombre de pages > 1. */
				if (nombreTotalPagesStudent > 1) {
					
					/* instancie un tableau des numéros des pages. */
					pages = new int[nombreTotalPagesStudent];
															
					/* passe au Model le tableau des numéros des pages (0-based) 
					 * dans l'attribut 'numerosPagesStudent'. */
					pModel.addAttribute("numerosPagesStudent", pages);
					
					/* passe au Model le nombre total de pages 
					 * dans l'attribut 'nombreTotalPagesStudent'. */
					pModel.addAttribute(
							"nombreTotalPagesStudent", nombreTotalPagesStudent);
					
				}
								
				reponseServiceListAll = true;
				
			} else {
				
				messageListAll 
				= "la page numéro " 
						+ pNumeroPage 
						+ " n'a pu être récupérée";
			
				reponseServiceListAll = false;
			}
						
		} catch (Exception e) {
			
			messageListAll 
			= "la page numéro " 
					+ pNumeroPage 
					+ " n'a pu être récupérée " + e.getMessage();
		
			reponseServiceListAll = false;
		}
		
		/* passe au Model la  Page (org.springframework.data.domain.Page) 
		 * dans l'attribut 'pageStudent'. */
		pModel.addAttribute("pageStudent", pageStudent);
		
		/* passe au Model le contenu de la  Page (liste d'objets métier) 
		 * dans l'attribut 'listeStudent'. */
		pModel.addAttribute("listeStudent", listeObjetsMetier);
				
		/* passe au Model le message de réalisation du SERVICE 
		 * dans l'attribut 'messageListPagineeStudent'. */
		pModel.addAttribute("messageListPagineeStudent", messageListAll);
		
		/* passe au Model le resultat de réalisation du SERVICE (OK/KO) 
		 * dans l'attribut 'reponseServiceListPagineeStudent'. */
		pModel.addAttribute(
				"reponseServiceListPagineeStudent", reponseServiceListAll);
		
	} // Fin de rafraichirListePaginee(...)._______________________________
	
	
	
} // FIN DE LA CLASSE StudentController.-------------------------------------
