/**
 * 
 */
package model;

/**
 * @author cw222kq
 *
 */
class Boat {

	// create variables
	private int id;
	private int size;
	private String type;
	private String imagePath;
	
	// the constructor sets the variables (used when creating a new boat (auto increment id));
	/*public Boat(int valueSize, String valueType, String valuePath) {
		
		this.size = valueSize;
		this.type = valueType;
		this.imagePath = valuePath;
	}*/
	
	public Boat(){}
	
	// setters (used when updating the information about the boat)
	public void setId(int valueId){
		this.id = valueId;
	}
	
	public void setSize(int valueSize){
		this.size = valueSize;
	}
	
	public void setType(String valueType){
		this.type = valueType;
	}
	
	public void setImagePath(String valueImagePath){
		this.imagePath = valueImagePath;
	}
	
	// getters
	public int getId(){
		return this.id;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public String getType(){
		return this.type;
	}
	
	public String getImagePath(){
		return this.imagePath;
	}
}
