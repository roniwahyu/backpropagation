import java.util.Scanner;
import java.util.Random;
/*
this is a testing class for all my poopies

*/
public class Test {
  // helper
  public static int getAnInteger(Scanner scanner, String prompt) {
     int input;
     System.out.print (prompt);
     do {
       	try {
          input = Integer.parseInt(scanner.nextLine());
          return Math.abs(input);
        }
        catch (NumberFormatException e) {
          System.out.print ("Please enter an integer > ");
        }
      } while (true);
    } // end getAnInteger()

    public static double randomWeight(Random r) {
      return -1.2 + (1.2 - -1.2) * r.nextDouble();
    }

    public static double randomTheta(Random r) {
      return -1 + (1 - -1) * r.nextDouble();
    }

  public static void main(String[] args) {

    // rate of learning
    double alpha = 0.1;

    // information for building network
    int numOfInputNeurons = 2;
    int numOfOutputNeurons = 1;
    int numOfHiddenNeurons = 2;
    int numOfHiddenLayers = 2;

    // arrays of neurons for each layer
    Neuron[] inputNeurons = new Neuron[numOfInputNeurons];
    Neuron[][] hiddenNeurons = new Neuron[numOfHiddenLayers][numOfHiddenNeurons];
    Neuron[] outputNeurons = new Neuron[numOfOutputNeurons];

    // create input layer
    for (int i = 0; i < numOfInputNeurons; i++) {
      inputNeurons[i] = new Neuron(numOfHiddenNeurons);
    } // end for

    //create hidden layers
    for (int i = 0; i < numOfHiddenLayers; i++) {
      for (int j = 0; j < numOfHiddenNeurons; j++) {
        hiddenNeurons[i][j] = new Neuron(numOfHiddenNeurons);
      } // end for
    } // end for

    //create output layer
    for (int i = 0; i < numOfOutputNeurons; i++) {
      outputNeurons[i] = new Neuron(numOfHiddenNeurons);
    }

    // make random weights and thetas
    Random r = new Random();
    System.out.println("DEBUG: Random weight: " + randomWeight(r)); // debug test
    System.out.println("DEBUG: Random theta: " + randomTheta(r));

    // setting up the first iteration

    // first neuron of input layer
    inputNeurons[0].setSynapseOut(0, randomWeight(r));
    inputNeurons[0].setSynapseOut(1, randomWeight(r));

    // second neuron of input layer
    inputNeurons[1].setSynapseOut(0, randomWeight(r));
    inputNeurons[1].setSynapseOut(1, randomWeight(r));

    // FIRST HIDDEN LAYER
    // first neuron of first hidden layer
    hiddenNeurons[0][0].setSynapseIn(0, inputNeurons[0].getSynapseOut(0)); // weight coming in is weight out of the input neurons
    hiddenNeurons[0][0].setSynapseIn(1, inputNeurons[1].getSynapseOut(0));
    hiddenNeurons[0][0].setSynapseOut(0, randomWeight(r)); // set weight out to random
    hiddenNeurons[0][0].setSynapseOut(1, randomWeight(r));
    hiddenNeurons[0][0].setTheta(randomTheta(r));

    // second neuron of first hidden layer
    hiddenNeurons[0][1].setSynapseIn(0, inputNeurons[0].getSynapseOut(1)); // weight coming in is weight out of input neurons
    hiddenNeurons[0][1].setSynapseIn(1, inputNeurons[1].getSynapseOut(1));
    hiddenNeurons[0][1].setSynapseOut(0, randomWeight(r)); // set weight out to random
    hiddenNeurons[0][1].setSynapseOut(1, randomWeight(r));
    hiddenNeurons[0][1].setTheta(randomTheta(r));

    // SECOND HIDDEN LAYER
    // first neuron of second hidden layer
    hiddenNeurons[1][0].setSynapseIn(0, hiddenNeurons[0][0].getSynapseOut(0));
    hiddenNeurons[1][0].setSynapseIn(1, hiddenNeurons[0][1].getSynapseOut(0));
    hiddenNeurons[1][0].setSynapseOut(0, randomWeight(r)); // only one synapse out, because only one output neuron
    hiddenNeurons[1][0].setTheta(randomTheta(r));

    // second neuron of second hidden layer
    hiddenNeurons[1][1].setSynapseIn(0, hiddenNeurons[0][0].getSynapseOut(1));
    hiddenNeurons[1][1].setSynapseIn(1, hiddenNeurons[0][1].getSynapseOut(1));
    hiddenNeurons[1][1].setSynapseOut(0, randomWeight(r)); // only one synapse out, because only one output neuron
    hiddenNeurons[1][1].setTheta(randomTheta(r));

    // only neuron of output layer
    outputNeurons[0].setSynapseIn(0, hiddenNeurons[1][0].getSynapseOut(0));
    outputNeurons[0].setSynapseIn(1, hiddenNeurons[1][1].getSynapseOut(0));
    outputNeurons[0].setTheta(randomTheta(r));

    // now setting up iterations
    int iteration = 1;
    double desiredOutput;
    double outputError;
    switch (iteration) {
      case 1:
        /*
        inputs    output
        1   1     0
        */
        inputNeurons[0].setYvalue(1);
        inputNeurons[1].setYvalue(1);
        desiredOutput = 0;
        break;
      case 2:
        /*
        inputs    output
        1   0     1
        */
        inputNeurons[0].setYvalue(1);
        inputNeurons[1].setYvalue(0);
        desiredOutput = 1;
        break;
      case 3:
        /*
        inputs    output
        0   1     1
        */
        inputNeurons[0].setYvalue(0);
        inputNeurons[1].setYvalue(1);
        desiredOutput = 1;
        break;
      case 4:
        /*
        inputs    output
        0   0     0
        */
        inputNeurons[0].setYvalue(0);
        inputNeurons[1].setYvalue(0);
        desiredOutput = 0;
        break;
      default:
        desiredOutput = 100; // hahahhahahaha
        System.out.println("error looping through iterations");
    }

    // ---------------------------ACTIVATION FUNCTIONS-------------------------------------------------
    // first hidden layer
    // current input for current iteration
    double input1 = inputNeurons[0].getYvalue(); // initial input from table
    double input2 = inputNeurons[1].getYvalue(); // inititial input from
    // for first neuron in hidden
    double w00 = inputNeurons[0].getSynapseOut(0); // input1's weight to this neuron
    double w10 = inputNeurons[1].getSynapseOut(0); // input2's weight to this
    // calculate y for the first hidden neuron and sets it
    hiddenNeurons[0][0].setYvalue(hiddenNeurons[0][0].calculateY(input1, w00, input2, w10, hiddenNeurons[0][0].getTheta()));
    // for second neuron in hidden
    double w01 = inputNeurons[0].getSynapseOut(1); // input1's weight to this neuron
    double w11 = inputNeurons[1].getSynapseOut(1); // input2's weight to this
    // calculate y for the second hidden neuron
    hiddenNeurons[0][1].setYvalue(hiddenNeurons[0][1].calculateY(input1, w01, input2, w11, hiddenNeurons[0][1].getTheta()));
    // second layer
    input1 = hiddenNeurons[0][0].getYvalue(); // "input" is just used again
    input2 = hiddenNeurons[0][1].getYvalue(); // even though the name is misleading, it's obvious what it
    // for first neuron in second hidden
    // weight variables
    w00 = hiddenNeurons[0][0].getSynapseOut(0);
    w10 = hiddenNeurons[0][1].getSynapseOut(0);
    // activation function
    hiddenNeurons[1][0].setYvalue(hiddenNeurons[1][0].calculateY(input1, w00, input2, w10, hiddenNeurons[1][0].getTheta()));
    // for the second neuron in second hidden
    // weight variables again
    w01 = hiddenNeurons[0][0].getSynapseOut(1);
    w11 = hiddenNeurons[0][1].getSynapseOut(1);
    // activation funciton
    hiddenNeurons[1][1].setYvalue(hiddenNeurons[1][1].calculateY(input1, w01, input2, w11, hiddenNeurons[1][1].getTheta()));
    // output layer only one neuron
    input1 = hiddenNeurons[1][0].getYvalue(); // "input" is just used again
    input2 = hiddenNeurons[1][1].getYvalue(); // even though the name is misleading, it's obvious what it is
    // weight variables
    w00 = hiddenNeurons[1][0].getSynapseOut(0);
    w10 = hiddenNeurons[1][1].getSynapseOut(0);
    // activation function
    outputNeurons[0].setYvalue(outputNeurons[0].calculateY(input1, w00, input2, w10, outputNeurons[0].getTheta()));


    // ---------------------------------CALCULATE OUTPUT ERROR-----------------------------------------------------
    // calculated the error as Desired - Actual
    outputError = desiredOutput - outputNeurons[0].getYvalue();
    System.out.println("DEBUG: outputError = " + outputError);



    /*

    Now we go backwards

    */



    //------------------------------------DETERMINE CHANGES IN WEIGHTS----------------------------------------------------
    double gradient;
    double yval;

    // calculate gradient
    // gradient of outputNeuron[0] is Yvalue(1-Yvalue)outputError
    // calculate gradient of outputNeuron[0]
    yval = outputNeurons[0].getYvalue();
    gradient = yval * (1 - yval) * outputError;
    outputNeurons[0].setGradient(gradient);
    // calculate gradients of second hidden layer
    // first neuron
    yval = hiddenNeurons[1][0].getYvalue();
    gradient = yval * (1 - yval) * outputNeurons[0].getGradient() * hiddenNeurons[1][0].getSynapseOut(0);
    hiddenNeurons[1][0].setGradient(gradient);
    // second neuron
    yval = hiddenNeurons[1][1].getYvalue();
    gradient = yval * (1 - yval) * outputNeurons[0].getGradient() * hiddenNeurons[1][1].getSynapseOut(0);
    hiddenNeurons[1][1].setGradient(gradient);
    // calculate gradients of first hidden layer
    // first neuron
    yval = hiddenNeurons[0][0].getYvalue();
    gradient = (yval * (1 - yval)) * hiddenNeurons[1][0].getGradient() * hiddenNeurons[0][0].getSynapseOut(0) * hiddenNeurons[1][1].getGradient() * hiddenNeurons[0][0].getSynapseOut(1);
    hiddenNeurons[0][0].setGradient(gradient);
    // second neuron
    yval = hiddenNeurons[0][1].getYvalue();
    gradient = (yval * (1 - yval)) * hiddenNeurons[1][0].getGradient() * hiddenNeurons[0][1].getSynapseOut(0) * hiddenNeurons[1][1].getGradient() * hiddenNeurons[0][1].getSynapseOut(1);
    hiddenNeurons[0][1].setGradient(gradient);







    // second hidden layer weights to output
    double[] deltaWeights = new double[10]; // represents the change in weights

    // calculate deltas

    // this array is confusing and unintuitive
    // deltaWeights[] holds all the deltas so we can update them later on
    // we have to be very careful about the order of this because deltaWeights[9] does not intuitively represent any specific weight.

    // weights going to outputNeuron[0]
    deltaWeights[9] = alpha * hiddenNeurons[1][0].getYvalue() * outputNeurons[0].getGradient();
    deltaWeights[8] = alpha * hiddenNeurons[1][1].getYvalue() * outputNeurons[0].getGradient();
    // weights going to hiddenNeurons[1][0]
    deltaWeights[7] = alpha * hiddenNeurons[0][0].getYvalue() * hiddenNeurons[1][0].getGradient();
    deltaWeights[6] = alpha * hiddenNeurons[0][1].getYvalue() * hiddenNeurons[1][0].getGradient();
    // weights going to hiddenNeurons[1][1]
    deltaWeights[5] = alpha * hiddenNeurons[0][0].getYvalue() * hiddenNeurons[1][1].getGradient();
    deltaWeights[4] = alpha * hiddenNeurons[0][1].getYvalue() * hiddenNeurons[1][1].getGradient();




    System.out.println("***********************************************"); // just for viewing in console easier
    // print all input neurons
    System.out.println("INPUT NEURONS");
    for (int i = 0; i < numOfInputNeurons; i++) {
      System.out.println("Neuron " + (i+1) + " in layer. " + inputNeurons[i]); // literally useless. only thing they have is weight outs
    } // end for

    // print all the hidden neurons
    System.out.println("HIDDEN NEURONS");
    for (int i = 0; i < numOfHiddenLayers; i++) {
      System.out.println("HIDDEN LAYER: " + (i+1));
      for (int j = 0; j < numOfHiddenNeurons; j++) {
        System.out.println("Neuron " + (j+1) + " in layer. " + hiddenNeurons[i][j]);
      } // end for
    }// end for

    // print all output neurons
    System.out.println("OUTPUT NEURONS");
    for (int i = 0; i < numOfOutputNeurons; i++) {
      System.out.println("Neuron " + (i+1) + " in layer. " + outputNeurons[i]);
    } // end for

  } // end main()
} // end Test
