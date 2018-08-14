REPO = 'MNT-Lab/d323dsl'
STUDENT = 'hsakovich'
BRANCH_LIST = [STUDENT, 'master']

def getJobsToExecute(indexes) {
    indexes.collect { "MNTLAB-${STUDENT}-child${it}-build-job" }
}

def stringifyList(list) {
    def decoratedItems = list.collect { "'${it}'" }
    "[${decoratedItems.join(', ')}]"
}

job("MNTLAB-${STUDENT}-main-build-job") {
    scm {
        github(REPO, 'master')
    }
    parameters {
        activeChoiceParam('BUILDS_TRIGGER') {
            description('Select the jobs')
            choiceType('CHECKBOX')
            filterable(false)
            groovyScript {
                script(stringifyList(getJobsToExecute((1..4))))
                // script('["choice1", "choice2"]')
                fallbackScript('"fallback choice"')
            }
        }
        choiceParam('BRANCH_NAME', BRANCH_LIST, 'Select target branch')
    }
}

getJobsToExecute((1..4)).each { jobName ->
    job(jobName) {
        steps {
            shell('pwd')
            shell('ls -la')
        }
    }
}
