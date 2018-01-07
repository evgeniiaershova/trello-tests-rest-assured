properties([parameters([string(defaultValue: 'api-tests-suite.xml', description: 'no description', name: 'suite')])])
node {
    stage('Build') {
        bat 'mvn --version'
        bat 'mvn clean install -DsuiteXmlFile=${params.suite}'
    }
}