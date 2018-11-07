/**
 * 
 */
package model;


/**
 * @author cw222kq
 *
 */
public class Boat {

	private int id;
	private int size;
	private String type;
	private String imagePath;

	public Boat(){}
	
	//ctor tillagt nu i kompletteringen
	public Boat(int id, int size, String type, String imagePath){
		
		this.id = id;
		this.size = size;
		this.type = type;
		this.imagePath = imagePath;
		
	}
	
	// setters
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
