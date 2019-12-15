package levy.daniel.application.model.persistence.metier.students.entities.jpa.impl;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.IConstantesApplicatives;

/**
 * CLASSE StudentEntityJPA :<br/>
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
 * @since 30 nov. 2019
 *
 */
@Entity(name="StudentEntityJPA")
@Table(name="STUDENTS", schema="PUBLIC"
, uniqueConstraints=@UniqueConstraint(name="UNICITE_NAME"
, columnNames={"NAME"})
, indexes={@Index(name="INDEX_NAME", columnList="NAME")})
public class StudentEntityJPA implements Serializable {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * 1L.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID en base.
	 */
	private Long id;
	
	/**
	 * nom de l'étudiant.
	 */
	private String name;
	
	/**
	 * Section de l'élève (biologie, mathématiques, ...).
	 */
	private String department;
	
	/**
	 * nom de l'administrateur qui a inscrit ou modifié l'élève.
	 */
	private String updatedBy;
	
	/**
	 * date de l'inscription ou de la modification de l'élève.
	 */
	private LocalDate upDatedOn;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(StudentEntityJPA.class);

	// *************************METHODES************************************/

		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public StudentEntityJPA() {
		this(null, null, null, null);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	 /**
	 * CONSTRUCTEUR COMPLET.<br/>
	 * 
	 * @param pName : String : nom de l'étudiant.
	 * @param pDepartment : String : 
	 * Section de l'élève (biologie, mathématiques, ...).
	 * @param pUpdatedBy : String : 
	 * nom de l'administrateur qui a inscrit ou modifié l'élève.
	 * @param pUpDatedOn : java.time.LocalDate : 
	 * date de l'inscription ou de la modification de l'élève.
	 */
	public StudentEntityJPA(
			final String pName
				, final String pDepartment
					, final String pUpdatedBy
						, final LocalDate pUpDatedOn) {
		
		super();
		
		this.name = pName;
		this.department = pDepartment;
		this.updatedBy = pUpdatedBy;
		this.upDatedOn = pUpDatedOn;
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________

	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		return Objects.hash(this.getName());
	} // Fin de  hashCode()._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean equals(
			final Object pObject) {
		
		if (this == pObject) {
			return true;
		}
		if (pObject == null) {
			return false;
		}
		if (!(pObject instanceof StudentEntityJPA)) {
			return false;
		}
		
		final StudentEntityJPA other = (StudentEntityJPA) pObject;
		
		return Objects.equals(this.getName(), other.getName());
		
	} // Fin de equals(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		
		final StringBuilder builder = new StringBuilder();
		
		builder.append("StudentEntityJPA [");
		
		builder.append("id=");
		if (this.getId() != null) {			
			builder.append(this.getId());
		} else {
			builder.append(IConstantesApplicatives.NULL);
		}
		
		builder.append(IConstantesApplicatives.VIRGULE_ESPACE);
		
		builder.append("name=");
		if (this.getName() != null) {			
			builder.append(this.getName());
		} else {
			builder.append(IConstantesApplicatives.NULL);
		}
		
		builder.append(IConstantesApplicatives.VIRGULE_ESPACE);
		
		builder.append("department=");
		if (this.getDepartment() != null) {			
			builder.append(this.getDepartment());
		} else {
			builder.append(IConstantesApplicatives.NULL);
		}
		
		builder.append(IConstantesApplicatives.VIRGULE_ESPACE);
		
		builder.append("updatedBy=");
		if (this.getUpdatedBy() != null) {			
			builder.append(this.getUpdatedBy());
		} else {
			builder.append(IConstantesApplicatives.NULL);
		}
		
		builder.append(IConstantesApplicatives.VIRGULE_ESPACE);
		
		builder.append("upDatedOn=");
		if (this.getUpDatedOn() != null) {
			
			final DateTimeFormatter formatter 
			= DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			builder.append(this.getUpDatedOn().format(formatter));
			
		} else {
			builder.append(IConstantesApplicatives.NULL);
		}
		
		builder.append(']');
		
		return builder.toString();
		
	} // Fin de toString().________________________________________________



	/**
	 * Getter de l'ID en base.
	 *
	 * @return this.id : Long.<br/>
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	public Long getId() {
		return this.id;
	} // Fin de getId().___________________________________________________



	/**
	* Setter de l'ID en base.
	*
	* @param pId : Long : 
	* valeur à passer à this.id.<br/>
	*/
	public void setId(
			final Long pId) {
		this.id = pId;
	} // Fin de setId(...).________________________________________________


	
	/**
	 * Getter du nom de l'étudiant.
	 *
	 * @return this.name : String.<br/>
	 */
	@Column(name = "NAME"
			, unique = false, updatable = true
			, insertable = true, nullable = false)
	@NotNull
	public String getName() {
		return this.name;
	} // Fin de getName()._________________________________________________


	
	/**
	* Setter du nom de l'étudiant.
	*
	* @param pName : String : 
	* valeur à passer à this.name.<br/>
	*/
	public void setName(final String pName) {
		this.name = pName;
	} // Fin de setName(...).______________________________________________


	
	/**
	 * Getter de la Section de l'élève (biologie, mathématiques, ...).
	 *
	 * @return this.department : String.<br/>
	 */
	@Column(name = "DEPARTMENT"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	public String getDepartment() {
		return this.department;
	} // Fin de getDepartment().___________________________________________


	
	/**
	* Setter de la Section de l'élève (biologie, mathématiques, ...).
	*
	* @param pDepartment : String : 
	* valeur à passer à this.department.<br/>
	*/
	public void setDepartment(final String pDepartment) {
		this.department = pDepartment;
	} // Fin de setDepartment(...).________________________________________


	
	/**
	 * Getter du nom de l'administrateur qui a inscrit ou modifié l'élève.
	 *
	 * @return this.updatedBy : String.<br/>
	 */
	@Column(name = "UPDATEDBY"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	public String getUpdatedBy() {
		return this.updatedBy;
	} // Fin de getUpdatedBy().____________________________________________


	
	/**
	* Setter du nom de l'administrateur qui a inscrit ou modifié l'élève.
	*
	* @param pUpdatedBy : String : 
	* valeur à passer à this.updatedBy.<br/>
	*/
	public void setUpdatedBy(
			final String pUpdatedBy) {
		this.updatedBy = pUpdatedBy;
	} // Fin de setUpdatedBy(...)._________________________________________


	
	/**
	 * Getter date de l'inscription ou de la modification de l'élève.
	 *
	 * @return this.upDatedOn : java.time.LocalDateTime.<br/>
	 */
//	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "UPDATEDON"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	public LocalDate getUpDatedOn() {
		return this.upDatedOn;
	} // Fin de getUpDatedOn().____________________________________________


	
	/**
	* Setter date de l'inscription ou de la modification de l'élève.
	*
	* @param pUpDatedOn : java.time.LocalDateTime : 
	* valeur à passer à this.upDatedOn.<br/>
	*/
	public void setUpDatedOn(final LocalDate pUpDatedOn) {
		this.upDatedOn = pUpDatedOn;
	} // Fin de setUpDatedOn(...)._________________________________________

		
	
} // FIN DE LA CLASSE StudentEntityJPA.--------------------------------------
