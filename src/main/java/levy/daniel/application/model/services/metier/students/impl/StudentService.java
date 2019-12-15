package levy.daniel.application.model.services.metier.students.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import levy.daniel.application.apptechnic.exceptions.technical.impl.MauvaisParametreRunTimeException;
import levy.daniel.application.model.persistence.metier.students.dao.jpaspring.StudentDao;
import levy.daniel.application.model.persistence.metier.students.entities.jpa.impl.StudentEntityJPA;
import levy.daniel.application.model.services.metier.students.IStudentService;

/**
 * CLASSE StudentService :<br/>
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
@Transactional
@Service(value="StudentService")
public class StudentService implements IStudentService {

	// ************************ATTRIBUTS************************************/

	/**
	 * ". ".
	 */
	public static final String POINT_ESPACE = ". ";

	/**
	 * "Le nombre d'enrgistrements par page ".
	 */
	public static final String NBRE_ENREGISTREMENTS_PAGE 
		= "Le nombre d'enrgistrements par page ";
	
	/**
	 * "doit être supérieur ou égal à 1".
	 */
	public static final String SUPERIEUR_A_UN 
		= "doit être supérieur ou égal à 1";
	
	/**
	 * StudentDao injecté par SPRING.
	 */
	@Autowired
	@Qualifier("StudentDao")
	private StudentDao studentDao;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(StudentService.class);

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public StudentService() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public StudentEntityJPA save(
			final StudentEntityJPA pObject) throws Exception {
		
		/* jette une MauvaisParametreRunTimeException 
		 * si l'objet METIER est null. */
		if (pObject == null) {
			
			final String message 
				= "Vous devez saisir un Student NON NULL";
			
			throw new MauvaisParametreRunTimeException(message);
		}
		
		/* jette une MauvaisParametreRunTimeException 
		 * si l'identifiant METIER est blank. */
		if (StringUtils.isBlank(pObject.getName())) {
			
			final String message 
				= "Vous devez renseigner au moins le NAME du Student";
			
			throw new MauvaisParametreRunTimeException(message);
			
		}
		
		/* jette une MauvaisParametreRunTimeException en cas de doublon. */
		if (this.exists(pObject)) {
			
			final String message 
				= "il existe déjà un objet avec le même identifiant "
						+ "métier dans le stockage - DOUBLON - "
						+ "STOCKAGE IMPOSSIBLE";
			
			throw new MauvaisParametreRunTimeException(message);
			
		}
		
		/* délègue au DAO la tâche de stocker 
		 * l'Objet Métier et le retourne. */
		try {
			
			return this.studentDao.save(pObject);
		
		/* jette une MauvaisParametreRunTimeException si le stockage est impossible. */
		} catch (Exception e) {
			
			final String message 
			= "STOCKAGE IMPOSSIBLE : " + e.getMessage();
		
			throw new MauvaisParametreRunTimeException(message);
		}
		
	} // Fin de save(...)._________________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public StudentEntityJPA findByIdMetier(
			final StudentEntityJPA pObject) throws Exception {
		
		/* retourne null si pObject == null. */
		if (pObject == null) {
			return null;
		}
		
		final String name = pObject.getName();
		
		/* retourne null si l'IDENTIFIANT METIER est blank. */
		if (StringUtils.isBlank(name)) {
			return null;
		}

		/* délègue au DAO la tâche de récupérer l'objet métier 
		 * dans le stockage via son IDENTIFIANT METIER. */		
		final StudentEntityJPA resultat = this.studentDao.loadByName(name);
		
		return resultat;
		
	} // Fin de findByIdMetier(...)._______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<StudentEntityJPA> findById(
			final Long pId) throws Exception {
				
		/* jette une MauvaisParametreRunTimeException si pId == null. */
		if (pId == null) {
			
			final String message 
			= "Vous devez fournir un ID en base NON NULL";
		
			throw new MauvaisParametreRunTimeException(message);
		}

		return this.studentDao.findById(pId);
		
	} // Fin de findById(...)._____________________________________________

	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<StudentEntityJPA> findByNameContains(
			final String pMotCle
				, final int pNumeroPage
					, final int pSize) throws Exception {
				
		/* jette une MauvaisParametreRunTimeException 
		 * si pSize < 1. */
		if (pSize < 1) {
			
			final String message 
				= NBRE_ENREGISTREMENTS_PAGE
						+ SUPERIEUR_A_UN;
			
			throw new MauvaisParametreRunTimeException(message);
		}

		String motCle = "";
		
		/* retourne tous les enregistrements paginés si pMotCle est blank. */
		if (StringUtils.isAllBlank(pMotCle)) {
			motCle = "";
		} else {
			motCle = pMotCle;
		}
		
		Page<StudentEntityJPA> resultat = null;
		
		try {
			
			/* délègue au DAO la tâche de récupérer tous les enregistrements 
			 * contenant le mot-clé dans le stockage. */
			resultat 
				= this.studentDao.findByNameContains(
						motCle, PageRequest.of(pNumeroPage, pSize));
			
			return resultat;
		
		/* jette une MauvaisParametreRunTimeException 
		 * si le mot-clé n'est pas trouvé. */
		} catch (Exception e) {
			
			final String message 
				= "Impossible de trouver la liste des objets métier "
						+ "dont l'ID METIER contient le mot-clé : " 
						+ pMotCle 
						+ POINT_ESPACE 
						+ e.getMessage();
			
			throw new MauvaisParametreRunTimeException(message);
		}
		
	} // Fin de findByNameContains(...).___________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<StudentEntityJPA> findAllPagine(
			final int pNumeroPage, final int pSize) throws Exception {
		
		/* jette une MauvaisParametreRunTimeException 
		 * si pSize < 1. */
		if (pSize < 1) {
			
			final String message 
				= NBRE_ENREGISTREMENTS_PAGE
						+ SUPERIEUR_A_UN;
			
			throw new MauvaisParametreRunTimeException(message);
		}

		/* délègue au DAO la tâche de trouver les enregistrements 
		 * de la page pNumeroPage (0-based) contenant 
		 * pSize enregistrements dans le stockage. */
		return this.studentDao.findAll(PageRequest.of(pNumeroPage, pSize));
		
	} // Fin de findAllPagine(...).________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<StudentEntityJPA> findAll() throws Exception {
		return this.studentDao.findAll();
	} // Fin de findAll()._________________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public StudentEntityJPA update(
			final StudentEntityJPA pObject) throws Exception {
		
		/* jette une MauvaisParametreRunTimeException 
		 * si l'objet METIER est null. */
		if (pObject == null) {
			
			final String message 
				= "Vous devez saisir un Student NON NULL.";
			
			throw new MauvaisParametreRunTimeException(message);
		}
		
		/* jette une MauvaisParametreRunTimeException si l'objet 
		 * métier passé en paramètre n'est pas persistant (pas d'ID en base). */
		if (pObject.getId() == null) {
			
			final String message 
			= "Vous ne pouvez modifier qu'un objet déjà persistant.";
		
			throw new MauvaisParametreRunTimeException(message);
		}
		
		/* jette une MauvaisParametreRunTimeException si l'objet
		 *  métier passé en paramètre n'est pas déjà persistant 
		 *  (ID inexistant en base). */
		if (!this.existsByIdStockage(pObject.getId())) {
			
			final String message 
			= "Vous ne pouvez modifier qu'un objet "
					+ "déjà persistant (présent en base).";
		
			throw new MauvaisParametreRunTimeException(message);
		}
		
		/* jette une MauvaisParametreRunTimeException 
		 * si l'identifiant METIER est blank. */
		if (StringUtils.isBlank(pObject.getName())) {
			
			final String message 
				= "Vous devez renseigner au moins le NAME du Student.";
			
			throw new MauvaisParametreRunTimeException(message);
			
		}
		
		
		/* délègue au DAO la tâche de stocker 
		 * l'Objet Métier et le retourne. */
		try {
			
			return this.studentDao.save(pObject);
			
		} catch (Exception e) {
			
			/* jette une MauvaisParametreRunTimeException 
			 * si les modifications engendreraient un doublon. */
			final String message 
			= "il existe déjà un objet avec le même identifiant "
					+ "métier dans le stockage - DOUBLON - "
					+ "MODIFICATION IMPOSSIBLE. " + e.getMessage();
		
			throw new MauvaisParametreRunTimeException(message);
		}
		
	} // Fin de update(...)._______________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteByIdStockage(final Long pId) throws Exception {
		
		/* jette une MauvaisParametreRunTimeException si pId == null. */
		if (pId == null) {
			
			final String message 
			= "Vous devez fournir un ID en base NON NULL";
		
			throw new MauvaisParametreRunTimeException(message);
		}
		
		/* récupère l'objet métier dans le stockage en utilisant 
		 * this.findById(pId). */
		final Optional<StudentEntityJPA> object = this.findById(pId);
		
		if (object.isPresent()) {
			
			final StudentEntityJPA objet = object.get();
			
			try {
				
				/* délègue au DAO la tâche de détruire l'objet métier 
				 * dans le stockage. */
				this.studentDao.delete(objet);
				
				return true;
			
			/* jette une MauvaisParametreRunTimeException si le DAO 
			 * ne parvient pas à détruire l'objet existant dans le stockage. */
			} catch (Exception e) {
				
				final String message 
					= "Impossible de détruire l'objet métier d'identifiant " 
							+ pId 
							+ POINT_ESPACE 
							+  e.getMessage();
				
				throw new MauvaisParametreRunTimeException(message);
			}
			
		}
		
		/* jette une MauvaisParametreRunTimeException si l'objet métier
		 *  d'ID en base pId n'existe pas dans le stockage. */
		final String message 
		= "PAs d'objet métier dans le stockage avec l'ID " + pId;
	
		throw new MauvaisParametreRunTimeException(message);
		
	} // Fin de deleteByIdStockage(...).___________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean delete(final StudentEntityJPA pObject) throws Exception {
		
		/* jette une MauvaisParametreRunTimeException si pObject == null. */
		if (pObject == null) {
			
			final String message 
			= "Vous devez fournir un Objet métier NON NULL";
		
			throw new MauvaisParametreRunTimeException(message);
		}
		
		/* récupère l'objet métier dans le stockage en utilisant 
		 * this.findByIdMetier(pObject). */
		try {
			
			final StudentEntityJPA objet = this.findByIdMetier(pObject);
			
			try {
				
				/* délègue au DAO la tâche de détruire l'objet métier 
				 * dans le stockage. */
				this.studentDao.delete(objet);
				
				return true;
			
			/* jette une MauvaisParametreRunTimeException si pObject 
			 * ne peut être détruit dans le stockage. */
			} catch (Exception e) {
				
				final String message 
				= "Impossible de détruire dans le stockage l'objet métier : " 
				+ pObject.toString() 
				+ POINT_ESPACE 
				+ e.getMessage();
				
				throw new MauvaisParametreRunTimeException(message);
			}
			
		/* jette une MauvaisParametreRunTimeException si pObject 
		 * ne peut être trouvé dans le stockage. */	
		} catch (Exception e) {
			
			final String message 
			= "Impossible de trouver dans le stockage l'objet métier : " 
			+ pObject.toString() 
			+ POINT_ESPACE 
			+ e.getMessage();
			
			throw new MauvaisParametreRunTimeException(message);
		}
		
	} // Fin de delete(...)._______________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean exists(final StudentEntityJPA pObject) throws Exception {
		
		/* retourne toujours false si pObject == null. */
		if (pObject == null) {
			return false;
		}
		
		/* recherche l'objet métier dans le stockage au moyen 
		 * de la méthode this.findByIdMetier(pObject). */
		final StudentEntityJPA resultat = this.findByIdMetier(pObject);
		
		/* retourne true si l'objet métier existe, false sinon.*/
		if (resultat != null) {
			return true;
		}
		
		return false;
		
	} // Fin de exists(...)._______________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existsByIdStockage(final Long pId) throws Exception {
		
		/* retourne toujours false si pId == null. */
		if (pId == null) {
			return false;
		}
		
		try {
			
			/* délègue au DAO la tâche de trouver l'objet métier 
			 * dans le stockage. */
			final Optional<StudentEntityJPA> resultat 
				= this.studentDao.findById(pId);
			
			/* retourne true si l'objet métier est présent en base
			 * , false sinon. */
			if (resultat.isPresent()) {
				return true;
			}
			
			return false;
		
		/* jette une MauvaisParametreRunTimeException si DAO.findById(pId) 
		 * lève une Exception. */
		} catch (Exception e) {
			
			final String message 
				= "Impossible de trouver l'objet métier d' ID " 
						+ pId + POINT_ESPACE 
						+ e.getMessage();
			
			throw new MauvaisParametreRunTimeException(message);
		}
		
	} // Fin de existsByIdStockage(...).___________________________________
	

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int findNumeroPage(
			final String pIdMetier
				, final int pSize) throws Exception {
		
		/* jette une MauvaisParametreRunTimeException 
		 * si pIdMetier est blank. */
		if (StringUtils.isAllBlank(pIdMetier)) {
			
			final String message = "vous devez renseigner l'ID METIER";
			
			throw new MauvaisParametreRunTimeException(message);
		}
		
		/* jette une MauvaisParametreRunTimeException 
		 * si pSize < 1. */
		if (pSize < 1) {
			
			final String message 
				= NBRE_ENREGISTREMENTS_PAGE
						+ SUPERIEUR_A_UN;
			
			throw new MauvaisParametreRunTimeException(message);
		}
		
		Page<StudentEntityJPA> page = null;
		
		final Page<StudentEntityJPA> premierePage 
			= this.findAllPagine(0, pSize);
		
		final int nombreTotalPages = premierePage.getTotalPages();
		
		for (int i = 0; i < nombreTotalPages; i++) {
			
			page = this.findAllPagine(i, pSize);
			
			final List<StudentEntityJPA> liste = page.getContent();
			
			for (final StudentEntityJPA objet : liste) {
				
				if (objet.getName().equals(pIdMetier)) {
					return i;
				}
												
			}
			
		}
		
		/* jette une MauvaisParametreRunTimeException 
		 * si la page n'est pas trouvée. */			
		final String message 
			= "Aucune page ne contient l'objet métier d'ID METIER : " 
		+ pIdMetier;
		
		throw new MauvaisParametreRunTimeException(message);
				
	} // Fin de findNumeroPage(...)._______________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existsNumeroPage(
			final int pNumeroPage, final int pSize) throws Exception  {

		/* jette une MauvaisParametreRunTimeException 
		 * si pSize < 1. */
		if (pSize < 1) {
			
			final String message 
				= NBRE_ENREGISTREMENTS_PAGE
						+ SUPERIEUR_A_UN;
			
			throw new MauvaisParametreRunTimeException(message);
		}

		try {
			
			final Page<StudentEntityJPA> page 
				= this.studentDao.findAll(PageRequest.of(pNumeroPage, pSize));
			
			/* teste si la page retournée est vide (DAO.findAll(Pageable) retourne une Page vide et non null si la Page n'est pas trouvée). */
			if (page != null && !page.isEmpty()) {
				return true;
			}
			
			return false;
			
		} catch (Exception e) {
			
			return false;
			
		}
		
	} // Fin de existsNumeroPage(...)._____________________________________
	
	
	
} // FIN DE LA CLASSE StudentService.----------------------------------------
