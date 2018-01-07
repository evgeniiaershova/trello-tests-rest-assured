pipeline {
    agent any

    parameters {
        string(defaultValue: "api-tests-suite.xml", description: 'What environment?', name: 'suite')
    }

    stages {
        stage("Build") {
            steps {
                bat "mvn --version"
                bat "mvn clean install -DsuiteXmlFile=${params.suite}"
            }
        }
    }
}