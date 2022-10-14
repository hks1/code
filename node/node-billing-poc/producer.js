const {Kafka} = require('./kafka-connection')

const producer = Kafka.producer()

module.exports = async function (message, key) {
    await producer.connect()
    await producer.send({
        //topic: "sample-topic-1",
        topic: "quickstart-events",
        messages: [
            { value: message,
            key: key }
        ]
    })
}

const MyPartitioner = () => {
    return ({ topic, partitionMetadata, message }) => {
        // select a partition based on some logic
        // return the partition number
        return 0
    }
}

//sendMsg().catch(console.error)

//module.exports = sendMsg()

