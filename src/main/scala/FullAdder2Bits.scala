import chisel3._

class FullAdder2Bits extends Module {
  val io = IO(new Bundle {
    val a = Vec(2, Input(UInt(1.W)))
    val b = Vec(2, Input(UInt(1.W)))
    val cin = Input(UInt(1.W))
    val cout = Output(UInt(1.W))
    val sum = Vec(2, Output(UInt(1.W)))
  })

  val a = Module(new FullAdder())
  a.io.a := io.a(0)
  a.io.b := io.b(0)
  a.io.cin := io.cin
  io.sum(0) := a.io.sum

  val b = Module(new FullAdder())
  b.io.a := io.a(1)
  b.io.b := io.b(1)
  b.io.cin := a.io.cout
  io.sum(1) := b.io.sum
  io.cout := b.io.cout
}

object Main extends App {
//  println((new chisel3.stage.ChiselStage).emitFirrtl(new FullAdderNBits(8)))
//  println((new chisel3.stage.ChiselStage).emitVerilog(new FullAdderNBits(8)))
  (new chisel3.stage.ChiselStage).emitFirrtl(new Risc)
//  (new chisel3.stage.ChiselStage).emitFirrtl(new Timmer)
//  (new chisel3.stage.ChiselStage).emitVerilog(new Timmer)
}
