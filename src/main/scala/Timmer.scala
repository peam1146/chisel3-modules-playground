import chisel3._

class Timmer extends Module{
  val io = IO(new Bundle{
    val clock = Input(Clock())
    val output = Output(UInt(2.W))
  })

  val counter = RegInit(0.U(2.W))

  withClock(io.clock){
    counter := counter + 1.U
  }

  io.output := counter
}
