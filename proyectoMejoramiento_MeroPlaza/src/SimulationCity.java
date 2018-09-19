import com.sun.source.tree.Tree;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SimulationCity {


    private BorderPane root;
    private GridPane cityGrid;
    private HBox panelTop, gridBox,typeserviceBox;
    private VBox panelRight ;
    private Label incomeLabel, expensesLabel, buildLabel, priceLabel;
    private ImageView grassGrid, typeServiceImage;
    private ComboBox listServices;
    private Button saveButton;
    private StackPane imageStackPane;

    private City c;
    private TypeService typeService;
    private TypeConstruction typeConstruction;



    SimulationCity(){

        root = new BorderPane();

        cityGrid = new GridPane();

        panelTop = new HBox();
        gridBox = new HBox();
        typeserviceBox = new HBox();


        panelRight = new VBox();

        incomeLabel = new Label();
        expensesLabel = new Label();
        buildLabel = new Label("Construir!");

        saveButton = new Button("Guardar");



        c = new City();
        typeService = new TypeService();
        typeConstruction = new TypeConstruction();

        addControls();



    }

    public void addControls(){

        //saveButton.setOnAction(event -> addingService());

     //top Part

        panelTop.setPadding(new Insets(5,0,5,5));
        panelTop.setSpacing(20);
        panelTop.getChildren().addAll(incomeLabel,expensesLabel);

        incomeLabel.setText("Ingresos: " + c.getIncome()); // label from the income, with the calculated value
        expensesLabel.setText("Egresos: " + c.getExpenses()); // label from the expenses, with the calculated value

    //center Part

        //loops that fills the gridpane with the grass image
        for (int i = 0; i < Configuration.FILAS; i++){
            for (int j = 0; j < Configuration.COLUMNAS; j++){
                imageStackPane = new StackPane(); //create an StackPane
                grassGrid = new ImageView(new Image("imagen/grassTile.png"));
                grassGrid.setFitHeight(35);
                grassGrid.setFitWidth(35);
                final int indexRow = i;
                final int indexColumn = j;
                imageStackPane.getChildren().add(grassGrid);
                imageStackPane.setOnMouseClicked(event -> cellAction(indexRow,indexColumn, this.imageStackPane));
                cityGrid.add(imageStackPane,j,i);

            }
        }


        cityGrid.setPadding(new Insets(0,0,5,5));
        gridBox.fillHeightProperty();
        gridBox.getChildren().addAll(cityGrid);

        root.setTop(panelTop);
        root.setCenter(gridBox);
        root.setRight(panelRight);

    }

    public BorderPane getRoot() {
        return root;
    }

    public void cellAction(int row, int column, StackPane imageStackPane){

        System.out.println("Action: " + row + " , " + column);

        if(panelRight.getChildren().isEmpty()){

            cellService();

            saveButton.setOnAction(event ->addingService(row,column, imageStackPane ));

            panelRight.setPadding(new Insets(5,10,5,0));
            panelRight.setSpacing(20);
            panelRight.setAlignment(Pos.BASELINE_LEFT);
            panelRight.getChildren().addAll(buildLabel,listServices,typeserviceBox,saveButton);

        }else{


            panelRight.getChildren().clear();
            typeserviceBox.getChildren().clear();

            cellService();


            panelRight.setPadding(new Insets(5,10,5,0));
            panelRight.setSpacing(20);
            panelRight.setAlignment(Pos.BASELINE_LEFT);
            panelRight.getChildren().addAll(buildLabel,listServices,typeserviceBox,saveButton);

        }

    }

    public void cellService(){

        listServices = new ComboBox();

        listServices.getItems().addAll(typeService.getServiceMap().keySet()); //add all the services on the comboBox

        listServices.setOnAction(event -> {


            if(typeserviceBox.getChildren().isEmpty()){
                selectService();

            } else{
                typeserviceBox.getChildren().clear();
                selectService();
            }

        });


    }

    public void selectService(){

        List<String> infoKey = typeService.getServiceMap().get(listServices.getValue());
        String ruta = infoKey.get(0);
        String precio = infoKey.get(1);


        typeServiceImage = new ImageView(ruta);
        typeServiceImage.setFitHeight(50);
        typeServiceImage.setFitWidth(50);

        priceLabel = new Label("Precio: " + precio);
        priceLabel.setAlignment(Pos.CENTER);

        typeserviceBox.getChildren().addAll(typeServiceImage,priceLabel);
        typeserviceBox.setSpacing(10);

    }

    public void addingService(int row, int column, StackPane imageStackPane ){

        typeServiceImage.setFitWidth(25);
        typeServiceImage.setFitWidth(25);

        imageStackPane.getChildren().add(typeServiceImage);
        cityGrid.add(imageStackPane,column,row);


//        StackPane stackPane = (StackPane) getNodeByRowColumnIndex(row,column,cityGrid);
//        stackPane.getChildren().add(typeServiceImage);
//


        System.out.println("here");

    }

    public Node getNodeByRowColumnIndex (int row, int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

    //moores algorithm
    public void checkNeightbor(){

    }

}


