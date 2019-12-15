package levy.daniel.application.model.persistence.metier.students.dao.jpaspring;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import levy.daniel.application.model.persistence.metier.students.entities.jpa.impl.StudentEntityJPA;

/**
 * INTERFACE StudentDao :<br/>
 * .<br/>
 * References :
 * <ul> 
 * <li>https://docs.spring.io/spring-data/jpa/docs/2.2.1.RELEASE/reference/html/#reference</li>
 * <li>https://docs.spring.io/spring-data/jpa/docs/2.2.1.RELEASE/reference/html/#repository-query-keywords</li>
 * <li>https://docs.spring.io/spring-data/jpa/docs/2.2.1.RELEASE/reference/html/#repositories.query-methods.query-lookup-strategies</li>
 *</ul>
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
@Repository(value="StudentDao")
public interface StudentDao extends JpaRepository<StudentEntityJPA, Long> {
	

	
	/**
	 * Retourne l'objet métier recherché via son IDENTIFIANT METIER.<br/>
	 * SPRING DATA ne possédant pas cette méthode, 
	 * on la définit via une requête HQL.
	 *
	 * @param pName : ID métier de l'Objet métier.
	 * 
	 * @return : StudentEntityJPA : 
	 * l'objet métier trouvé via son IDENTIFIANT METIER.
	 */
	@Query("select student from StudentEntityJPA student where student.name = :x")
	StudentEntityJPA loadByName(@Param(value="x")  String pName);
	

	
	/**
	 * retourne la Page de numéro (0-based) et de taille indiqués dans pPageable 
	 * contenant les objets métier dont l'ID METIER contient pMotCle.
	 * <ul>
	 * <li><strong>ne nécessite pas de définir de Query car SPRING DATA INTUITE 
	 * LA REQUETE en se basant sur des 
	 * <i>mots-clé dans la signature de la méthode 
	 * <u>prédéfinis</u></i> : </strong>
	 * <ol>
	 * <li><strong>'find'</strong> en tout début du nom de la 
	 * méthode indique <strong>'SELECT *'</strong> (SQL).</li>
	 * <li>l' Entity JPA précisée dans la définition de la présente 
	 * interface fait le <strong>FROM TABLE ...</strong> (SQL).</li>
	 * <li><strong>'By'</strong> réalise le 
	 * <strong>WHERE</strong> (SQL).</li>
	 * <li><strong>'Name'</strong> réalise le <strong>PROPRIETE</strong> 
	 * (SQL).<br/>
	 * ATTENTION, Name est le nom "camélisé d'une propriété 'name' 
	 * <i>existante</i> dans l'OBJET METIER.</li>
	 * <li><strong>'Contains'</strong> réalise le 
	 * <strong>LIKE</strong> (SQL).</li>
	 * <li>Le paramètre de la méthode pMotcle réalise 
	 * le <strong>:pMotCle</strong> (SQL).</li>
	 * </ol>
	 * </li>
	 * <li>
	 * <code>findByNameContains(String pMotCle)</code> est donc 
	 * traduit par SPRING DATA en JPQL en : <br/>
	 * <strong>"select student from StudentEntityJPA student 
	 * where student.name like :pMotCle"</strong>.
	 * </li>
	 * <li>Le <i>respect</i> des mots clés 
	 * <strong>('find', 'By', 'prop camélisée', 'Contains')</strong> 
	 * dans le nom de la méthode est <strong>OBLIGATOIRE</strong>.</li>
	 * <li><i>Ne pas mettre de <strong>%</strong> autour 
	 * de la valeur passée à pMotCle. SPRING DATA s'en charge</i>.</li>
	 * <li>Le résultat est <strong>sensible à la casse</strong>.
	 * <br/>"D" passé en paramètre n'aura pas le même effet que 
	 * "d" passé en paramètre.</li>
	 * </ul>
	 *
	 * @param pMotCle : String : 
	 * l'expression que les ID METIER de l'objet métier doivent contenir.
	 * <br/>Par exemple, "ard" retournera les "Gérard".
	 * @param pPageable : org.springframework.data.domain.Pageable
	 * 
	 * @return : org.springframework.data.domain.Page&lt;StudentEntityJPA&gt;
	 */
	Page<StudentEntityJPA> findByNameContains(
			String pMotCle, Pageable pPageable);
	
	
	
} // FIN DE L'INTERFACE StudentDao.------------------------------------------
