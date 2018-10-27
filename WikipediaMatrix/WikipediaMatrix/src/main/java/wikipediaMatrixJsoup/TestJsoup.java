package wikipediaMatrixJsoup;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class TestJsoup {

	public ArrayList<Elements> parseTable(String URL) {
    	ArrayList<Elements> data = new ArrayList<Elements>();
        try {
           
            Document doc = Jsoup.connect(URL).get();
            Element table = doc.select("table.wikitable").get(0);
            Elements rows = table.select("tr");
            data.add(rows.get(0).select("th"));
            for (int j = 1; j<rows.size(); j++) {
                data.add(rows.get(j).select("td"));
            } 
            
         
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
	
	
	
	
	public static void main(String[] args) throws IOException {

List<String> donnes = new ArrayList<String>();
    	TestJsoup parseTable = new TestJsoup();
        ArrayList<Elements> tableData = parseTable.parseTable("https://en.wikipedia.org/wiki/Comparison_of_Canon_EOS_digital_cameras");
        String csvFile = "D:\\ABDOUL\\abc.csv";
        FileWriter writer = new FileWriter(csvFile);
        for (Elements elements : tableData) {
            for (Iterator<Element> iterator = elements.iterator(); 
            		iterator.hasNext();) {
                Element element = (Element) iterator.next();
                
                System.out.print("  " +element.text() + " / ");
                donnes.add(element.text());
                
             

                
            }
            System.out.println("\n");
            CSVUtils.writeLine(writer, donnes);
            donnes.clear();
            
        }
        
        
        
        
        
        writer.flush();
        writer.close();


    }
    
}
