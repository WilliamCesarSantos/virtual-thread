import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class PaymentSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8080")
    .contentTypeHeader("application/json")

  val scn = scenario("Payment load test")
    .exec(
      http("Process payment")
        .post("/payments")
        .queryParam("orderId", "ORD-${randomInt(1000000)}")
        .queryParam("amount", "100")
        .check(status.is(200))
    )

  setUp(
    scn.inject(
      rampUsers(10_000).during(30.seconds)
    )
  ).protocols(httpProtocol)
}