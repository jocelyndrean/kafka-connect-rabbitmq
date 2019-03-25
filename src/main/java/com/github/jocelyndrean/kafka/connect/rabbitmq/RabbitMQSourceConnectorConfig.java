/**
 * Copyright © 2019 Jocelyn DREAN (jocelyndrean) and Jeremy Custenborder (jcustenborder)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jocelyndrean.kafka.connect.rabbitmq;

import com.github.jcustenborder.kafka.connect.utils.template.StructTemplate;
import org.apache.kafka.common.config.ConfigDef;

import java.util.Map;

class RabbitMQSourceConnectorConfig extends RabbitMQConnectorConfig {

  static final String KAFKA_TOPIC_TEMPLATE = "kafkaTopicTemplate";
  public static final String TOPIC_CONF = "kafka.topic";
  static final String TOPIC_DOC = "Kafka topic to write the messages to.";

  public static final String EXCHANGE_CONF = "rabbitmq.exchange";
  static final String EXCHANGE_DOC = "The RabbitMQ exchange you want to bind";

  public static final String ROUTING_KEY_CONF = "rabbitmq.routing.key";
  static final String ROUTING_KEY_DOC = "The routing key is a message attribute. The exchange might look at this key when deciding how to route the message to queues (depending on exchange type). When a queue is bound with “#” (hash) binding key, it will receive all messages, regardless of the routing key";

  public static final String QUEUE_DURABLE_CONF = "rabbitmq.queue.durable";
  static final String QUEUE_DURABLE_DOC = "true if we are declaring a durable queue (the queue will survive a server restart)";

  public static final String QUEUE_EXCLUSIVE_CONF = "rabbitmq.queue.exclusive";
  static final String QUEUE_EXCLUSIVE_DOC = "true if we are declaring an exclusive queue (restricted to this connection)";

  public static final String QUEUE_AUTODELETE_CONF = "rabbitmq.queue.autodelete";
  static final String QUEUE_AUTODELETE_DOC = "true if we are declaring an autodelete queue (server will delete it when no longer in use)";

  public static final String PREFETCH_COUNT_CONF = "rabbitmq.prefetch.count";
  static final String PREFETCH_COUNT_DOC = "Maximum number of messages that the server will deliver, 0 if unlimited. " +
      "See `Channel.basicQos(int, boolean) <https://www.rabbitmq.com/releases/rabbitmq-java-client/current-javadoc/com/rabbitmq/client/Channel.html#basicQos-int-boolean->`_";

  public static final String PREFETCH_GLOBAL_CONF = "rabbitmq.prefetch.global";
  static final String PREFETCH_GLOBAL_DOC = "True if the settings should be applied to the entire channel rather " +
      "than each consumer. " +
      "See `Channel.basicQos(int, boolean) <https://www.rabbitmq.com/releases/rabbitmq-java-client/current-javadoc/com/rabbitmq/client/Channel.html#basicQos-int-boolean->`_";

  public final StructTemplate kafkaTopic;
  public final String exchange;
  public final String routingKey;
  public final int prefetchCount;
  public final boolean prefetchGlobal;
  public final boolean durable;
  public final boolean exclusive;
  public final boolean autoDelete;

  public RabbitMQSourceConnectorConfig(Map<String, String> settings) {
    super(config(), settings);

    final String kafkaTopicFormat = this.getString(TOPIC_CONF);
    this.kafkaTopic = new StructTemplate();
    this.kafkaTopic.addTemplate(KAFKA_TOPIC_TEMPLATE, kafkaTopicFormat);
    this.exchange = this.getString(EXCHANGE_CONF);
    this.routingKey = this.getString(ROUTING_KEY_CONF);
    this.prefetchCount = this.getInt(PREFETCH_COUNT_CONF);
    this.prefetchGlobal = this.getBoolean(PREFETCH_GLOBAL_CONF);
    this.durable = this.getBoolean(QUEUE_DURABLE_CONF);
    this.exclusive = this.getBoolean(QUEUE_EXCLUSIVE_CONF);
    this.autoDelete = this.getBoolean(QUEUE_AUTODELETE_CONF);
  }

  public static ConfigDef config() {
    return RabbitMQConnectorConfig.config()
        .define(TOPIC_CONF, ConfigDef.Type.STRING, ConfigDef.Importance.HIGH, TOPIC_DOC)
        .define(PREFETCH_COUNT_CONF, ConfigDef.Type.INT, 0, ConfigDef.Importance.MEDIUM, PREFETCH_COUNT_DOC)
        .define(PREFETCH_GLOBAL_CONF, ConfigDef.Type.BOOLEAN, false, ConfigDef.Importance.MEDIUM, PREFETCH_GLOBAL_DOC)
        .define(EXCHANGE_CONF, ConfigDef.Type.STRING, ConfigDef.Importance.HIGH, EXCHANGE_DOC)
        .define(ROUTING_KEY_CONF, ConfigDef.Type.STRING, "#", ConfigDef.Importance.HIGH, ROUTING_KEY_DOC)
        .define(QUEUE_DURABLE_CONF, ConfigDef.Type.BOOLEAN, false, ConfigDef.Importance.HIGH, QUEUE_DURABLE_DOC)
        .define(QUEUE_EXCLUSIVE_CONF, ConfigDef.Type.BOOLEAN, true, ConfigDef.Importance.HIGH, QUEUE_EXCLUSIVE_DOC)
        .define(QUEUE_AUTODELETE_CONF, ConfigDef.Type.BOOLEAN, true, ConfigDef.Importance.HIGH, QUEUE_AUTODELETE_DOC);
  }
}
