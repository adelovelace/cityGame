import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Map;


public class SimulacionCiudad {


    private BorderPane root;
    private GridPane cityGrid;
    private HBox panelTop, gridBox,typeserviceBox;
    private VBox panelRight ;
    private Label incomeLabel, expensesLabel, buildLabel, priceLabel;
    private ImageView grassGrid, typeServiceImage;
    private ComboBox listServices;
    private Button save;

    private City c;
    private TypeService typeService;
    private TypeConstruction typeConstruction;



    SimulacionCiudad(){

        root = new BorderPane();

        cityGrid = new GridPane();

        panelTop = new HBox();
        gridBox = new HBox();

        panelRight = new VBox();

        listServices = new ComboBox();

        incomeLabel = new Label();
        expensesLabel = new Label();
        buildLabel = new Label("Construir!");

        save = new Button("Guardar");


        c = new City();
        typeService = new TypeService();
        typeConstruction = new TypeConstruction();

        addControls();



    }

    public void addControls(){


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
                grassGrid = new ImageView(new Image("imagen/grassTile.png"));
                grassGrid.setFitHeight(35);
                grassGrid.setFitWidth(35);
                final int indexRow = i;
                final int indexColumn = j;
                grassGrid.setOnMouseClicked(event -> cellAction(indexRow,indexColumn));
                cityGrid.add(grassGrid,j,i);

            }
        }



        cityGrid.setPadding(new Insets(0,0,5,5));
        gridBox.fillHeightProperty();
        gridBox.getChildren().addAll(cityGrid);


    //right Part

        listServices.getItems().addAll(typeService.getServiceMap().keySet()); //add all the services on the comboBox





        panelRight.setPadding(new Insets(5,10,5,0));
        panelRight.setSpacing(20);
        panelRight.setAlignment(Pos.BASELINE_LEFT);
        panelRight.getChildren().addAll(buildLabel,listServices);


        //action on comboBox

        listServices.valueProperty().addListener( new ChangeListener() {


            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {


                String tempOld = newValue.toString();
                String tempNew = newValue.toString();

                typeserviceBox = new HBox();
                typeServiceImage = new ImageView();
                priceLabel = new Label();


                if (tempNew.equals(tempOld)){
                    typeServiceImage.setImage(new Image(typeService.getServiceMap().get(newValue.toString()).get(0)));
                    typeServiceImage.setFitWidth(35);
                    typeServiceImage.setFitHeight(35);
                    priceLabel.setText("Precio: "+ typeService.getServiceMap().get(newValue.toString()).get(1));


                }

                typeserviceBox.getChildren().addAll(typeServiceImage, priceLabel);
                panelRight.getChildren().addAll(typeserviceBox);





            }

        });


        root.setTop(panelTop);
        root.setCenter(gridBox);
        root.setRight(panelRight);

    }

    public BorderPane getRoot() {
        return root;
    }

    public void cellAction(int row, int column){
        System.out.println("Action: " + row + " , " +column);

    }


    public void changeCombo(){

        typeserviceBox = new HBox();
        typeServiceImage = new ImageView();
        priceLabel = new Label();

        String selectedService = (String) listServices.getValue();
        System.out.println(selectedService);

        if (typeService.getServiceMap().containsKey(selectedService)){

            List<String> temp = typeService.getServiceMap().get(selectedService);

           typeServiceImage.setImage(new Image(temp.get(0)));
           priceLabel.setText("Precio: " + temp.get(1));

           typeServiceImage.setFitHeight(35);
           typeServiceImage.setFitWidth(35);
           typeserviceBox.setSpacing(10);
           typeserviceBox.getChildren().addAll(typeServiceImage,priceLabel);

        }



    }





}


/*

        for (int i = 0; i < Configuration.FILAS; i++){

            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(25);
            cityGrid.getColumnConstraints().add(colConst);

        }

        for (int i = 0; i < Configuration.COLUMNAS; i++){

            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(25);
            cityGrid.getColumnConstraints().add(colConst);
        }


 */