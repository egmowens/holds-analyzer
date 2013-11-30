package holdsanalyzer;
import java.util.Comparator;

public class CorrelComparator implements Comparator<Result> {
    @Override
    public int compare(Result a, Result b) {
        return b.correlationCoefficient.compareTo(a.correlationCoefficient);
    }
}
    
