var { Kafka } = require('kafkajs')

Kafka = new Kafka({
    clientId: "billing-poc-app",
    brokers: ["localhost:9092"]
})

exports.Kafka = Kafka