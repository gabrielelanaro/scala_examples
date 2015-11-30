import org.nd4s.Implicits._
import org.nd4j.linalg.api.ndarray._
import org.nd4j.linalg.factory._
import org.nd4j.linalg.ops.transforms.Transforms
import scala.collection.SortedMap

object Example2 {
    def exec(args: Array[String]) = {
        println("Example 2 make a nn")
        val input: Layer = new Layer(4)
        val hidden: Layer = new Layer(2)
        val output: Layer = new Layer(2)

        val nn: Network = new Network(input, hidden, output)
        nn.predict(Nd4j.create(4))

        //  Generate samples
        val truth = { x : INDArray => x(0->2)}

        for (i <- 1 to 10) {
          val test_input = Nd4j.rand(Array(4))
          nn.train(test_input, truth(test_input))
        }

        println(Nd4j.create(1))
    }
}

class Network(layers: Layer*) {
    // We ned a set of weights for each layer except the input one
    var weights: List[INDArray] = layers.sliding(2).toList.map(v => Nd4j.create(v(0).size, v(1).size))

    def predict(input: INDArray): INDArray = {
        var activations: INDArray = input

        // Head tail, init last
        for ((layer, i) <- layers.tail.zipWithIndex) {
            activations = weights(i) dot activations
            activations = layer.activate(activations)
        }

        activations
  }

    def train(input : INDArray, output : INDArray) = {
      // Forward pass
      var layer_output: INDArray = input

      var inputs: Array[INDArray] = Array[INDArray](input)
      var outputs: Array[INDArray] = Array[INDArray](input)

      // Head tail, init last
      for ((layer, i) <- layers.tail.zipWithIndex) {
          val layer_input = weights(i) dot layer_output
          inputs = inputs :+ layer_input

          layer_output = layer.activate(layer_input)
          outputs = outputs :+ layer_output
      }

      var sqError: INDArray = Transforms.pow(layer_output - output, 2)
      val nlayers = layers.length - 1
      var k = nlayers - 1;
      // From here we backpropagate the error

      // This is the gradient with respect
      val gradError = (layer_output - output)
      val sigma_prime = {x : INDArray => Transforms.exp(-x)/Transforms.pow(Transforms.exp(-x) + 1, 2)}
      val delta: Array[INDArray] = layers.tail.map(l => Nd4j.create(l.size)).toArray
      var grad: Array[INDArray] = weights.map(l => Nd4j.create(0)).toArray

      println("output is of size", outputs.length)
      println("delta is of size", delta.length)
      println(k)

      println(outputs(k))
      delta(k) = gradError * sigma_prime(outputs(k))

      // Now that we have the base case we have to calculate the error in upper layers

      println("Input is of size", inputs.length)
      // println("grad is of size", grad.length)
      println("Layers is of size", nlayers)

      for (k <- (1 to (nlayers - 1)).reverse) {
        println("Layer", k)
        delta(k-1) = (delta(k) dot weights(k)) * sigma_prime(outputs(k))
        grad(k) = delta(k) dot inputs(k)
      }
      // At this point we have the partial derivative with respect to any weight.
      // and this is really cool shit DUDE
      for (g <- grad) {
        println("Gradient", g)
      }

      for (w <- weights) {
        println("Weights", w)
      }
      // Now that we have the gradient we just apply gradient descent
      weights = weights.zipWithIndex.map{case (w, i) => w - grad(i)}

    }
}



class Layer(size: Int) {
    val neurons: List[Neuron] = for (i <- List.range(0, size)) yield new Neuron(0.0)

    def activate(input: INDArray): INDArray = {
      neurons.zipWithIndex.map {case (n, i) => n.activate(input(i))}.asNDArray(neurons.length)
    }

    def size(): Int = neurons.size

}

class Neuron (val activation: Double) {
  override def toString() = {
      s"Neuron(${activation})"
  }

  def activate(input: Double): Double = {
    squash(input * activation)
  }

  def squash(input: Double): Double = {
    1.0/(1.0 + math.exp(-input))
  }
}
