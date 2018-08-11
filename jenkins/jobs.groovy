def student = 'hsakovich'
def gitUrl = 'https://github.com/MNT-Lab/d323dsl'
def mainJobName = "MNTLAB-${student}-main-build-job"

def getJobsToExecute(indexes) {
    indexes.collect { "MNTLAB-${student}-child${it}-build-job" }
}

job(mainJobName) {
    scm {
        git(gitUrl)
    }

    getJobsToExecute().each { jobName ->

    }
}
