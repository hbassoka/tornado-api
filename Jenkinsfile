pipeline {
    agent any

    parameters {
        choice(
            name: 'BRANCH_TO_BUILD',
            choices: ['master','develop','preproduction','production'],
            description: 'Choisissez la branche à builder'
        )
    }

    environment {
		

        GIT_REPO_URL = 'https://github.com/hbassoka/tornado-api.git'

        GITHUB_CREDENTIALS_ID = 'github_creds'
        TAG_PREFIX = 'release-'

        SONARQUBE_ENV  = 'SonarQubeServer'
        SONARQUBE_HOST_URL = 'https://sonar.jdevhub.com'
                
        NEXUS_CREDENTIALS_ID = 'nexus-creds'
        MAVEN_SETTINGS='user-maven-settings'
       
        DOCKER_REGISTRY_URL = "nexus.jdevhub.com"
        DOCKER_REGISTRY_CREDENTIALS_ID = 'nexus-creds'

        //  Production Env
        PROD_KUBECONFIG_CREDENTIAL_ID = 'prod-kubeconfig-creds'
        
         // Non sensibles (runtime)
        PROD_SPRING_PROFILES_ACTIVE ='prod'    
        
         // Sensibles (stockées dans Jenkins Credentials)
        PROD_DB_URL = 'prod-db-url'
        PROD_DB_USERNAME = 'prod-db-user'
        PROD_DB_PASSWORD = 'prod-db-password'    // Jenkins Credential ID
         
        PROD_MAIL_SERVER = 'prod-mail-server'
        PROD_MAIL_PORT = 'prod-mail-port'
        PROD_MAIL_USERNAME = 'prod-mail-username'
        PROD_MAIL_PASSWORD = 'prod-mail-password'    
        
        PROD_KAFKA_SERVERS ='prod-kafka-servers'     
               
        PROD_JWT_SECRET_KEY='prod-jwt-secret-key'
        
        PROD_GOOGLE_CLIENT_ID='prod-google-client-id'        
		PROD_GOOGLE_CLIENT_SECRET='prod-google-client-secret'
		
		PROD_FACEBOOK_CLIENT_ID='prod-facebook-client-id'
		PROD_FACEBOOK_CLIENT_SECRET='prod-facebook-client-secret'   
		
		
		
		//  PréProduction Env
        
        PPROD_KUBECONFIG_CREDENTIAL_ID  = 'dev-kubeconfig-creds'
        
        // Non sensibles (runtime)
        PPROD_SPRING_PROFILES_ACTIVE ='pprod'
        // Sensibles (stockées dans Jenkins Credentials)
        
        // Sensibles (stockées dans Jenkins Credentials)
        PPROD_DB_URL = 'pprod-db-url'
        PPROD_DB_USERNAME = 'pprod-db-user'
        PPROD_DB_PASSWORD = 'pprod-db-password'    // Jenkins Credential ID
         
        PPROD_MAIL_SERVER = 'pprod-mail-server'
        PPROD_MAIL_PORT = 'pprod-mail-port'
        PPROD_MAIL_USERNAME = 'pprod-mail-username'
        PPROD_MAIL_PASSWORD = 'pprod-mail-password'    
        
        PPROD_KAFKA_SERVERS ='pprod-kafka-servers'     
                                
        PPROD_JWT_SECRET_KEY='pprod-jwt-secret-key'
        
        PPROD_GOOGLE_CLIENT_ID='pprod-google-client-id'        
		PPROD_GOOGLE_CLIENT_SECRET='pprod-google-client-secret'
		
		PPROD_FACEBOOK_CLIENT_ID='pprod-facebook-client-id'
		PPROD_FACEBOOK_CLIENT_SECRET='pprod-facebook-client-secret'   
        
        
    }

    tools {
        jdk 'java-21-LTS'
        maven 'maven-3.9.9'
    }

    stages {

        stage('Init') {
            steps {
                script { env.BUILD_TIME = System.currentTimeMillis().toString() }
            }
        }

        stage('Set Environment') {
            steps {
                script {
                    // Variables dynamiques selon la branche
                    // Stocker dans env pour les étapes suivantes
                    env.SPRING_PROFILES_ACTIVE = (params.BRANCH_TO_BUILD == 'preproduction') ? env.PPROD_SPRING_PROFILES_ACTIVE : env.PROD_SPRING_PROFILES_ACTIVE
                    
                    echo "Environment variables set for branch ${params.BRANCH_TO_BUILD}"
                }
            }
        }
        
        stage('Checkout') {
            steps {
                echo "🔍 Clonage de la branche : ${params.BRANCH_TO_BUILD}"
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: "*/${params.BRANCH_TO_BUILD}"]],
                    extensions: [[$class: 'WipeWorkspace']], // force clean
                    userRemoteConfigs: [[
                        url: "${env.GIT_REPO_URL}",
                        credentialsId: "${env.GITHUB_CREDENTIALS_ID}"
                    ]]
                ])
            }
        }

        stage('Extract Maven props') {
            steps {
                script {
                    env.ARTIFACT_ID = sh(script: 'mvn help:evaluate -Dexpression=project.artifactId -q -DforceStdout', returnStdout: true).trim()
                    env.VERSION     = sh(script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout', returnStdout: true).trim()
                }
            }
        }

        stage('Build & Test') {
            steps {
				
				configFileProvider([
				    configFile(fileId: 'user-maven-settings', variable: 'MAVEN_SETTINGS')
				]) {
				    sh """
				       mvn clean verify -B -V -s ${MAVEN_SETTINGS}
				     """
				 }
               
            }
        }

     
        stage('SonarQube Analysis') {
            steps {
              timeout(time: 30, unit: 'MINUTES') {   // <-- augmente le temps si nécessaire
                withSonarQubeEnv("${SONARQUBE_ENV}") {
                    sh """
                        mvn sonar:sonar \
                        -Dsonar.projectKey=${env.ARTIFACT_ID} \
                        -Dsonar.projectName=${env.ARTIFACT_ID} \
                        -Dsonar.language=java                  \
                        -Dsonar.sources=src/main/java \
                        -Dsonar.tests=src/test/java \
                        -Dsonar.host.url=${SONARQUBE_HOST_URL} \
                        -Dsonar.java.binaries=target/classes
                    """
                }
               }
            }
        }

      
        stage('Package') {
            steps {
				
				configFileProvider([
					 configFile(fileId: 'user-maven-settings', variable: 'MAVEN_SETTINGS')
			    ]) {
                sh """
                mvn package -B -V -s ${MAVEN_SETTINGS}
                
                """
                }
            }
        }

        stage('Deploy Artifacts to Nexus') {
		    when { expression { params.BRANCH_TO_BUILD == 'master' } }
		    steps {
		        withCredentials([usernamePassword(
		            credentialsId: "${env.NEXUS_CREDENTIALS_ID}", 
		            usernameVariable: 'NEXUS_USER', 
		            passwordVariable: 'NEXUS_PASS')]) {
		            
		           configFileProvider([
					    configFile(fileId: 'user-maven-settings', variable: 'MAVEN_SETTINGS')
					]) {
					    sh """
		                echo "📦 Déploiement vers Nexus (master uniquement)"
		                
		                # Utilise les credentials dans Maven via options -D
		                
		                mvn clean deploy -B -s ${MAVEN_SETTINGS} -DskipTests
		                
		                """
		            }
		        }
		    }
		}


        stage('Docker Build & Push') {
		    when { anyOf { expression { params.BRANCH_TO_BUILD in ['preproduction','production'] } } }
		    steps {
		        script {
					
					echo " Docker Build & Push  STARTED"							
		            // Build Docker
		            
		            echo " Build, Tag & Push  Docker Image STARTED"
		            
		            configFileProvider([
					  configFile(fileId: env.MAVEN_SETTINGS, variable: 'MAVEN_SETTINGS')
					]) {
					sh """
					
					# Copy settings.xml to the build context
                    cp ${MAVEN_SETTINGS} ./settings.xml
    
					docker build --no-cache \
					--build-arg ARTIFACT_ID=${env.ARTIFACT_ID} \
					--build-arg VERSION=${env.VERSION} \
					--label build_time=${env.BUILD_TIME} \
					-t ${DOCKER_REGISTRY_URL}/${env.ARTIFACT_ID}:${env.VERSION} .
					
					"""
		            }
		            		            
		            echo " Tag Docker image"
		           
		            sh "docker tag ${DOCKER_REGISTRY_URL}/${env.ARTIFACT_ID}:${env.VERSION} ${DOCKER_REGISTRY_URL}/${env.ARTIFACT_ID}:latest"
		
		            
		            echo " Push Docker image"
		            
		            withCredentials([
						usernamePassword(credentialsId: DOCKER_REGISTRY_CREDENTIALS_ID, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')
					]) {
						
		                sh "echo \$DOCKER_PASS | docker login ${DOCKER_REGISTRY_URL} -u \$DOCKER_USER --password-stdin"
		            }
		
		            sh "docker push ${DOCKER_REGISTRY_URL}/${env.ARTIFACT_ID}:${env.VERSION}"
		            sh "docker push ${DOCKER_REGISTRY_URL}/${env.ARTIFACT_ID}:latest"
		            sh "docker logout ${DOCKER_REGISTRY_URL}"
		
		            // Supprimer les images locales après push
		            sh "docker rmi -f ${DOCKER_REGISTRY_URL}/${env.ARTIFACT_ID}:${env.VERSION}"
		            sh "docker rmi -f ${DOCKER_REGISTRY_URL}/${env.ARTIFACT_ID}:latest"
		            sh "docker image prune -a -f"
		            
		            
		            
		            echo " Build, Tag & Push  Docker Image ENDED"
		        }
		    }
		}
        stage('Deploy to Kubernetes') {
    when { expression { params.BRANCH_TO_BUILD in ['preproduction','production'] } }

    steps {
        script {
			
			
            def kubeCred = (params.BRANCH_TO_BUILD == 'production')
                ? env.PROD_KUBECONFIG_CREDENTIAL_ID
                : env.PPROD_KUBECONFIG_CREDENTIAL_ID

           // Database properties
           
           def dbUrl = (params.BRANCH_TO_BUILD == 'preproduction') 
               ? env.PPROD_DB_URL 
               : env.PROD_DB_URL
           
           def dbUser = (params.BRANCH_TO_BUILD == 'preproduction') 
              ? env.PPROD_DB_USERNAME 
              : env.PROD_DB_USERNAME
                    
                    
           def dbPassword = (params.BRANCH_TO_BUILD == 'production')
                ? env.PROD_DB_PASSWORD
                : env.PPROD_DB_PASSWORD            
            
            
            // Mail server properties
            
            def mailServer  = (params.BRANCH_TO_BUILD == 'preproduction') 
               ? env.PPROD_MAIL_SERVER 
               : env.PROD_MAIL_SERVER
               
            def mailPort = (params.BRANCH_TO_BUILD == 'preproduction') 
               ? env.PPROD_MAIL_PORT 
               : env.PROD_MAIL_PORT
               
            def mailUsername  = (params.BRANCH_TO_BUILD == 'preproduction')
               ? env.PPROD_MAIL_USERNAME 
               : env.PROD_MAIL_USERNAME
                        
            def mailPassword = (params.BRANCH_TO_BUILD == 'production')
                ? env.PROD_MAIL_PASSWORD
                : env.PPROD_MAIL_PASSWORD

            // Kafka properties
                    
            def kafkaServers  = (params.BRANCH_TO_BUILD == 'preproduction') 
                ? env.PPROD_KAFKA_SERVERS 
                : env.PROD_KAFKA_SERVERS

            // JWT TOken  properties
            
            
             def jwtSecretKey = (params.BRANCH_TO_BUILD == 'preproduction') 
	             ? env.PPROD_JWT_SECRET_KEY 
	             : env.PROD_JWT_SECRET_KEY
                          
             // GOOGLE OAUTH2  properties
             def googleClientId = (params.BRANCH_TO_BUILD == 'preproduction') 
	             ? env.PPROD_GOOGLE_CLIENT_ID 
	             : env.PROD_GOOGLE_CLIENT_ID
             
             def googleClientSecret = (params.BRANCH_TO_BUILD == 'preproduction')
	              ? env.PPROD_GOOGLE_CLIENT_SECRET 
	              : env.PROD_GOOGLE_CLIENT_SECRET
	      	              
	         // FACBOOK OAUTH2  properties
	         
             def facebookClientId     = (params.BRANCH_TO_BUILD == 'preproduction') 
	             ? env.PPROD_FACEBOOK_CLIENT_ID 
	             : env.PROD_FACEBOOK_CLIENT_ID
             
             def facebookClientSecret = (params.BRANCH_TO_BUILD == 'preproduction') 
	             ? env.PPROD_FACEBOOK_CLIENT_SECRET 
	             : env.PROD_FACEBOOK_CLIENT_SECRET

            withCredentials([
                file(credentialsId: kubeCred, variable: 'KUBECONFIG'),
                string(credentialsId: dbUrl, variable: 'DB_URL'),
                string(credentialsId: dbUser, variable: 'DB_USERNAME'),
                string(credentialsId: dbPassword, variable: 'DB_PASSWORD'),   
                string(credentialsId: mailServer, variable: 'MAIL_SERVER'),
                string(credentialsId: mailPort, variable: 'MAIL_PORT'),
                string(credentialsId: mailUsername, variable: 'MAIL_USERNAME'),
                string(credentialsId: mailPassword, variable: 'MAIL_PASSWORD'),
                string(credentialsId: kafkaServers, variable: 'KAFKA_SERVERS'),                
                string(credentialsId: jwtSecretKey, variable: 'JWT_SECRET_KEY'),
                string(credentialsId: googleClientId, variable: 'GOOGLE_CLIENT_ID'),
                string(credentialsId: googleClientSecret, variable: 'GOOGLE_CLIENT_SECRET'),
                string(credentialsId: facebookClientId, variable: 'FACEBOOK_CLIENT_ID'),
                string(credentialsId: facebookClientSecret, variable: 'FACEBOOK_CLIENT_SECRET')
            ]) {

                sh """
                
                kubectl delete configmap tornado-api-config --ignore-not-found
                kubectl delete secret tornado-api-secrets --ignore-not-found
                envsubst < k8s/deployment.yaml | kubectl delete -f - --ignore-not-found

                kubectl create configmap tornado-api-config \
                  --from-literal=SPRING_PROFILES_ACTIVE=$SPRING_PROFILES_ACTIVE \
                  --dry-run=client -o yaml | kubectl apply -f -

                kubectl create secret generic tornado-api-secrets \
                  --from-literal=DB_URL=$DB_URL \
                  --from-literal=DB_USERNAME=$DB_USERNAME \
                  --from-literal=MAIL_SERVER=$MAIL_SERVER \
                  --from-literal=MAIL_PORT=$MAIL_PORT \
                  --from-literal=MAIL_USERNAME=$MAIL_USERNAME \
                  --from-literal=KAFKA_SERVERS=$KAFKA_SERVERS \
                  --from-literal=DB_PASSWORD=$DB_PASSWORD \
                  --from-literal=MAIL_PASSWORD=$MAIL_PASSWORD \
                  --from-literal=JWT_SECRET_KEY=$JWT_SECRET_KEY \
                  --from-literal=GOOGLE_CLIENT_ID=$GOOGLE_CLIENT_ID \
                  --from-literal=GOOGLE_CLIENT_SECRET=$GOOGLE_CLIENT_SECRET \
                  --from-literal=FACEBOOK_CLIENT_ID=$FACEBOOK_CLIENT_ID \
                  --from-literal=FACEBOOK_CLIENT_SECRET=$FACEBOOK_CLIENT_SECRET \
                  --dry-run=client -o yaml | kubectl apply -f -

                envsubst < k8s/deployment.yaml | kubectl apply -f -
                """
            }
        }
    }
}


    } // end stages

    post {
        always { echo "🏁 Pipeline Finished pour ${params.BRANCH_TO_BUILD}" }
        success { echo "✅ Pipeline Success pour ${params.BRANCH_TO_BUILD}" }
        failure { echo "❌ Pipeline Failed pour ${params.BRANCH_TO_BUILD}" }
    }
}
