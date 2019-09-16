const { ServiceBroker } = require('moleculer')
const HTTPServer = require('moleculer-web')

const brokerNode1 = new ServiceBroker({
    nodeID: "node-1",
    transporter: "NATS",
})

brokerNode1.createService({
    name: 'gateway',
    mixins: [HTTPServer],
    settings: {
        routes: [
            {
                aliases: {
                    "GET /products": "products.listProducts",
                },
            },
        ],
    },
})

module.exports = brokerNode1