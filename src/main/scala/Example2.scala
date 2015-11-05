object Example2 {
    def exec(args: Array[String]) = {
        println("Example 2 make a nn")
        val input: Layer = new Layer(4)
        val hidden: Layer = new Layer(2)
        val output: Layer = new Layer(2)
        
        val nn: Network = new Network(input, hidden, output)
        
        Array(0, 0, 0, 0)
        Array(0, 0)
        
        println(input)
    } 
}

class Network(layers: Layer*)

class Layer(size: Int) {
    val list: List[Neuron] = for (i <- List.range(0, size)) yield new Neuron(0.0)
}

class Neuron (weight: Double) {
    override def toString() = {
        s"Neuron(${weight})"
    }
}
