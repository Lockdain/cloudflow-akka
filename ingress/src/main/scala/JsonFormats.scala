import ru.neoflex.cloudflow.training.vtb.{Name, Payment}

object JsonFormats {
  import spray.json.{DefaultJsonProtocol, DeserializationException, JsNumber, JsObject, JsString, JsValue, RootJsonFormat}

  object PaymentJsonProtocol extends DefaultJsonProtocol {

    implicit object PaymentJsonFormat extends RootJsonFormat[Name] {
      def write(p: Name) = JsObject(
        "name"  -> JsString(p.name),
      )

      def read(value: JsValue) = {
        value.asJsObject.getFields("name") match {
          case Seq(JsString(name)) ⇒
            Name(
              name
            )
          case _ ⇒ throw new DeserializationException("Name expected")
        }
      }
    }

  }

}
