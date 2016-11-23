Dummy consumer to check the payload :
    call bin/windows/kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic output --from-beginning

Run 2 instances of kafka in local
    call bin/windows/kafka-server-start.bat config/server-1.properties
    call bin/windows/kafka-server-start.bat config/server-2.properties
