package M;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class ProductDB {

	public int product_id;	
	public String product_name;	
	public double price_per_unit;	
	public String product_description;	
	public BufferedImage product_images;	
	
	public ProductDB()
	{
		
	}
	public ProductDB(int product_id,String product_name,double price_per_unit,String product_description,BufferedImage product_images)
	{
		this.product_id = product_id;
		this.product_name = product_name;
		this.price_per_unit = price_per_unit;
		this.product_description = product_description;
		this.product_images = product_images;
	}

}
