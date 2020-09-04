package fr.univ_lyon1.info.m1.cv_search.view;


import fr.univ_lyon1.info.m1.cv_search.controller.AbstractController;
import fr.univ_lyon1.info.m1.cv_search.controller.CvSearchController;
import fr.univ_lyon1.info.m1.cv_search.model.AbstractModel;
import fr.univ_lyon1.info.m1.cv_search.model.CvSearchModel;
import fr.univ_lyon1.info.m1.cv_search.model.ModelAction;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.companie.Companie;
import fr.univ_lyon1.info.m1.cv_search.model.companie.CompanieList;
import fr.univ_lyon1.info.m1.cv_search.model.request.AbstractRequest;
import fr.univ_lyon1.info.m1.cv_search.model.request.History;
import fr.univ_lyon1.info.m1.cv_search.model.request.HistoryElement;
import fr.univ_lyon1.info.m1.cv_search.model.request.SearchRequest;
import fr.univ_lyon1.info.m1.cv_search.model.skill.Skill;
import fr.univ_lyon1.info.m1.cv_search.model.skill.SkillList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Accordion;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import org.controlsfx.control.ToggleSwitch;

import java.util.ArrayList;
import java.util.Map;
import java.util.Observer;


public class CvSearchView extends AbstractView {
    private static final String LABEL_STRAT1 = "Strategy 1";
    private static final String LABEL_STRAT2 = "Strategy 2";
    private static final String LABEL_HISTORY = "History";
    TextField textField;
    private HBox searchSkillsBox;
    private HBox searchCompanieBox;
    private VBox resultBox;
    private VBox historyBox;
    private ComboBox choiceStrategies;
    private ComboBox choiceStrategiesCompanie;
    private Accordion resultsDisplay;
    private Accordion historyDisplay;
    private TextField textField2;
    private JMetro metro;

    /**
     *  Classe View de CvSearch, view principale
     * @param controller le controller(vous vous en doutiez hein)
     * @param model le model(devine quoi ...)
     */
    public CvSearchView(AbstractController controller, AbstractModel model) {
        this.controller = controller;
        this.model = model;
        initView();
        initObservers();

    }

