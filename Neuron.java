import java.lang.Math;
/* Public class to represent a node! */

public class Neuron {
  // Each Neuron has a theta value associated with it.
  private float theta;

  // Each Neuron has synapses coming in and going out.
  // The number of vectors in is equal to the number of Neurons in the layer
  private int neuronsInLayer;
  private float[] synapseIn;
  private float[] synapseOut;

  // Each Neuron also has a Y value that is associated with it, it changes based on the iteration
  private double yvalue;

  // constructor -- initializes every member variable to default values
  public Neuron(int newNeuronsInLayer) {
    yvalue = 0;
    theta = 0;
    neuronsInLayer = newNeuronsInLayer;
    synapseIn = new float[neuronsInLayer];
    synapseOut = new float[neuronsInLayer]; // used for input only at this time
    for (int i = 0; i < neuronsInLayer; i++) {
      synapseIn[i] = 0;
      synapseOut[i] = 0;
    }
  } // end constructor

  //helper sigmoid
  private double sigmoid(double x) {
    return ( 1 / ( 1 + Math.pow(Math.E, (-1*x))));
  } // end helper
  public double calculateY(int iteration) { // NOTE THIS NOT FINISHED THIS IS ONLY FOR A SAMPLE ITERATION
    double x = 0;
    for (int i = 0; i < synapseIn.length; i++) { // adding up (x)(w1) + (x)(w2) ... etc
      x += (1) * synapseIn[i];
    } // end for

    x = x - ((1) * theta); // subtracting theta
    x = sigmoid(x); // pass this to sigmoid
    return x;
  } // end calculateY()
  public void setYvalue(double y) {
    yvalue = y; //-------------------------------maybe just make this ^^^^^^^^
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
  public void setTheta(float newTheta) {
    theta = newTheta;
  } // end setTheta()

  // setter for number of neurons in this neuron's layer
  public void setNeuronsInLayer(int newNum) {
    neuronsInLayer = newNum;
  } // end setNeuronsInLayer()

  // setter for the weights coming into the neuron
  public void setSynapseIn(int index, float weight) {
    synapseIn[index] = weight;
  }

  // setter for the weights that are leaving the neuron
  public void setSynapseOut(int index, float weight) {
    synapseOut[index] = weight;
  } // end setSynapseOut()

  // getter for theta
  public float getTheta() {return theta;}

} // end Neuron