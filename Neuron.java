import java.lang.Math;
/* Public class to represent a node! */

// Hello this is a test for git!

// and another test

public class Neuron {
  // Each Neuron has a theta value associated with it.
  private double theta;

  // Each Neuron has synapses coming in and going out.
  // The number of vectors in is equal to the number of Neurons in the layer
  private int neuronsInLayer;
  private double[] synapseIn;
  private double[] synapseOut;

  // Each Neuron also has a Y value that is associated with it, it changes based on the iteration
  private double yvalue;

  private double gradient;

  // constructor -- initializes every member variable to default values
  public Neuron(int newNeuronsInLayer) {
    yvalue = 0;
    theta = 0;
    neuronsInLayer = newNeuronsInLayer;
    synapseIn = new double[neuronsInLayer];
    synapseOut = new double[neuronsInLayer]; // used for input only at this time
    for (int i = 0; i < neuronsInLayer; i++) {
      synapseIn[i] = 0;
      synapseOut[i] = 0;
    }
    gradient = 0;
  } // end constructor

  public void setGradient(double newGradient) {
    gradient = newGradient;
  }
  public double getGradient() { return gradient; }
  //helper sigmoid
  private double sigmoid(double x) {
    return ( 1 / ( 1 + Math.pow(Math.E, (-1*x))));
  } // end helper
  public double calculateY(double input1, double w1, double input2, double w2, double theta) { // NOTE THIS NOT FINISHED THIS IS ONLY FOR A SAMPLE ITERATION
    double x = 0;
    x = ((input1*w1) + (input2*w2) - theta);
    x = sigmoid(x); // pass this to sigmoid
    return x;
  } // end calculateY()
  public void setYvalue(double y) {
    yvalue = y;
  }
  public double getYvalue() {
    return yvalue;
  }

  // simple toString for ya
  public String toString() {
    String s = "";
    s += "Weights coming in: ";
    for (int i = 0; i < synapseIn.length; i++) {
      s+= synapseIn[i] + ", ";
    }
    s += "Theta: " + theta + ", Y-Value: " + yvalue;
    return s;
  }

  // setter for theta
  public void setTheta(double newTheta) {
    theta = newTheta;
  } // end setTheta()

  // setter for number of neurons in this neuron's layer
  public void setNeuronsInLayer(int newNum) {
    neuronsInLayer = newNum;
  } // end setNeuronsInLayer()

  // setter for the weights coming into the neuron
  public void setSynapseIn(int index, double weight) {
    synapseIn[index] = weight;
  }

  // setter for the weights that are leaving the neuron
  public void setSynapseOut(int index, double weight) {
    synapseOut[index] = weight;
  } // end setSynapseOut()

  // get the weight sent out from a neuron at an index
  public double getSynapseOut(int index) {
    return synapseOut[index];
  } //end getSynapseOut()

  // getter for theta
  public double getTheta() {return theta;}

} // end Neuron
