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
    switch (iteration) {
      case 1:
        /*
        inputs    output
        1   1     0
        */
        inputNeurons[0].setYvalue(1);
        inputNeurons[1].setYvalue(1);
        break;
      case 2:
        /*
        inputs    output
        1   0     1
        */
        inputNeurons[0].setYvalue(1);
        inputNeurons[1].setYvalue(0);
        break;
      case 3:
        /*
        inputs    output
        0   1     1
        */
        inputNeurons[0].setYvalue(0);
        inputNeurons[1].setYvalue(1);
        break;
      case 4:
        /*
        inputs    output
        0   0     0
        */
        inputNeurons[0].setYvalue(0);
        inputNeurons[1].setYvalue(0);
        break;
      default:
        System.out.println("error looping through iterations");
    }

    // first hidden layer

    // current input for current iteration
    double input1 = inputNeurons[0].getYvalue(); // initial input from table
    double input2 = inputNeurons[1].getYvalue(); // inititial input from table

    // for first neuron in hidden
    double w00 = inputNeurons[0].getSynapseOut(0); // input1's weight to this neuron
    double w10 = inputNeurons[1].getSynapseOut(0); // input2's weight to this neuron

    // calculate y for the first hidden neuron
    hiddenNeurons[0][0].setYvalue(hiddenNeurons[0][0].calculateY(input1, w00, input2, w10, hiddenNeurons[0][0].getTheta()));

    // for second neuron in hidden
    double w01 = inputNeurons[0].getSynapseOut(1); // input1's weight to this neuron
    double w11 = inputNeurons[1].getSynapseOut(1); // input2's weight to this neuron

    // calculate y for the second hidden neuron
    hiddenNeurons[0][1].setYvalue(hiddenNeurons[0][1].calculateY(input1, w01, input2, w11, hiddenNeurons[0][1].getTheta()));
    
    /*
    // calculate Y for every hidden neuron
    for (int i = 0; i < numOfHiddenLayers; i++) {
      for (int j = 0; j < numOfHiddenNeurons; j++) {
        // if first hidden layer
        if (i == 0) {
          // current input for current iteration
          double input1 = inputNeurons[0].getYvalue(); // initial input from table
          double input2 = inputNeurons[1].getYvalue(); // inititial input from table
        } // end if
          // for first neuron in hidden
          double w00 = inputNeurons[0].getSynapseOut(0); // input1's weight to this neuron
          double w10 = inputNeurons[1].getSynapseOut(0); // input2's weight to this neuron

          hiddenNeuron[i][j].calculateY(input1, w00, input2, w01, hiddenNeuron[i][j].getTheta());

          // for second neuron in hidden
          double w01 = inputNeurons[0].getSynapseOut(1); // input1's weight to this neuron
          double w11 = inputNeurons[1].getSynapseOut(1); // input2's weight to this neuron
          // for each neuron in this hidden layer
            // receive each input neuron
              // input neuron will have the INPUT and the WEIGHT corresponding to this current hidden neuron
            // calculate the activiation function for this current neuron
            // save result this neuron
          // end loop
        // end if
        // else (if not first hidden layer)
          // for each neuron in this hidden layer
            // recieve each neuron in previous hidden layer
              // incoming neuron will have the YVALUE and the WEIGHT corresponding to this current hidden neuron
            // calculate the activation function for this current neuron
          // save the result to this neuron
        // end loop
      // end else
        //hiddenNeurons[i][j].setYvalue(hiddenNeurons[i][j].calculateY(iteration));
      } // end for
    }// end for */

    // calculate Y for every output neuron
    for (int i = 0; i < numOfOutputNeurons; i++) {
      //outputNeurons[i].setYvalue(outputNeurons[i].calculateY(iteration));
    }// end for

    // calculate error for the output neurons
    // lol okay.



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
