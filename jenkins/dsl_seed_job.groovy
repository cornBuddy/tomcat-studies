STUDENT = 'hsakovich'

def getJobsToExecute(indexes) {
    indexes.collect { "MNTLAB-${STUDENT}-child${it}-build-job" }
}

job("MNTLAB-${STUDENT}-main-build-job") {
    scm {
        github('MNT-Lab/d323dsl', 'master')
    }
    parameters {
        gitParam('sha') {
            type('BRANCH')
        }
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
