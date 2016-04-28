import java.util.Scanner;
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

    // this is simulating the first iteration
    // therefore:
    int iteration = 1;

    // initializes the Neurons to match our assignment.
    // actually the weights and thresholds (?) need to be random.
    // i will make them random after I am positive each calculation is correct.
    inputNeurons[0].setSynapseOut(0, 0.5f);
    inputNeurons[0].setSynapseOut(1, 0.9f); // Neuron 1 created.

    inputNeurons[1].setSynapseOut(0, 0.4f);
    inputNeurons[1].setSynapseOut(1, 1.0f); // Neuron 2 created.

    hiddenNeurons[0][0].setSynapseIn(0, 0.5f);
    hiddenNeurons[0][0].setSynapseIn(1, 0.4f);
    hiddenNeurons[0][0].setTheta(0.8f); // Neuron 3 created.

    hiddenNeurons[0][1].setSynapseIn(0, 0.9f);
    hiddenNeurons[0][1].setSynapseIn(1, 1.0f);
    hiddenNeurons[0][1].setTheta(-0.1f); // Neuron 4 created.

    hiddenNeurons[1][0].setSynapseIn(0, 0.7f);
    hiddenNeurons[1][0].setSynapseIn(1, -0.9f);
    hiddenNeurons[1][0].setTheta(-0.6f); // Neuron 5 created.

    hiddenNeurons[1][1].setSynapseIn(0, 1.2f);
    hiddenNeurons[1][1].setSynapseIn(1, -0.3f);
    hiddenNeurons[1][1].setTheta(-1.0f); // Neuron 6 created

    outputNeurons[0].setSynapseIn(0, -1.2f);
    outputNeurons[0].setSynapseIn(1, -1.1f);
    outputNeurons[0].setTheta(0.3f); // Neuron 7 created.


    // calculate Y for every hidden neuron
    for (int i = 0; i < numOfHiddenLayers; i++) {
      for (int j = 0; j < numOfHiddenNeurons; j++) {
        // if first hidden layer
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
        hiddenNeurons[i][j].setYvalue(hiddenNeurons[i][j].calculateY(iteration));
      } // end for
    }// end for

    // calculate Y for every output neuron
    for (int i = 0; i < numOfOutputNeurons; i++) {
      outputNeurons[i].setYvalue(outputNeurons[i].calculateY(iteration));
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
