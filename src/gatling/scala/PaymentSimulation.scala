import ch.qos.logback.classic.LoggerContext
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import java.util.UUID
import scala.concurrent.duration._
import ch.qos.logback.classic.Level

class PaymentSimulation extends Simulation {

  val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]

  context.getLogger("io.gatling").setLevel(Level.WARN)

  val httpProtocol = http
    .baseUrl("http://localhost:8080")
    .contentTypeHeader("application/json")

  val scn = scenario("Payment load test")
    .exec { session =>
      session.set("orderId", UUID.randomUUID().toString)
    }.exec(
      http("Process payment")
        .post("/api/v1/payments")
        .header(HttpHeaderNames.ContentType, "application/json")
        .body(StringBody(s"""{"orderId":"#{orderId}","amount":10}""".stripMargin))
        .check(status.in(200, 201))
    )

  setUp(
    scn.inject(
      rampUsers(10_000).during(5.minutes)
    )
  ).protocols(httpProtocol)
}