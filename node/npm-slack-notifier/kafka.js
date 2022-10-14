
/*
# Kafka
bootstrap.servers = pkc-419q3.us-east4.gcp.confluent.cloud:9092
security.protocol = SASL_SSL
sasl.mechanisms = PLAIN
sasl.username = {{ CLUSTER_API_KEY }}
sasl.password = {{ CLUSTER_API_SECRET }}
*/

const { Kafka } = require('kafkajs')


const kafka = new Kafka({
    clientId: 'npm-slack-notifier',
    brokers: [process.env.KAFKA_BOOTSTRAP_SERVER] ,
    ssl: true ,
    sasl: null
})

module.exports = kafka