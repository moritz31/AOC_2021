import java.util.ArrayList;
import java.util.List;

public class ThreesumCalculator {

    int calculate(List<Integer> inputs) {
        List<ThreeSum> threeSums = splitIntoThreeSums(inputs);
        return calculateDifference(threeSums);
    }

    List<ThreeSum> splitIntoThreeSums(List<Integer> inputs) {
        List<ThreeSum> threeSums = new ArrayList<>();
        for(int i = 0;i < inputs.size(); i++) {
            if(i+2 < inputs.size()) {
                threeSums.add(new ThreeSum(inputs.get(i), inputs.get(i+1), inputs.get(i+2)));
            }
        }
        return threeSums;
    }

    int calculateDifference(List<ThreeSum> threeSums) {
        int result = 0;
        for(int i = 0; i < threeSums.size()-1;i++) {
            result += threeSums.get(i+1).getSum() > threeSums.get(i).getSum() ? 1 : 0;
        }
        return result;
    }
}
