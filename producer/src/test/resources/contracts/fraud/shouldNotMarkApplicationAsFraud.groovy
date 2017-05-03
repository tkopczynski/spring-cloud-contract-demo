package contract

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url ('/fraud') {
            queryParameters {
                parameter 'age': value(consumer(matching("[1-9]")), producer(5))
            }
        }

    }
    response {
        status 200
        body([
                fraud: false
        ])
        headers {
            contentType('application/json')
        }
    }
}