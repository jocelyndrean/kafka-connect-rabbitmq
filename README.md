# Kafka Connect - RabbitMQ Source Connector
This is a Source Connector for Kafka Connect used to read from a RabbitMQ Exchange (supports routing key) to write date to Kafka topics.
This project is inspired by this existing [project](https://github.com/osterzel/kafka-connect-rabbitmq) and based on previous work from [Jeremy Custenborder](https://github.com/jcustenborder)

## RabbitMQSourceConnector

Source Connector used to read from a RabbitMQ Exchange (supports routing key) to write date to Kafka topics.

### Configuration

| Name                                  | Type    | Importance | Default Value | Validator | Documentation                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| ------------------------------------- | ------- | ---------- | ------------- | --------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| kafka.topic                           | String  | High       |               |           | Kafka topic to write the messages to.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| rabbitmq.exchange                     | String  | High       |               |           | RabbitMQ exchange you want to bind                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| rabbitmq.routing.key                  | String  | High       | #             |           | Routing Key used for publishing the messages                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| rabbitmq.queue.durable                | Boolean | High       | false         |           | true if we are declaring a durable queue (the queue will survive a server restart)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| rabbitmq.queue.exclusive              | Boolean | High       | true          |           | true if we are declaring an exclusive queue (restricted to this connection)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| rabbitmq.queue.autodelete             | Boolean | High       | true          |           | true if we are declaring an autodelete queue (server will delete it when no longer in use)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
| rabbitmq.host                         | String  | High       | localhost     |           | The RabbitMQ host to connect to. See `ConnectionFactory.setHost(java.lang.String) <https://www.rabbitmq.com/releases/rabbitmq-java-client/current-javadoc/com/rabbitmq/client/ConnectionFactory.html#setHost-java.lang.String->`_                                                                                                                                                                                                                                                                                                                                                                                           |
| rabbitmq.password                     | String  | High       | guest         |           | The password to authenticate to RabbitMQ with. See `ConnectionFactory.setPassword(java.lang.String) <https://www.rabbitmq.com/releases/rabbitmq-java-client/current-javadoc/com/rabbitmq/client/ConnectionFactory.html#setPassword-java.lang.String->`_                                                                                                                                                                                                                                                                                                                                                                     |
| rabbitmq.username                     | String  | High       | guest         |           | The username to authenticate to RabbitMQ with. See `ConnectionFactory.setUsername(java.lang.String) <https://www.rabbitmq.com/releases/rabbitmq-java-client/current-javadoc/com/rabbitmq/client/ConnectionFactory.html#setUsername-java.lang.String->`_                                                                                                                                                                                                                                                                                                                                                                     |
| rabbitmq.virtual.host                 | String  | High       | /             |           | The virtual host to use when connecting to the broker. See `ConnectionFactory.setVirtualHost(java.lang.String) <https://www.rabbitmq.com/releases/rabbitmq-java-client/current-javadoc/com/rabbitmq/client/ConnectionFactory.html#setVirtualHost-java.lang.String->`_                                                                                                                                                                                                                                                                                                                                                       |
| rabbitmq.port                         | Int     | Medium     | 5672          |           | The RabbitMQ port to connect to. See `ConnectionFactory.setPort(int) <https://www.rabbitmq.com/releases/rabbitmq-java-client/current-javadoc/com/rabbitmq/client/ConnectionFactory.html#setPort-int->`_                                                                                                                                                                                                                                                                                                                                                                                                                     |
| rabbitmq.prefetch.count               | Int     | Medium     | 0             |           | Maximum number of messages that the server will deliver, 0 if unlimited. See `Channel.basicQos(int, boolean) <https://www.rabbitmq.com/releases/rabbitmq-java-client/current-javadoc/com/rabbitmq/client/Channel.html#basicQos-int-boolean->`_                                                                                                                                                                                                                                                                                                                                                                              |
| rabbitmq.prefetch.global              | Boolean | Medium     | false         |           | True if the settings should be applied to the entire channel rather than each consumer. See `Channel.basicQos(int, boolean) <https://www.rabbitmq.com/releases/rabbitmq-java-client/current-javadoc/com/rabbitmq/client/Channel.html#basicQos-int-boolean->`_                                                                                                                                                                                                                                                                                                                                                               |
| rabbitmq.automatic.recovery.enabled   | Boolean | Low        | true          |           | Enables or disables automatic connection recovery. See `ConnectionFactory.setAutomaticRecoveryEnabled(boolean) <https://www.rabbitmq.com/releases/rabbitmq-java-client/current-javadoc/com/rabbitmq/client/ConnectionFactory.html#setAutomaticRecoveryEnabled-boolean->`_                                                                                                                                                                                                                                                                                                                                                   |
| rabbitmq.connection.timeout.ms        | Int     | Low        | 60000         |           | Connection TCP establishment timeout in milliseconds. zero for infinite. See `ConnectionFactory.setConnectionTimeout(int) <https://www.rabbitmq.com/releases/rabbitmq-java-client/current-javadoc/com/rabbitmq/client/ConnectionFactory.html#setConnectionTimeout-int->`_                                                                                                                                                                                                                                                                                                                                                   |
| rabbitmq.handshake.timeout.ms         | Int     | Low        | 10000         |           | The AMQP0-9-1 protocol handshake timeout, in milliseconds. See `ConnectionFactory.setHandshakeTimeout(int) <https://www.rabbitmq.com/releases/rabbitmq-java-client/current-javadoc/com/rabbitmq/client/ConnectionFactory.html#setHandshakeTimeout-int->`_                                                                                                                                                                                                                                                                                                                                                                   |
| rabbitmq.network.recovery.interval.ms | Int     | Low        | 10000         |           | See `ConnectionFactory.setNetworkRecoveryInterval(long) <https://www.rabbitmq.com/releases/rabbitmq-java-client/current-javadoc/com/rabbitmq/client/ConnectionFactory.html#setNetworkRecoveryInterval-long->`_                                                                                                                                                                                                                                                                                                                                                                                                              |
| rabbitmq.requested.channel.max        | Int     | Low        | 0             |           | Initially requested maximum channel number. Zero for unlimited. See `ConnectionFactory.setRequestedChannelMax(int) <https://www.rabbitmq.com/releases/rabbitmq-java-client/current-javadoc/com/rabbitmq/client/ConnectionFactory.html#setRequestedChannelMax-int->`_                                                                                                                                                                                                                                                                                                                                                        |
| rabbitmq.requested.frame.max          | Int     | Low        | 0             |           | Initially requested maximum frame size, in octets. Zero for unlimited. See `ConnectionFactory.setRequestedFrameMax(int) <https://www.rabbitmq.com/releases/rabbitmq-java-client/current-javadoc/com/rabbitmq/client/ConnectionFactory.html#setRequestedFrameMax-int->`_                                                                                                                                                                                                                                                                                                                                                     |
| rabbitmq.requested.heartbeat.seconds  | Int     | Low        | 60            |           | Set the requested heartbeat timeout. Heartbeat frames will be sent at about 1/2 the timeout interval. If server heartbeat timeout is configured to a non-zero value, this method can only be used to lower the value; otherwise any value provided by the client will be used. See `ConnectionFactory.setRequestedHeartbeat(int) <https://www.rabbitmq.com/releases/rabbitmq-java-client/current-javadoc/com/rabbitmq/client/ConnectionFactory.html#setRequestedHeartbeat-int->`_                                                                                                                                           |
| rabbitmq.shutdown.timeout.ms          | Int     | Low        | 10000         |           | Set the shutdown timeout. This is the amount of time that Consumer implementations have to continue working through deliveries (and other Consumer callbacks) after the connection has closed but before the ConsumerWorkService is torn down. If consumers exceed this timeout then any remaining queued deliveries (and other Consumer callbacks, *including* the Consumer's handleShutdownSignal() invocation) will be lost. See `ConnectionFactory.setShutdownTimeout(int) <https://www.rabbitmq.com/releases/rabbitmq-java-client/current-javadoc/com/rabbitmq/client/ConnectionFactory.html#setShutdownTimeout-int->`_|
| rabbitmq.topology.recovery.enabled    | Boolean | Low        | true          |           | Enables or disables topology recovery. See `ConnectionFactory.setTopologyRecoveryEnabled(boolean) <https://www.rabbitmq.com/releases/rabbitmq-java-client/current-javadoc/com/rabbitmq/client/ConnectionFactory.html#setTopologyRecoveryEnabled-boolean->`_                                                                                                                                                                                                                                                                                                                                                                 |

The RabbitMQ Queue is generated by the connector using `rabbitmq.exchange` + `rabbitmq.routing.key` + "-kafka-exporter".

#### Standalone Example

```properties
name=RabbitMQSourceConnector
tasks.max=1
connector.class=com.github.jocelyndrean.kafka.connect.rabbitmq.RabbitMQSourceConnector
# The following values must be configured.
rabbitmq.topology.recovery.enabled=true
rabbitmq.password=guest
rabbitmq.network.recovery.interval.ms=10000
rabbitmq.username=guest
rabbitmq.exchange=payments
rabbitmq.queue.autodelete=false
rabbitmq.virtual.host=my-vhost
rabbitmq.queue.durable=true
rabbitmq.port=5672
rabbitmq.routing.key=#
rabbitmq.host=localhost
kafka.topic=payments-events-rabbitmq-exporter-test
rabbitmq.queue.exclusive=false
rabbitmq.automatic.recovery.enabled=true
```

#### Distributed Example

```json
{
    "name": "RabbitMQSourceConnector",
    "config": {
        "connector.class": "com.github.jocelyndrean.kafka.connect.rabbitmq.RabbitMQSourceConnector",
        "rabbitmq.topology.recovery.enabled": "true",
        "tasks.max": "1",
        "rabbitmq.password": "guest",
        "rabbitmq.network.recovery.interval.ms": "10000",
        "rabbitmq.username": "guest",
        "rabbitmq.exchange": "payments",
        "rabbitmq.queue.autodelete": "false",
        "rabbitmq.virtual.host": "my-vhost",
        "rabbitmq.queue.durable": "true",
        "rabbitmq.port": "5672",
        "rabbitmq.routing.key": "#",
        "rabbitmq.host": "localhost",
        "kafka.topic": "payments-events-rabbitmq-exporter-test",
        "rabbitmq.queue.exclusive": "false",
        "rabbitmq.automatic.recovery.enabled": "true"
    }
}
```


# Transformations

## ExtractHeader(Key)

This transformation is used to extract a header from the message and use it as a key.

### Configuration

| Name        | Type   | Importance | Default Value | Validator | Documentation|
| ----------- | ------ | ---------- | ------------- | --------- | -------------|
| header.name | String | High       |               |           | Header name. |


#### Standalone Example

```properties
transforms=Key
transforms.Key.type=com.github.jocelyndrean.kafka.connect.rabbitmq.ExtractHeader$Key
# The following values must be configured.
transforms.Key.header.name=
```

#### Distributed Example

```json
{
"name": "connector1",
    "config": {
        "connector.class": "com.github.jocelyndrean.kafka.connect.rabbitmq.ExtractHeader$Key",
        "transforms": "Key",
        "transforms.Key.type": "com.github.jocelyndrean.kafka.connect.rabbitmq.ExtractHeader$Key",
        "transforms.Key.header.name":"",
    }
}
```

## ExtractHeader(Value)

This transformation is used to extract a header from the message and use it as a value.

### Configuration

| Name        | Type   | Importance | Default Value | Validator | Documentation|
| ----------- | ------ | ---------- | ------------- | --------- | -------------|
| header.name | String | High       |               |           | Header name. |


#### Standalone Example

```properties
transforms=Value
transforms.Value.type=com.github.jocelyndrean.kafka.connect.rabbitmq.ExtractHeader$Value
# The following values must be configured.
transforms.Value.header.name=
```

#### Distributed Example

```json
{
"name": "connector1",
    "config": {
        "connector.class": "com.github.jocelyndrean.kafka.connect.rabbitmq.ExtractHeader$Value",
        "transforms": "Value",
        "transforms.Value.type": "com.github.jocelyndrean.kafka.connect.rabbitmq.ExtractHeader$Value",
        "transforms.Value.header.name":"",
    }
}
```
