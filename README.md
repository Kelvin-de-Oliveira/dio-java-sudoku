# Sudoku Game - Terminal Application

Projeto de um jogo Sudoku via terminal, implementado em Java 21, com foco na aplicação de princípios de Programação Orientada a Objetos (POO) e no uso intensivo da API Stream para manipulação dos dados.

---

## Descrição

Este projeto implementa um jogo Sudoku que pode ser jogado diretamente no terminal. O jogo **espera que seja fornecido o tabuleiro completo**, ou seja, as posições de todas as células do Sudoku, incluindo números fixos e não fixos, para que o jogador possa interagir adicionando ou removendo números.
A implementação foi pensada para reforçar conceitos de POO e programação funcional com streams, privilegiando código limpo, modularidade e legibilidade.

---

## Tecnologias Utilizadas

- Java 21
- API Stream do Java
- Padrões de Projeto orientados a objetos

---

## Estrutura do Projeto

- **Model**
    - `Cell`: Representa cada célula do tabuleiro, armazenando número, posição, se é fixa, e valor esperado (pretend).
    - `Board`: Representa o tabuleiro inteiro, armazenando uma matriz 9x9 de células e as regras de manipulação e validação.
    - `GameStatus`: Enum para indicar o status do jogo (`NAO_INICIADO`, `INCOMPLETO`, `COMPLETO`).

- **Controller**
    - `GameController`: Controla o fluxo principal do jogo, recebendo entradas do usuário via view, manipulando o tabuleiro e aplicando as regras do jogo.

- **View**
    - `TerminalView`: Responsável pela interface do usuário no terminal, incluindo entrada de dados e exibição do tabuleiro.

- **Util**
    - `BoardTemplate`: Template visual do tabuleiro para exibição no terminal.

- **Main**
    - `Main`: Classe principal que inicia o jogo recebendo os valores iniciais via argumento de linha de comando.

---

## Como usar

1. Compile o projeto com Java 21.
2. Execute a aplicação passando os valores iniciais do tabuleiro via argumento na linha de comando.  
   Exemplo de uso:
   ```bash
   java br.com.sudoku.Main "0,0;4,false 1,0;7,false 2,2;5,true ..."

Cada argumento define a posição (linha,coluna), o número e se a célula é fixa (`true` ou `false`).

No terminal, siga as instruções do menu para jogar:

- **Adicionar número**  
  Permite inserir um número em uma célula não fixa. O jogador deve informar a linha (0-8), a coluna (0-8) e o número (1-9) a ser inserido.

- **Remover número**  
  Remove o número inserido em uma célula não fixa. O jogador deve informar a linha (0-8) e a coluna (0-8) da célula que deseja limpar.

- **Verificar jogo**  
  Exibe o tabuleiro atual no terminal, mostrando os números inseridos e os fixos.

- **Verificar status do jogo**  
  Mostra o estado atual do jogo:
    - **Não iniciado** (nenhum número inserido pelo jogador)
    - **Incompleto** (tabuleiro com células vazias ou erros)
    - **Completo** (todas as células preenchidas; pode ter erros)  
      Também informa se existem erros (números incorretos).

- **Limpar tudo**  
  Remove todos os números inseridos pelo jogador, mantendo apenas as células fixas do tabuleiro.

- **Finalizar jogo**  
  Tenta finalizar a partida. Só é possível finalizar se o tabuleiro estiver completo e sem erros. Caso contrário, informa o que falta corrigir.

- **Encerrar jogo**  
  Encerra a aplicação, finalizando a execução do programa.

---

## Principais objetivos do projeto

- Aplicar conceitos de **Programação Orientada a Objetos**: encapsulamento, imutabilidade onde aplicável, modelagem clara do domínio (células, tabuleiro, estado do jogo).
- Utilizar a **API Stream do Java** para manipulação eficiente e expressiva da matriz de células.
- Criar uma interface simples e intuitiva via terminal.
- Desenvolver um código modular e de fácil manutenção.

---

## Considerações finais

Este exercício foi desenvolvido como parte da minha participação no **Bootcamp Santander 2025 - Back-End com Java**, onde busquei praticar conceitos  da linguagem e boas práticas de desenvolvimento.

---

## Autor

Kelvin de Oliveira

---

