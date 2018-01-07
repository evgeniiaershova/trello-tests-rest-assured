pipeline {
  agent any

   parameters {
          string(name: 'suite', defaultValue: 'api-tests-suite.xml', description: 'no description')
   }

  stages {
    stage('build') {
      steps {
        bat 'mvn --version'
        bat 'mvn clean install -DsuiteXmlFile=${params.suite}'
      }
    }
  }
}