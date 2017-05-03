package contract

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url ('/fraud') {
            queryParameters {
                parameter 'age': value(consumer(matching("[1-9][0-9]+")), producer(10))
            }
        }

    }
    response {
        status 200
        body([
               fraud: true
        ])
        headers {
            contentType('application/json')
        }
    }
}