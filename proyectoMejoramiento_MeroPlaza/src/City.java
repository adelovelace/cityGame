public class City {

    private int income;
    private int expenses;
    private CityServices cityServices;
    private Construccions cityConstructions;


    //constructor
    City(){
        income = 0;
        cityServices = new CityServices();
        cityConstructions = new Construccions();

    }

    //getters
    public int getIncome() {
        return income;
    }

    public int getExpenses() {
        return expenses;
    }

    //setters
    public void setIncome(int income) {
        this.income = income;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }
}
