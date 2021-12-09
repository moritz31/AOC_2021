import java.io.*;

import static java.util.Objects.nonNull;

public class Day1_1 {
    public static void main(String[] args) throws FileNotFoundException {
        InputStream fileInputStream = Day1_1.class.getResourceAsStream("measurements.txt");

        if(!nonNull(fileInputStream)) {
            throw new FileNotFoundException();
        }

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream))) {
            System.out.println(evaluateDifference(bufferedReader));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int evaluateDifference(BufferedReader reader) throws IOException {
        String line;
        String oldLine = null;
        int result = 0;
        while((nonNull(line = reader.readLine()))) {
            if(nonNull(oldLine)) {
                int compare = Integer.compare(Integer.parseInt(line), Integer.parseInt(oldLine));
                if(compare > 0) {
                    result++;
                }
            }
            oldLine = line;
        }
        return result;
    }
}
