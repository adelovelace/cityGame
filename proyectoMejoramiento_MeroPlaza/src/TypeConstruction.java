import java.io.*;
import java.util.*;

public class TypeConstruction {


    private List<String> constructions;
    private List<String> infoConstructions;
    private Map<String,List<String>> constructionMap;

    TypeConstruction(){

        constructions = new ArrayList<>();
        constructionMap = new TreeMap<>();

        loadConstructions();

    }

    public Map<String, List<String>> getConstructionMap() {
        return constructionMap;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void loadConstructions(){

        try{

            String pathFile = "C:\\Users\\Mario\\Documents\\POO\\cityGame\\proyectoMejoramiento_MeroPlaza\\src\\archivos\\tipoConstruccion.txt";
            File file = new File(pathFile);
            BufferedReader br = new BufferedReader(new FileReader(file));

            String str;

            //read line by line of the file
            while ((str = br.readLine()) != null) {
                constructions.add(str);
                System.out.println(str);

            }

            constructions.remove(0); //tags'elemination

            //clean the data
            for (String s: constructions) {

                String [] line = s.trim().split(";"); //separate info by semicolon
                String [] info = Arrays.copyOfRange(line,1,line.length); //make a copy of the line array from the index = 1 to the end

                infoConstructions = new ArrayList<String>(); //create for each key an

                for (String data: info) {
                    infoConstructions.add(data);
                }

                constructionMap.put(line[0],infoConstructions); //add the data into the map

            }


        } catch(FileNotFoundException fileNotFoundException){
            fileNotFoundException.printStackTrace();
            System.out.println("Error: " + fileNotFoundException);
        } catch(IOException e){
            System.out.println("Error: " + e);
        }
    }


}
