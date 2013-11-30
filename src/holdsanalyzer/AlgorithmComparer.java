package holdsanalyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import weka.classifiers.*;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.meta.RegressionByDiscretization;
import weka.classifiers.rules.ZeroR;
import weka.classifiers.trees.*;
import weka.core.*;
import weka.core.converters.ConverterUtils.DataSource;

public class AlgorithmComparer {

    public Instances sampleData;

    public AlgorithmComparer() {

        DataSource source = null;
        try {
            source = new DataSource("sampleData.arff");
            sampleData = source.getDataSet();
            sampleData.setClassIndex(3);
        } catch (Exception e) {
            System.out.println("Something seems to be wrong with your data file.");
            System.out.println(e);
        }

    }

    public void compare() throws Exception {
        
        ArrayList<Result> results = new ArrayList<Result>();
        
        try {
            
            M5P m5 = new M5P();
            m5.buildClassifier(sampleData);
            Evaluation m5e = new Evaluation(sampleData);
            m5e.crossValidateModel(m5, sampleData, 10, new Random(1));
            results.add(new Result(
                    "M5P",
                    m5e.correlationCoefficient(),
                    m5e.rootRelativeSquaredError()));
            
            
            ZeroR z = new ZeroR();
            z.buildClassifier(sampleData);
            Evaluation ze = new Evaluation(sampleData);
            ze.crossValidateModel(z, sampleData, 10, new Random(1));
            results.add(new Result(
                    "ZeroR",
                    ze.correlationCoefficient(),
                    ze.rootRelativeSquaredError()));
            
            RegressionByDiscretization r = new RegressionByDiscretization();
            r.buildClassifier(sampleData);
            Evaluation re = new Evaluation(sampleData);
            re.crossValidateModel(r, sampleData, 10, new Random(1));
            results.add(new Result(
                    "Regression by discretization",
                    re.correlationCoefficient(),
                    re.rootRelativeSquaredError()));
            
            LinearRegression l = new LinearRegression();
            l.buildClassifier(sampleData);
            Evaluation le = new Evaluation(sampleData);
            le.crossValidateModel(l, sampleData, 10, new Random(1));
            results.add(new Result(
                    "Linear regression",
                    le.correlationCoefficient(),
                    le.rootRelativeSquaredError()));
            
            MultilayerPerceptron mp = new MultilayerPerceptron();
            mp.buildClassifier(sampleData);
            Evaluation mpe = new Evaluation(sampleData);
            mpe.crossValidateModel(mp, sampleData, 10, new Random(1));
            results.add(new Result(
                    "Multilayer perceptron",
                    mpe.correlationCoefficient(),
                    mpe.rootRelativeSquaredError()));
            
            Collections.sort(results, new CorrelComparator());
            System.out.println("Best correlation first");
            System.out.println("ALGORITHM, ROOT RELATIVE SQ ERROR, CORREL COEFF");
            for (Result eachResult: results) {
                System.out.println(eachResult.algorithmName + ", " +
                        eachResult.rootRelativeSquaredError + ", " +
                        eachResult.correlationCoefficient);
            }
            System.out.println("\nAnalyzed " + sampleData.numInstances() +
                    " instances on " + (sampleData.numAttributes()-1) + 
                    " attributes.");

        } catch (Exception e) {
            System.out.println("Could not create model.");
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws Exception {

        AlgorithmComparer experiment = new AlgorithmComparer();
        experiment.compare();
    }
}
