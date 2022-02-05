package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class fileChart {

    String absolutePath = "data/chart.txt";

    public fileChart() {
    }

    public void write(String string) {
        try (BufferedWriter buffered = new BufferedWriter(new FileWriter(absolutePath, true))) {
            buffered.write(string);
            buffered.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}
