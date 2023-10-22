package MainScene;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Controller implements Initializable{

    @FXML
    private Button ClearBtn;

    @FXML
    private Button AddBtn;

    @FXML
    private Button ApplyBtn;

    @FXML 
    private Button SaveBtn;
    @FXML
    private Label ErrorLb;

    @FXML
    private Label ExpenseLb;

    @FXML
    private ChoiceBox<String> FrequencyCB;

    @FXML
    private Label IncomeLb;

    @FXML
    private Label NetIncomeLb;

    @FXML
    private Label TaxesLb;

    @FXML
    private Label OutputLb;

    @FXML
    private Label PPHLb;

    @FXML
    private ChoiceBox<String> TypeCB;

    @FXML
    private TextField tfAmount;

    @FXML
    private TextField tfExpense;

    @FXML
    private TextField tfHrWorked;

    @FXML
    private TextField tfIncome;

    @FXML 
    private TextField tfFileName; 

    @FXML
    private ComboBox<String> LocationComboBox;

    //! Code starts here

    private String[] frequencyList = {"Per day","Per week", "Per month", "Per year"};

    private String[] incomeTypeList = {"Salary", "Per hour"};

    private String[] locationsList = {"Alberta", "British Columbia", "Manitoba", "New Brunswick",
    "Newfoundland and Labrador", "Northwest Territories", "Nova Scotia",
    "Nunavut", "Ontario", "Prince Edward Island", "Quebec", "Saskatchewan", "Yukon"};

    double totalExpenses = 0;

    String outputString = "";

    String costString;

    double number;

    String incomeText;
    String expenseText;
    String totalTaxesString;
    String netIncomeText;
    String profitPerText;

    DecimalFormat moneyFormat = new DecimalFormat("0.00");

    @FXML
    void changesApplied(ActionEvent event) {

        double incomeNumber = 0;
        double hoursNumber = 0;
        double netIncome = 0;

        double federalTaxes;
        double provincialTaxes = 0.0;
        double totalTaxes;
        double taxBracket;

        String incomeType = TypeCB.getValue();
        String location = LocationComboBox.getValue();

        // expenses (money lost)
        expenseText = moneyFormat.format(totalExpenses);
        ExpenseLb.setText(expenseText + "$");

        ErrorLb.setText("--- Error messages appear here ---");

        if (incomeType == "Salary") {

            // income (total money made)
            try {
                incomeNumber = Double.parseDouble(tfIncome.getText());
            } catch (NumberFormatException e) {
                ErrorLb.setText("Income must be a positive number");
            }

            incomeText = moneyFormat.format(incomeNumber);
            IncomeLb.setText(incomeText + "$");

        } else if (incomeType == "Per hour") {
            // income (total money made)
            try {
                incomeNumber = Double.parseDouble(tfIncome.getText());
            } catch (NumberFormatException e) {
                ErrorLb.setText("Income must be a positive number");
            }

            hoursNumber = Integer.parseInt(tfHrWorked.getText());
            incomeText = moneyFormat.format(incomeNumber * hoursNumber);
            IncomeLb.setText(incomeText + "$");

            incomeNumber = incomeNumber * hoursNumber;
        } else{
            ErrorLb.setText("Select an income type");
        };

        // federal taxes
        if (incomeNumber <= 15000.0) {
            federalTaxes = 0;
        } else if (incomeNumber <= 50197.0) {
            federalTaxes = incomeNumber * 0.15;
        } else if (incomeNumber <= 100392.0) {
            taxBracket = incomeNumber - 50197;
            federalTaxes = 7529.55 + (taxBracket * 0.205);
        } else if (incomeNumber <= 155625.0) {
            taxBracket = incomeNumber - 100392;
            federalTaxes = 17820.55 + (taxBracket * 0.26);
        } else if (incomeNumber <= 221708.0) {
            taxBracket = incomeNumber - 155625;
            federalTaxes = 30375.55 + (taxBracket * 0.29);
        } else {
            taxBracket = incomeNumber - 221708;
            federalTaxes = 48793.75 + (taxBracket * 0.33);
        }

        // provincial taxes (total taxes depending on province)
        // ! Code generated with help from AI, may contain errors. 
        if (location == "Alberta"){
            if (incomeNumber <= 19369){
                provincialTaxes = 0;
            } else if (incomeNumber <= 142292) {
                provincialTaxes = incomeNumber * 0.1;
            } else if (incomeNumber <= 170751) {
                taxBracket = incomeNumber - 142292;
                provincialTaxes = 14229.20 + (taxBracket * 0.12);
            } else if (incomeNumber <= 227668) {
                taxBracket = incomeNumber - 170751;
                provincialTaxes = 20391.60 + (taxBracket * 0.13);
            } else if (incomeNumber <= 341502) {
                taxBracket = incomeNumber - 227668;
                provincialTaxes = 30558.53 + (taxBracket * 0.14);
            } else {
                taxBracket = incomeNumber - 341502;
                provincialTaxes = 46332.18 + (taxBracket * 0.15);
            }
        } else if (location == "British Columbia") {
            if (incomeNumber <= 11400) {
                provincialTaxes = 0;
            } else if (incomeNumber <= 45654) {
                taxBracket = incomeNumber - 11400;
                provincialTaxes = (taxBracket * 0.0506);
            } else if (incomeNumber <= 91310) {
                taxBracket = incomeNumber - 45654;
                provincialTaxes = 1825.12 + (taxBracket * 0.077);
            } else if (incomeNumber <= 104835) {
                taxBracket = incomeNumber - 91310;
                provincialTaxes = 5423.47 + (taxBracket * 0.105);
            } else if (incomeNumber <= 127299) {
                taxBracket = incomeNumber - 104835;
                provincialTaxes = 7463.58 + (taxBracket * 0.1229);
            } else if (incomeNumber <= 172602) {
                taxBracket = incomeNumber - 127299;
                provincialTaxes = 11264.93 + (taxBracket * 0.147);
            } else if (incomeNumber <= 240716) {
                taxBracket = incomeNumber - 172602;
                provincialTaxes = 20498.09 + (taxBracket * 0.168);
            } else {
                taxBracket = incomeNumber - 240716;
                provincialTaxes = 40398.25 + (taxBracket * 0.205);
            }
        } else if (location == "Manitoba") {
            if (incomeNumber <= 9626) {
                provincialTaxes = 0;
            } else if (incomeNumber <= 36842) {
                taxBracket = incomeNumber - 9626;
                provincialTaxes = (taxBracket * 0.108);
            } else if (incomeNumber <= 79625) {
                taxBracket = incomeNumber - 36842;
                provincialTaxes = 2713.16 + (taxBracket * 0.1275);
            } else {
                taxBracket = incomeNumber - 79625;
                provincialTaxes = 9861.27 + (taxBracket * 0.174);
            }
        } else if (location == "New Brunswick") {
            if (incomeNumber <= 10750) {
                provincialTaxes = 0;
            } else if (incomeNumber <= 47715) {
                taxBracket = incomeNumber - 10750;
                provincialTaxes = (taxBracket * 0.094);
            } else if (incomeNumber <= 95431) {
                taxBracket = incomeNumber - 47715;
                provincialTaxes = 3694.65 + (taxBracket * 0.14);
            } else if (incomeNumber <= 176756) {
                taxBracket = incomeNumber - 95431;
                provincialTaxes = 13397.05 + (taxBracket * 0.16);
            } else {
                taxBracket = incomeNumber - 176756;
                provincialTaxes = 28464.69 + (taxBracket * 0.195);
            }
        } else if (location == "Newfoundland and Labrador") {
            if (incomeNumber <= 10104) {
                provincialTaxes = 0;
            } else if (incomeNumber <= 41457) {
                taxBracket = incomeNumber - 10104;
                provincialTaxes = (taxBracket * 0.087);
            } else if (incomeNumber <= 82913) {
                taxBracket = incomeNumber - 41457;
                provincialTaxes = 2415.99 + (taxBracket * 0.145);
            } else if (incomeNumber <= 148027) {
                taxBracket = incomeNumber - 82913;
                provincialTaxes = 9950.35 + (taxBracket * 0.158);
            } else if (incomeNumber <= 207239) {
                taxBracket = incomeNumber - 148027;
                provincialTaxes = 15383.93 + (taxBracket * 0.178);
            } else if (incomeNumber <= 264750) {
                taxBracket = incomeNumber - 207239;
                provincialTaxes = 25811.41 + (taxBracket * 0.198);
            } else if (incomeNumber <= 529500) {
                taxBracket = incomeNumber - 264750;
                provincialTaxes = 48648.09 + (taxBracket * 0.208);
            } else if (incomeNumber <= 1059000) {
                taxBracket = incomeNumber - 529500;
                provincialTaxes = 112961.17 + (taxBracket * 0.213);
            } else {
                taxBracket = incomeNumber - 1059000;
                provincialTaxes = 232721.22 + (taxBracket * 0.218);
            }
        } else if (location == "Nova Scotia") {
            if (incomeNumber <= 11635) {
                provincialTaxes = 0;
            } else if (incomeNumber <= 29590) {
                taxBracket = incomeNumber - 11635;
                provincialTaxes = (taxBracket * 0.0879);
            } else if (incomeNumber <= 59180) {
                taxBracket = incomeNumber - 29590;
                provincialTaxes = 1354.61 + (taxBracket * 0.1495);
            } else if (incomeNumber <= 93000) {
                taxBracket = incomeNumber - 59180;
                provincialTaxes = 4245.53 + (taxBracket * 0.1667);
            } else if (incomeNumber <= 150000) {
                taxBracket = incomeNumber - 93000;
                provincialTaxes = 8259.71 + (taxBracket * 0.175);
            } else {
                taxBracket = incomeNumber - 150000;
                provincialTaxes = 20792.46 + (taxBracket * 0.21);
            }
        } else if (location == "Ontario") {
            if (incomeNumber <= 10354) {
                provincialTaxes = 0;
            } else if (incomeNumber <= 49231) {
                taxBracket = incomeNumber - 10354;
                provincialTaxes = (taxBracket * 0.0505);
            } else if (incomeNumber <= 98463) {
                taxBracket = incomeNumber - 49231;
                provincialTaxes = 1965.71 + (taxBracket * 0.0915);
            } else if (incomeNumber <= 150000) {
                taxBracket = incomeNumber - 98463;
                provincialTaxes = 5935.89 + (taxBracket * 0.1116);
            } else if (incomeNumber <= 220000) {
                taxBracket = incomeNumber - 150000;
                provincialTaxes = 10258.53 + (taxBracket * 0.1216);
            } else {
                taxBracket = incomeNumber - 220000;
                provincialTaxes = 16858.53 + (taxBracket * 0.1316);
            }
        } else if (location == "Prince Edward Island") {
            if (incomeNumber <= 10000) {
                provincialTaxes = 0;
            } else if (incomeNumber <= 31984) {
                taxBracket = incomeNumber - 10000;
                provincialTaxes = (taxBracket * 0.098);
            } else if (incomeNumber <= 63969) {
                taxBracket = incomeNumber - 31984;
                provincialTaxes = 2198.64 + (taxBracket * 0.138);
            } else {
                taxBracket = incomeNumber - 63969;
                provincialTaxes = 6284.78 + (taxBracket * 0.167);
            }
        } else if (location == "Quebec") {
            if (incomeNumber <= 15012) {
                provincialTaxes = 0;
            } else if (incomeNumber <= 49275) {
                taxBracket = incomeNumber - 15012;
                provincialTaxes = (taxBracket * 0.15);
            } else if (incomeNumber <= 98540) {
                taxBracket = incomeNumber - 49275;
                provincialTaxes = 5738.5 + (taxBracket * 0.2);
            } else if (incomeNumber <= 119910) {
                taxBracket = incomeNumber - 98540;
                provincialTaxes = 12403 + (taxBracket * 0.24);
            } else {
                taxBracket = incomeNumber - 119910;
                provincialTaxes = 18679.6 + (taxBracket * 0.2575);
            }
        } else if (location == "Saskatchewan") {
            if (incomeNumber <= 16065) {
                provincialTaxes = 0;
            } else if (incomeNumber <= 49720) {
                taxBracket = incomeNumber - 16065;
                provincialTaxes = (taxBracket * 0.105);
            } else if (incomeNumber <= 142058) {
                taxBracket = incomeNumber - 49720;
                provincialTaxes = 33502.75 + (taxBracket * 0.125);
            } else {
                taxBracket = incomeNumber - 142058;
                provincialTaxes = 46374.75 + (taxBracket * 0.145);
            }
        } else if (location == "Northwest Territories") {
            if (incomeNumber <= 13382) {
                provincialTaxes = 0;
            } else if (incomeNumber <= 48326) {
                taxBracket = incomeNumber - 13382;
                provincialTaxes = (taxBracket * 0.059);
            } else if (incomeNumber <= 96655) {
                taxBracket = incomeNumber - 48326;
                provincialTaxes = 2304.44 + (taxBracket * 0.086);
            } else {
                taxBracket = incomeNumber - 96655;
                provincialTaxes = 7016.37 + (taxBracket * 0.122);
            }
        } else if (location == "Nunavut") {
            if (incomeNumber <= 16147) {
                provincialTaxes = 0;
            } else if (incomeNumber <= 50877) {
                taxBracket = incomeNumber - 16147;
                provincialTaxes = (taxBracket * 0.04);
            } else if (incomeNumber <= 101754) {
                taxBracket = incomeNumber - 50877;
                provincialTaxes = 1372.4 + (taxBracket * 0.07);
            } else if (incomeNumber <= 165429) {
                taxBracket = incomeNumber - 101754;
                provincialTaxes = 6359.91 + (taxBracket * 0.09);
            } else {
                taxBracket = incomeNumber - 165429;
                provincialTaxes = 13861.69 + (taxBracket * 0.115);
            }
        } else if (location == "Yukon") {
            if (incomeNumber <= 14223) {
                provincialTaxes = 0;
            } else if (incomeNumber <= 53359) {
                taxBracket = incomeNumber - 14223;
                provincialTaxes = (taxBracket * 0.064);
            } else if (incomeNumber <= 106717) {
                taxBracket = incomeNumber - 53359;
                provincialTaxes = 2447.36 + (taxBracket * 0.09);
            } else if (incomeNumber <= 165430) {
                taxBracket = incomeNumber - 106717;
                provincialTaxes = 7163.19 + (taxBracket * 0.109);
            } else {
                taxBracket = incomeNumber - 165430;
                provincialTaxes = 13167.62 + (taxBracket * 0.128);
            }
        } else {
            ErrorLb.setText("Pick a location");
        }

        // displaying taxes
        totalTaxes = federalTaxes + provincialTaxes;
        totalTaxesString = moneyFormat.format(totalTaxes);
        TaxesLb.setText(totalTaxesString + "$");


        // net income (money made after removing money lost)
        netIncome = incomeNumber - totalExpenses - totalTaxes;
        netIncomeText = moneyFormat.format(netIncome);
        NetIncomeLb.setText(netIncomeText + "$");
            
        // profit per hour (net income divided by the hours worked)
        try {
            hoursNumber = Integer.parseInt(tfHrWorked.getText());
        } catch (NumberFormatException e) {
            ErrorLb.setText("Your hours worked must be a positive number");
        }
        profitPerText = moneyFormat.format(netIncome/hoursNumber);
        PPHLb.setText(profitPerText + "$");
    }

    @FXML
    void expenseAdded(ActionEvent event) {
        String expenseName = tfExpense.getText();
        String expenseFrequency = FrequencyCB.getValue();
        costString = tfAmount.getText();
        
        ErrorLb.setText("--- Error messages appear here ---");

        try {
            number = Double.parseDouble(costString);
        } catch (NumberFormatException e) {
            ErrorLb.setText("Enter a valid expense cost");
            costString = "0.00";
        }

        if (expenseFrequency == "Per day") {
            number = number * 365;
        } else if (expenseFrequency == "Per week") {
            number = number * 52;
        } else if (expenseFrequency == "Per month") {
            number = number * 12;
        }

        // Increase the total expenses value
        totalExpenses = totalExpenses + number;

        costString = moneyFormat.format(number);

        if (costString.equals("0.00")) {
            
        } else {
            if (outputString == "" || outputString == "Name      Total Cost")
            {   
                outputString = expenseName + "      " + costString + "$";
            } else {
                outputString = outputString + "\n" + expenseName + "      " + costString + "$";
            }
        }

        if (costString.equals("0.00")) {
            ErrorLb.setText("You must enter an expense cost higher than 0");
        } else {
            OutputLb.setText(outputString);
        }
    }   

    @Override 
    public void initialize(URL arg0, ResourceBundle arg1) {

        FrequencyCB.getItems().addAll(frequencyList);
        TypeCB.getItems().addAll(incomeTypeList);
        LocationComboBox.getItems().addAll(locationsList);
    }

    @FXML
    void projectSaved(ActionEvent event) {
        String csvFilePath = "budgetPlans.csv";
        File file = new File(csvFilePath);

        String projectName = tfFileName.getText();

        boolean fileExists = file.exists();

        try (FileWriter writer = new FileWriter(csvFilePath, true)) {
            // Write the header row
            if (!fileExists || file.length() == 0) {
                writer.append("Name, Income, Expenses, Taxes, Net Income, ProfPerHour\n");
            }
            
            if (incomeText == null || expenseText == null || totalTaxesString == null || netIncomeText == null || profitPerText == null) {
                ErrorLb.setText("You cannot save an empty project");
            } else {
                writer.append(projectName + ", " + incomeText + ", " + expenseText + ", " + totalTaxesString + ", " + netIncomeText + ", " + profitPerText + ", " + "\n");
                ErrorLb.setText("Project saved!");
            }

            

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void clearText(ActionEvent event) {
        // Add code for clearing text.
        totalExpenses = 0;
        outputString = "Name      Total Cost";
        OutputLb.setText(outputString);
    }

}
