const broker1 = require('./broker-1')
const broker2 = require('./broker-2')

Promise.all([broker1.start(), broker2.start()])