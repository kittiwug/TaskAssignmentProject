package com.ws.rs.restful.assign.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import com.ws.rs.restful.assign.model.ObjectTaskM;
import com.ws.rs.restful.assign.model.TaskM;

public class FileDATUtil {
	
	private static final String FILE_NAME = "objects_store.dat";

	public static void saveFileTask(HashMap<Integer, TaskM> taskMMap) {
		try {
			ObjectTaskM obj = new ObjectTaskM();
			obj.setHashMapTask(taskMMap);
			
			File file = new File(FILE_NAME);
			FileOutputStream fos;
			fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
			oos.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<Integer, TaskM> readFileTask() {
		
		HashMap<Integer, TaskM> hashMap = new HashMap<>(); 
		try { 
         File file = new File(FILE_NAME); 
         if (!file.exists()) { 
        	 
        	 ObjectTaskM obj = new ObjectTaskM();
 			 obj.setHashMapTask(hashMap);
        	 
        	 FileOutputStream fos;
        	 fos = new FileOutputStream(file);
 			 ObjectOutputStream oos = new ObjectOutputStream(fos);
 			 oos.writeObject(obj);
 			 oos.close();
         }
         
         FileInputStream fis = new FileInputStream(file); 
         ObjectInputStream ois = new ObjectInputStream(fis); 
         ObjectTaskM obj = (ObjectTaskM) ois.readObject(); 
         if (obj != null) {
        	 hashMap = obj.getHashMapTask();
         }
         ois.close(); 
      } 
      catch (IOException e) {
    	  e.printStackTrace();
      } 
      catch (ClassNotFoundException e) { 
    	  e.printStackTrace();
      }  
      
      return hashMap; 
	}
}
