node("slave-ic"){

    stage("checkout-commande"){
        checkout([$class: 'GitSCM', branches: [[name: '*/develop']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/sbenbelkacem/commande']]])
    }

    stage("build"){
        sh "chmod 777 mvnw"
        sh "./mvnw clean package -DskipTests"
        stash includes: 'target/commande*.jar', name: 'livrable-commande'
        stash includes: 'Dockerfile', name: 'commande-dockerfile'
        stash includes: 'docker-compose.yaml', name: 'commande-dockerfile-compose'
        stash includes: 'launch.sh', name: 'commande-launch'
    }

    stage("quality check"){
        sh "./mvnw sonar:sonar \
          -Dsonar.projectKey=commande \
          -Dsonar.host.url=http://54.234.48.131:11001 \
          -Dsonar.login=5a4a695f5fc203837db9c3345848907456499d12"
    }

    node("vm-deploy"){
        stage("deploy"){
            unstash 'livrable-commande'
            unstash 'commande-dockerfile'
            unstash 'commande-dockerfile-compose'
            unstash 'commande-launch'
            sh "chmod 777 launch.sh"
            sh "./launch.sh"
        }
    }


}
