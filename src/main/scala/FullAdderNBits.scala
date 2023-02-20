import chisel3._
class FullAdderNBits(bits: Int) extends Module{
  val io = IO(new Bundle {
    val a = Vec(bits, Input(UInt(1.W)))
    val b = Vec(bits, Input(UInt(1.W)))
    val cin = Input(UInt(1.W))
    val cout = Output(UInt(1.W))
    val sum = Vec(bits, Output(UInt(1.W)))
  })

  // vector of full adders

  val fullAdders = VecInit(Seq.fill(bits)(Module(new FullAdder()).io))
  fullAdders(0).a := io.a(0)
  fullAdders(0).b := io.b(0)
  fullAdders(0).cin := io.cin
  io.sum(0) := fullAdders(0).sum

  for (i <- 1 until bits) {
    fullAdders(i).a := io.a(i)
    fullAdders(i).b := io.b(i)
    fullAdders(i).cin := fullAdders(i-1).cout
    io.sum(i) := fullAdders(i).sum
  }
  io.cout := fullAdders(bits-1).cout
}
