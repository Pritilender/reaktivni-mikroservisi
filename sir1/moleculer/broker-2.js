const { ServiceBroker } = require('moleculer')

const brokerNode2 = new ServiceBroker({
  nodeID: 'node-2',
  transporter: 'NATS',
})

brokerNode2.createService({
  name: 'products',
  actions: {
    listProducts(ctx) {
      return [
        { name: "Apples", price: 5 },
        { name: "Oranges", price: 3 },
        { name: "Bananas", price: 2 },
      ];
    },
  },
})

module.exports = brokerNode2