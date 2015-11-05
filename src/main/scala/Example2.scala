object Example2 {
    def exec(args: Array[String]) = {
        println("Example 2 make a nn")
        val input: Layer = new Layer(4)
        val hidden: Layer = new Layer(2)
        val output: Layer = new Layer(2)

        val nn: Network = new Network(input, hidden, output)

        nn.predict(Array(0, 0, 0, 0))

        println(input)
    }
}

class Network(layers: Layer*) {
  def predict(input: Array[Double]): Array[Double] = {
    var intermediate = input
    for (layer <- layers) {
      val activations = layer.activate(intermediate)
      // weights = layer.weights
      // intermediate = (activations zip weights)
    }
    Array(0, 0)
  }
}

class Layer(size: Int) {
    val neurons: List[Neuron] = for (i <- List.range(0, size)) yield new Neuron(0.0)
    val weights = neurons.map(_.weigth)

    def activate(input: Array[Double]): Array[Double] = {
      (neurons, input).zipped map(_ activate _) toArray
    }
}

class Neuron (weight: Double) {
  val weight = weight
  override def toString() = {
      s"Neuron(${weight})"
  }

  def activate(input: Double): Double = {
    input
  }
}
