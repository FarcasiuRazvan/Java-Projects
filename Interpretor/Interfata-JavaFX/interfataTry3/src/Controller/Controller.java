package Controller;

import Model.IStmt;
import Model.Pair;
import Model.PrgState;
import Repository.IPrgRepo;
import Utils.PrgStateUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller implements Utils.Observer<PrgState>{

@FXML
    private Button OneStepButton;
@FXML
   private TextField NoPrgStates;
@FXML
    private Label prgId;
@FXML
    private TableView<Map.Entry<Integer,Integer>> HeapTableView;
@FXML
    private ListView<String> OutputListView;
@FXML
    private TableView<Pair<Integer,String>> FileTableView;
@FXML
    private ListView<PrgState> PrgStatesListView;
@FXML
    private TableView<Pair<String,Integer>> SymTableView;
@FXML
    private ListView<IStmt> ExeStackListView;

    private IPrgRepo repo;
    private boolean startThreads = true;
    private ExecutorService executor;
    private ObservableList<PrgState> prgStateModel;
    private ObservableList<String> outListModel;
    private ObservableList<Map.Entry<Integer, Integer>> heapTableModel;
    private ObservableList<Pair<Integer, String>> fileTableModel;
    private ObservableList<IStmt> exeStackModel;
    private PrgStateUtil prgStateUtil;
    private ObservableList<Pair<String, Integer>> symTableModel;

    @FXML
    private void initialize() {
    }

    public void setService(PrgStateUtil prgStateUtil) {
        this.prgStateUtil = prgStateUtil;
        this.repo = this.prgStateUtil.getRepo();
        this.prgStateModel = FXCollections.observableArrayList();
        this.PrgStatesListView.setItems(this.prgStateModel);

        this.PrgStatesListView.setCellFactory(new Callback<ListView<PrgState>, ListCell<PrgState>>() {
            @Override
            public ListCell<PrgState> call(ListView<PrgState> param) {
                return new ListCell<PrgState>() {
                    @Override
                    protected void updateItem(PrgState e, boolean empty) {
                        super.updateItem(e, empty);
                        if (e == null || empty)
                            setText("");
                        else
                            setText(String.valueOf(e.getId()));
                    }
                };
            }
        });
        heapServiceSetup();
        fileTableServiceSetup();
        symTableServiceSetup();
        // OUTPUT
        this.outListModel = FXCollections.observableArrayList();
        this.OutputListView.setItems(this.outListModel);

        this.PrgStatesListView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    prgId.setText(String.valueOf(newValue.getId()));
                    List<PrgState> prgStates = prgStateUtil.getAll();
                    PrgState current = prgStates.stream()
                            .filter(e -> e.getId() == Integer.valueOf(prgId.getText())).findFirst().orElse(null);
                    if (current != null) {
                        List<IStmt> list = current.getStk().getStk();
                        Collections.reverse(list);
                        exeStackModel.setAll(list);
                        symTableModel.setAll(current.getSymTable().clone().toMap().entrySet().stream()
                                .map(e -> new Pair<>(e.getKey(), e.getValue())).collect(Collectors.toList()));
                    }
                });

        // EXESTACK
        this.exeStackModel = FXCollections.observableArrayList();
        this.ExeStackListView.setItems(this.exeStackModel);

        this.update(this.prgStateUtil);
    }

    private void symTableServiceSetup() {
        TableColumn<Pair<String, Integer>, String> symNameColumn = new TableColumn<>("Symbol");
        TableColumn<Pair<String, Integer>, String> symValueColumn = new TableColumn<>("Value");
        symNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getKey()));
        symValueColumn
                .setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getValue())));
        this.SymTableView.getColumns().setAll(symNameColumn, symValueColumn);
        this.symTableModel = FXCollections.observableArrayList();
        this.SymTableView.setItems(this.symTableModel);
    }

    private void fileTableServiceSetup() {
        /// fileTableView
        this.fileTableModel = FXCollections.observableArrayList();
        TableColumn<Pair<Integer, String>, String> fd = new TableColumn<>("File descriptor");
        TableColumn<Pair<Integer, String>, String> fn = new TableColumn<>("File name");
        fd.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getKey())));
        fn.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getValue())));

        this.FileTableView.getColumns().setAll(fd, fn);
        this.FileTableView.setItems(this.fileTableModel);
    }

    private void heapServiceSetup() {
        // heapTableView
        this.heapTableModel = FXCollections.observableArrayList();
        TableColumn<Map.Entry<Integer, Integer>, String> key = new TableColumn<>("Key");
        TableColumn<Map.Entry<Integer, Integer>, String> value = new TableColumn<>("Value");
        key.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getKey())));
        value.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getValue())));
        this.HeapTableView.getColumns().setAll(key, value);
        this.HeapTableView.setItems(this.heapTableModel);
    }

    /////////////////////////////////////////////

    private void barrierTableServiceSetup() {
        // heapTableView
        this.heapTableModel = FXCollections.observableArrayList();
        TableColumn<Map.Entry<Integer, Integer>, String> key = new TableColumn<>("Key");
        TableColumn<Map.Entry<Integer, Integer>, String> value = new TableColumn<>("Value");
        key.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getKey())));
        value.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getValue())));
        this.HeapTableView.getColumns().setAll(key, value);
        this.HeapTableView.setItems(this.heapTableModel);
    }

    /////////////////////////////////////////////

    private Map<Integer, Integer> conservativeGarbageCollector(Collection<Integer> symTableValues,
                                                               Map<Integer, Integer> heap) {
        return heap.entrySet().stream().filter(e -> symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> collectSymTableValuesPrgList(List<PrgState> prgList) {
        List<Integer> tempList = new ArrayList<>();
        for (PrgState prgState : prgList) {
            tempList.addAll(prgState.getSymTable().getContent().values());
        }
        return tempList;
    }

    private List<PrgState> heapCleanup(List<PrgState> prgList) {
        Collection<Integer> allSymTableValues = collectSymTableValuesPrgList(prgList);
        return prgList.stream().peek(prgState -> {
            Map<Integer, Integer> garbageCollected = conservativeGarbageCollector(allSymTableValues,
                    prgState.getHeap().getHeap());
            prgState.getHeap().setHeap(garbageCollected);
        }).collect(Collectors.toList());
    }

    private List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream().filter(PrgState::isNotCompleted).collect(Collectors.toList());
    }

    public void reset() {
        this.repo.reset();
    }

    public void allStep() throws InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        while (prgList.size() > 0) {
            oneStepForAllPrg(prgList);

            repo.setPrgList(new ArrayList<>(heapCleanup(prgList)));
            prgList.forEach(prg -> {
                try {
                    repo.logPrgStateExec(prg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            prgList = removeCompletedPrg(repo.getPrgList());
        }
        closeBuffer(repo.getPrgList().get(0).getFileTable().values());
        executor.shutdownNow();
        repo.setPrgList(new ArrayList<>(prgList));
    }

    @FXML
    public void oneStepForGui() throws InterruptedException {
        if (this.startThreads) {
            this.startThreads = false;
            this.executor = Executors.newFixedThreadPool(2);
        }
        List<PrgState> prgList = removeCompletedPrg(this.repo.getPrgList());
        prgList.forEach(prg -> {
            try {
                this.repo.logPrgStateExec(prg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        oneStepForAllPrg(prgList);
        this.repo.setPrgList(new ArrayList<>(heapCleanup(prgList)));
        prgList.forEach(prg -> {
            try {
                this.repo.logPrgStateExec(prg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        prgList = removeCompletedPrg(this.repo.getPrgList());
        this.repo.setPrgList(new ArrayList<>(prgList));
        if (this.repo.getSize() == 0) {
            this.executor.shutdownNow();
            this.OneStepButton.setDisable(true);
        }
    }

    private void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {
        List<Callable<PrgState>> callList = prgList.stream().map((PrgState p) -> (Callable<PrgState>) (p::executeOneStatement))
                .collect(Collectors.toList());
        List<PrgState> newPrgList = executor.invokeAll(callList).stream().map(future -> {
            try {
                return future.get();
            } catch (InterruptedException | ExecutionException ee) {
                createAlertFromException(ee);
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        prgList.addAll(newPrgList);
        repo.setPrgList(new ArrayList<>(prgList));
        this.prgStateUtil.notifyObservers();
    }

    private void closeBuffer(Collection<Pair<String, BufferedReader>> values) {
        values.forEach(e -> {
            try {
                e.getValue().close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    public String getFilePath() {
        return this.repo.getLogFilePath();
    }

    private void createAlertFromException(Exception ex) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("Program State Exception Dialog");
        alert.setContentText("There was an exception:\n" + ex.getMessage());
        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();

    }

    public void update(Utils.Observable<PrgState> observable) {
        List<PrgState> prgStates = this.prgStateUtil.getAll();
        this.NoPrgStates.setText(String.valueOf(prgStates.size()));
        this.prgStateModel.setAll(prgStates);
        this.outListModel.setAll(this.prgStateUtil.getOutList());
        this.heapTableModel.setAll(this.prgStateUtil.getHeapList());
        this.heapTableModel.setAll(this.prgStateUtil.getHeapList());
        /// this we change
        this.fileTableModel.setAll(prgStates.get(0).getFileTable().keys().stream()
                .map(k -> new Pair<>(k, prgStates.get(0).getFileTable().lookup(k).getKey()))
                .collect(Collectors.toList()));
        PrgState current = prgStates.stream().filter(e -> e.getId() == Integer.valueOf(prgId.getText()))
                .findFirst().orElse(null);
        if(current != null) {
            List<IStmt> list = new ArrayList<>(current.getStk().getStk());
            this.exeStackModel.setAll(list);
            this.symTableModel.setAll(current.getSymTable().clone().toMap().entrySet().stream()
                    .map(e -> new Pair<>(e.getKey(), e.getValue())).collect(Collectors.toList()));
        }
    }
}
