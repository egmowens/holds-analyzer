holds-analyzer
==============

Code to use the Weka libraries to analyze library hold times

In this project, Nathan Cosgray extracted and process data on the processing of holds from our ILS and I used it to create prediction model for when library patrons would receive their holds. Together, we analyzed the implications of holds processing for our library service levels. Our LITA Forum presentation, which provides some context, can be found here: http://connect.ala.org/node/213947

It's possible to do what I did with the [Weka](http://www.cs.waikato.ac.nz/ml/weka/) GUI, but it's more shareable this way. This is just some demo code that allows you to test out the algorithms and learn a little about how to use Weka.

* Preliminarily: You need the weka.jar file to make this work! For larger data sets you'll need to increase memory allocation to Java with the -Xmx flag.

* *sampleData.arff* is some properly formatted data to try. I included about 500 sample instances with three independent variables, namely the patron's initial queue position, the minimum number of copies over the life of the hold, and the maximum number of copies. The fourth column, the number of days left until the patron receive the item, is the dependent variable we're trying to predict.

Note: the results with 500 instances will be pretty bad; in our real work we used 2.2 to 3.5 million instances!

* *AlgorithmComparer* loads the data and processes it with five of Weka's algorithms that are applicable. Then it evaluates each one and reports on them in order of the correlation coefficient.

* *M5P_Tester* shows a bit more, using the M5Prime algorithm, which was consistently the most accurate for our data. It saves the model to be reloaded later and reports more detailed statistics on its success.

This code is basic and doesn't go into the analysis surrounding our number crunching. If this isn't enough to work with yet, we are still working on the project and will publish more details once we've amassed more year-over-year data, i.e. after Spring 2014.
