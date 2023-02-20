import chisel3._

 class FullAdder extends Module {
  val io = IO(new Bundle {
    val a = Input(UInt(1.W))
    val b = Input(UInt(1.W))
    val cin = Input(UInt(1.W))
    val cout = Output(UInt(1.W))
    val sum = Output(UInt(1.W))
  })

  val a_xor_b = io.a ^ io.b
  io.sum := a_xor_b ^ io.cin

  val a_and_b = io.a & io.b
  val a_xor_b_and_cin = a_xor_b & io.cin
  io.cout := a_and_b | a_xor_b_and_cin
}

//object Main extends App {
//  println((new chisel3.stage.ChiselStage).emitFirrtl(new FullAdder))
//}
