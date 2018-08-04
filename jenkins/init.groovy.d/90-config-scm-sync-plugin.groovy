import net.sf.json.JSONObject
import org.kohsuke.stapler.StaplerRequest
import hudson.plugins.scm_sync_configuration.ScmSyncConfigurationPlugin

def params = [
    noUserCommitMessage: false,
    displayStatus: true,
    scm: 'hudson.plugins.scm_sync_configuration.scms.ScmSyncGitSCM',
    gitRepositoryUrl: 'git@github.com:cornBuddy/jenkins-config.git',
    commitMessagePattern: '[message]',
]

def JSONObject formData = [ params ] as JSONObject
def req = [
    getParameter: { name ->
        params[name]
    },
    getParameterValues: { name ->
        if (params[name]) {
            return params[name] as String[]
        } else {
            return null
        }
    }
] as org.kohsuke.stapler.StaplerRequest

scm = new ScmSyncConfigurationPlugin().getInstance()

scm.configure(req, formData)
scm.reloadAllFilesFromScm()
