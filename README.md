# Substitutiva
O projeto é de um jogo de tabuleiro em formato matricial, onde o objetivo do jogador é mover peças desse tabuleiro e combiná-los em peças de valores maiores.
Por trás da lógica criada, há o padrão de projeto Strategy, que para o projeto em específico, define uma interface que descreve o comportamento geral de um jogo de tabuleiro.
A interface 'GameStrategy' fornece um conjunto de métodos que qualquer jogo de tabuleiro deve implementar para ser compatível com a estrutura do projeto.
Ao usar uma interface, é possível criar várias implementações diferentes para diferentes jogos de tabuleiro, como o Threes, o 2048 e o 1024, mantendo um contrato comum entre eles. Isso permite que os jogos sejam tratados de forma genérica e possam ser selecionados dinamicamente pelo usuário (como foi implementado no arquivo GameSelector) ou integrados em outros componentes do projeto sem que seja necessário conhecer os detalhes específicos de cada jogo.
