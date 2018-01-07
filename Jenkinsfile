properties([parameters([string(defaultValue: "api-tests-suite.xml", description: 'no description', name: 'SUITE')])])
node {
    stage('Build') {
        bat 'mvn --version'
        bat 'mvn clean install -DsuiteXmlFile=${params.SUITE}'
    }
}