    /**
     * Create the main view of the application.
     */
    protected void initView() {
        // Name of window
        root = new Pane();
        historyBox = new VBox();

        // Init row and col
        HBox row1 = new HBox();
        row1.setLayoutX(0);
        row1.setLayoutY(0);

        HBox row2 = new HBox();
        row2.setLayoutX(0);
        row2.setLayoutY(50);

        HBox row3 = new HBox();
        row3.setLayoutX(0);
        row3.setLayoutY(100);

        HBox row5 = new HBox();
        row5.setLayoutY(160);
        row5.setLayoutX(0);

        HBox row6 = new HBox();
        row6.setLayoutX(0);
        row6.setLayoutY(210);

        HBox row7 = new HBox();
        row7.setLayoutX(0);
        row7.setLayoutY(250);

        VBox col1 = new VBox();
        col1.setLayoutX(600);

        // Set padding and spacing
        row1.setPadding(new Insets(10));
        row5.setPadding(new Insets(10));
        row2.setPadding(new Insets(10));
        row1.setSpacing(10);
        row5.setSpacing(10);

        // Create all combobox
        createStrategiesComboBoxWidget();
        createCompanieComboBoxWidget();

        Separator separator = new Separator();
        separator.setLayoutX(100);
        separator.setLayoutY(150);
        separator.setMinWidth(400);

        Separator separator2 = new Separator();
        separator2.setLayoutX(100);
        separator2.setLayoutY(300);
        separator2.setMinWidth(400);

        createCurrentSearchSkillsWidget();

        Node search = createSearchWidget();
        search.setLayoutX(150);
        search.setLayoutY(565);

        Node resultBox = createResultsWidget();
        resultBox.setLayoutX(100);
        resultBox.setLayoutY(320);

        resultsDisplay = new Accordion();

        // All label here
        Label label = new Label(LABEL_STRAT1);
        Label label2 = new Label(LABEL_STRAT2);
        Label label3 = new Label(LABEL_HISTORY);

        // ADD TO COL1
        col1.getChildren().add(label3);
        col1.getChildren().add(historyBox);

        // ADD TO ROW1
        row1.getChildren().add(label);
        row1.getChildren().add(choiceStrategies);
        // ADD TO ROW2
        row2.getChildren().add(createAddSkillWidget());
        // ADD TO ROW3
        row3.getChildren().add(searchSkillsBox);
        // ADD TO ROW4
        // ADD TO ROW5
        row5.getChildren().add(label2);
        row5.getChildren().add(choiceStrategiesCompanie);
        // ADD TO ROW6
        row6.getChildren().add(createAddCompanieWidget());
        // ADD TO ROW6
        row7.getChildren().add(searchCompanieBox);


        Button saveHistory = new Button("Save History");
        saveHistory.setLayoutX(250);
        saveHistory.setLayoutY(565);
        saveHistory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ((CvSearchController) controller).saveHistory();
            }
        });
        Button clearHistory = new Button("Clear history");
        clearHistory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ((CvSearchController) controller).clearHistory();
            }
        });
        clearHistory.setLayoutX(370);
        clearHistory.setLayoutY(565);
        Button loadHistory = new Button("Load history");
        loadHistory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ((CvSearchController) controller).loadHistory();
            }
        });
        loadHistory.setLayoutX(480);
        loadHistory.setLayoutY(565);

        ToggleSwitch ts = createTs();

        // ADD TO ROOT
        ((Pane) root).getChildren().add(row1);
        ((Pane) root).getChildren().add(row2);
        ((Pane) root).getChildren().add(row3);
        ((Pane) root).getChildren().add(row5);
        ((Pane) root).getChildren().add(row6);
        ((Pane) root).getChildren().add(row7);
        ((Pane) root).getChildren().add(separator);
        ((Pane) root).getChildren().add(separator2);
        ((Pane) root).getChildren().add(search);
        ((Pane) root).getChildren().add(col1);
        ((Pane) root).getChildren().add(resultBox);
        ((Pane) root).getChildren().add(resultsDisplay);
        ((Pane) root).getChildren().add(ts);
        ((Pane) root).getChildren().add(saveHistory);
        ((Pane) root).getChildren().add(clearHistory);
        ((Pane) root).getChildren().add(loadHistory);

        // Init JMetro style
        metro = new JMetro(root, Style.DARK);
        metro.reApplyTheme();
        metro.setAutomaticallyColorPanes(true);
    }

    /**
     * Create the ToggleSwitch to change the theme.
     *
     * @return ToggleSwitch
     */
    private ToggleSwitch createTs() {
        ToggleSwitch t = new ToggleSwitch();
        t.setId("#67");
        t.setLayoutY(570);
        t.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue,
                                Boolean aBoolean, Boolean t1) {
                metro.setStyle(metro.getStyle().equals(Style.DARK) ? Style.LIGHT : Style.DARK);
            }
        });
        return t;
    }


    @Override
    public void initObservers() {
        model.addObserver(new Observer() {
            @Override
            public void update(java.util.Observable o, Object arg) {
                switch ((ModelAction) arg) {
                    case ADD_SKILL:
                        addSkillBtn(((CvSearchModel) model).getLastSkill());
                        break;
                    case ADD_COMPANIE:
                        addCompanieBtn(((CvSearchModel) model).getCompanieSelected());
                        break;
                    case REMOVE_SKILL:
                        removeBtn(((CvSearchModel) model).getSkillList());
                        break;
                    case REMOVE_COMPANIE:
                        removeBtnCompanie(((CvSearchModel) model).getCompanies());
                        break;
                    case SEARCH:
                        updateSearch(((CvSearchModel) model).getLastRequest());
                        break;
                    case UPDATE_HISTORY:
                        updateHistoryWidget(((CvSearchModel) model).getHistory());
                        break;
                    case UPDATE_SELECT:
                        updateComboBox(((CvSearchModel) model).getStrategySelected());
                        break;
                    case CLEAR_SKILL:
                        clearSkillBtn();
                        break;
                    case CLEAR_COMPANIE:
                        clearCompanieBtn();
                        break;
                    case LOAD_HISTORY:
                        updateHistoryWidget(((CvSearchModel) model).getHistory());
                        break;
                    default:
                        break;
                }
            }
        });

    }

    private void clearCompanieBtn() {
        searchCompanieBox.getChildren().clear();

    }

    private void clearSkillBtn() {
        searchSkillsBox.getChildren().clear();
    }

    /**
     * Update current selected value when apply history element.
     *
     * @param strategySelected bla
     */
    private void updateComboBox(Map<String, String> strategySelected) {
        choiceStrategies.getSelectionModel().select(strategySelected.get(choiceStrategies.getId()));
        choiceStrategiesCompanie.getSelectionModel().select(
                strategySelected.get(choiceStrategiesCompanie.getId())
        );
    }

    /**
     * update result with history result.
     *
     * @param request objet request
     *
     * @since 3.0
     */
    private void updateSearch(SearchRequest request) {
        this.resultBox.getChildren().clear();
        ApplicantList appList = ((CvSearchModel) this.model).getLastRequest().getResult();

        updateApplicantsWidget(appList);
    }

    /**
     * Create history widget
     *
     * @param history Hbox history
     *
     * @since 3.0
     */
    private void updateHistoryWidget(History history) {
        historyDisplay = new Accordion();
        int i = 1;
        for (HistoryElement r : history.all()) {
            HBox elems = new HBox();
            elems.setSpacing(10);

            GridPane filters = new GridPane();
            filters.setHgap(10);
            filters.setVgap(10);

            int row = 0;
            GridPane strats = new GridPane();
            strats.setVgap(10);
            strats.setHgap(10);
            for (AbstractRequest sr : r.getEl()) {
                SearchRequest sr2 = (SearchRequest) sr;
                filters.addRow(row, new Label(sr2.getToApply().getName()));
                row++;
            }

            GridPane skills = new GridPane();
            skills.setVgap(10);
            skills.setHgap(10);
            ArrayList<Label> sk = new ArrayList<>();
            sk.add(new Label("Skills : "));

            for (Skill ss : ((SearchRequest) r.get(0)).getOptions().getSkillList()) {
                sk.add(new Label(ss.getName()));
            }
            skills.addRow(
                    0,
                    sk.toArray(new Label[sk.size()])
            );
            skills.addRow(
                    1,
                    new Separator(Orientation.HORIZONTAL)
            );
            Button btnApply = new Button("Apply");
            skills.addRow(
                    2,
                    btnApply

            );
            btnApply.setId("h-" + i);
            btnApply.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String id = btnApply.getId();
                    ((CvSearchController) controller).applyHistory(id);
                }
            });

            elems.getChildren().add(filters);
            elems.getChildren().add(new Separator(Orientation.VERTICAL));
            elems.getChildren().add(skills);


            historyDisplay.getPanes().add(new TitledPane("Request n°" + i, elems));

            i++;
        }
        historyBox.getChildren().clear();
        historyBox.getChildren().addAll(historyDisplay);
    }

    /**
     * Update the Applicant widget when a new result is set in the model
     *
     * @param appl
     *         new applicantlist to set
     *
     * @since 3.0
     */
    private void updateApplicantsWidget(ApplicantList appl) {
        // resultsDisplay.getChildrenUnmodifiable().clear();
        resultsDisplay = new Accordion();

        for (int i = 0; i < appl.size(); i++) {
            HBox elems = new HBox();
            elems.setSpacing(10);

            GridPane skills = new GridPane();
            skills.setHgap(10);
            skills.setVgap(10);

            int row = 0;
            for (Map.Entry<String, Integer> entry : appl.get(i).getSkills().entrySet()) {
                skills.addRow(
                        row,
                        new Label(entry.getKey()),
                        new Label(String.valueOf(entry.getValue()))
                );
                row++;
            }

            GridPane experiences = new GridPane();
            experiences.setVgap(10);
            experiences.setHgap(10);

            for (int j = 0; j < appl.get(i).getExperiences().size(); j++) {
                ArrayList<Label> exp = new ArrayList<>();

                exp.add(new Label(appl.get(i).getExperiences().get(j).getName()));
                exp.add(new Label(String.valueOf(appl.get(i).getExperiences().get(j).getStart())));
                exp.add(new Label(String.valueOf(appl.get(i).getExperiences().get(j).getEnd())));

                for (Skill s : appl.get(i).getExperiences().get(j).getSkillsDuring()) {
                    exp.add(new Label(s.getName()));

                }

                experiences.addRow(
                        j,
                        exp.toArray(new Label[exp.size()])
                );
            }

            elems.getChildren().add(skills);
            elems.getChildren().add(new Separator(Orientation.VERTICAL));
            elems.getChildren().add(experiences);

            resultsDisplay.getPanes().add(
                    new TitledPane(i + 1 + ". " + appl.get(i).getName(), elems)
            );
            // ((VBox) root).getChildren().add(resultsDisplay);
        }
        resultBox.getChildren().addAll(resultsDisplay);
    }


    private void removeBtn(SkillList skills) {
        Node del = null;
        for (Node b : searchSkillsBox.getChildren()) {
            HBox cBox = ((HBox) b);
            if (!skills.contains(new Skill(((Label)cBox.getChildren()
                    .get(0)).getText().strip()))) {
                del = b;
            }

        }
        if (del != null) {
            searchSkillsBox.getChildren().remove(del);
        }
    }

    private void removeBtnCompanie(CompanieList companies) {
        Node del = null;
        for (Node b : searchCompanieBox.getChildren()) {
            HBox cBox = ((HBox) b);
            if (!companies.contains(((Label) cBox.getChildren().get(0)).getText().strip())) {
                del = b;
            }

        }
        if (del != null) {
            searchCompanieBox.getChildren().remove(del);
        }
    }


    private void addCompanieBtn(Companie s) {
        Button b = new Button("x");
        HBox box = createAddBtn(s.getName(), textField2, b, searchCompanieBox);
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = ((Label) box.getChildren().get(0)).getText();
                //String text = l.getText();
                ((CvSearchController) controller).removeCompanie(text);
            }
        });

    }

    private HBox createAddBtn(String name, TextField textF, Button btn, HBox p) {
        HBox box = new HBox();
        Label l = new Label(name + " ");
        box.setStyle("-fx-padding: 2;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 1;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" + "-fx-border-color: black;");
        box.getChildren().add(l);
        box.getChildren().add(btn);
        textF.setText("");
        textF.requestFocus();
        p.getChildren().add(box);
        return box;
    }

    private void addSkillBtn(Skill s) {
        Button b = new Button("x");
        HBox box = createAddBtn(s.getName(), textField, b, searchSkillsBox);
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = ((Label) box.getChildren().get(0)).getText();
                //String text = l.getText();
                ((CvSearchController) controller).removeSkill(text);
            }
        });
    }

    private ComboBox createComboBox(String[] elems, String id) {
        ComboBox combo;
        ObservableList<String> options = FXCollections.observableArrayList(elems);
        combo = new ComboBox(options);
        combo.setId(id);
        return combo;
    }

    private void createStrategiesComboBoxWidget() {
        choiceStrategies = createComboBox(((CvSearchModel) model).initStrategiesNameType1(), "99");

        // Handle event
        choiceStrategies.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                ((CvSearchController) controller).updateStrategies(choiceStrategies.getId(), t1);
            }
        });
    }

    private void createCompanieComboBoxWidget() {
        choiceStrategiesCompanie = createComboBox(
                ((CvSearchModel) model).initStrategiesNameType2(),
                "100"
        );

        // Handle event
        choiceStrategiesCompanie.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                ((CvSearchController) controller).updateStrategies(
                        choiceStrategiesCompanie.getId(),
                        t1
                );
            }
        });
    }

    /**
     * Create the text field to enter a new skill.
     */
    private Node createAddSkillWidget() {
        HBox newSkillBox = new HBox();
        Label labelSkill = new Label("Skill:");
        textField = new TextField();
        Button submitButton = new Button("Add skill");
        newSkillBox.getChildren().addAll(labelSkill, textField, submitButton);
        newSkillBox.setSpacing(10);

        EventHandler<ActionEvent> skillHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (searchSkillsBox.getWidth() >= 550) {
                    return;
                }
                String text = textField.getText();
                ((CvSearchController) controller).addSkill(text);


            }
        };
        submitButton.setOnAction(skillHandler);
        textField.setOnAction(skillHandler);

        return newSkillBox;
    }

    /**
     * Create the text field to enter a new skill.
     */
    private Node createAddCompanieWidget() {
        HBox newCBox = new HBox();
        Label labelSkill = new Label("Companie :");
        textField2 = new TextField();
        Button submitButton = new Button("Add Companie");
        newCBox.getChildren().addAll(labelSkill, textField2, submitButton);
        newCBox.setSpacing(10);

        EventHandler<ActionEvent> skillHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (searchCompanieBox.getWidth() >= 550) {
                    return;
                }
                String text = textField2.getText();
                ((CvSearchController) controller).addCompanie(text);


            }
        };
        submitButton.setOnAction(skillHandler);
        textField2.setOnAction(skillHandler);

        return newCBox;
    }

    /**
     * Create the widget showing the list of applicants.
     */
    private Node createResultsWidget() {
        resultBox = new VBox();
        return resultBox;
    }

    /**
     * Create the widget used to trigger the search.
     */
    private Node createSearchWidget() {
        Button search = new Button("Search");

        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO
                ((CvSearchController) controller).search();

            }
        });
        return search;
    }


    /**
     * Create the widget showing the list of skills currently searched
     * for.
     */
    private void createCurrentSearchSkillsWidget() {
        searchSkillsBox = new HBox();
        searchCompanieBox = new HBox();
    }
}
