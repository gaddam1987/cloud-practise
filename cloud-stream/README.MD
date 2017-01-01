Run Zookeeper
    call bin/windows/zookeeper-server-start.bat config/zookeeper.properties
Dummy consumer to check the payload :
    call bin/windows/kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic output --from-beginning

Run 2 instances of kafka in local
    call bin/windows/kafka-server-start.bat config/server-1.properties
    call bin/windows/kafka-server-start.bat config/server-2.properties


Events
EventListeners
EventChannel

Producer
Consumer

Route
Filter
Aggregate
Transform

 Channel Adapter  --> Unidirectional inbound or outbound adapter

 Bidirectional --> Called Gateway

 pipes-and-filters

 Command Message tells the receiver to do something

 Event Message notifies the receiver that something has happened

 Channels can be categorized based on two dimensions: type of handoff (sync or async) and type of delivery (p2p or p2s)

Competing Consumers (composite) pattern --> point to point
Idempotent Receiver or Compensating Transactions --> Publish Subscribe

