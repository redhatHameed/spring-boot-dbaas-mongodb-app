## Spring Boot & Spring Cloud example applications for MongoDB [Kubernetes Service Binding](https://github.com/servicebinding/spec)
Tested with:
* OpenShift
* DBaaS

## Building/deploying all modules
```shell
# build all modules
$ ./mvnw spring-boot:build-image

# push the images to your docker image registry
# e.g. mongodb
$ docker push spring-mongodb-app:0.0.1-SNAPSHOT

# deploy the application to a OpenShift cluster
$ oc apply -f mongodb-app/k8s/deploy-mongodb-app.yaml 
$ oc apply -f postgresql-app/k8s/deploy-postgresql-app.yaml 
```

## Building/deploying an individual module
```shell
# build individual module, and customize the image name: e.g. postgresql
$ cd postgresql-app

$ ./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=quay.io/<someaccount>/spring-postgresql-demo:v0.0.1

$ docker push quay.io/<someaccount>/spring-postgresql-demo:v0.0.1

# edit/update the deployment yaml (file: k8s/deploy-mongodb-app.yaml) for your customized image

# deploy the application to a OpenShift cluster
$ cd postgresql-app
$ oc apply -f k8s/deploy-postgresql-app.yaml 
```

## Service Binding

Provide data to be consumed by Service Binding clients (the applications in this repo.).

### Managed cloud database resources

Managing your cloud databases such as `CrunchyBridge` and `MongoDB Atlas`, automating Secrets, ConfigMap creation, and volume mounting for applications,
you can use `DBaaS`, aka [`Red Hat OpenShift Database Access Operator`](https://github.com/RHEcosystemAppEng/dbaas-operator)

### Manual database resources preparation

1. Provision the services, such as MongoDB, PostgreSql
2. Create a Secret or ConfigMap containing the properties to access the service
3. Mount the Secret and/or ConfigMap as a volume within the Pod for the application
4. Set the `SERVICE_BINDING_ROOT` environment variable specifying where the service information has been mounted

Instead of manually preparing steps 2 through 4, they can be automated by installing and using the [`Service Binding Operator`](https://github.com/redhat-developer/service-binding-operator)

## Relevant resources

* [Quarkus App for Mongodb](https://github.com/RHEcosystemAppEng/mongo-quickstart)
* [Quarkus App for PostgreSql](https://github.com/RHEcosystemAppEng/postgresql-orm-quickstart)
* [Spring Cloud Bindings](https://github.com/spring-cloud/spring-cloud-bindings)
