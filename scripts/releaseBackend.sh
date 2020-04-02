#!/bin/bash
# Script for releasing backend


export INHOUSE="http://maven2.rd.francetelecom.fr/proxy/content/repositories/inhouse"

#------------------------
# A little function to exit the script with an appropriate message when there is an error
#------------------------
die() {
    echo "$@" >&2 ; exit 1
}


#------------------------
# A little function to check the java version used by maven
#------------------------
checkJavaVersion() {
    #
    # Will work only for me (Denis BORSCIA)
    # force a value for Java home if not set
    #export JAVA_HOME=${JAVA_HOME:-"/C:\Program Files\Java\jdk1.7.0_75"}
	export JAVA_HOME="C:\My Program Files\java\jdk1.7.0_45"

    mvn -v | grep "Java version: 1.7" > /dev/null || die Bad Java Version try setting JAVA_HOME
}

#------------------------
# A little function to initialize a clean workspace
#------------------------
initWorkspace() {

    export WORKSPACE=${1:-~/release.workspace}

    rm -rf ${WORKSPACE} || die fail to clean workspace : ${WORKSPACE}
    mkdir -p ${WORKSPACE} || die fail to create workspage : ${WORKSPACE}
}

#------------------------
# A function to checkout a fresh copy of the project
# to be sure to have the last version
# By default the branch is development
#------------------------
checkoutProject() {
    export PROJECT=$1
    export BRANCH=${2:-development}
    cd ${WORKSPACE}
    #git clone ssh://gitolite@www.forge.orange-labs.fr/api-bss-mea/${PROJECT}.git ${PROJECT}
	git clone https://ECUS6396@www.forge.orange-labs.fr/plugins/git/api-bss-mea/${PROJECT}.git ${PROJECT}
    cd ${PROJECT}
    git checkout ${BRANCH}
}

#------------------------
# A function to update the version of dependencies
# The project is supposed to be up to date (via checkoutProject)
# Each dependency is supposed to be set by a property
# <property.version>value</property.version>
#------------------------
updateDependencies() {
    while (($#)); do
        temp=`declare -p $1`
        eval "declare -A dep=${temp#*=}"

        # Get dependency information
        DEPENDENCY=${dep[artifactId]%-amea}
        VERSION=${dep[release]}

        
        if [ `grep "<${DEPENDENCY}.version>${VERSION}</${DEPENDENCY}.version>" pom.xml` ]
        then
            echo "${DEPENDENCY} VERSION IS UP TO DATE"
        else
            # substitute and commit
             sed -i "s/<${DEPENDENCY}.version>.*<\/${DEPENDENCY}.version>/<${DEPENDENCY}.version>${VERSION}<\/${DEPENDENCY}.version>/g" pom.xml
             git commit -am "[update] ${DEPENDENCY}.version"
        fi

        # shift to next dependency information
        shift
    done
}

#------------------------
# A function to compute a path from a groupId 
# a.b.c ==> a/b/c
#------------------------
groupIdToPath() {
    echo $1 | tr . /
}

#------------------------
# A function to check is a project is already release in the inhouse repository
#    GROUPID=$1
#    ARTIFACTID=$2
#    VERSION=$3
#------------------------
isReleasePresent() {
    curl --output /dev/null --silent --head --fail ${INHOUSE}/`groupIdToPath $1`/$2/$3/$2-$3.pom
}

#------------------------
# A function to release a project
# The project is supposed to be up to date (via checkoutProject)
#------------------------
releaseProject() {
    ARTIFACTID=$1
    RELEASE_VERSION=$2
    NEXT_VERSION=$3-SNAPSHOT

    git checkout -b release
    mvn release:prepare release:perform -B -DdevelopmentVersion=${NEXT_VERSION} -DreleaseVersion=${RELEASE_VERSION}

    # Merging release to development branch (don't forget to push tags and remove release branch)
    git checkout development
    git merge release
    git push origin development
    git push --tags
    git branch -d release

    #
    # Merging tags to master
    export SHA1_TAG=`git rev-list -n 1 ${ARTIFACTID}-${RELEASE_VERSION}`
    git checkout master
    git merge ${SHA1_TAG}
    git push origin master

}


tagCountries() {
    while (($#)); do

        COUNTRY=$1
    
        # Merge or create Country branch
        if [ `git branch -a | grep "remotes/origin/${COUNTRY}"` ]
        then
            git checkout ${COUNTRY}
            git merge master
        else
            git checkout -b ${COUNTRY}
        fi
        
        git push origin ${COUNTRY}

        shift
    done

}


#------------------------
# A function to check if a release exist and release a project 
#------------------------
checkAndRelease() {
    temp=`declare -p $1`
    eval "declare -A project="${temp#*=}

    GROUPID=${project[groupId]}
    ARTIFACTID=${project[artifactId]}
    RELEASE_VERSION=${project[release]}
    NEXT_VERSION=${project[next]}
    if isReleasePresent ${GROUPID} ${ARTIFACTID} ${RELEASE_VERSION}; then
        echo "${ARTIFACTID}-${RELEASE_VERSION} is already released"
    else
        REPO=${project[repo]:-${project[artifactId]}}
        checkoutProject ${REPO} 
        updateDependencies ${project[dependencies]}
        releaseProject ${ARTIFACTID} ${RELEASE_VERSION} ${NEXT_VERSION}
        tagCountries ${project[countries]}
    fi

}


declare -A apiAmea
apiAmea=([artifactId]=api-amea [groupId]=com.orange.mea.apisi.workspace 
            [release]=1.16 [next]=1.17
#            [countries]="OTN OBW")
			[countries]="OBW")
			 
checkJavaVersion

#TODO: update with local directory
initWorkspace "C:\Users\ECUS6396\Documents\Projets\SPEC\AMEA\livraison\release\1.16"

checkAndRelease apiAmea

