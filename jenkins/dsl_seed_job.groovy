REPO = 'MNT-Lab/d323dsl'
STUDENT = 'hsakovich'
BRANCH_LIST = ['master', STUDENT]

def getJobsToExecute(indexes) {
    indexes.collect { "MNTLAB-${STUDENT}-child${it}-build-job" }
}

def stringifyList(list) {
    def decoratedItems = list.collect { "'${it}'" }
    "[${decoratedItems.join(', ')}]"
}

job("MNTLAB-${STUDENT}-main-build-job") {
    parameters {
        activeChoiceParam('BUILDS_TRIGGER') {
            description('Select the jobs')
            choiceType('CHECKBOX')
            filterable(false)
            groovyScript {
                script(stringifyList(getJobsToExecute((1..4))))
                fallbackScript('"fallback choice"')
            }
        }
        choiceParam('BRANCH_NAME', BRANCH_LIST, 'Select target branch')
    }
    steps {
        downstreamParameterized {
            trigger('$BUILDS_TRIGGER') {
                block {
                    buildStepFailure('FAILURE')
                    failure('FAILURE')
                    unstable('UNSTABLE')
                }
                parameters {
                    predefinedProp('BRANCH_NAME', '$BRANCH_NAME')
                }
            }
        }
    }
}

getJobsToExecute((1..4)).each { jobName ->
    job(jobName) {
        scm {
            github(REPO, "${BRANCH_NAME}")
        }
        steps {
            shell('bash ./script.sh > output.txt')
            shell('cat output.txt')
        }
        publishers {
            archiveArtifacts {
                pattern('./output.txt')
                onlyIfSuccessful()
                allowEmpty(false)
            }
        }
    }
}
