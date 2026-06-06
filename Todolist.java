import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TodoList {
    private ArrayList<String> tasks;
    private final String filePath = "data/todo.csv";

    // #2 Uzdevums: 
    public TodoList() {
        this.tasks = new ArrayList<>();
        loadFromFile();
    }

    // #2 Uzdevums: 
    private void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                if (!line.trim().isEmpty()) {
                    this.tasks.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Kļūda, lasot failu: " + e.getMessage());
        }
    }

    // #3 Uzdevums: 
    private int getLastId() {
        if (tasks.isEmpty()) {
            return 0;
        }
        String lastLine = tasks.get(tasks.size() - 1);
        String[] parts = lastLine.split(",");
        try {
            return Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    // #4 Uzdevums: 
    public void add(String task) {
        // #6 Uzdevums: 
        if (!checkEventString(task)) {
            System.out.println("Kļūda: Aktivizāte neatbilst prasībām (min 3 simboli, tikai burti/cipari/atstarpes)!");
            return;
        }

        int nextId = getLastId() + 1;
        String newRecord = nextId + "," + task;
        
        this.tasks.add(newRecord); 
        updateFile();              
    }

    // #5 Uzdevums: 
    private boolean updateFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
          
            bw.write("id,task");
            bw.newLine();
            
            
            for (String taskLine : tasks) {
                bw.write(taskLine);
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("Kļūda, saglabājot failu: " + e.getMessage());
            return false;
        }
    }

    // #5 Uzdevums: 
    public void remove(int id) {
        int indexToRemove = -1;
        
        for (int i = 0; i < tasks.size(); i++) {
            String[] parts = tasks.get(i).split(",");
            if (Integer.parseInt(parts[0]) == id) {
                indexToRemove = i;
                break;
            }
        }
        
        if (indexToRemove != -1) {
            tasks.remove(indexToRemove);
            updateFile(); 
            System.out.println("Uzdevums ar ID " + id + " veiksmīgi izdzēsts.");
        } else {
            System.out.println("Uzdevums ar ID " + id + " netika atrasts.");
        }
    }

    // #6 Uzdevums: RegEx pārbaude 
    public boolean checkEventString(String value) {
        if (value == null || value.length() < 3) {
            return false;
        }
        
        return value.matches("^[a-zA-Z0-9 ]+$");
    }
}