package M;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.mysql.jdbc.PreparedStatement;

import common.GlobalData;

public class ProductManager {

		public static ArrayList<ProductDB> getAllProducts()
		{
			
			ArrayList<ProductDB> list = new ArrayList<ProductDB>();
			
			  try
			    {
			      // create our mysql database connection
			      String myDriver = "org.gjt.mm.mysql.Driver";
			      String myUrl = "jdbc:mysql://"+GlobalData.DATBASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
			      Class.forName(myDriver);
			      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
			      
			      // our SQL SELECT query. 
			      // if you only need a few columns, specify them by name instead of using "*"
			      String query = "SELECT * FROM products";

			      // create the java statement
			      Statement st = conn.createStatement();
			      
			      // execute the query, and get a java resultset
			      ResultSet rs = st.executeQuery(query);
			      
			      // iterate through the java resultset
			      while (rs.next())
			      {
			        int id = rs.getInt("product_id");
			        String pName = rs.getString("product_name");
			        double price = rs.getDouble("price_per_unit");
			        String dresc = rs.getString("product_description");
			       
			        byte img_byte[] = rs.getBytes("product_images");
			        
			        ByteArrayInputStream bais = new ByteArrayInputStream(img_byte); 
			        BufferedImage bufferedImg = ImageIO.read(bais);
			        bais = new ByteArrayInputStream(img_byte);
			        bais.close();
			        
			        ProductDB cc = new ProductDB(id,pName,price,dresc,bufferedImg);
			        list.add(cc);
			        // print the results
			       // System.out.format("%d, %s, %s, %s\n", id, firstName, lastName, phone);
			      }
			      st.close();
			    }
			    catch (Exception e)
			    {
			      System.err.println("Got an exception! ");
			      System.err.println(e.getMessage());
			      System.out.println(e);
			    }
			
			return list;
		}
		
		public static void saveProuct(ProductDB x)
		{
			
			  try
			    {
			      // create our mysql database connection
			      String myDriver = "org.gjt.mm.mysql.Driver";
			      String myUrl = "jdbc:mysql://"+GlobalData.DATBASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
			      Class.forName(myDriver);
			      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
			      
			     
			      String query = "INSERT INTO products VALUES (0, ?,?,?,?)";
			      PreparedStatement st = (PreparedStatement) conn.prepareStatement(query);
			      st.setString(1, x.product_name);
			      st.setDouble(2, x.price_per_unit);
			      st.setString(3, x.product_description);
			      
			      if(x.product_images != null)
			      {
			    	   ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			    	   ImageIO.write(x.product_images, "png", outStream);
			    	   byte[] buffer =  outStream.toByteArray();
			    	   st.setBytes(4, buffer);
			    	   outStream.close();
			      }
			      else
			      {
			    	  byte[] buffer = new byte[0];
			    	  st.setBytes(4, buffer);
			    	  //outStream.close();
			      }
			      
			      st.executeUpdate();
			      
			
			      st.close(); //close connection with databse
			    }
			    catch (Exception e)
			    {
			      System.err.println("Got an exception! ");
			      System.err.println(e.getMessage());
			      System.out.println(e);
			    }
		}
}
