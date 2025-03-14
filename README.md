# Sudoku em Java - Terminal

Este projeto foi desenvolvido como parte do desafio do Bootcamp Java Bradesco na DIO (Digital Innovation One).

## Descrição do Projeto

O Sudoku é um jogo de raciocínio e lógica que consiste em preencher uma grade 9x9 com números de 1 a 9, sem repetir números em cada linha, coluna ou região 3x3 da grade.

Este projeto implementa uma versão do jogo Sudoku que funciona via terminal, permitindo ao jogador interagir com o jogo através de comandos simples.

## Funcionalidades

- Inicialização de um novo jogo com um tabuleiro pré-configurado
- Inserção de valores nas posições do tabuleiro
- Remoção de valores inseridos
- Visualização do tabuleiro atual
- Verificação do status do jogo (não iniciado, incompleto, completo)
- Validação de regras do Sudoku (sem números repetidos em linhas, colunas ou blocos 3x3)
- Limpeza do tabuleiro para reiniciar o jogo
- Verificação se o jogo foi concluído com sucesso

## Como Jogar

1. Inicie o jogo selecionando a opção 1
2. Use a opção 2 para inserir valores no tabuleiro
3. Navegue pelo menu para realizar as operações desejadas
4. Complete o tabuleiro seguindo as regras do Sudoku para vencer o jogo

## Tecnologias Utilizadas

- Java
- Orientação a Objetos
- Estruturas de Dados (ArrayList, HashSet)
- Enums
- Streams API

## Estrutura do Código

O projeto é organizado nas seguintes classes principais:
- `Main`: Contém a interface com o usuário e a lógica de execução do jogo
- `Board`: Representa o tabuleiro do jogo e suas operações
- `Space`: Representa cada posição do tabuleiro
- `GameStatusEnum`: Enumera os possíveis estados do jogo

Este projeto foi desenvolvido como demonstração das habilidades aprendidas durante o Bootcamp Java Bradesco da DIO.
