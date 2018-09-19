import java.io.*;
import java.util.*;


public class TypeService {


    private List<String> services;
    private List<String> infoService;
    private Map<String,List<String>> serviceMap;


    TypeService(){

        services = new ArrayList<>();
        serviceMap = new TreeMap<>();

        loadServices();

    }

    //getters
    public List<String> getServices() {
        return services;
    }

    public List<String> getInfoService() {
        return infoService;
    }


    public Map<String, List<String>> getServiceMap() {
        return serviceMap;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    //load the file of services and save it on an array list
    public void loadServices(){

        try{

            String pathFile = "C:\\Users\\Mario\\Documents\\POO\\cityGame\\proyectoMejoramiento_MeroPlaza\\src\\archivos\\servicios.txt";
            File file = new File(pathFile);
            BufferedReader br = new BufferedReader(new FileReader(file));

            String str;

            //read line by line of the file
            while ((str = br.readLine()) != null) {
                services.add(str);
                System.out.println(str);

            }

            services.remove(0); //tags'elemination

            //clean the data
            for (String s: services) {

                String [] line = s.trim().split(";"); //separate info by semicolon
                String [] info = Arrays.copyOfRange(line,1,line.length); //make a copy of the line array from the index = 1 to the end

                infoService = new ArrayList<String>(); //create for each key an

                for (String data: info) {
                    infoService.add(data);
                }

                serviceMap.put(line[0],infoService); //add the data into the map

            }


        } catch(FileNotFoundException fileNotFoundException){
            fileNotFoundException.printStackTrace();
            System.out.println("Error: " + fileNotFoundException);
        } catch(IOException e){
            System.out.println("Error: " + e);
        }
    }


}
