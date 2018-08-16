def exitCode = ["/usr/bin/sudo", "/bin/chmod", "777", "/var/run/docker.sock"]
    .execute()
    .waitFor()
if (exitCode != 0)
    throw new Exception("chmod, ${exitCode}")
