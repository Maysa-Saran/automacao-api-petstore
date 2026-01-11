Feature: Pet Management
  As a store manager
  I want to register and consult pets
  To keep the stock updated

  @register
  Scenario: Register a new pet successfully
    Given que eu possuo os dados de um pet chamado "Thor" com status "available"
    When eu envio uma requisicao POST para o endpoint "/pet"
    Then o status code da resposta deve ser 200
    And o corpo da resposta deve conter o ID do pet criado
    And o corpo da resposta deve conter o nome "Thor"
