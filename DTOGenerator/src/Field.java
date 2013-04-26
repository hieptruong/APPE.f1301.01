
public class Field {
	private String fModifier;
	private String fType;
	private String fGenericType;
	private String fName;
	
	public Field(String modifier, String typ, String name) {
		fModifier = modifier;
		if (typ.equals("int")) typ = "Integer";
		fType = typ;
		fName = name;
	}

	public String getModifier() {
		return fModifier;
	}

	public void setModifier(String modifier) {
		fModifier = modifier;
	}

	public String getType() {
		return fType;
	}

	public void setType(String type) {
		fType = type;
	}

	public String getGenericType() {
		return fGenericType;
	}

	public void setGenericType(String genericType) {
		fGenericType = genericType;
	}

	public String getName() {
		return fName;
	}

	public void setName(String name) {
		fName = name;
	}
	
	public String getAttributeName() {
		return "f" + getNameWithCapitalFirstLetter();
	}
	
	public String getNameWithCapitalFirstLetter() {
		return fName.substring(0, 1).toUpperCase() + fName.substring(1);
	}
}
