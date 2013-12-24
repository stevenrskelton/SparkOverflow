package ca.stevenskelton.sparkoverflow

import com.esotericsoftware.kryo.Kryo

class KyroRegistrator extends org.apache.spark.serializer.KryoRegistrator {
  override def registerClasses(kryo: Kryo) {
    kryo.register(classOf[Post])
  }
}