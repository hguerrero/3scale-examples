# gRPC Example for 3scale API Management

This is an example on how to protect a gRPC service using 3scale API Management.

## Deployment

You will need to install and configure 3scale API Management using the Operator. This instructions were tested with 3scale 2.12.

1. Clone this github repo:
    ```sh
    git clone https://github.com/hguerrero/3scale-examples.git
    cd 3scale-examples/grpc
    ```

1. If you are using a cluster that allows you to create namespaces:

    ```sh
    oc new-project grpc
    ```

    In case you can't create a new project, do select the working project with:

    ```sh
    oc project <WORKING_PROJECT>
    ```

1. Deploy the gRPC service and Kubernetes resources:

    ```sh
    oc apply -f k8s/
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

1. Enter the testing/staging url obtained from 3scale product configuration in the **server URL** box. Do not forget to replace https with grpc. So you server URL should start like this **grpc://grpcapi** instead of **https://grpcapi**
1. From the *Select a method* drop down select **import a .proto file** option and enter this URL in the text box provided `https://raw.githubusercontent.com/hguerrero/3scale-examples/master/grpc/grpc-helloworld/src/main/proto/helloworld.proto` and hit **Next**
1. Click on the **Use without importing** text link shown on the screen 
1. The **Select a method** drop down should now show the **SayHello** method. Select it.
1. Select TLS(Click on the lock icon beside the server URL text field) but turn off the certificate verification under setting. 
1. Under Metadata add the `user_key` and it's value obtained from 3scale. 
1. Click on **Generate Example Message** and replace the default name with any name of your choice 
1. Invoke the API call and you should able to see a greeting message as a response.

You will be able to check the analytics in 3scale after a successful call.

If you want, you can add a limit to the plan or create a new plan that as some limits on the calls to the `SayHello` method.
