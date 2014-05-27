package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TestClass {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("D:\\Workspace\\CIMSTest\\JavaSource\\Test.txt")));
        String s = null;
        while((s = br.readLine()) != null){
        	if(s.trim().equals("")){
        		continue;
        	}
        	System.out.println(String.format("%.1f", Double.parseDouble(s.trim())  * 1.2));
//        	System.out.println(Math.(Integer.parseInt(s.trim())  * 1.2));
        }
        
        
        	
	
	}
}