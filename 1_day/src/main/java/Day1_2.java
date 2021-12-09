import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class Day1_2 {
    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> inputs = new ArrayList<>();
        ThreesumCalculator calculator = new ThreesumCalculator();
        InputStream fileInputStream = Day1_2.class.getResourceAsStream("measurements.txt");

        if(!nonNull(fileInputStream)) {
            throw new FileNotFoundException();
        }

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream))) {
            String line;
            while((nonNull(line = bufferedReader.readLine()))) {
                inputs.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(calculator.calculate(inputs));
    }
}
