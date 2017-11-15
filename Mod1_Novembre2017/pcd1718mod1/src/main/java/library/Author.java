package library;

/**
 * This class models an author in our library context.
 * 
 */
public class Author implements Cloneable {
	private String SSN, name, surname;
	/**
	 * Builds an author given its social security number
	 * 
	 * @param SSN: social security number 
	 */
	public Author(String SSN) throws IllegalArgumentException{
		if(SSN == null || SSN.length() ==0) throw new IllegalArgumentException("Author parameters should not be empty or null");
		this.SSN =SSN;
	}
	
	/**
	 * Builds an author given its name, surname and SSN
	 * 
	 * @param name: 	optional, the name of the author, might be empty
	 * @param surname:	optional, the surname of the author, might be empty
	 * @param SSN: 		mandatory, social security number, cannot be empty
	 * 
	 * @throws IllegalArgumentException in case parameters are not valid e.g., are null.
	 */
	public Author(String name, String surname, String SSN) throws IllegalArgumentException{
		if(name == null || surname == null || SSN == null
			|| name.length()==0|| surname.length()==0 || SSN.length() ==0) throw new IllegalArgumentException("Author parameters should not be empty or null");
		this.SSN = SSN; this.name = name; this.surname = surname;
	}

	/**
	 * @return the authors name
	 * */
	public String getAuthorName() {
		return name;
	}
	
	/**
	 * @return the authors surname
	 * */
	public String getAuthorSurname() {
		return surname;
	}
	
	/**
	 * @return the authors SSN
	 * */
	public String getAuthorSSN() {
		return SSN;
	}
	@Override
	public boolean equals(Object a){
		Author aa = (Author)a;
		return aa.SSN == SSN && aa.surname == surname && aa.name == name;
	}
	public Author clone(){
		return new Author(name,surname,SSN);
	}
}
