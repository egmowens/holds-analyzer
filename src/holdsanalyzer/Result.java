package holdsanalyzer;

public class Result {
    
    String algorithmName;
    Double correlationCoefficient;
    Double rootRelativeSquaredError;
    
    public Result(String s,Double c,Double r) {
        algorithmName = s;
        correlationCoefficient = c;
        rootRelativeSquaredError = r;
    }
    
}


