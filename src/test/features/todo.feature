Feature: Todo CRUD Lifecycle

  Scenario: 1. Create a Task
    Given que je suis sur la page "https://tasks-app-likz.onrender.com"
    When je saisis la tâche "Task Master" et je clique sur ajouter
    Then la tâche "Task Master" doit être visible

  Scenario: 2. Edit a Task
    Given que je suis sur la page "https://tasks-app-likz.onrender.com"
    When je saisis la tâche "Edit Me" et je clique sur ajouter
    And je double-clique sur "Edit Me" pour changer en "Edited Task"
    Then la tâche "Edited Task" doit être visible

  Scenario: 3. Delete a Task
    Given que je suis sur la page "https://tasks-app-likz.onrender.com"
    When je saisis la tâche "Delete Me" et je clique sur ajouter
    And je supprime la tâche "Delete Me"
    Then la tâche "Delete Me" ne doit plus exister
