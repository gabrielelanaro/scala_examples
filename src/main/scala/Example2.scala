object Example2 {
    def exec(args: Array[String]) = {
        println("Example 2 make a nn")
        val input: Layer = new Layer(4)
        val hidden: Layer = new Layer(2)
        val output: Layer = new Layer(2)

        val nn: Network = new Network(input, hidden, output)

        nn.predict(Array(0, 0, 0, 0))

        println(Array(0, 0))
    }
}

// class NDArray(dimensions: (Int, Int))

class Network(layers: Layer*) {
    // We ned a set of weights for each layer except the input one
    val weights: List[Array[Array[Double]]] = layers
                               .sliding(2).toList
                               .map(v => (v(0).size, v(1).size))
                               .map( dim => {
                                   Array.ofDim[Double](dim._1, dim._2)
                               })
        
    def predict(input: Array[Double]): Array[Double] = {
        var intermediate: Array[Double] = input
        // Head tail, init last
        for ((layer, i) <- layers.tail.zipWithIndex) {
            val activations = layer.activate(intermediate)
            println(intermediate)
            // intermediate = activations * weights(i)
        }
        Array(0, 0)
  }
}

class Layer(size: Int) {
    val neurons: List[Neuron] = for (i <- List.range(0, size)) yield new Neuron(0.0)

    def activate(input: Array[Double]): Array[Double] = {
      (neurons, input).zipped map(_ activate _) toArray
    }
    
    def size(): Int = neurons.size
    
}

class Neuron (val activation: Double) {
  override def toString() = {
      s"Neuron(${activation})"
  }

  def activate(input: Double): Double = {
    input * activation
  }
}
