

STEP 1: DOWNLOAD AND INSTALL KAFKA
https://kafka.apache.org/downloads

STEP 2: START THE KAFKA ENVIRONMENT
# Start the ZooKeeper service
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

# Start the Kafka broker service
.\bin\windows\kafka-server-start.bat .\config\server.properties

STEP 3: CREATE A TOPIC TO STORE YOUR EVENTS
#.\bin\windows\kafka-topics.bat --create --topic topic_demo --bootstrap-server localhost:9092
#note: no need to create topics it's integrated into api

STEP 4: WRITE SOME EVENTS INTO THE TOPIC
.\bin\windows\kafka-console-producer.bat --topic topic_demo --bootstrap-server localhost:9092
#note: no need to push messages into topic it's integrated into api
hello world
topic demo

STEP 5:  READ THE EVENTS:::IMPORTANT
.\bin\windows\kafka-console-consumer.bat --topic topic_demo --from-beginning --bootstrap-server localhost:9092
hello world
topic demo

STEP 6: START THE MICROSERVICE

Logging:
STEP-1: START ELASTIC SEARCH
C:\LoggingTools\elasticsearch-8.5.2\bin>elasticsearch.bat


STEP-2:START LOGSTASH

C:\LoggingTools\logstash-8.5.2\bin>logstash.bat -f logstash.conf
