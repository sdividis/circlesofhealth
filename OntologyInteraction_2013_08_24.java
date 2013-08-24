 
import java.io.File;   
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.update.GraphStore;
import com.hp.hpl.jena.util.FileManager;
import com.mysql.jdbc.Statement;
 
/**
 * @version 2.0 24/08/2013
 * @author Laia Subirats lsubirats@bdigital.org
 */  
public class OntologyInteractionAct {
	static String URLOntology="CirclesofHealth_2013_08_24.owl";
	static String ns="http://purl.bioontology.org/ontology/PMR.owl#";
	static String st="xsd:string";
	static String db="E10-E14";
	static String db2="73211009";
	private GraphStore graphStore=null;
	private OntModel model;
	private File file;  
	/** 
	 * Creates an instance to query the ontology and loads the location of the ontology file.
	 * @param fileURL String of the absolute URL of the OWL file
	 */ 
	public OntologyInteractionAct(String fileURL){
		this.file=new File("C://Program Files (x86)/apache-tomcat-7.0.32/webapps/ROOT/PMR.owl");
		this.model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_RULE_INF );
		java.io.InputStream in = FileManager.get().open(fileURL);
		if (in == null) {
		    throw new IllegalArgumentException("Archivo no encontrado "+fileURL);
		} 
		this.model.read(in, "");
	} 

	public static void main(String args[]) throws IOException{
		//Creates an instance of the class to load the ontology
		OntologyInteractionAct Rehabilita=new OntologyInteractionAct(URLOntology);
		printList(Rehabilita.getIndicators("Paraplegia_and_tetraplegia"));
		printList(Rehabilita.getIndicators("Cerebral_infarction,_unspecified"));
		//Gets all indicators
			//printList(Rehabilita.getIndicators());
		//Gets indicators from each activity/scenario
			//printList(Rehabilita.getIndicators("Robotic_training"));
			//printList(Rehabilita.getIndicators("WalkingActivity"));
			//printList(Rehabilita.getIndicators("Nebulizer_usage"));
			//printList(Rehabilita.getIndicators("BuyBread"));
		
		//Gets rehabilitation processes
			//printList(Rehabilita.getProcesosRehabilitacion());
			//printList(Rehabilita.getChildProcesses("Cardiac_Rehabilitation"));
		
		//Gets the information (name, code, description and parameters) of an activity
			//printList(Rehabilita.getInfoActividad("Comprar_el_pan"));
		//Gets activities
			//printList(Rehabilita.getActivities("Aerobic_exercise"));
			//printList(Rehabilita.getActivities());
		//Get recommended activities depending on indicators and parameters
			//printList(Rehabilita.getActivitiesbyIndicator("Arterial_blood_pressure"));
			//printList(Rehabilita.getActivitiesbyParameter("Arterial_blood_pressure"));
		//Get parameters of activities
			printList(Rehabilita.getParameters("Bicycle_ergometer"));
		//printList(Rehabilita.getFases("WalkingActivity"));
		
		//Gets diseases
			//printList(Rehabilita.getDiseases("Congenital_malformations_of_pulmonary_and_tricuspid_valves"));
			//printList(Rehabilita.getDiseasesbyProcess("Cardiac_Rehabilitation"));
			//printList(Rehabilita.getDiseases());
		//Gets pathologies
			//printList(Rehabilita.getPatologias());
			//printList(Rehabilita.getPathologies("WalkingActivity"));
			//printList(Rehabilita.getPathologies("Robotic_training"));
			//printList(Rehabilita.getPathologies("Nebulizer_usage"));
			//printList(Rehabilita.getPathologies("BuyBread"));
		
		//Gets objectives	
			//printList(Rehabilita.getObjectivesbyType("Therapeutic_objective"));
			//printList(Rehabilita.getObjectivesbyType("DLA_objective"));
			//printList(Rehabilita.getObjectivesbyType("Participation_objective"));
			//printList(Rehabilita.getObjectives("Comprar_el_pan"));
			
		//Remove property
			//Rehabilita.removeObject("WalkingActivity","has_indicator","Chest_pain");

		//Add description	
			//Rehabilita.setDescription("WalkingActivity","Descripción de la actividad de la marcha");
		//Add parameter
			//Rehabilita.setObjectProperty("Bicycle_ergometer", "has_parameter", "Heart_rate_after_0_minutes_of_activity_end");
			//Rehabilita.setDefault("Heart_rate_after_0_minutes_of_activity_end","100 bpm");
		
		//Add indicator
			//Rehabilita.setClass("Pulse_rate","Heart_rate_after_X_minutes_of_activity_end");
			//Rehabilita.setObjectProperty("Motor_rehabilitation", "has_indicator", "Heart_rate_after_X_minutes_of_activity_end");
			//Rehabilita.setName("Heart_rate_after_X_minutes_of_activity_end","Frecuencia cardíaca tras X minutos de acabar la actividad");
			//Rehabilita.setCode("Heart_rate_after_X_minutes_of_activity_end","364075005_a_XXm_a");
			//printList(Rehabilita.getIndicators());
		
		//Add disease
			//Rehabilita.setClass("Other_forms_of_heart_disease","Heart_failureX");
			//Rehabilita.setObjectProperty("Cardiac_Rehabilitation", "has_disease","Heart_failureX");
			//Rehabilita.setName("Heart_failureX","Insuficiencia cardíaca X");
			//printList(Rehabilita.getDiseases());
		
		//Add process
			//Rehabilita.setClass("Therapeutic_process","Conferences2");
			//Rehabilita.setName("Conferences2","Educación (charlas) 2");
			//printList(Rehabilita.getProcesosRehabilitacion());
		
		//Add activity
			//Rehabilita.setClass("Other_activities","Diet_conferences2");
			//Rehabilita.setName("Diet_conferences2","Charlas sobre la dieta 2");
			//Rehabilita.setObjectProperty("Conferences", "has_activity", "Diet_conferences2");
			//printList(Rehabilita.getActivities());
		
		//Add pathology
			//Rehabilita.setClass("Disease","PathologyX");
			//Rehabilita.setName("PathologyX", "Patología X");
			//Rehabilita.setCode("PathologyX","Xxx");
			//Rehabilita.setObjectProperty("Heart_failure", "has_pathology","PathologyX");
			//Rehabilita.setObjectProperty("PathologyX", "has_disease","Heart_failure");
			//Rehabilita.addObject("Diagnosis", "PathologyX");
			//printList(Rehabilita.getPatologias());
			
		
	} 
	/**
	 * Prints on the console the list 
	 * @param aList List to be printed
	 */
	public static void printList(List aList){
		System.out.println("Size: "+aList.size());
		for (int b=0;b<aList.size(); b++){
			System.out.println(aList.get(b));
		}
	}
	/**
	 * Adds a subclass in a parent class.
	 * @param Parent class.
	 * @param Child class.
	 */
	public void setClass(String parent, String child) throws IOException{
		OntModel model = this.model;
    	OntClass informaciondeSalud=model.getOntClass(ns+parent);
    	OntClass Child=model.createClass(ns+child);
    	informaciondeSalud.addSubClass(Child);
    	Individual subject=Child.createIndividual(ns+child);
        FileOutputStream b=new FileOutputStream(this.file);
        this.model.write(b);
        b.flush();
        b.close();
		
	}
	/**
	 * Adds a parameter in the person's profile.
	 * @param Process.
	 * @param Indicator.
	 */
	public void setObjectProperty(String subjectName, String propertyName, String objectValue) throws IOException{
		OntModel model = this.model;
    	OntClass informaciondeSalud=model.getOntClass(ns+subjectName);
    	//OntClass aTCE=model.createClass(ns+"TraumatismoCraneoEncefálico");
    	OntClass aTCE=model.getOntClass(ns+objectValue);
    	Individual subject=informaciondeSalud.createIndividual(ns+subjectName);
    	Individual object=aTCE.createIndividual(ns+objectValue);
    	ObjectProperty aproperty=model.createObjectProperty(ns+propertyName);
    	subject.addProperty(aproperty, object);
        FileOutputStream b=new FileOutputStream(this.file);
        this.model.write(b);
        b.flush();
        b.close();
		
	}
	/**
	 * Adds an instance in the ontology
	 * @param parent Class of the instance
	 * @param instance Instance
	 */
	public void addObject(String parent, String instance) throws IOException{
    	OntClass informaciondeSalud=model.getOntClass(ns+parent);
    	Individual anIndividual=informaciondeSalud.createIndividual(ns+instance);
        FileOutputStream b=new FileOutputStream(this.file);
        model.write(b);
        b.flush();
        b.close();
	}
	/**
	 * Removes an instance in the ontology
	 * @param parent Class of the instance
	 * @param instance Instance
	 */
	public void removeObject(String parent, String property, String child) throws IOException{
		
		Resource s=model.getResource(ns+parent);
		Property p=model.getProperty(ns+property);
		Resource o=model.getResource(ns+child);
		model.remove(s,p,o);
		FileOutputStream b=new FileOutputStream(this.file);
        model.write(b);
        b.flush();
        b.close();    
	
	}
	/**
	 * Adds a name to a concept
	 * @param concept
	 * @param name
	 */
	public void setName(String concept, String name) throws IOException{
    	OntClass informaciondeSalud=model.getOntClass(ns+concept);
    	
    	Resource r=ResourceFactory.createResource();
    	Property pro=ResourceFactory.createProperty(ns,"name");
    	informaciondeSalud.addLiteral(pro, name);
    	//System.out.println(model.getDatatypeProperty(codi));
        FileOutputStream b=new FileOutputStream(this.file);
        model.write(b);
        b.flush();
        b.close();
	}
	/**
	 * Adds a code to a concept
	 * @param concept
	 * @param code
	 */
	public void setCode(String concept, String code) throws IOException{
    	OntClass informaciondeSalud=model.getOntClass(ns+concept);
    	Resource r=ResourceFactory.createResource();
    	Property pro=ResourceFactory.createProperty(ns,"code");
    	informaciondeSalud.addLiteral(pro, code);
        FileOutputStream b=new FileOutputStream(this.file);
        model.write(b);
        b.flush();
        b.close();
	}
	/**
	 * Adds a description to a concept
	 * @param concept
	 * @param description
	 */
	public void setDescription(String concept, String description) throws IOException{
    	OntClass informaciondeSalud=model.getOntClass(ns+concept);
    	Resource r=ResourceFactory.createResource();
    	Property pro=ResourceFactory.createProperty(ns,"description");
    	informaciondeSalud.addLiteral(pro, description);
        FileOutputStream b=new FileOutputStream(this.file);
        model.write(b);
        b.flush();
        b.close();
	}
	/**
	 * Adds a default value to a parameter
	 * @param parameter
	 * @param vañue
	 */
	public void setDefault(String parameter, String value) throws IOException{
    	OntClass informaciondeSalud=model.getOntClass(ns+parameter);
    	Resource r=ResourceFactory.createResource();
    	Property pro=ResourceFactory.createProperty(ns,"default");
    	informaciondeSalud.addLiteral(pro, value);
        FileOutputStream b=new FileOutputStream(this.file);
        model.write(b);
        b.flush();
        b.close();
	}
	/**
	 * Gets list of rehabilitation processes.
	 * @returns List of Processes.
	 */
	public List getProcesosRehabilitacion(){
		String queryString ="PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "+ 
				"select ?process ?name "+
			    "where { "+
			     "?process  a <" + ns + "Process" +">"+
			     ".?process  <" + ns + "name" +"> ?name"+
			    "} \n ";
				//System.out.println("Query: "+queryString);
				Query query = QueryFactory.create(queryString);
				QueryExecution qe = QueryExecutionFactory.create(query, this.model);
				List resultado=null;
				try {
				   ResultSet results = qe.execSelect();
				   resultado=ResultSetFormatter.toList(results);
				} finally { qe.close() ; 
				}	
				for(int i=0;i<resultado.size();i++){String a=resultado.get(i).toString();resultado.set(i,a=a.replaceAll(ns,""));resultado.set(i,a=a.replaceAll("<","\""));resultado.set(i,a=a.replaceAll(">","\""));resultado.set(i,a=a.replaceAll(st,""));
				resultado.set(i,a.replaceAll("\\^",""));}
				return resultado;
	}
	/**
	 * Gets list of rehabilitation subprocesses.
	 * @param Parent process
	 * @returns List of Processes.
	 */
	
	public List getChildProcesses(String process){
		String queryString ="PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "+ 
				"SELECT ?child ?name WHERE {?child rdfs:subClassOf <" + ns + process +">  " +
				".?child  <" + ns + "name" +"> ?name"+
						"}" +
				" \n ";
				//System.out.println("Query: "+queryString);
				Query query = QueryFactory.create(queryString);
				QueryExecution qe = QueryExecutionFactory.create(query, this.model);
				List resultado=null;
				try {
				   ResultSet results = qe.execSelect();
				   resultado=ResultSetFormatter.toList(results);
				} finally { qe.close() ; 
				}	
				for(int i=0;i<resultado.size();i++){String a=resultado.get(i).toString();resultado.set(i,a=a.replaceAll(ns,""));resultado.set(i,a=a.replaceAll("<","\""));resultado.set(i,a.replaceAll(">","\""));}
				return resultado;
	}
	/**
	 * Gets list of annotation properties of an activity.
	 * @returns List of properties.
	 */
	public List getInfoActividad(String aActivity){
		String queryString ="PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "+ 
				"select *"+
			    "where { "+
			    "?activ  a <" + ns + aActivity +">"+
			    ".?activ  <" + ns + "description" +"> ?description"+
			    ".?activ <" + ns + "code" +"> ?code"+
			    ".?activ  <" + ns + "name" +"> ?name"+
			    
			    ".?activ <" + ns + "parameters" +"> ?params"+
			    "} \n ";
				//System.out.println("Query: "+queryString);
				Query query = QueryFactory.create(queryString);
				QueryExecution qe = QueryExecutionFactory.create(query, this.model);
				List resultado=null;
				try {
				   ResultSet results = qe.execSelect();
				   resultado=ResultSetFormatter.toList(results);
				} finally { qe.close() ; 
				}
				for(int i=0;i<resultado.size();i++){
					String a=resultado.get(i).toString();
					resultado.set(i,a=a.replaceAll(ns,""));
					resultado.set(i,a=a.replaceAll("<","\""));
					resultado.set(i,a.replaceAll(">","\""));
					resultado.set(i,a=a.replaceAll(st,""));
					resultado.set(i,a.replaceAll("\\^",""));}
				
				return resultado;

	}
	/**
	 * Gets list of parameters of an activity.
	 * @returns List of properties.
	 */
	public List getParams(String aActivity){
		String queryString ="PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "+ 
				"select *"+
			    "where { "+
			    "?activ  a <" + ns + aActivity +">"+
			    ".?activ <" + ns + "parameters" +"> ?params"+
			    "} \n ";
				//System.out.println("Query: "+queryString);
				Query query = QueryFactory.create(queryString);
				QueryExecution qe = QueryExecutionFactory.create(query, this.model);
				List resultado=null;
				try {
				   ResultSet results = qe.execSelect();
				   resultado=ResultSetFormatter.toList(results);
				} finally { qe.close() ; 
				}
				for(int i=0;i<resultado.size();i++){
					String a=resultado.get(i).toString();
					resultado.set(i,a=a.replaceAll(ns,""));
					resultado.set(i,a=a.replaceAll("<","\""));
					resultado.set(i,a.replaceAll(">","\""));
					resultado.set(i,a=a.replaceAll(st,""));
					resultado.set(i,a=a.replaceAll("\\^",""));;}
				
				return resultado;

	}
	/**
	 * Gets list of activities.
	 * @returns List of Activities.
	 */
	public List getActivities(){
		String queryString ="PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "+ 
				"select ?activ ?name "+
			    "where { "+
			    "?activ a <" + ns + "Other_activities" +">"+
			    ".?activ  <" + ns + "name" +"> ?name"+
			    "} \n ";
				System.out.println("Query: "+queryString);
				Query query = QueryFactory.create(queryString);
				QueryExecution qe = QueryExecutionFactory.create(query, this.model);
				List resultado=null;
				try {
				   ResultSet results = qe.execSelect();
				   //ResultSetFormatter.out(System.out, results, query) ;
				   resultado=ResultSetFormatter.toList(results);
				} finally { qe.close() ; 
				}	
				for(int i=0;i<resultado.size();i++){String a=resultado.get(i).toString();resultado.set(i,a=a.replaceAll(ns,""));resultado.set(i,a=a.replaceAll("<","\""));resultado.set(i,a=a.replaceAll(">","\""));resultado.set(i,a=a.replaceAll(st,""));
				resultado.set(i,a.replaceAll("\\^",""));}
				return resultado;
	}
	/**
	 * Gets activities of a process.
	 * @param a Process.
	 * @returns List of activities associated to a Process.
	 */
	public List getActivities (String aProcess){
		String queryString ="PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "+ 
		"select ?activity "+
	    "where { "+
	     "?activity  <" + ns + "is_activity_of" +"> <" + ns + aProcess +">"+
	    "} \n ";
		System.out.println("Query: "+queryString);
		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, this.model);
		List resultado=null;
		try {
		   ResultSet results = qe.execSelect();
		   ResultSetFormatter.out(System.out, results, query) ;
		   resultado=ResultSetFormatter.toList(results);
		} finally { qe.close() ; 
		}
		
		return resultado;
	}
	/**
	 * Gets parameters of an activity.
	 * @param an Activity.
	 * @returns List of parameters associated to a Process.
	 */
	public List getParameters (String aActivity){
		String queryString ="PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "+ 
		"select *"+
	    "where { "+
	     "?param  <" + ns + "is_parameter_of" +"> <" + ns + aActivity +">"+
	     ".?param  <" + ns + "default" +"> ?default"+
		 ".?param  <" + ns + "unidad" +"> ?unidad"+
		 ".?param  <" + ns + "name" +"> ?name"+
		 ".?param  <" + ns + "avanzado" +"> ?avanzado"+
		 ".?param  <" + ns + "code" +"> ?code"+
		 ".?param  <" + ns + "optional" +"> ?optional"+
		 ".?param  <" + ns + "type" +"> ?type"+
		 ".?param  <" + ns + "fase" +"> ?fase"+
	    "} \n ";
		System.out.println("Query: "+queryString);
		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, this.model);
		List resultado=null;
		try {
		   ResultSet results = qe.execSelect();
		   ResultSetFormatter.out(System.out, results, query) ;
		   resultado=ResultSetFormatter.toList(results);
		} finally { qe.close() ; 
		}
		
		return resultado;
	}
	/**
	 * Gets stages of an activity.
	 * @param an Activity.
	 * @returns List of stages associated to an Activity.
	 */
	public List getFases (String aActivity){
		String queryString ="PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "+ 
		"select *"+
	    "where { "+
	     "?fase  <" + ns + "is_fase_of" +"> <" + ns + aActivity +">"+
	     ".?fase  <" + ns + "orden" +"> ?default"+
		 ".?fase  <" + ns + "repetible" +"> ?unidad"+
		 ".?fase  <" + ns + "name" +"> ?name"+
	    "} \n ";
		System.out.println("Query: "+queryString);
		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, this.model);
		List resultado=null;
		try {
		   ResultSet results = qe.execSelect();
		   ResultSetFormatter.out(System.out, results, query) ;
		   resultado=ResultSetFormatter.toList(results);
		} finally { qe.close() ; 
		}
		
		return resultado;
	}
	/**
	 * Gets list of indicators.
	 * @returns List of Indicators.
	 */
	public List getIndicators(){
		String queryString ="PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "+ 
				"select *"+
			    "where { "+
			    "?indicator a <" + ns + "Observation_parameter" +">"+
			    ".?indicator  <" + ns + "code" +"> ?code"+
			    ".?indicator  <" + ns + "name" +"> ?name."+
			    "filter(?code!=\""+ db +"\")"+
			    "} \n ";
				
				//System.out.println("Query: "+queryString);
				Query query = QueryFactory.create(queryString);
				QueryExecution qe = QueryExecutionFactory.create(query, this.model);
				List resultado=null;
				try {
				   ResultSet results = qe.execSelect();
				   //ResultSetFormatter.out(System.out, results, query) ;
				   resultado=ResultSetFormatter.toList(results);
				} finally { qe.close() ; 
				}	
				for(int i=0;i<resultado.size();i++){String a=resultado.get(i).toString();resultado.set(i,a=a.replaceAll(ns,""));resultado.set(i,a=a.replaceAll("<","\""));resultado.set(i,a=a.replaceAll(">","\""));resultado.set(i,a=a.replaceAll(st,""));
				resultado.set(i,a.replaceAll("\\^",""));}
				return resultado;
	}/**
	 * Gets list of objectives of a particular type.
	 * @param Type of objectives.
	 * @returns List of objectives.
	 */
	public List getObjectivesbyType(String aObjective){
		String queryString ="PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "+ 
				"select * "+
			    "where { "+
			    " <" + ns + aObjective +"><" + ns + "has_objective" +"> ?objective "+
			    "} \n ";
				System.out.println("Query: "+queryString);
				Query query = QueryFactory.create(queryString);
				QueryExecution qe = QueryExecutionFactory.create(query, this.model);
				List resultado=null;
				try {
				   ResultSet results = qe.execSelect();
				   ResultSetFormatter.out(System.out, results, query) ;
				   resultado=ResultSetFormatter.toList(results);
				} finally { qe.close() ; 
				}	
				return resultado;
	}
	/**
	 * Gets list of objectives included in an activity.
	 * @param Activity.
	 * @returns List of objectives.
	 */
	public List getObjectives(String aActivity){
		String queryString ="PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "+ 
				"select *"+
			    "where { "+
			    "?objective <" + ns + "code" +"> ?code"+
			    ".?objective  <" + ns + "name" +"> ?name"+
			    ".?objective  <" + ns + "description" +"> ?description"+
			    ".?objective <" + ns + "description_objective" +"> ?comment"+
			    ".?objective  a <" + ns + aActivity +">"+
			    "} \n ";
				System.out.println("Query: "+queryString);
				Query query = QueryFactory.create(queryString);
				QueryExecution qe = QueryExecutionFactory.create(query, this.model);
				List resultado=null;
				try {
				   ResultSet results = qe.execSelect();
				   ResultSetFormatter.out(System.out, results, query) ;
				   resultado=ResultSetFormatter.toList(results);
				} finally { qe.close() ; 
				}
				for(int i=0;i<resultado.size();i++){String a=resultado.get(i).toString();resultado.set(i,a=a.replaceAll(ns,""));resultado.set(i,a=a.replaceAll("<","\""));resultado.set(i,a.replaceAll(">","\""));}

				return resultado;
	}
	/**
	 * Gets indicators of a process.
	 * @param a Process.
	 * @returns List of indicators associated to a Process.
	 */
	public List getIndicators (String aProcess){
		String queryString ="PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "+ 
		"select * "+
	    "where { "+
	     "?indicator  <" + ns + "is_indicator_of" +"> <" + ns + aProcess +">"+
	     ".?indicator <" + ns + "code" +"> ?code"+
		 ".?indicator  <" + ns + "name" +"> ?name."+
	     "filter(?code!=\""+ db +"\")"+
	    "} \n ";
		System.out.println("Query: "+queryString);
		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, this.model);
		List resultado=null;
		try {
		   ResultSet results = qe.execSelect();
		   ResultSetFormatter.out(System.out, results, query) ;
		   resultado=ResultSetFormatter.toList(results);
		} finally { qe.close() ; 
		}	
		return resultado;
	}
	/**
	 * Gets activities of an indicator.
	 * @param an Indicator.
	 * @returns List of activities associated to an Indicator.
	 */
	public List getActivitiesbyIndicator (String aIndicator){
		String queryString ="PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "+ 
		"select * "+
	    "where { "+
	    //"?activity  <" + ns + "has_parameter" +"> <" + ns + aIndicator +">"+
	    "?activity  <" + ns + "has_indicator" +"> <" + ns + aIndicator +">"+
	    "} \n ";
		System.out.println("Query: "+queryString);
		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, this.model);
		List resultado=null;
		try {
		   ResultSet results = qe.execSelect();
		   ResultSetFormatter.out(System.out, results, query) ;
		   resultado=ResultSetFormatter.toList(results);
		} finally { qe.close() ; 
		}	
		return resultado;
	}
	/**
	 * Gets activities of a parameter.
	 * @param a Parameter.
	 * @returns List of activities associated to a Parameter.
	 */
	public List getActivitiesbyParameter (String aIndicator){
		String queryString ="PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "+ 
		"select * "+
	    "where { "+
	    "?activity  <" + ns + "has_parameter" +"> <" + ns + aIndicator +">"+
	    "} \n ";
		System.out.println("Query: "+queryString);
		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, this.model);
		List resultado=null;
		try {
		   ResultSet results = qe.execSelect();
		   ResultSetFormatter.out(System.out, results, query) ;
		   resultado=ResultSetFormatter.toList(results);
		} finally { qe.close() ; 
		}	
		return resultado;
	}
	/**
	 * Gets list of pathologies.
	 * @returns List of Pathologies.
	 */
	public List getPatologias(){
		String queryString ="PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "+ 
				"select ?diagnosis ?code ?name "+
			    "where { "+
			    "?diagnosis  <" + ns + "name" +"> ?name"+
			    ".?diagnosis  <" + ns + "code" +"> ?code"+
			     ".?diagnosis a <" + ns + "Diagnosis" +">"+
			    "} \n ";
				System.out.println("Query: "+queryString);
				Query query = QueryFactory.create(queryString);
				QueryExecution qe = QueryExecutionFactory.create(query, this.model);
				List resultado=null;
				try {
				   ResultSet results = qe.execSelect();
				   resultado=ResultSetFormatter.toList(results);
				} finally { qe.close() ; 
				}	
				for(int i=0;i<resultado.size();i++){String a=resultado.get(i).toString();resultado.set(i,a=a.replaceAll(ns,""));resultado.set(i,a=a.replaceAll("<","\""));resultado.set(i,a=a.replaceAll(">","\""));resultado.set(i,a=a.replaceAll(st,""));
				resultado.set(i,a.replaceAll("\\^",""));}
				return resultado;
	}
	/**
	 * Gets pathologies of an activity.
	 * @param an Activity.
	 * @returns List of pathologies associated to an activity.
	 */
	public List getPathologies (String aActivity){
		String queryString ="PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "+ 
		"select * "+
	    "where { "+
	     "?pathology  <" + ns + "is_pathology_of" +"> <" + ns + aActivity +">"+
	     ".?pathology <" + ns + "code" +"> ?code"+
		 ".?pathology  <" + ns + "name" +"> ?name"+
	    "} \n ";
		System.out.println("Query: "+queryString);
		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, this.model);
		List resultado=null;
		try {
		   ResultSet results = qe.execSelect();
		   ResultSetFormatter.out(System.out, results, query) ;
		   resultado=ResultSetFormatter.toList(results);
		} finally { qe.close() ; 
		}	
		return resultado;
	}
	/**
	 * Gets diseases associated to a pathology.
	 * @param A pathology.
	 * @returns List of Diseases.
	 */
	public List getDiseases(String pathology){
		String queryString ="PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "+ 
				"select * "+
			    "where { "+
			    "?disease  <" + ns + "has_pathology" +"> <" + ns + pathology +">"+
			    "} \n ";
				System.out.println("Query: "+queryString);
				Query query = QueryFactory.create(queryString);
				QueryExecution qe = QueryExecutionFactory.create(query, this.model);
				List resultado=null;
				try {
				   ResultSet results = qe.execSelect();
				   ResultSetFormatter.out(System.out, results, query) ;
				   resultado=ResultSetFormatter.toList(results);
				} finally { qe.close() ; 
				}	
				return resultado;
	}
	/**
	 * Gets list of diseases of functional, cognitive, cardiac and respiratory rehabilitation processes.
	 * @returns List of diseases.
	 */
	public List getDiseases(){
		String queryString ="PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "+ 
				"select *"+
			    "where { "+
			    "?disease a <" + ns + "Observation_of_damage" +">"+
			    ".?disease  <" + ns + "name" +"> ?name"+
			    ".?disease  <" + ns + "code" +"> ?code."+
			    "filter(?code!=\""+ db2 +"\")"+
			    "} \n ";
				System.out.println("Query: "+queryString);
				Query query = QueryFactory.create(queryString);
				QueryExecution qe = QueryExecutionFactory.create(query, this.model);
				List resultado=null;
				try {
				   ResultSet results = qe.execSelect();
				   //ResultSetFormatter.out(System.out, results, query) ;
				   resultado=ResultSetFormatter.toList(results);
				} finally { qe.close() ; 
				}	
				for(int i=0;i<resultado.size();i++){String a=resultado.get(i).toString();resultado.set(i,a=a.replaceAll(ns,""));resultado.set(i,a=a.replaceAll("<","\""));resultado.set(i,a=a.replaceAll(">","\""));resultado.set(i,a=a.replaceAll(st,""));
				resultado.set(i,a.replaceAll("\\^",""));}
				return resultado;

	}
	/**
	 * Gets list of diseases which perform a rehabilitation process.
	 * @param Rehabilitation process.
	 * @returns List of Diseases.
	 */
	public List getDiseasesbyProcess(String aProcess){
		String queryString ="PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "+ 
				"select * "+
			    "where { "+
			    " <" + ns + aProcess +"><" + ns + "has_disease" +"> ?disease "+
			    "} \n ";
				System.out.println("Query: "+queryString);
				Query query = QueryFactory.create(queryString);
				QueryExecution qe = QueryExecutionFactory.create(query, this.model);
				List resultado=null;
				try {
				   ResultSet results = qe.execSelect();
				   ResultSetFormatter.out(System.out, results, query) ;
				   resultado=ResultSetFormatter.toList(results);
				} finally { qe.close() ; 
				}	
				return resultado;
	}
}