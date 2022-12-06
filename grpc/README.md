# gRPC Example for 3scale API Management

This is an example on how to protect a gRPC service using 3scale API Management.

## Deployment

You will need to install and configure 3scale API Management using the Operator. This instructions were tested with 3scale 2.12.

1. Clone this github repo:
    ```sh
    git clone https://github.com/hguerrero/3scale-examples.git
    cd 3scale-examples/grpc
    ```

1. Deploy the gRPC service and Kubernetes resources:
    ```sh
    kubectl apply -f k8s/
    ```

1. Change to the 3scale installation project
    ```sh
    oc project <THREESCALE_PROJECT>
    ```

1. Deploy the API Management resources:
    ```sh
    kubectl apply -f threescale/
    ```

It will take a couple of moments for the operator to catch the new resources.

### Manual step

Currently the 2.12 version of the operator does not support Applications. This means that you will need to create and application in the Admin Portal for the service we just created. 

1. Select the Developer Account that has the `myusername01` admin.
1. Select the Application plan `Basic gRPC Plan`.

This will create the `user_key` required to authenticate a test client.

## Test

Get the testing URL from the Product configuration and use postman to run a request. 

1. Select TLS but uncheck the certificate verification. 
1. Add the `user_key` under Metadata.

You will be able to check the analytics in 3scale after a successful call.

If you want, you can add a limit to the plan or create a new plan that as some limits on the calls to the `SayHello` method.
