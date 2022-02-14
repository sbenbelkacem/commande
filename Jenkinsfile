node("slave-ci"){

    stage("checkout-commande"){
        checkout([$class: 'GitSCM', branches: [[name: '*/develop']], extensions: [], userRemoteConfigs: [[url: 'git@github.com:sbenbelkacem/commande.git']]])
    }

    stage("build"){
        sh "chmod 777 mvnw"
        sh "./mvnw clean package -DskipTests"
        stash includes: 'target/catalog*.jar', name: 'livrable-commande'
        stash includes: 'Dockerfile', name: 'commande-dockerfile'
        stash includes: 'docker-compose.yaml', name: 'commande-dockerfile-compose'
        stash includes: 'launch.sh', name: 'commande-launch'
    }

    stage("quality check"){
        sh "mvnw sonar:sonar \
          -Dsonar.projectKey=commande \
          -Dsonar.host.url=http://54.234.48.131:11001 \
          -Dsonar.login=5a4a695f5fc203837db9c3345848907456499d12"
    }

    node("vm-deploy"){
        stage("deploy"){
            unstash 'livrable-commande'
            unstach 'commande-dockerfile'
            unstach 'commande-dockerfile-compose'
            unstach 'commande-launch'
            sh "chmod 777 launch.sh"
            sh "./launch.sh"
        }
    }


}