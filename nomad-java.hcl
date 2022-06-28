variable "version" {}
variable "vault_host" {}
variable "vault_port" {}
variable "role_id" {}
variable "secret_id" {}

job "demo-java-vault" {
  datacenters = ["dc1"]

  type = "service"

  group "java-process" {
    count = 1

    scaling {
      enabled = true
      min = 1
      max = 3
    }

    network {
      port "http" {}
    }

    task "demo" {
      driver = "java"
      resources {
        cpu    = 200
        memory = 512
      }
      env {
        DYNAMIC_PROPERTIES_PATH = "secrets/dynamic.properties"
        VAULT_HOST = var.vault_host
        VAULT_PORT = var.vault_port
        VAULT_URI  = "http://${var.vault_host}:${var.vault_port}"
        VAULT_ROLE_ID = var.role_id
        VAULT_SECRET_ID = var.secret_id
      }
      config {
        jar_path    = "local/file"
        jvm_options = ["-Xms256m", "-Xmx512m"]
      }
      logs {
        max_files     = 10
        max_file_size = 10
      }
      artifact {
        source = "http://localhost:3000/file?file=demo-vault-${var.version}.jar"
        destination = "local"
      }
    }

    update {
      max_parallel     = 1
      min_healthy_time = "10s"
      healthy_deadline = "5m"
    }
  }
}
