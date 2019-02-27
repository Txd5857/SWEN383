package View;
import Controller.DietManagerController;
import Model.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.spec.ECField;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class DietManager_UI extends JFrame implements ActionListener {

    private FoodCollection fc = new FoodCollection();
    private ExerciseCollection ec = new ExerciseCollection();
    private DailyLog dl = new DailyLog(fc,ec);
    private DietManagerController manager = new DietManagerController(fc,ec,dl);
    private String currDate;
    private DateFormat df;
    private Date date;
    private String recipeDetailDisplay = "";
    //private String subRecipeDetail = "";
    private String detailName;
    private String detailCount;
    Map<Food, Double> values = new HashMap<>();

    // Text fields
    private JTextField jtfAfFood = new JTextField(5);
    private JTextField jtfAfCal = new JTextField(5);
    private JTextField jtfAfCarb = new JTextField(5);
    private JTextField jtfAfProtein = new JTextField(5);
    private JTextField jtfAfFat = new JTextField(5);
    private JTextField jtfAeName = new JTextField(5);
    private JTextField jtfAeCal = new JTextField(5);
    private JTextField jtfRecipeName = new JTextField(30);
    private JTextField jtfSubRecipeFoodName = new JTextField(10);
    private JTextField jtfSubRecipeFoodCount = new JTextField(5);
    private JTextField jtfSubRecipeFoodName1 = new JTextField(10);
    private JTextField jtfSubRecipeFoodCount1 = new JTextField(5);
    private JTextField jtfDate = new JTextField(10);
    private JTextField jtfDateLog = new JTextField(10);
    private JTextField jtfHomeDate = new JTextField(10);
    private JTextField jtfServings = new JTextField(5);
    private JTextField jtfMinutes = new JTextField(5);
    private JTextField jtfAddWeight = new JTextField(10);
    private JTextField jtfAddCalIntake = new JTextField(10);

    private JTextField updateCal = new JTextField(5);
    private JTextField updatePro = new JTextField(5);
    private JTextField updateCarb = new JTextField(5);
    private JTextField updateFat = new JTextField(5);
    private JTextField updateCalExer = new JTextField(5);
    private JTextField updateRecipeServ = new JTextField(5);
    //private JTextField updateIngName = new JTextField(5);
    //private JTextField updateIngSer = new JTextField(5);

    // JPanel
    private JPanel BarChartPanel = new JPanel();
    private JPanel jpWelcome = new JPanel();
    private JPanel jpBarFat = new JPanel();
    private JPanel jpBarCarb = new JPanel();
    private JPanel jpBarPro = new JPanel();

    private JLabel jlRecipeDetail = new JLabel("");
    private JLabel jlNumWeight = new JLabel("#.##");
    private JLabel jlNumGoal = new JLabel("#.##");

    private JLabel jlNumCalLost = new JLabel("###.##");
    private JLabel jlNumCal = new JLabel("###.##");
    private JLabel jldifference = new JLabel("###.##");

    private JLabel jlNumCalConsumed = new JLabel("##.##");
    private JLabel jlNumExerCal = new JLabel("##.##");
    private JLabel jlCalories = new JLabel("##.##");
    private JLabel jlFat = new JLabel("##.##");
    private JLabel jlCarbs = new JLabel("##.##");
    private JLabel jlPro = new JLabel("##.##");
    private JLabel jlBarFat = new JLabel("");
    private JLabel jlBarCarb = new JLabel("");
    private JLabel jlBarPro = new JLabel("");

    // ArrayLists
    private ArrayList<Food> foods = fc.getFoodCollectionList();
    private ArrayList<Exercise> exercises = ec.getExerciseCollectionList();
    private List<ConsumedFood> dailyloglist;
    private List<RecordExercise> dailyloglist1;

    // JLists
    private DefaultListModel modelF = new DefaultListModel();
    private JList jListFc = new JList( modelF );

    private DefaultListModel modelE = new DefaultListModel();
    private JList jListEc = new JList( modelE );

    private DefaultListModel modelEc = new DefaultListModel();
    private JList jListEcAdd = new JList( modelEc );

    private DefaultListModel modelFcR = new DefaultListModel();
    private JList jListFcRecipe = new JList( modelFcR );

    private DefaultListModel modelER = new DefaultListModel();
    private JList jListSubRecipe = new JList (modelER);

    private DefaultListModel modelFcF = new DefaultListModel();
    private JList jListFcFood = new JList( modelFcF );

    private DefaultListModel modelLog = new DefaultListModel();
    private JList jListLog = new JList( modelLog );

    public DietManager_UI() {
        setupWindow();
        getCurrentDate();
        getFoodCollection();
        getExerciseCollection();
        getDailyLog();
        viewData();
        viewBarChartInfo();
        // Scroll panes
        JScrollPane fcScroll = new JScrollPane(jListFc);
        JScrollPane fcScrollAdd = new JScrollPane(jListFcFood);
        JScrollPane fcScrollAddRecipe = new JScrollPane(jListFcRecipe);
        JScrollPane ecScrollAdd = new JScrollPane(jListEcAdd);
        JScrollPane rcScrollEditRecipe = new JScrollPane(jListSubRecipe);

        //this.add(jpWelcome, BorderLayout.NORTH);
        //this.add(jpAddFood, BorderLayout.CENTER);
        // Tabs
        JTabbedPane tabbedPane = new JTabbedPane();
        this.add(tabbedPane,BorderLayout.CENTER);

        // Panels
        JPanel jpWelcome = new JPanel();
        tabbedPane.addTab("Home", jpWelcome);
        JPanel jpAddFood = new JPanel();
        tabbedPane.addTab("Add Basic Food", jpAddFood);
        JPanel jpAddRecipe = new JPanel();
        tabbedPane.addTab("Add Recipe", jpAddRecipe);
        JPanel jpAddExercise = new JPanel();
        tabbedPane.addTab("Add Exercise", jpAddExercise);
        JPanel jpLog = new JPanel();
        tabbedPane.addTab("Add to Log", jpLog);
        JPanel jpViewLog = new JPanel();
        tabbedPane.addTab("View Log", jpViewLog);


        ///////////////////////////////////////// HOME /////////////////////////////////////////
        // Labels
        JLabel jlWelcome = new JLabel("Diet Manager ");
        jpWelcome.add(jlWelcome);

        JPanel homeContainer = new JPanel();
        JPanel homeDetailContainer = new JPanel();

        //JPanel dateSelectContainer = new JPanel();
        JPanel dateSelectPanel = new JPanel();
        JLabel jlHomeDate = new JLabel("Enter in the date to view your entries on: ex. yyyy/mm/dd");
        dateSelectPanel.add(jlHomeDate);
        dateSelectPanel.add(jtfHomeDate);
        JButton jbHomeDate = new JButton("View");
        dateSelectPanel.add(jbHomeDate);
        jbHomeDate.addActionListener(this);
        homeContainer.add(dateSelectPanel);

        JPanel weightRecordPanel = new JPanel();
        JLabel jlWeight = new JLabel("Weight Recorded: ");
        weightRecordPanel.add(jlWeight);
        weightRecordPanel.add(jlNumWeight);
        homeDetailContainer.add(weightRecordPanel);

        JPanel CalIntakeGoal = new JPanel();
        JLabel jlGoal = new JLabel("Set Caloric Intake Goal: ");
        CalIntakeGoal.add(jlGoal);
        CalIntakeGoal.add(jlNumGoal);
        homeDetailContainer.add(CalIntakeGoal);

        JPanel calConsumedPanel = new JPanel();
        JLabel jlCalConsumed = new JLabel("Total Calories Consumed: ");
        calConsumedPanel.add(jlCalConsumed);
        calConsumedPanel.add(jlNumCalConsumed);
        homeDetailContainer.add(calConsumedPanel);

        JPanel calLostPanel = new JPanel();
        JLabel jlCalLost = new JLabel("Total Calories Lost: ");
        calLostPanel.add(jlCalLost);
        calLostPanel.add(jlNumCalLost);
        homeDetailContainer.add(calLostPanel);

        JPanel netCal = new JPanel();
        JLabel jlnetCal = new JLabel("The Net Calories: ");
        netCal.add(jlnetCal);
        netCal.add(jlNumCal);
        homeDetailContainer.add(netCal);

        JPanel difference = new JPanel();
        JLabel jldiff = new JLabel("The Difference between Net Calories and Caloric Intake Goal: ");
        difference.add(jldiff);
        difference.add(jldifference);
        homeDetailContainer.add(difference);

        homeContainer.add(homeDetailContainer);


        JPanel BarChartContainer = new JPanel();

        JPanel BarChartTextPanel = new JPanel();
        JLabel barDataTitle = new JLabel("About Today: ");
        BarChartTextPanel.add(barDataTitle);
        JLabel jlBarFatTitle = new JLabel("Fat: ");
        BarChartTextPanel.add(jlBarFatTitle);
        BarChartTextPanel.add(jlBarFat);
        JLabel jlBarCarbTitle = new JLabel("Carbohydrates: ");
        BarChartTextPanel.add(jlBarCarbTitle);
        BarChartTextPanel.add(jlBarCarb);
        JLabel jlBarProTitle = new JLabel("Protein: ");
        BarChartTextPanel.add(jlBarProTitle);
        BarChartTextPanel.add(jlBarPro);
        BarChartPanel.add(jpBarFat);
        jpBarFat.setBackground(Color.red);

        BarChartPanel.add(jpBarCarb);
        jpBarCarb.setBackground(Color.green);

        BarChartPanel.add(jpBarPro);
        jpBarPro.setBackground(Color.blue);



        BarChartContainer.add(BarChartTextPanel);
        BarChartContainer.add(BarChartPanel);

        homeDetailContainer.setLayout(new BoxLayout(homeDetailContainer, BoxLayout.Y_AXIS));
        homeContainer.setLayout(new BoxLayout(homeContainer, BoxLayout.Y_AXIS));
        BarChartContainer.setLayout(new BoxLayout(BarChartContainer, BoxLayout.Y_AXIS));


        jpWelcome.add(homeContainer);
        jpWelcome.add(BarChartContainer);
        ///////////////////////////////////////// END HOME /////////////////////////////////////////



        ///////////////////////////////////////// ADD BASIC FOOD /////////////////////////////////////////
        JPanel addFoodContainer = new JPanel();
        JPanel addFoodPanelTitle = new JPanel();

        JLabel jlAddFood = new JLabel("Add food to collection");
        addFoodPanelTitle.add(jlAddFood);
        addFoodContainer.add(addFoodPanelTitle);

        JPanel addFoodPanelInput = new JPanel();
        JLabel jlAfFood = new JLabel("Food name");
        addFoodPanelInput.add(jlAfFood);
        addFoodPanelInput.add(jtfAfFood);
        JLabel jlAfCal = new JLabel("Calories");
        addFoodPanelInput.add(jlAfCal);
        addFoodPanelInput.add(jtfAfCal);
        JLabel jlAfCarb = new JLabel("Carbs");
        addFoodPanelInput.add(jlAfCarb);
        addFoodPanelInput.add(jtfAfCarb);
        JLabel jlAfFat = new JLabel("Fat");
        addFoodPanelInput.add(jlAfFat);
        addFoodPanelInput.add(jtfAfFat);
        JLabel jlAfProtein = new JLabel("Protein");
        addFoodPanelInput.add(jlAfProtein);
        addFoodPanelInput.add(jtfAfProtein);
        // Buttons
        JButton jbAddFood = new JButton("Add Food");
        addFoodPanelInput.add(jbAddFood);

        addFoodContainer.add(addFoodPanelInput);

        JPanel viewFoodCollection = new JPanel();

        JLabel jlFoodCollection = new JLabel("Food Collection");
        viewFoodCollection.add(jlFoodCollection);
        viewFoodCollection.add(fcScrollAdd);

        addFoodContainer.add(viewFoodCollection);

        JPanel editsFoodCollection = new JPanel();
        //JButton jbviewNut = new JButton("View Nutritional Values ");
        JButton jbviewNut = new JButton("View Nutritional Values");
        editsFoodCollection.add(jbviewNut);
        jbviewNut.addActionListener(this);

        JPanel foodCalories = new JPanel();
        JLabel jlFoodCalories = new JLabel("Calories:  ");
        foodCalories.add(jlFoodCalories);
        foodCalories.add(jlCalories);
        editsFoodCollection.add(foodCalories);

        JPanel foodCarbs = new JPanel();
        JLabel jlFoodCarbs = new JLabel("Carbs:  ");
        foodCarbs.add(jlFoodCarbs);
        foodCarbs.add(jlCarbs);
        editsFoodCollection.add(foodCarbs);

        JPanel foodFat = new JPanel();
        JLabel jlFoodFat = new JLabel("Fat:  ");
        foodFat.add(jlFoodFat);
        foodFat.add(jlFat);
        editsFoodCollection.add(foodFat);

        JPanel foodPro = new JPanel();
        JLabel jlFoodPro = new JLabel("Protein:  ");
        foodPro.add(jlFoodPro);
        foodPro.add(jlPro);
        editsFoodCollection.add(foodPro);

        JPanel editBasicFood = new JPanel();
        JButton jbupdateFood = new JButton("Update Basic Food");
        editBasicFood.add(jbupdateFood);
        jbupdateFood.addActionListener(this);
        JLabel jlCal = new JLabel("Calories");
        editBasicFood.add(jlCal);
        editBasicFood.add(updateCal);
        JLabel jlCarbs = new JLabel("Carbs");
        editBasicFood.add(jlCarbs);
        editBasicFood.add(updateCarb);
        JLabel jlFat = new JLabel("Fat");
        editBasicFood.add(jlFat);
        editBasicFood.add(updateFat);
        JLabel jlPro = new JLabel("Protein");
        editBasicFood.add(jlPro);
        editBasicFood.add(updatePro);

        addFoodContainer.add(editsFoodCollection);
        addFoodContainer.add(editBasicFood);



        addFoodContainer.setLayout(new BoxLayout(addFoodContainer, BoxLayout.PAGE_AXIS));
        jpAddFood.add(addFoodContainer);

        jbAddFood.addActionListener(this);

        /////////////////////////////////////////ADD BASIC FOOD END//////////////////////////////////////////////



        /////////////////////////////////////////ADD RECIPE//////////////////////////////////////////////
        JPanel addRecipeContainer = new JPanel();

        JPanel addRecipePanel = new JPanel();
        JLabel jlRecipe = new JLabel("Enter in recipe name: ");
        addRecipePanel.add(jlRecipe);
        addRecipePanel.add(jtfRecipeName);

        JPanel addSubRecipePanel = new JPanel();
        JLabel jlSubRecipeFoodName = new JLabel("Enter in sub-recipe name: ");
        addSubRecipePanel.add(jlSubRecipeFoodName);
        addSubRecipePanel.add(jtfSubRecipeFoodName);
        JLabel jlSubRecipeFoodCount = new JLabel("Enter in number of servings: ");
        addSubRecipePanel.add(jlSubRecipeFoodCount);
        addSubRecipePanel.add(jtfSubRecipeFoodCount);
        JButton jbAddSubRecipe = new JButton("Save & Add More");
        addSubRecipePanel.add(jbAddSubRecipe);

        JPanel addRecipeDetail = new JPanel();
        addRecipeDetail.add(jlRecipeDetail);

        JPanel addRecipeButtonPanel = new JPanel();
        JButton jbAddRecipe = new JButton("Add Recipe");
        addRecipeButtonPanel.add(jbAddRecipe);

        JPanel viewFoodCollectionRecipe = new JPanel();

        JLabel jlRecipeCollection = new JLabel("Recipe Collection");
        viewFoodCollectionRecipe.add(jlRecipeCollection);
        viewFoodCollectionRecipe.add(fcScrollAddRecipe);

        addRecipeContainer.add(addRecipePanel);
        addRecipeContainer.add(addSubRecipePanel);
        addRecipeContainer.add(addRecipeDetail);
        addRecipeContainer.add(addRecipeButtonPanel);

        addRecipeContainer.add(viewFoodCollectionRecipe);

        JPanel jpeditRecipe = new JPanel();
        JButton jbEditRecipe  = new JButton("View Recipe's Ingredients");
        jbEditRecipe.addActionListener(this);
        jpeditRecipe.add(jbEditRecipe);
        jpeditRecipe.add(rcScrollEditRecipe);

        JButton jbEditRecipe1  = new JButton("Remove Ing");
        jbEditRecipe1.addActionListener(this);
        jpeditRecipe.add(jbEditRecipe1);

        JPanel editRecipe = new JPanel();
        JButton jbEditRecipe2  = new JButton("Update Servings");
        jbEditRecipe2.addActionListener(this);
        editRecipe.add(jbEditRecipe2);
        editRecipe.add(updateRecipeServ);

        JPanel addSubRecipePanel1 = new JPanel();
        JLabel jlSubRecipeFoodName1 = new JLabel("Enter in sub-recipe name: ");
        addSubRecipePanel1.add(jlSubRecipeFoodName1);
        addSubRecipePanel1.add(jtfSubRecipeFoodName1);
        JLabel jlSubRecipeFoodCount1 = new JLabel("Enter in number of servings: ");
        addSubRecipePanel1.add(jlSubRecipeFoodCount1);
        addSubRecipePanel1.add(jtfSubRecipeFoodCount1);
        JButton jbAddSubRecipe1 = new JButton("Update Recipe Ingredients");
        jbAddSubRecipe1.addActionListener(this);
        addSubRecipePanel1.add(jbAddSubRecipe1);

        JPanel addRecipeDetail1 = new JPanel();
        JLabel jlRecipeDetail1 = new JLabel("");
        addRecipeDetail1.add(jlRecipeDetail1);

        addRecipeContainer.add(jpeditRecipe);
        addRecipeContainer.add(editRecipe);
        addRecipeContainer.add(addSubRecipePanel1);
        addRecipeContainer.add(addRecipeDetail1);

        addRecipeContainer.setLayout(new BoxLayout(addRecipeContainer, BoxLayout.PAGE_AXIS));
        jpAddRecipe.add(addRecipeContainer);
        jbAddSubRecipe.addActionListener(this);
        jbAddRecipe.addActionListener(this);
        /////////////////////////////////////////ADD RECIPE END//////////////////////////////////////////////


        /////////////////////////////////////////ADD EXERCISE//////////////////////////////////////////////
        JPanel addExerciseContainer = new JPanel();

        JPanel addExercisePanelTitle = new JPanel();
        JLabel jlExercise = new JLabel("Add exercise to collection");
        addExercisePanelTitle.add(jlExercise);
        addExerciseContainer.add(addExercisePanelTitle);

        JPanel addExercisePanelInput = new JPanel();
        JLabel jlAeName = new JLabel("Exercise Name");
        addExercisePanelInput.add(jlAeName);
        addExercisePanelInput.add(jtfAeName);
        JLabel jlAeCal = new JLabel("Calories");
        addExercisePanelInput.add(jlAeCal);
        addExercisePanelInput.add(jtfAeCal);
        JButton jbAddExercise = new JButton("Add Exercise");
        addExercisePanelInput.add(jbAddExercise);
        jbAddExercise.addActionListener(this);
        addExerciseContainer.add(addExercisePanelInput);

        JPanel viewExerciseCollection = new JPanel();

        JLabel jlExerciseCollection = new JLabel("Exercise Collection");
        viewExerciseCollection.add(jlExerciseCollection);
        viewExerciseCollection.add(ecScrollAdd);

        JPanel editsExerciseCollection = new JPanel();
                JButton jbviewCal = new JButton("View Exercise Calories");
                editsExerciseCollection.add(jbviewCal);
                jbviewCal.addActionListener(this);

        JLabel jlExerCal = new JLabel("Calories: ");
        editsExerciseCollection.add(jlExerCal);
        editsExerciseCollection.add(jlNumExerCal);

        JPanel exerciseCalories = new JPanel();
        JLabel jlExerciseCalories = new JLabel("Calories:  ");
        exerciseCalories.add(jlExerciseCalories);
        //exerciseCalories.add(jlCalories);
        editsFoodCollection.add(exerciseCalories);

        JPanel editExercise = new JPanel();
        JButton jbupdateExercise = new JButton("Update Exercise");
        editExercise.add(jbupdateExercise);
        editExercise.add(jlExerCal);
        editExercise.add(updateCalExer);
        jbupdateExercise.addActionListener(this);


        addExerciseContainer.add(viewExerciseCollection);
        addExerciseContainer.add(editsExerciseCollection);
        addExerciseContainer.add(editExercise);
        addExerciseContainer.setLayout(new BoxLayout(addExerciseContainer, BoxLayout.PAGE_AXIS));
        jpAddExercise.add(addExerciseContainer);
        /////////////////////////////////////////ADD EXERCISE END//////////////////////////////////////////////



        /////////////////////////////////////////LOG//////////////////////////////////////////////
        JPanel logContainer = new JPanel();

        JPanel dateContainer = new JPanel();
        JPanel datePanel = new JPanel();
        JLabel jlDate = new JLabel("Enter in the date to log your entries on: ex. yyyy/mm/dd");
        datePanel.add(jlDate);
        datePanel.add(jtfDate);
        JButton jbDate = new JButton("Change Date");
        datePanel.add(jbDate);
        jbDate.addActionListener(this);

        dateContainer.add(datePanel);
        dateContainer.setLayout(new BoxLayout(dateContainer, BoxLayout.Y_AXIS));
        logContainer.add(dateContainer);

        JPanel weightContainer = new JPanel();
        JLabel jlAddWeight = new JLabel("Today's Weight");
        weightContainer.add(jlAddWeight);
        weightContainer.add(jtfAddWeight);
        JButton jbAddWeight = new JButton("Add Weight");
        weightContainer.add(jbAddWeight);
        jbAddWeight.addActionListener(this);
        logContainer.add(weightContainer);

        JPanel SetCalIntake = new JPanel();
        JLabel jlCalIntake = new JLabel("Today's Caloric Intake");
        SetCalIntake.add(jlCalIntake);
        SetCalIntake.add(jtfAddCalIntake);
        JButton jbCalIntake = new JButton("Set Calorie Intake Goal");
        SetCalIntake.add(jbCalIntake);
        jbCalIntake.addActionListener(this);
        logContainer.add(SetCalIntake);

        JPanel exerciseToLogContainer = new JPanel();
        JPanel exerciseToLogTitle= new JPanel();
        JPanel exerciseToLogPanel = new JPanel();
        JLabel jlEc = new JLabel("Select an exercise from the exercise collection to log");
        exerciseToLogTitle.add(jlEc);

        JScrollPane ecScroll = new JScrollPane(jListEc);
        exerciseToLogPanel.add(ecScroll);

        JLabel jlMinutes = new JLabel("Minutes");
        exerciseToLogPanel.add(jlMinutes);
        exerciseToLogPanel.add(jtfMinutes);
        JButton jbAddExerciseToLog = new JButton("Record Exercise");
        exerciseToLogPanel.add(jbAddExerciseToLog);
        jbAddExerciseToLog.addActionListener(this);

        exerciseToLogContainer.add(exerciseToLogTitle);
        exerciseToLogContainer.add(exerciseToLogPanel);
        exerciseToLogContainer.setLayout(new BoxLayout(exerciseToLogContainer, BoxLayout.Y_AXIS));
        logContainer.add(exerciseToLogContainer);

        JPanel foodToLogContainer = new JPanel();
        JPanel foodToLogTitle= new JPanel();
        JPanel foodToLogPanel = new JPanel();
        JLabel jlFc = new JLabel("Select a food from the food collection to log");
        foodToLogTitle.add(jlFc);

        foodToLogPanel.add(fcScroll);

        JLabel jlServings = new JLabel("Servings");
        foodToLogPanel.add(jlServings);
        foodToLogPanel.add(jtfServings);
        JButton jbAddFoodToLog = new JButton("Record Food");
        foodToLogPanel.add(jbAddFoodToLog);
        jbAddFoodToLog.addActionListener(this);

        foodToLogContainer.add(foodToLogTitle);
        foodToLogContainer.add(foodToLogPanel);
        foodToLogContainer.setLayout(new BoxLayout(foodToLogContainer, BoxLayout.Y_AXIS));
        logContainer.add(foodToLogContainer);

        logContainer.setLayout(new BoxLayout(logContainer, BoxLayout.Y_AXIS));
        jpLog.add(logContainer);

        ///////////////////////////////////////// END LOG /////////////////////////////////////////



        ///////////////////////////////////////// VIEW LOG /////////////////////////////////////////
        JPanel viewLogContainer = new JPanel();

        JPanel datePanelLog = new JPanel();
        JLabel jlDateLog = new JLabel("Enter in the date to log your entries on: ex. yyyy/mm/dd");
        datePanelLog.add(jlDateLog);
        datePanelLog.add(jtfDateLog);
        JButton jbDate2 = new JButton("Change Log Date");
        datePanelLog.add(jbDate2);
        jbDate2.addActionListener(this);

       // JPanel dateContainerLog = new JPanel();
       // dateContainerLog.add(datePanelLog);
       // dateContainerLog.setLayout(new BoxLayout(dateContainerLog, BoxLayout.Y_AXIS));

        JPanel viewPanel = new JPanel();
        JLabel jlViewLog = new JLabel("Daily log");
        viewPanel.add(jlViewLog);
        JScrollPane scrollLog = new JScrollPane(jListLog);
        viewPanel.add(scrollLog);

        viewLogContainer.add(datePanelLog);
        viewLogContainer.add(viewPanel);

        JPanel jpDelete = new JPanel();
        JButton jbDelete = new JButton("Delete From Daily Log");
        jbDelete.addActionListener(this);
        jpDelete.add(jbDelete);
        viewLogContainer.add(jpDelete);
        viewLogContainer.setLayout(new BoxLayout(viewLogContainer, BoxLayout.Y_AXIS));

        jpViewLog.add(viewLogContainer);

        ///////////////////////////////////////// END VIEW LOG /////////////////////////////////////////



        ///////////////////////////////////////// STYLES /////////////////////////////////////////

        jlWelcome.setFont(new Font("SansSerif", Font.BOLD, 30));
        jlAddFood.setFont(new Font("SansSerif", Font.BOLD, 15));
        jlEc.setFont(new Font("SansSerif", Font.BOLD, 15));
        jlFc.setFont(new Font("SansSerif", Font.BOLD, 15));
        jlDate.setFont(new Font("SansSerif", Font.BOLD, 15));
        jlFoodCollection.setFont(new Font("SansSerif", Font.BOLD, 15));
        JLabel jlFoodCollection2 = new JLabel("Food Collection");
        jlFoodCollection2.setFont(new Font("SansSerif", Font.BOLD, 15));
        jlRecipeCollection.setFont(new Font("SansSerif", Font.BOLD, 15));
        jlExerciseCollection.setFont(new Font("SansSerif", Font.BOLD, 15));
        jlExercise.setFont(new Font("SansSerif", Font.BOLD, 15));
        dateContainer.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        jListFc.setFixedCellWidth(400);
        //jListLog.setFixedCellWidth(400);
        jListEc.setFixedCellWidth(400);
        jListEcAdd.setFixedCellWidth(600);
        jListFcFood.setFixedCellWidth(600);
        jListFcRecipe.setFixedCellWidth(600);
        jListLog.setFixedCellWidth(700);
        jListSubRecipe.setFixedCellWidth(400);
        BarChartTextPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        BarChartPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        BarChartPanel.setPreferredSize(new Dimension(400, 260));
        this.setVisible(true);
    }

    private void setupWindow() {
        this.setTitle("Diet Manager");
        this.setSize(850,700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                manager.writer.foodsave();
//              manager.writer.logsave();
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
        switch(ae.getActionCommand()) {
            case "Add Food":
                if(jtfAfFood.getText().equals("") || jtfAfCal.getText().equals("") || jtfAfCarb.getText().equals("") || jtfAfFat.getText().equals("") || jtfAfProtein.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Please enter in a food name and nutritional information for the food.", "error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    addFood();
                }
                break;
            case "View Nutritional Values":
                viewInfo();
                break;
            case "View Calories":
                viewInfoExer();
                break;
            case "Add Recipe":
                addRecipe();
                break;
            case "Save & Add More":
                setRecipeDetail();
                break;
            case "Add Exercise":
                if(jtfAeCal.getText().equals("") || jtfAeName.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Please enter in an exercise name and calories.", "error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    addExercise();
                }
                break;
            case "Record Exercise":
                if(jtfMinutes.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Please enter in the amount of time you exercised in minutes.", "error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    logExercise();
                    viewData();
                }
                break;
            case "Record Food":
                if(jtfServings.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Please enter in the amount of servings you ate.", "error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    logFood();
                    viewData();
                }
                break;
            case "Change Date":
                getDate();
                JOptionPane.showMessageDialog(this, "Date changed to " + df.format(getDate()), "information", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Update Servings":
                updaterecServ();
                break;
            case "Change Log Date":
                getDateViewLog();
                getDailyLog();
                JOptionPane.showMessageDialog(this, "Date changed to " + df.format(getDateViewLog()), "information", JOptionPane.INFORMATION_MESSAGE);
                break;
            case  "View Food Collection":
                manager.printFoodCollection();
                break;
            case "Update Basic Food":
                updateBasic();
                viewInfo();
                viewData();
                JOptionPane.showMessageDialog(this, "Food Updated", "information", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Update Exercise":
                updateExercise();
                JOptionPane.showMessageDialog(this, "Exercise Updated", "information", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "View Recipe's Ingredients":
                displaySubIng();
                break;
            case "View Exercise Calories":
                viewInfoExer();
                break;
            case "Delete From Daily Log":
                deleteFDL();
                viewData();
                break;
            case "Remove Ing":
                removeIng();
                break;
            case "Update Recipe Ingredients":
                updateRecipeIng();
                break;
            case "Add Weight":
                addWeight();
                viewData();
                JOptionPane.showMessageDialog(this, "Weight Recorded", "information", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Set Calorie Intake Goal":
                setGoal();
                viewData();
                break;
            case "View":
                viewData();
                break;
        }
    }

    public void viewData(){
        jlNumCalLost.setText(dl.totalCalLost(getHomeDate()).toString());
        jlNumCalConsumed.setText((dl.totalCalConsumed(getHomeDate()).toString()));
        jlNumWeight.setText((dl.getRecordedWeight(getHomeDate()).toString()));
        jlNumGoal.setText((dl.getCaloricIntake(getHomeDate()).toString()));

        double net = dl.totalCalConsumed(getHomeDate()) - dl.totalCalLost(getHomeDate());
        double difference = dl.getCaloricIntake(getHomeDate()) - net;
        jlNumCal.setText(Double.toString(net));
        jldifference.setText(Double.toString(difference));
        viewBarChartInfo();
        double fat = getBarChartFat();
        double carb = getBarChartCarb();
        double pro = getBarChartPro();
        double highestNum = fat;
        if (carb > highestNum){
            highestNum = carb;
        }

        if (pro > highestNum){
            highestNum = pro;
        }

        System.out.println(" high " + highestNum);
        jpBarFat.setPreferredSize(new Dimension(80, (int)(250*(fat/highestNum))));
        jpBarCarb.setPreferredSize(new Dimension(80, (int)(250*(carb/highestNum))));
        jpBarPro.setPreferredSize(new Dimension(80, (int)(250*(pro/highestNum))));
        BarChartPanel.revalidate();
        BarChartPanel.repaint();

    }

    public double getBarChartFat(){
        return dl.totalFat(getHomeDate());
    }

    public double getBarChartCarb(){
        return dl.totalCalConsumed(getHomeDate());
    }

    public double getBarChartPro(){
        return dl.totalCarb(getHomeDate());
    }

    public void viewBarChartInfo(){
        jlBarFat.setText(dl.totalFat(getHomeDate()).toString());
        jlBarCarb.setText(dl.totalCalConsumed(getHomeDate()).toString());
        jlBarPro.setText(dl.totalCarb(getHomeDate()).toString());
    }

    public void addWeight(){
        manager.addWeight(getDate(), Double.parseDouble(jtfAddWeight.getText()));
    }

    public void setRecipeDetail(){
        detailName = jtfSubRecipeFoodName.getText();
        detailCount = jtfSubRecipeFoodCount.getText();
        if(detailName.isEmpty() || detailCount.isEmpty()){
            JOptionPane.showMessageDialog(this, "Sub-Recipe Name and Count can not be empty, please try again.", "error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String subName = detailName;
        double subServing = Double.parseDouble(detailCount);
        if (fc.checkContains(subName)) {
            fc.getFood(subName).setServings(subServing);
            values.put(fc.getFood(subName), subServing);

        } else {
            JOptionPane.showMessageDialog(this, "This food does not exist in the Food collection. Must add the subrecipes to the Food Collection", "error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        recipeDetailDisplay += "(" + detailName + "," + detailCount + ") ";
        jlRecipeDetail.setText(recipeDetailDisplay);

    }

    /**
     * adds recipe to food collection and updates jLists
     */
    public void addRecipe(){
        String recipeName = jtfRecipeName.getText().trim();
        if(recipeName.isEmpty()){
            JOptionPane.showMessageDialog(this, "Recipe Name can not be empty, please try again.", "information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (!fc.checkContains(recipeName) && !jtfSubRecipeFoodName.getText().equals("")) {
            manager.addRecipe(recipeName, values);
            modelF.addElement(recipeName);
            modelFcR.addElement(recipeName);
            modelFcF.addElement(recipeName);
            JOptionPane.showMessageDialog(this, "Recipe Added", "information", JOptionPane.INFORMATION_MESSAGE);
        } else if (fc.checkContains(recipeName)) {
            JOptionPane.showMessageDialog(this, recipeName + " is already in the Food Collection.", "error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(this, "Please add at least 1 sub-ingredient.", "error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void updateRecipeIng(){
        detailName = jtfSubRecipeFoodName1.getText().trim();
        detailCount = jtfSubRecipeFoodCount1.getText().trim();
        if(detailName.isEmpty() || detailCount.isEmpty()){
            JOptionPane.showMessageDialog(this, "Sub-Recipe Name and Count can not be empty, please try again.", "error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String recipename = jListFcRecipe.getSelectedValue().toString();
        Food recipeFood = fc.getFood(recipename);
        Map<Food, Double> rFoodItems = recipeFood.getFoodItems();
        String subName = detailName;
        double subServing = Double.parseDouble(detailCount);
        if (fc.checkContains(subName)) {
            Food ingredient = fc.getFood(subName);
            ingredient.setServings(subServing);
            Double subserv = ingredient.getServings();
            rFoodItems.put(ingredient,subserv);
            displaySubIng();
        } else {
            JOptionPane.showMessageDialog(this, "This food does not exist in the Food collection. Must add the subrecipes to the Food Collection", "error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    public void viewInfo(){
        String foodName = jListFcFood.getSelectedValue().toString();
        if (!fc.checkContains(foodName)){
            System.out.println("rip");
        }
        double calories = fc.getFood(foodName).getCalorieCount();
        jlCalories.setText(Double.toString(calories));
        double fat = fc.getFood(foodName).getFat();
        double carb = fc.getFood(foodName).getCarb();
        double protein = fc.getFood(foodName).getProtein();
        jlCarbs.setText(Double.toString(carb));
        jlFat.setText(Double.toString(fat));
        jlPro.setText(Double.toString(protein));
    }

    public void viewInfoExer(){
        String exerciseName = jListEcAdd.getSelectedValue().toString();
        if (!ec.checkContains(exerciseName)){
            System.out.println("rip");
        }
        Exercise exer = ec.getExercise(exerciseName);
        double calories = exer.getCalories();
        jlNumExerCal.setText(Double.toString(calories));
    }

    /**
     * updates basic food item when user enters new values
     */
    public void updateBasic(){
        String foodName = jListFcFood.getSelectedValue().toString();
        double calorie_input = Double.parseDouble(updateCal.getText().trim());
        double fat_input = Double.parseDouble(updateFat.getText().trim());
        double carb_input = Double.parseDouble(updateCarb.getText().trim());
        double protein_input = Double.parseDouble(updatePro.getText().trim());

        if (!fc.checkContains(foodName)){
            System.out.println("rip");
        }

        fc.getFood(foodName).setCalorieCount(calorie_input);
        fc.getFood(foodName).setProtein(protein_input);
        fc.getFood(foodName).setCarb(carb_input);
        fc.getFood(foodName).setFat(fat_input);

        updateCal.setText("");
        updateFat.setText("");
        updatePro.setText("");
        updateCarb.setText("");
    }

    /**
     * updates exercise when user enters new values
     */
    public void updateExercise(){
        String exerciseName = jListEcAdd.getSelectedValue().toString();
        System.out.println(exerciseName);

        double calorie_input = Double.parseDouble(updateCalExer.getText().trim());
        Exercise exer = ec.getExercise(exerciseName);
        if (ec.checkContains(exerciseName)){
            exer.setCalories(calorie_input);
        }
        updateCalExer.setText("");
        viewInfoExer();
    }

    public void setGoal(){
        manager.addCaloricLimit(getDate(), Double.parseDouble(jtfAddCalIntake.getText()));

    }
    /**
     * logs food into daily log and updates JLists
     */
    public void logFood() {
        String foodName = jListFc.getSelectedValue().toString();
        double serving = Double.parseDouble(jtfServings.getText());
        if (fc.checkContains(foodName)) {
            manager.addFoodIntake(getDate(), foodName, serving);
            modelLog.addElement("ConsumedFoodName: " + fc.getFood(foodName).getName() + ":      Servings: " + fc.getFood(foodName).getServings());//"      Calories: " + fc.getFood(foodName).getCalorieCount() + "      Carbs: " + fc.getFood(foodName).getCarb() + "       Protein: " + fc.getFood(foodName).getProtein() + "      Fat: " + fc.getFood(foodName).getFat() + "      Servings: " + fc.getFood(foodName).getServings());
            JOptionPane.showMessageDialog(this, foodName + " added to the log for " + df.format(getDate()), "information", JOptionPane.INFORMATION_MESSAGE);
        } else if (!fc.checkContains(foodName)) {
            System.out.println(foodName);
            JOptionPane.showMessageDialog(this, foodName + " is not in the Food Collection. Please add it to the Food Collection and try again.", "information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void displaySubIng(){
        modelER.removeAllElements();
        String recipename = jListFcRecipe.getSelectedValue().toString();
        if (!fc.checkContains(recipename)){
            System.out.println("DOES NOT EXIST");
        }
            Food recipeFood = fc.getFood(recipename);
            Map<Food,Double> rm = recipeFood.getFoodItems();
            for (Food food : rm.keySet()){
            modelER.addElement(food.getName() + "," + food.getServings());
        }
    }

    public void removeIng(){
        String recipeandserv = jListSubRecipe.getSelectedValue().toString();
        System.out.println(recipeandserv);
        String[] splitted  = recipeandserv.split( ",");
        String name = splitted[0];
        Double serv = Double.parseDouble(splitted[1]);
        String recipename = jListFcRecipe.getSelectedValue().toString();
        Food recipeFood = fc.getFood(recipename);
        Map<Food, Double> rFoodItems = recipeFood.getFoodItems();
        Food ingre = fc.getFood(name);

        if (rFoodItems.containsKey(ingre)){
            rFoodItems.remove(ingre);
            displaySubIng();
        }
    }

    public void deleteFDL(){
        String dailyentry = jListLog.getSelectedValue().toString();
        int ind = jListLog.getSelectedIndex();
        String[] entry =  dailyentry.split(":");
        String foodname = entry[1].trim();
        double serving = Double.parseDouble(entry[3].trim());
        List<ConsumedFood> ar = dl.getConsumedFoods(date);
        System.out.println(ind);
        dl.removefromCF(ind,date);
        getDailyLog();
//        System.out.println(ind);
//        ListModel list = jListLog.getModel();
//
//        for(int i = 0; i < list.getSize(); i++){
//            Object obj = list.getElementAt(i);
//            System.out.println(i);
//        }
//        for (int i = 0; i<ar.size(); i++) {
//
//            Food foo = fc.getFood(ar.get(i).getName());
//            Double serv = ar.get(i).getServings();
//            String conname = ar.get(i).getName();
//
//            System.out.println( "Index " + i + " ConsumedFood Name: " + conname + "-- Servings: " + serv );//+ "-- Total Calories: " + calcount);
//
//            System.out.println(i);
    }

    public void updaterecServ(){
        String recipeandserv = jListSubRecipe.getSelectedValue().toString();
        System.out.println(recipeandserv);
        String[] splitted  = recipeandserv.split( ",");
        String name = splitted[0];
        Double serv = Double.parseDouble(splitted[1]);
        String recipename = jListFcRecipe.getSelectedValue().toString();
        Food recipeFood = fc.getFood(recipename);
        Map<Food, Double> rFoodItems = recipeFood.getFoodItems();
        Food ingre = fc.getFood(name);
        serv = Double.parseDouble(updateRecipeServ.getText().trim());

        if (rFoodItems.containsKey(ingre)){
            ingre.setServings(serv);
            rFoodItems.put(ingre,ingre.getServings());
            displaySubIng();
        }
    }

    /**
     * logs exercise in daily log
     */
    public void logExercise() {
        String exerciseName = jListEc.getSelectedValue().toString();
        double minutes = Double.parseDouble(jtfMinutes.getText());
        if (ec.checkContains(exerciseName)) {
            manager.recordExercise(getDate(), exerciseName, minutes);
            JOptionPane.showMessageDialog(this, exerciseName + " added to the log for " + df.format(getDate()), "information", JOptionPane.INFORMATION_MESSAGE);
        } else if (!ec.checkContains(exerciseName)) {
            JOptionPane.showMessageDialog(this, exerciseName + " is not in the Exercise Collection. Please add it to the Exercise Collection and try again.", "information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * adds food into food collection and updates jLists
     */
    public void addFood() {
        String food_input = jtfAfFood.getText().trim();
        double calorie_input = Double.parseDouble(jtfAfCal.getText().trim());
        double fat_input = Double.parseDouble(jtfAfFat.getText().trim());
        double carb_input = Double.parseDouble(jtfAfCarb.getText().trim());
        double protein_input = Double.parseDouble(jtfAfProtein.getText().trim());
        jtfAfFood.setText("");
        jtfAfCal.setText("");
        jtfAfFat.setText("");
        jtfAfCarb.setText("");
        jtfAfProtein.setText("");

        if (!fc.checkContains(food_input)) {
            Food addedFood = manager.addFood(food_input, calorie_input, fat_input, carb_input, protein_input);
            modelF.addElement(addedFood.getName());
            //modelFcR.addElement(addedFood.getName());
            modelFcF.addElement(addedFood.getName());
            JOptionPane.showMessageDialog(this, food_input + " added to Food Collection", "information", JOptionPane.INFORMATION_MESSAGE);            System.out.println("Your food item was added to the Food Collection ");
        } else if (fc.checkContains(food_input)) {
            JOptionPane.showMessageDialog(this, food_input + " has already been added. Check the Food Collection below. ", "information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Adds exercise input to the exercise collection and updates the JLists
     */
    public void addExercise() {
        String exercise_input = jtfAeName.getText().trim();
        double calorie_input = Double.parseDouble(jtfAeCal.getText());
        jtfAeName.setText("");
        jtfAeCal.setText("");

        if (!ec.checkContains(exercise_input)) {
            Exercise addedExercise = manager.addExercise(exercise_input, calorie_input);
            modelE.addElement(addedExercise.getName());
            modelEc.addElement(addedExercise.getName());
            JOptionPane.showMessageDialog(this, exercise_input + " added to the Exercise Collection", "information", JOptionPane.INFORMATION_MESSAGE);            System.out.println("Your food item was added to the daily log");
        } else if (ec.checkContains(exercise_input)) {
            JOptionPane.showMessageDialog(this, exercise_input + " has already been added. Check the Exercise Collection below. ", "information", JOptionPane.INFORMATION_MESSAGE);            System.out.println("Your food item was added to the daily log");
        }

    }

    /**
     * Gets the current date
     * @return current date
     */
    public Date getCurrentDate() {
        df = new SimpleDateFormat("yyyy/MM/dd");
        date = new Date();
        currDate = df.format(date);
        jtfDate.setText(currDate);
        jtfDateLog.setText(currDate);
        //viewData();
        jtfHomeDate.setText(currDate);
        try {
            date = new SimpleDateFormat("yyyy/MM/dd").parse(currDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Gets the date that the user inputted
     * @return date
     */
    public Date getDate() {
        date = new Date();
        currDate = jtfDate.getText();
        try {
            date = new SimpleDateFormat("yyyy/MM/dd").parse(currDate);
            dailyloglist = dl.getConsumedFoods(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Gets the date that the user inputted on the view log tab
     * @return date
     */
    public Date getDateViewLog() {
        date = new Date();
        currDate = jtfDateLog.getText();
        try {
            date = new SimpleDateFormat("yyyy/MM/dd").parse(currDate);
            dailyloglist = dl.getConsumedFoods(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * gets the date that the user inputted on the home tab
     * @return date
     */
    public Date getHomeDate() {
        date = new Date();
        currDate = jtfHomeDate.getText();
        dailyloglist = dl.getConsumedFoods(date);
        try {
            date = new SimpleDateFormat("yyyy/MM/dd").parse(currDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * adds the food collection into jLists for viewing
     */
    public void getFoodCollection() {
        for (int i = 0; i < foods.size(); i++){
            Food food = foods.get(i);
            if (food instanceof Recipe){
                modelFcR.addElement(foods.get(i).getName() ); //+ "    Calories:" + foods.get(i).getCalorieCount() + "      Fat: " + foods.get(i).getFat() + "      Protein: " + foods.get(i).getProtein() + "      Carbs: " + foods.get(i).getCarb());
            }
            if (food instanceof BasicFood){
                modelFcF.addElement(foods.get(i).getName() ); //+ "      Calories: " + foods.get(i).getCalorieCount() + "      Fat: " + foods.get(i).getFat() + "      Protein: " + foods.get(i).getProtein() + "      Carbs: " + foods.get(i).getCarb());
            }
            modelF.addElement(foods.get(i).getName());
        }
    }

    /**
     * adds the exercise collection into jLists for viewing
     */
    public void getExerciseCollection() {
        for (int i = 0; i < exercises.size(); i++)
        {
            modelE.addElement(exercises.get(i).getName());
            modelEc.addElement(exercises.get(i).getName()); //+ "      Calories: " + exercises.get(i).getCalories());
        }
    }

    /**
     * adds the daily log into jLists for viewing
     */
    public void getDailyLog() {
        modelLog.removeAllElements();
        date = getDateViewLog();
        if(dl.getConsumedFoods(date)!=null) {
            dailyloglist = dl.getConsumedFoods(date);
            for (ConsumedFood cf : dailyloglist) {
                Food foo = fc.getFood(cf.getName());
                modelLog.addElement("ConsumedFood Name: " + foo.getName() + ":      Servings: " + cf.getServings()); //"      Calories: " + foo.getCalorieCount() * cf.getServings() + "      Carbs: " + foo.getCarb() * cf.getServings() + "       Protein: " + foo.getProtein() * cf.getServings() + "      Fat: " + foo.getFat() * cf.getServings());
            }
        }
        if(dl.getRecordedExercise(date)!=null) {
            dailyloglist1 = dl.getRecordedExercise(date);
            for (RecordExercise re : dailyloglist1) {
                Exercise ex = ec.getExercise(re.getName());
                modelLog.addElement("RecordedExercise Name: " + ex.getName() + ":       Minutes: " + re.getMinutes());
            }
        }
    }
}
