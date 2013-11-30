package holdsanalyzer;

import weka.classifiers.*;
import weka.classifiers.trees.*;
import weka.core.*;
import weka.core.converters.ConverterUtils.DataSource;
import java.util.ArrayList;
import java.util.Random;

public class M5P_Tester {

    public static String MODEL; 
    public Instances sampleData;

    public M5P_Tester() {

        MODEL = "m5pexamplemodel.save";

        DataSource source = null;
        try {
            source = new DataSource("sampleData.arff");
            //You can also load directly from a CSV file, but you will get some
            //errors related to the JDBC library if you don't add it. 
            //It is very easy to add metadata to a CSV file to make it into ARFF:
            //http://weka.wikispaces.com/ARFF+(stable+version)
            sampleData = source.getDataSet();
            sampleData.setClassIndex(3);
            //the class index means the attribute you are predicting, in this case the 
            //4th column of the csv-like ARFF file
        } catch (Exception e) {
            System.out.println("Something seems to be wrong with your data file.");
            System.out.println(e);
        }

    }

    public void train() throws Exception {

        M5P cl = new M5P();
        cl.buildClassifier(sampleData);

        //Save model
        ArrayList al = new ArrayList();
        al.add(cl);
        al.add(new Instances(sampleData, 0));
        SerializationHelper.write(MODEL, al);

    }

    public void check() throws Exception {

        ArrayList al = (ArrayList) SerializationHelper.read(MODEL);
        Classifier cl = (Classifier) al.get(0);

        Evaluation eval = new Evaluation(sampleData);
        eval.crossValidateModel(cl, sampleData, 10, new Random(1));
        System.out.println(eval.toSummaryString());
    }

    public static void main(String[] args) throws Exception {
        M5P_Tester mt = new M5P_Tester();
        mt.train();
        mt.check();
    }
}
